package com.smileBlog.article.dao;

import com.smileBlog.article.entity.Article;
import com.smileBlog.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/6/3.
 */
public class ArticleDAO
{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

	public int add(Article article) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "INSERT INTO article(ownuid, title, content, contentTxt, create_time) VALUES(?, ?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, article.getOwnuid());
		ps.setString(2, article.getTitle());
		ps.setString(3, article.getContent());
		ps.setString(4, article.getContentTxt());
		ps.setTimestamp(5, new java.sql.Timestamp((new java.util.Date()).getTime()));

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);

		return flag;
	}

//	删除文章
	public int deleteByUidAndAid(int uid, int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELETE FROM article WHERE ownuid = ? AND aid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ps.setInt(2, aid);

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	更新文章各个功能的数量
	public int updateNumberByAid(int aid, String classification, String operate) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql;
		if(operate.equalsIgnoreCase("delete"))
		{
			sql = "UPDATE article SET " + classification + "_number = " + classification + "_number - 1 WHERE aid = ?";
		}
		else
		{
			sql = "UPDATE article SET " + classification + "_number = " + classification + "_number + 1 WHERE aid = ?";
		}

		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

	public List<Article> selectArticleByUid(int uid, int flag) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql;
		if(flag == 1)
		{
			System.out.println("all");
			sql = "SELECT aid, ownuid, title, create_time FROM article WHERE ownuid = ?";
		}
		else
		{
			sql = "SELECT aid, ownuid, title, create_time FROM article WHERE ownuid = ? AND status = 0;";
		}

		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<Article> articleList = new ArrayList<Article>();
		while(rs.next())
		{
			Article article = new Article();
			article.setAid(rs.getInt("aid"));
			article.setOwnuid(rs.getInt("ownuid"));
			article.setTitle(rs.getString("title"));
			article.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));

			articleList.add(article);
			System.out.println("aid = " + article.getAid());
		}

		DataSource.close(conn, ps, rs);
		return articleList;
	}

	public Article selectArticleByAid(int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM article WHERE aid=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		ResultSet rs = ps.executeQuery();

		Article article = new Article();
		while(rs.next())
		{
			article.setAid(rs.getInt("aid"));
			article.setOwnuid(rs.getInt("ownuid"));
			article.setTitle(rs.getString("title"));
			article.setContent(rs.getString("content"));
			article.setStatus(rs.getInt("status"));
			article.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
			article.setCollectionNumber(rs.getInt("collection_number"));
			article.setLikeNumber(rs.getInt("like_number"));
			article.setCommentNumber(rs.getInt("comment_number"));
		}

		DataSource.close(conn, ps, rs);
		return article;
	}

	public Article selectUidAndTitleByAid(int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT ownuid, title FROM article WHERE aid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		ResultSet rs = ps.executeQuery();

		Article article = new Article();
		while(rs.next())
		{
			article.setOwnuid(rs.getInt("ownuid"));
			article.setTitle(rs.getString("title"));
		}

		DataSource.close(conn, ps, rs);
		return article;
	}

//	取这篇文章的上一篇和下一篇
	public Article getArticleByAidAndUid(int aid, int uid, String to) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql;
		if(to.equalsIgnoreCase("pre"))
		{
			sql = "SELECT * FROM article WHERE aid<? AND ownuid=? ORDER BY aid DESC LIMIT 1";
		}
		else
		{
			sql = "SELECT * FROM article WHERE aid>? AND ownuid=? ORDER BY aid ASC LIMIT 1";
		}
		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		ps.setInt(2, uid);
		ResultSet rs = ps.executeQuery();

		Article article = null;
		while(rs.next())
		{
			article = new Article();
			article.setAid(rs.getInt("aid"));
			article.setOwnuid(rs.getInt("ownuid"));
			article.setTitle(rs.getString("title"));
		}

		DataSource.close(conn, ps, rs);
		return article;
	}

//	模糊搜索文章
	public List<Article> selectArticleLikeNameOrContent(String str) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM article, user WHERE (user.uid = article.ownuid) AND (title LIKE ? OR contentTxt LIKE ? OR user.nickname LIKE ?);";
		ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + str + "%");
		ps.setString(2, "%" + str + "%");
		ps.setString(3, "%" + str + "%");
		ResultSet rs = ps.executeQuery();

		List<Article> articleList = new ArrayList<Article>();
		while(rs.next())
		{
			Article article = new Article();
			article.setAid(rs.getInt("aid"));
			article.setOwnuid(rs.getInt("ownuid"));
			article.setOwnNickname(rs.getString("nickname"));
			article.setTitle(rs.getString("title"));
			article.setContentTxt(rs.getString("contentTxt"));
			article.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));

			articleList.add(article);
		}

		DataSource.close(conn, ps, rs);
		return articleList;
	}

//	更改文章的显示状态
	public int changeStatusArticleByAidAndUid(int aid, int uid, String operate) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "UPDATE article SET status = ? WHERE aid = ? AND ownuid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(2, aid);
		ps.setInt(3, uid);
		if(operate.equalsIgnoreCase("show"))
		{
			ps.setInt(1, 0);
		}
		else
		{
			ps.setInt(1, 1);
		}

		int flag;
		flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	public Article getPreArticleByAidAndUid(int aid, int uid) throws SQLException
//	{
//		conn = DataSource.getConnection();
//
//		String sql = "SELECT * FROM article WHERE aid<? AND uid=? ORDER BY aid DASC LIMIT 1";
//		ps = conn.prepareStatement(sql);
//		ps.setInt(1, aid);
//		ps.setInt(2, uid);
//		ResultSet rs = ps.executeQuery();
//
//		rs.next();
//		Article article = new Article();
//		article.setAid(rs.getInt("aid"));
//		article.setOwnuid(rs.getInt("ownuid"));
//		article.setTitle(rs.getString("title"));
//
//		return article;
//	}
}

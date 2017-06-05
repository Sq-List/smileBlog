package com.smileBlog.article.dao;

import com.smileBlog.article.entity.Article;
import com.smileBlog.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/6/3.
 */
public class ArticleDAO
{
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;

	public void add(Article article) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "INSERT INTO article(ownuid, title, content, create_time) VALUES(?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, article.getOwnuid());
		ps.setString(2, article.getTitle());
		ps.setString(3, article.getContent());
		ps.setTimestamp(4, new java.sql.Timestamp((new java.util.Date()).getTime()));
		ps.executeUpdate();
	}

	public List<com.smileBlog.user.entity.Article> selectArticleByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT aid, ownuid, title, create_time FROM article WHERE ownuid=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<com.smileBlog.user.entity.Article> articleList = new ArrayList<com.smileBlog.user.entity.Article>();
		while(rs.next())
		{
			com.smileBlog.user.entity.Article article = new com.smileBlog.user.entity.Article();
			article.setAid(rs.getInt("aid"));
			article.setOwnuid(rs.getInt("ownuid"));
			article.setTitle(rs.getString("title"));
			article.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));

			articleList.add(article);
			System.out.println("aid = " + article.getAid());
		}

		return articleList;
	}

	public Article selectArticleByAid(int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM article WHERE aid=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		ResultSet rs = ps.executeQuery();

		rs.next();
		Article article = new Article();
		article.setAid(rs.getInt("aid"));
		article.setOwnuid(rs.getInt("ownuid"));
		article.setTitle(rs.getString("title"));
		article.setContent(rs.getString("content"));
		article.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
		article.setPageView(rs.getInt("collection_number"));
		article.setLikeNumber(rs.getInt("like_number"));
		article.setCommentNumber(rs.getInt("comment_number"));

		return article;
	}

	public Article getArticleByAidAndUid(int aid, int uid, String to) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql;
		if(to.equalsIgnoreCase("pre"))
		{
			sql = "SELECT * FROM article WHERE aid<? AND ownuid=? ORDER BY aid ASC LIMIT 1";
		}
		else
		{
			sql = "SELECT * FROM article WHERE aid>? AND ownuid=? ORDER BY aid DESC LIMIT 1";
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

		return article;
	}

	public Article getPreArticleByAidAndUid(int aid, int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM article WHERE aid<? AND uid=? ORDER BY aid DASC LIMIT 1";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		ps.setInt(2, uid);
		ResultSet rs = ps.executeQuery();

		rs.next();
		Article article = new Article();
		article.setAid(rs.getInt("aid"));
		article.setOwnuid(rs.getInt("ownuid"));
		article.setTitle(rs.getString("title"));

		return article;
	}
}

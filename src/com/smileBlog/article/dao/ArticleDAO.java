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

	public List<Article> selectByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM article WHERE ownuid=?";
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
			article.setContent(rs.getString("content"));
			article.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
			article.setPageView(rs.getInt("page_view"));
			article.setLikeNumber(rs.getInt("like_number"));
			article.setCommentNumber(rs.getInt("comment_number"));

			articleList.add(article);
			System.out.println(article.getAid());
		}

		return articleList;
	}
}

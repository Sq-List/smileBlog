package com.smileBlog.collection.dao;

import com.smileBlog.article.entity.Article;
import com.smileBlog.collection.entity.Collection;
import com.smileBlog.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/6/6.
 */
public class CollectionDAO
{
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;

	public int add(Collection collection) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "INSERT INTO collection(aid, uid) VALUES(?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, collection.getAid());
		ps.setInt(2, collection.getUid());
		return ps.executeUpdate();
	}

	public int delect(Collection collection) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELETE FROM collection WHERE collection.aid = ? AND collection.uid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, collection.getAid());
		ps.setInt(2, collection.getUid());
		return ps.executeUpdate();
	}

//	列出某个用户的所有收藏文章的编号
	public List<Integer> selectCollectionAidByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT aid FROM collection WHERE uid=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<Integer> aidList = new ArrayList<Integer>();
		while(rs.next())
		{
			aidList.add(rs.getInt(1));
		}

		return aidList;
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
		article.setContentTxt(rs.getString("contentTxt"));

		return article;
	}
}

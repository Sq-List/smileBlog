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
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

	public int add(Collection collection) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "INSERT INTO collection(operate_uid, aid, uid, create_time) VALUES(?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, collection.getOperateUid());
		ps.setInt(2, collection.getAid());
		ps.setInt(3, collection.getUid());
		ps.setTimestamp(4, new java.sql.Timestamp((new java.util.Date()).getTime()));

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

	public int delect(Collection collection) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELETE FROM collection WHERE aid = ? AND operate_uid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, collection.getAid());
		ps.setInt(2, collection.getOperateUid());

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	列出某个用户的所有收藏文章的编号
	public List<Integer> selectCollectionAidByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT aid FROM collection WHERE operate_uid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<Integer> aidList = new ArrayList<Integer>();
		while(rs.next())
		{
			aidList.add(rs.getInt(1));
		}

		DataSource.close(conn, ps, rs);
		return aidList;
	}

	public Article selectArticleByAid(int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM article WHERE aid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		ResultSet rs = ps.executeQuery();

		rs.next();
		Article article = new Article();
		article.setAid(rs.getInt("aid"));
		article.setOwnuid(rs.getInt("ownuid"));
		article.setTitle(rs.getString("title"));
		article.setContentTxt(rs.getString("contentTxt"));

		DataSource.close(conn, ps, rs);
		return article;
	}

//	某篇文章是否收藏过
	public int selectCollectionByUidAndAid(int operateUid, int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT COUNT(*) FROM collection WHERE operate_uid = ? AND aid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, operateUid);
		ps.setInt(2, aid);
		ResultSet rs = ps.executeQuery();

		int flag = 0;
		while(rs.next())
		{
			flag = rs.getInt(1);
		}

		DataSource.close(conn, ps, rs);
		return flag;
	}

//	根据用户查找收藏了自己文章的人
	public List<Collection> selectByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM collection, article, user WHERE collection.uid = ? AND collection.operate_uid = user.uid AND article.aid = collection.aid";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<Collection> collectionList = new ArrayList<>();
		while(rs.next())
		{
			Collection collection = new Collection();
			collection.setOperateUid(rs.getInt("operate_uid"));
			collection.setOperateNickname(rs.getString("nickname"));
			collection.setOperateHeadPic(rs.getString("head_pic"));
			collection.setAid(rs.getInt("aid"));
			collection.setTitle(rs.getString("title"));
			collection.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
			collectionList.add(collection);
		}

		DataSource.close(conn, ps, rs);
		return collectionList;
	}

//	更新文章的收藏数
//	public int updateArticleCollectionByAid(int aid, String operate) throws SQLException
//	{
//		conn = DataSource.getConnection();
//
//		String sql;
//		if(operate.equalsIgnoreCase("add"))
//		{
//			sql = "UPDATE article SET collection_number = collection_number + 1 WHERE aid = ?";
//		}
//		else
//		{
//			sql = "UPDATE article SET collection_number = collection_number - 1 WHERE aid = ?";
//		}
//
//		ps = conn.prepareStatement(sql);
//		ps.setInt(1, aid);
//
//		return ps.executeUpdate();
//	}
}

package com.smileBlog.like.dao;

import com.smileBlog.like.entity.Like;
import com.smileBlog.util.DataSource;

import java.sql.*;

/**
 * Created by asus on 2017/6/8.
 */
public class LikeDAO
{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

//	添加赞
	public int add(Like like) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "INSERT INTO smile(operate_uid, operate_nickname, aid, title, uid, create_time) VALUES(?, ?, ?, ?, ?, ?);";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, like.getOperateUid());
		ps.setString(2, like.getOperateNickname());
		ps.setInt(3, like.getAid());
		ps.setString(4, like.getTitle());
		ps.setInt(5, like.getUid());
		ps.setTimestamp(6, new java.sql.Timestamp((new java.util.Date()).getTime()));

		int flag = ps.executeUpdate();
		conn.close();
		return flag;
	}

//	删除赞
	public int delect(Like like) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELECT FROM smile WHERE operate_uid = ? AND aid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, like.getOperateUid());
		ps.setInt(2, like.getAid());

		int flag = ps.executeUpdate();
		conn.close();
		return flag;
	}

//	更新文章的赞数
//	public int updateArticleLikeByAid(int aid, String operate) throws SQLException
//	{
//		conn = DataSource.getConnection();
//
//		String sql;
//		if(operate.equalsIgnoreCase("add"))
//		{
//			sql = "UPDATE article SET like_number = like_number + 1 WHERE aid = ?";
//		}
//		else
//		{
//			sql = "UPDATE article SET like_number = like_number - 1 WHERE aid = ?";
//		}
//
//		ps = conn.prepareStatement(sql);
//		ps.setInt(1, aid);
//
//		return ps.executeUpdate();
//	}

//	查找赞
	public int selectByUidAndAid(int uid, int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT COUNT(*) FROM smile WHERE operate_uid = ? AND aid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ps.setInt(2, aid);
		ResultSet rs = ps.executeQuery();

		int flag = 0;
		while(rs.next())
		{
			flag = rs.getInt(1);
		}

		conn.close();
		return flag;
	}
}

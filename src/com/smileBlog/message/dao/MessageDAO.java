package com.smileBlog.message.dao;

import com.smileBlog.message.entity.Message;
import com.smileBlog.util.DataSource;

import java.sql.*;

/**
 * Created by asus on 2017/6/8.
 */
public class MessageDAO
{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

//	添加消息用户
	public int add(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "INSERT INTO message(uid) VALUES(?);";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);

		int flag = ps.executeUpdate();
		conn.close();
		return flag;
	}

//	更改消息数量为0
	public int updateNumberOByUid(int uid, String classification) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "UPDATE message SET ? = ? WHERE uid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setString(1, classification + "_number");
		ps.setInt(2, 0);
		ps.setInt(3, uid);

		int flag = ps.executeUpdate();
		conn.close();
		return flag;
	}

//	更新消息数量
	public int updateByUid(int uid, String classification, String operate) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql;
		if(operate.equalsIgnoreCase("delect"))
		{
			sql = "UPDATE message SET " + classification + "_number = " + classification + "_number - 1 WHERE uid = ?;";
		}
		else
		{
			sql = "UPDATE message SET " + classification + "_number = " + classification + "_number + 1 WHERE uid = ?;";
		}

		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);

		int flag = ps.executeUpdate();
		conn.close();
		return flag;
	}

//	获取单个消息数量
	public int selectNmuberByUid(int uid, String classification) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT " + classification + "_number FROM message WHERE uid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		int num = 0;
		while(rs.next())
		{
			num = rs.getInt(1);
		}

		return num;


	}

//	获取消息数量
	public int selectByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT (like_number + collection_number + comment_number) FROM message WHERE uid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		int number = 0;
		while(rs.next())
		{
			number = rs.getInt(1);
		}

		conn.close();
		return number;
	}
}

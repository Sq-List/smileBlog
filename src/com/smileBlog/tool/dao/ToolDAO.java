package com.smileBlog.tool.dao;

import com.smileBlog.tool.entity.Tool;
import com.smileBlog.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/6/6.
 */
public class ToolDAO
{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

//	添加工具
	public int addTool(Tool tool) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "INSERT INTO tool(uid, name, src, create_time) VALUES(?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, tool.getUid());
		ps.setString(2, tool.getName());
		ps.setString(3, tool.getSrc());
		ps.setTimestamp(4, new java.sql.Timestamp((new java.util.Date()).getTime()));

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	列出某个用户的所有工具
	public List<Tool> selectToolByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM tool WHERE uid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<Tool> toolList = new ArrayList<Tool>();
		while(rs.next())
		{
			Tool tool = new Tool();
			tool.setTid(rs.getInt("tid"));
			tool.setUid(rs.getInt("uid"));
			tool.setName(rs.getString("name"));
			tool.setSrc(rs.getString("src"));
			tool.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
			toolList.add(tool);
		}

		DataSource.close(conn, ps, rs);
		return toolList;
	}

//	根据某个工具的名字和用户的编号找到路径
	public String selectSrcByUidAndName(String name, int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT src FROM tool WHERE uid = ? AND name = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ps.setString(2, name);
		ResultSet rs = ps.executeQuery();

		String src = null;
		while(rs.next())
		{
			src = rs.getString("src");
		}

		DataSource.close(conn, ps, rs);
		return src;
	}

//	根据某个工具的名字和用户的编号删除工具
	public int delectByUidAndName(String name, int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELETE FROM tool WHERE uid = ? AND name = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ps.setString(2, name);

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	找出某个工具
	public Tool selectByNameAndUid(String name, int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM tool WHERE name = ? AND uid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, uid);
		ResultSet rs = ps.executeQuery();

		Tool tool = new Tool();
		while(rs.next())
		{
			System.out.println("rs.");
			tool.setName(name);
			tool.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
		}

		return tool;
	}
}

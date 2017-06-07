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
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;

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
		return ps.executeUpdate();
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

		return toolList;
	}

//	根据某个工具的名字找到路径
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

		return src;
	}
}

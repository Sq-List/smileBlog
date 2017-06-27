package com.smileBlog.admin.dao;

import com.smileBlog.admin.entity.Article;
import com.smileBlog.admin.entity.Tool;
import com.smileBlog.admin.entity.User;
import com.smileBlog.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/6/18.
 */
public class AdminDAO
{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

//	登录
	public int login(String username, String password) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM admin WHERE username = ? AND password = ?;";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();


		while(rs != null)
		{
			DataSource.close(conn, ps, rs);
			return 1;
		}
		DataSource.close(conn, ps, rs);
		return 0;
	}

//	显示用户
	public List<User> showUser() throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM user;";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<User> userList = new ArrayList<>();
		while(rs.next())
		{
			User user = new User();

			user.setUid(rs.getInt("uid"));
			user.setNickname(rs.getString("nickname"));
			user.setHeadPic(rs.getString("head_pic"));
			user.setLabel(rs.getString("label"));
			user.setStatus(rs.getInt("status"));
			user.setArticleNumber(rs.getInt("article_number"));

			userList.add(user);
		}

		DataSource.close(conn, ps, rs);
		return userList;
	}

//	删除用户
	public int deleteUserByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELETE FROM user WHERE uid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	限制或解除限制用户
	public int limitUserByUid(int uid, String operate) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "UPDATE user SET status = ? WHERE uid = ?";
		ps = conn.prepareStatement(sql);
		if(operate.equalsIgnoreCase("unfreeze"))
		{
			ps.setInt(1, 0);
		}
		else
		{
			ps.setInt(1, 1);
		}
		ps.setInt(2, uid);

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	显示文章
	public List<Article> showArticle() throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM article, user WHERE article.ownuid = user.uid;";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<Article> articleList = new ArrayList<>();
		while(rs.next())
		{
			Article article = new Article();

			article.setAid(rs.getInt("aid"));
			article.setTitle(rs.getString("title"));
			article.setOwnuid(rs.getInt("ownuid"));
			article.setOwnNickname(rs.getString("nickname"));
			article.setContentTxt(rs.getString("contentTxt"));
			article.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));

			articleList.add(article);
		}

		DataSource.close(conn, ps, rs);
		return articleList;
	}

//	删除文章
	public int deleteArticleByAid(int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELETE FROM article WHERE aid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	显示工具
	public List<Tool> showTool() throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM tool, user WHERE tool.uid = user.uid;";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<Tool> toolList = new ArrayList<>();
		while(rs.next())
		{
			Tool tool = new Tool();

			tool.setTid(rs.getInt("tid"));
			tool.setUid(rs.getInt("uid"));
			tool.setNickname(rs.getString("nickname"));
			tool.setName(rs.getString("name"));
//			tool.setSrc(rs.getString("src"));
			tool.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));

			toolList.add(tool);
		}

		DataSource.close(conn, ps, rs);
		return toolList;
	}

//	删除工具
	public int deleteToolByTid(int tid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELETE FROM tool WHERE tid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, tid);

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}
}

package com.smileBlog.comment.dao;

import com.smileBlog.comment.entity.Comment;
import com.smileBlog.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/6/8.
 */
public class CommentDAO
{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

//	添加评论
	public int add(Comment comment) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "INSERT INTO comment(operate_uid, operate_nickname, aid, title, uid, create_time, content) VALUES(?, ?, ?, ?, ?, ?, ?);";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, comment.getOperateUid());
		ps.setString(2, comment.getOperateNickname());
		ps.setInt(3, comment.getAid());
		ps.setString(4, comment.getTitle());
		ps.setInt(5, comment.getUid());
		ps.setTimestamp(6, new java.sql.Timestamp((new java.util.Date()).getTime()));
		ps.setString(7, comment.getContent());

		int flag = ps.executeUpdate();
		conn.close();
		return flag;
	}

//	删除评论
	public int delect(Comment comment) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELECT FROM comment WHERE operate_uid = ? AND comid = ? AND aid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, comment.getOperateUid());
		ps.setInt(2, comment.getComid());
		ps.setInt(3, comment.getAid());

		int flag = ps.executeUpdate();
		conn.close();
		return flag;
	}

//	更新文章的评论数
//	public int updateArticleByAid(int aid, String operate) throws SQLException
//	{
//		conn = DataSource.getConnection();
//
//		String sql;
//		if(operate.equalsIgnoreCase("add"))
//		{
//			sql = "UPDATE comment SET comment_number = comment_number + 1 WHERE aid = ?";
//		}
//		else
//		{
//			sql = "UPDATE article SET comment_number = comment_number - 1 WHERE aid = ?";
//		}
//
//		ps = conn.prepareStatement(sql);
//		ps.setInt(1, aid);
//
//		return ps.executeUpdate();
//	}

//	根据文章获取评论的列表
	public List<Comment> selectByAid(int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM comment WHERE aid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		ResultSet rs = ps.executeQuery();

		List<Comment> commentList = new ArrayList<Comment>();
		while(rs.next())
		{
			Comment comment = new Comment();
			comment.setComid(rs.getInt("comid"));
			comment.setOperateUid(rs.getInt("operate_uid"));
//			comment.setOperateNickname(rs.getString("operate_nickname"));
			comment.setAid(rs.getInt("aid"));
			comment.setContent(rs.getString("content"));
			comment.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
			commentList.add(comment);
		}

		conn.close();
		return commentList;
	}

//	根据用户获取评论列表
	public List<Comment> selectByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM comment WHERE uid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<Comment> commentList = new ArrayList<Comment>();
		while(rs.next())
		{
			Comment comment = new Comment();
			comment.setComid(rs.getInt("comid"));
			comment.setOperateUid(rs.getInt("operate_uid"));
//			comment.setOperateNickname(rs.getString("operate_nickname"));
			comment.setAid(rs.getInt("aid"));
			comment.setContent(rs.getString("content"));
			comment.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
			commentList.add(comment);
		}

		conn.close();
		return commentList;
	}
}

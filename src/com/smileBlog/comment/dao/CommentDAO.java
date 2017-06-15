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

		String sql = "INSERT INTO comment(operate_uid, aid, uid, create_time, content) VALUES(?, ?, ?, ?, ?);";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, comment.getOperateUid());
		ps.setInt(2, comment.getAid());
		ps.setInt(3, comment.getUid());
		ps.setTimestamp(4, new java.sql.Timestamp((new java.util.Date()).getTime()));
		ps.setString(5, comment.getContent());

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	删除评论
	public int delect(Comment comment) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELETE FROM comment WHERE operate_uid = ? AND comid = ? AND aid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, comment.getOperateUid());
		ps.setInt(2, comment.getComid());
		ps.setInt(3, comment.getAid());

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
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

		String sql = "SELECT * FROM comment, user WHERE user.uid = comment.operate_uid AND aid = ? ORDER BY create_time ASC;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		ResultSet rs = ps.executeQuery();

		List<Comment> commentList = new ArrayList<Comment>();
		while(rs.next())
		{
			Comment comment = new Comment();
			comment.setComid(rs.getInt("comid"));
			comment.setOperateUid(rs.getInt("operate_uid"));
			comment.setOperateNickname(rs.getString("nickname"));
			comment.setOperateHeadPic(rs.getString("head_pic"));
			comment.setAid(rs.getInt("aid"));
			comment.setContent(rs.getString("content"));
			comment.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
			commentList.add(comment);
		}

		DataSource.close(conn, ps, rs);
		return commentList;
	}

//	根据用户获取评论列表
	public List<Comment> selectByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM comment, user, article WHERE article.aid = comment.aid AND comment.uid = ? AND user.uid = comment.operate_uid;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<Comment> commentList = new ArrayList<Comment>();
		while(rs.next())
		{
			Comment comment = new Comment();
			comment.setComid(rs.getInt("comid"));
			comment.setOperateUid(rs.getInt("operate_uid"));
			comment.setOperateNickname(rs.getString("nickname"));
			comment.setOperateHeadPic(rs.getString("head_pic"));
			comment.setAid(rs.getInt("aid"));
			comment.setTitle(rs.getString("title"));
			comment.setContent(rs.getString("content"));
			comment.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
			commentList.add(comment);
		}

		DataSource.close(conn, ps, rs);
		return commentList;
	}

//	获取某用户对某文章最新的评论
	public Comment selectCommentByUidAndAid(int operateUid, int aid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM comment, user WHERE user.uid = operate_uid AND operate_uid = ? AND aid = ? ORDER BY create_time DESC LIMIT 1";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, operateUid);
		ps.setInt(2, aid);
		ResultSet rs = ps.executeQuery();

		Comment comment = new Comment();
		while(rs.next())
		{
			comment.setComid(rs.getInt("comid"));
			comment.setOperateUid(rs.getInt("operate_uid"));
			comment.setOperateNickname(rs.getString("nickname"));
			comment.setOperateHeadPic(rs.getString("head_pic"));
			comment.setContent(rs.getString("content"));
			comment.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
		}

		DataSource.close(conn, ps, rs);
		return comment;
	}
}

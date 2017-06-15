package com.smileBlog.like.dao;

import com.smileBlog.like.entity.Like;
import com.smileBlog.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

		String sql = "INSERT INTO smile(operate_uid, aid, uid, create_time) VALUES(?, ?, ?, ?);";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, like.getOperateUid());
		ps.setInt(2, like.getAid());
		ps.setInt(3, like.getUid());
		ps.setTimestamp(4, new java.sql.Timestamp((new java.util.Date()).getTime()));

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	删除赞
	public int delect(Like like) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "DELETE FROM smile WHERE operate_uid = ? AND aid = ?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, like.getOperateUid());
		ps.setInt(2, like.getAid());

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
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

		DataSource.close(conn, ps, rs);
		return flag;
	}

//	根据用户查找赞了自己文章的人
	public List<Like> selectByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM smile, user, article WHERE smile.uid = ? AND smile.operate_uid = user.uid AND smile.aid = article.aid";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<Like> likeList = new ArrayList<>();
		while(rs.next())
		{
			Like like = new Like();
			like.setOperateUid(rs.getInt("operate_uid"));
			like.setOperateNickname(rs.getString("nickname"));
			like.setOperateHeadPic(rs.getString("head_pic"));
			like.setAid(rs.getInt("aid"));
			like.setTitle(rs.getString("title"));
			like.setCreateTime(new java.util.Date(rs.getTimestamp("create_time").getTime()));
			likeList.add(like);
		}

		DataSource.close(conn, ps, rs);
		return likeList;
	}
}

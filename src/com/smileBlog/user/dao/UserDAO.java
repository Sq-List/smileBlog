package com.smileBlog.user.dao;

import com.smileBlog.message.entity.Message;
import com.smileBlog.user.entity.User;
import com.smileBlog.util.DataSource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/5/29.
 */
public class UserDAO
{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

	public UserDAO()
	{

	}

	public static String stringMd5(String str)
	{
		try
		{
			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 输入的字符串转换成字节数组
			byte[] inputByteArray = str.getBytes();
			// inputByteArray是输入字符串转换得到的字节数组
			messageDigest.update(inputByteArray);
			// 转换并返回结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 字符数组转换成字符串返回
			return byteArrayToHex(resultByteArray);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private static String byteArrayToHex(byte[] byteArray)
	{
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray =new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray)
		{
			resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}

//	添加用户
	public int add(User user) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "INSERT INTO user(username, password, nickname, head_pic) VALUES (?, ?, ?, ?);";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, stringMd5(user.getPassword()));
		ps.setString(3, user.getNickname());
		ps.setString(4, user.getHeadPic());

		int flag = ps.executeUpdate();

		DataSource.close(conn, ps, null);
		return flag;
	}

//	public int addMessage(int uid) throws SQLException
//	{
//		conn = DataSource.getConnection();
//
//		String sql = "INSERT INTO message(uid, number) VALUES(?, ?);";
//		ps = conn.prepareStatement(sql);
//		ps.setInt(1, uid);
//		ps.setInt(2, 0);
//
//		return ps.executeUpdate();
//	}

//	通过用户名和密码查找用户
	public User selectByUsernameAndPassword(String username, String password) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM user WHERE username = ? AND password = ?;";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, stringMd5(password));
		ResultSet rs = ps.executeQuery();

		User user = null;
		while(rs.next())
		{
			user = new User();

			user.setUid(rs.getInt("uid"));
			user.setUsername(rs.getString("username"));
			user.setNickname(rs.getString("nickname"));
			user.setHeadPic(rs.getString("head_pic"));
			user.setLable(rs.getString("label"));
			user.setStatus(rs.getInt("status"));
		}

		DataSource.close(conn, ps, rs);
		return user;
	}

//	查找是否存在用户名
	public int ajaxValidateLoginname(String username) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT uid FROM user WHERE username = ?;";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();

		int uid = 0;
		while(rs.next())
		{
			uid = rs.getInt(1);
		}

		DataSource.close(conn, ps, rs);
		return uid;
	}

	public User selectUserByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM user WHERE uid=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		User user;
		if(rs.next())
		{
			user = new User();

			user.setUid(rs.getInt("uid"));
			user.setNickname(rs.getString("nickname"));
			user.setHeadPic(rs.getString("head_pic"));
			user.setLable(rs.getString("label"));
		}
		else
		{
			user = null;
		}

		DataSource.close(conn, ps, rs);
		return user;
	}

//	搜索用户的头像和昵称
	public User selectPicAndNicknameByUid(int uid) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT uid, head_pic, nickname FROM user WHERE uid = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		User user = new User();
		while(rs.next())
		{
			user.setUid(rs.getInt("uid"));
			user.setHeadPic(rs.getString("head_pic"));
			user.setNickname(rs.getString("nickname"));
		}

		DataSource.close(conn, ps, rs);
		return user;
	}

//	更新用户文章数量
	public int updateArticleNumberByUid(int uid, String operate) throws SQLException
	{
		System.out.println(uid);
		conn = DataSource.getConnection();

		String sql;
		if(operate.equalsIgnoreCase("add"))
		{
			sql = "UPDATE user SET article_number = article_number + 1 WHERE uid = ?;";
		}
		else
		{
			sql = "UPDATE user SET article_number = article_number - 1 WHERE uid = ?;";
		}
		ps = conn.prepareStatement(sql);
		ps.setInt(1, uid);

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	更新用户的信息
	public int updateUserInfo(User user) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "UPDATE user SET nickname = ?, head_pic = ? WHERE uid = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getNickname());
		ps.setString(2, user.getHeadPic());
		ps.setInt(3, user.getUid());

		int flag = ps.executeUpdate();
		DataSource.close(conn, ps, null);
		return flag;
	}

//	模糊搜索用户
	public List<User> selectUserLikeNickname(String str) throws SQLException
	{
		conn = DataSource.getConnection();

		String sql = "SELECT * FROM user WHERE nickname LIKE ?;";
		ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + str + "%");
		ResultSet rs = ps.executeQuery();

		List<User> userList = new ArrayList<User>();
		while(rs.next())
		{
			User user = new User();
			user.setUid(rs.getInt("uid"));
			user.setNickname(rs.getString("nickname"));
			user.setLable(rs.getString("label"));
			user.setHeadPic(rs.getString("head_pic"));
			user.setArticleNumber(rs.getInt("article_number"));

			userList.add(user);
		}

		DataSource.close(conn, ps, rs);
		return userList;
	}
}

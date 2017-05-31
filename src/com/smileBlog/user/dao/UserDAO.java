package com.smileBlog.user.dao;

import com.smileBlog.user.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Created by asus on 2017/5/29.
 */
public class UserDAO
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/smileBlog";

	static final String USER = "root";
	static final String PASS = "123456";

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps = null;

	public UserDAO()
	{
		try
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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

	public void add(User user) throws SQLException
	{
		String sql = "INSERT INTO `user`(`username`, `password`, `nickname`, `head-pic`) VALUES (?, ?, ?, ?);";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, stringMd5(user.getPassword()));
		ps.setString(3, user.getNickname());
		ps.setString(4, user.getHeadPic());
		ps.executeUpdate();
	}

//	public

	public boolean ajaxValidateLoginname(String username) throws SQLException
	{
		String sql = "SELECT COUNT(1) FROM user WHERE username = ?;";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet result = ps.executeQuery();
		result.next();
		int count = result.getInt(1);
		return count == 0;
	}
}

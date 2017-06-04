package com.smileBlog.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by asus on 2017/6/3.
 */
public class DataSource
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/smileBlog";

	static final String USER = "root";
	static final String PASS = "123456";

	public static Connection getConnection() throws SQLException
	{
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl(DB_URL);
		dataSource.setUser(USER);
		dataSource.setPassword(PASS);

		return dataSource.getConnection();
	}
}

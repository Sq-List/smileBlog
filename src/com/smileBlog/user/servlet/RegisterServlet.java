package com.smileBlog.user.servlet;

import com.smileBlog.user.dao.UserDAO;
import com.smileBlog.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by asus on 2017/5/30.
 */
//@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet
{
	private UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		User user = new User();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setHeadPic("../image/head-pic.jpg");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try
		{
			userDAO.add(user);

			out.print("true");
		}
		catch (SQLException e)
		{
			out.print("false");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}
}

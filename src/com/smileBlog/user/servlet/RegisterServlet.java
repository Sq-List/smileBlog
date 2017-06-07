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
import java.util.Random;

/**
 * Created by asus on 2017/5/30.
 */
//@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet
{
	private UserDAO userDAO = new UserDAO();

	private StringBuffer getNickname()
	{
		StringBuffer nickname = new StringBuffer("Smile");

		int len = nickname.length();
		Random random = new Random(System.currentTimeMillis());
		for(int i = 0; i < 10 - len; i++)
		{
			nickname.append(random.nextInt(10));
		}

		return nickname;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		User user = new User();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		String nickname = request.getParameter("nickname");
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(getNickname().toString());
		user.setHeadPic("./image/head-pic.jpg");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try
		{
			if(userDAO.add(user) == 1)
			{
				out.write("<script language='javascript'>alert('register success!');window.location.href='"+request.getContextPath()+"/jsp/home.jsp';</script>");
			}
			else
			{
				out.write("<script language='javascript'>alert('register fail!');window.location.href='"+request.getContextPath()+"/jsp/home.jsp';</script>");
			}

//			out.print("true");
		}
		catch (SQLException e)
		{
//			out.print("false");
			out.write("<script language='javascript'>alert('register wrong!');window.location.href='"+request.getContextPath()+"/jsp/home.jsp';</script>");
		}


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}
}

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
 * Created by asus on 2017/5/31.
 */
//@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet
{

	private UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try
		{
			User user = userDAO.selectByUsernameAndPassword(username, password);

			if (user != null)
			{
				request.getSession().setAttribute("user", user);
//				request.setAttribute("");

//				response.sendRedirect(request.getContextPath() + "/jsp/owner.jsp");
				System.out.println(request.getContextPath());
				request.getRequestDispatcher("/jsp/owner.jsp").forward(request, response);
			}
			else
			{
				out.write("<script language='javascript'>alert('username or password is wrong!');window.location.href='"+request.getContextPath()+"/jsp/home.jsp';</script>");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			out.write("<script language='javascript'>alert('login fail!');window.location.href='"+request.getContextPath()+"/jsp/home.jsp';</script>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}
}

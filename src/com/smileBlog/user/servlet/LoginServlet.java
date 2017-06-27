package com.smileBlog.user.servlet;

import com.smileBlog.user.dao.UserDAO;
import com.smileBlog.user.entity.User;
import net.sf.json.JSONObject;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println(username + ", " + password);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try
		{
			User user = userDAO.selectByUsernameAndPassword(username, password);

			if (user != null)
			{
				request.getSession().setAttribute("user", user);
				System.out.println("LoginServlet:" + request.getSession().getAttribute("oldRequest"));

//				response.sendRedirect(request.getContextPath() + "/index");

//				如果原请求的页面不空则跳转到原请求页面，否则跳转到首页
				if(request.getSession().getAttribute("oldRequest") != null && request.getSession().getAttribute("oldRequest").toString().length() > 0)
				{
					String oldRequest = request.getSession().getAttribute("oldRequest").toString();
					request.getSession().setAttribute("oldRequest", "");
					response.sendRedirect(oldRequest);
					System.out.println(oldRequest);
				}
				else
				{
					response.sendRedirect(request.getContextPath() + "/index");
				}
			}
			else
			{
				out.write("<script language='javascript'>alert('username or password wrong!');window.location.href='" + request.getContextPath()
					+ "/home';</script>");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println(username + ", " + password);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try
		{
			User user = userDAO.selectByUsernameAndPassword(username, password);

			if (user != null)
			{
				request.getSession().setAttribute("user", user);

				out.print("true");
			}
			else
			{
				out.write("<script language='javascript'>alert('login fail!');window.location.href='" + request.getContextPath()
						+ "/home';</script>");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

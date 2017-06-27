package com.smileBlog.admin.servlet;

import com.smileBlog.admin.dao.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by asus on 2017/6/24.
 */
//@WebServlet(name = "AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet
{
	AdminDAO adminDAO = new AdminDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		System.out.println(password);

		try
		{
			if(adminDAO.login(username, password) == 1)
			{
				System.out.println("adminLogin success");
				request.getSession().setAttribute("admin", "admin");
				response.sendRedirect(request.getContextPath() + "/adminIndex");
			}
			else
			{
				System.out.println("adminLogin fail");
				out.write("<script language='javascript'>alert('username or password wrong!');window.location.href='" + request.getContextPath()
						+ "/admin';</script>");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}
}

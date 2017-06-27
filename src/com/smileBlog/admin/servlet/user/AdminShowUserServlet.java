package com.smileBlog.admin.servlet.user;

import com.smileBlog.admin.dao.AdminDAO;
import com.smileBlog.admin.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/6/24.
 */
//@WebServlet(name = "AdminShowUserServlet")
public class AdminShowUserServlet extends HttpServlet
{
	AdminDAO adminDAO = new AdminDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("adminShowUser/post");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("adminShowUser/get");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if(request.getSession().getAttribute("admin") != null)
		{
			List<User> userList;
			try
			{
				userList = adminDAO.showUser();

				request.setAttribute("userList", userList);

				request.getRequestDispatcher("/AdminShowArticleServlet").forward(request, response);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			out.write("<script language='javascript'>alert('login first!');window.location.href='" + request.getContextPath()
					+ "/admin';</script>");
		}
	}
}

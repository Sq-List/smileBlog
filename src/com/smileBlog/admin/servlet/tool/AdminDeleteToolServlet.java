package com.smileBlog.admin.servlet.tool;

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
//@WebServlet(name = "AdminDeleteToolServlet")
public class AdminDeleteToolServlet extends HttpServlet
{
	AdminDAO adminDAO = new AdminDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		int tid = Integer.parseInt(request.getParameter("id"));
		System.out.println(tid);

		try
		{
			if(adminDAO.deleteToolByTid(tid) == 1)
			{
				out.print("true");
			}
			else
			{
				out.print("false");
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

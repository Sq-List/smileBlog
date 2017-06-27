package com.smileBlog.admin.servlet.article;

import com.smileBlog.admin.dao.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by asus on 2017/6/25.
 */
public class AdminDeleteArticleServlet extends HttpServlet
{
	AdminDAO adminDAO = new AdminDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		int aid = Integer.parseInt(request.getParameter("id"));
		System.out.println("aid = " + aid);

		try
		{
			if(adminDAO.deleteArticleByAid(aid) == 1)
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

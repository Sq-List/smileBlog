package com.smileBlog.article.servlet;

import com.smileBlog.article.dao.ArticleDAO;
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
 * Created by asus on 2017/6/13.
 */
//@WebServlet(name = "DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet
{
	ArticleDAO articleDAO = new ArticleDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		int aid = Integer.parseInt(request.getParameter("aid"));
		int uid = ((User)request.getSession().getAttribute("user")).getUid();

		try
		{
			if(articleDAO.deleteByUidAndAid(uid, aid) == 1)
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

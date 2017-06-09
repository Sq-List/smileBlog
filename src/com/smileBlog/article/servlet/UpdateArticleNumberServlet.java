package com.smileBlog.article.servlet;

import com.smileBlog.article.dao.ArticleDAO;
import com.smileBlog.article.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by asus on 2017/6/8.
 */
//@WebServlet(name = "UpdateArticleNumberServlet")
public class UpdateArticleNumberServlet extends HttpServlet
{
	ArticleDAO articleDAO = new ArticleDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String classification = request.getParameter("classification");
		String operate = request.getParameter("operate");
		int aid = Integer.parseInt(request.getParameter("aid"));

		if(request.getSession().getAttribute("user") == null)
		{
			out.print("unlogin");
			return ;
		}
		else
		{
			try
			{
				Article article = articleDAO.selectUidAndTitleByAid(aid);
				request.setAttribute("uid", article.getOwnuid());
				request.setAttribute("title", article.getTitle());
				System.out.println(article.getOwnuid());

				if(articleDAO.updateNumberByAid(aid, classification, operate) == 1)
				{
//					out.print("true");
					request.getRequestDispatcher("/AjaxAddOrDelete" + classification.substring(0, 1).toUpperCase() + classification.substring(1) + "Servlet").forward(request, response);
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
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}
}

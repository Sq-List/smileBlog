package com.smileBlog.article.servlet;

import com.smileBlog.article.dao.ArticleDAO;
import com.smileBlog.article.entity.Article;
import net.sf.json.JSONObject;

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
 * Created by asus on 2017/6/8.
 */
//@WebServlet(name = "SearchArticleServlet")
public class SearchArticleServlet extends HttpServlet
{
	ArticleDAO articleDAO = new ArticleDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("SearchArticleServlet/post");
//		ajax
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String str = request.getParameter("search");

		try
		{
			List<Article> userList = articleDAO.selectArticleLikeNameOrContent(str);

			out.print(JSONObject.fromObject(userList));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("SearchArticleServlet/get");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String str = request.getParameter("search");
		System.out.println(str);

		try
		{
			List<Article> articleList = articleDAO.selectArticleLikeNameOrContent(str);

			request.setAttribute("articleList", articleList);

			request.getRequestDispatcher("/SearchUserServlet").forward(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
}

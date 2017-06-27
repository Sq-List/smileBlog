package com.smileBlog.admin.servlet.article;

import com.smileBlog.admin.dao.AdminDAO;
import com.smileBlog.admin.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by asus on 2017/6/24.
 */
public class AdminShowArticleServlet extends HttpServlet
{
	AdminDAO adminDAO = new AdminDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("adminShowArticle/post");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("adminShowArticle/get");
		List<Article> articleList;
		try
		{
			articleList = adminDAO.showArticle();

			request.setAttribute("articleList", articleList);

			request.getRequestDispatcher("/AdminShowToolServlet").forward(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

package com.smileBlog.collection.servlet;

import com.smileBlog.article.entity.Article;
import com.smileBlog.collection.dao.CollectionDAO;
import com.smileBlog.user.entity.User;

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
 * Created by asus on 2017/6/6.
 */
//@WebServlet(name = "GetCollectionListServlet")
public class GetCollectionListServlet extends HttpServlet
{
	CollectionDAO collectionDAO = new CollectionDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");

		int uid = ((User)request.getSession().getAttribute("user")).getUid();

		try
		{
			List<Article> articleList = new ArrayList<Article>();
			List<Integer> aidList = collectionDAO.selectCollectionAidByUid(uid);

			for(int i = 0; i < aidList.size(); i++)
			{
				int aid = aidList.get(i);
				Article article = collectionDAO.selectArticleByAid(aid);
				articleList.add(article);
			}

			request.setAttribute("articleList", articleList);

			request.getRequestDispatcher("/jsp/collection.jsp").forward(request, response);
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

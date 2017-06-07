package com.smileBlog.article.servlet;

import com.smileBlog.article.dao.ArticleDAO;
import com.smileBlog.user.dao.UserDAO;
import com.smileBlog.article.entity.Article;
import com.smileBlog.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by asus on 2017/6/4.
 */
//@WebServlet(name = "GetArticleListServlet")
public class GetArticleListServlet extends HttpServlet
{
	UserDAO userDAO = new UserDAO();
	ArticleDAO articleDAO = new ArticleDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("index/post");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("index/get");

		String uidString = request.getParameter("uid");

		int uid;
		User thisUser;

		try
		{
			if(uidString != null && uidString.length() > 0)
			{
				uid = Integer.parseInt(uidString);
				thisUser = userDAO.selectUserByUid(uid);
			}
			else
			{
				thisUser = (User) request.getSession().getAttribute("user");
				uid = thisUser.getUid();
			}

			System.out.println("uid = " + uid);

			List<Article> articleList = articleDAO.selectArticleByUid(uid);

			request.setAttribute("articleList", articleList);
			request.setAttribute("uid", uid);
			request.setAttribute("thisUser", thisUser);

			request.getRequestDispatcher("/jsp/owner.jsp").forward(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

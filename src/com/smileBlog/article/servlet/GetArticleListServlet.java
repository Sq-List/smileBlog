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

		String uidString = request.getParameter("uid");

		int uid = 0;
		User thisUser = null;
		int flag;

		try
		{
//			判断url中有没有uid的参数，如果有说明是别人的主页
//			访客状态也可以访问
			if(uidString != null && uidString.length() > 0)
			{
				System.out.println("uidString != null");
				uid = Integer.parseInt(uidString);
				User user = (User) request.getSession().getAttribute("user");
				if(user != null)
				{
					if(uid == user.getUid())
					{
						thisUser = (User) request.getSession().getAttribute("user");
						uid = thisUser.getUid();
						flag = 1;
					}
					else
					{
						thisUser = userDAO.selectUserByUid(uid);
						flag = 0;
					}
				}
				else
				{
					thisUser = userDAO.selectUserByUid(uid);
					flag = 0;
				}
			}
//			如果没有uid参数，则先判断有没有登录
			else
			{
//				如果登录了，则说明进的是自己的主页
				if(request.getSession().getAttribute("user") != null)
				{
					System.out.println("user != null");
					thisUser = (User) request.getSession().getAttribute("user");
					uid = thisUser.getUid();
					flag = 1;
				}
//				如果没登录则说明是访客状态，则跳转登录
				else
				{
					System.out.println("user == null");
					response.sendRedirect(request.getContextPath() + "/home");
					return;
				}
			}

			System.out.println("uid = " + uid + "&flag = " + flag);

			List<Article> articleList = articleDAO.selectArticleByUid(uid, flag);

			request.setAttribute("articleList", articleList);
//			request.setAttribute("uid", uid);
			request.setAttribute("thisUser", thisUser);

			request.getRequestDispatcher("/jsp/owner.jsp").forward(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("index/get");
		doPost(request, response);
	}
}

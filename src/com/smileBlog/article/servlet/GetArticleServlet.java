package com.smileBlog.article.servlet;

import com.smileBlog.article.dao.ArticleDAO;
import com.smileBlog.article.entity.Article;
import com.smileBlog.user.dao.UserDAO;
import com.smileBlog.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by asus on 2017/6/4.
 */
//@WebServlet(name = "GetArticleServlet")
public class GetArticleServlet extends HttpServlet
{
	ArticleDAO articleDAO = new ArticleDAO();
	UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("article/post");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("article/get");

		int aid = Integer.parseInt(request.getParameter("aid"));

		try
		{
			Article article = articleDAO.selectArticleByAid(aid);
			int uid = article.getOwnuid();
			User thisUser = userDAO.selectUserByUid(uid);
			Article preArticle = articleDAO.getArticleByAidAndUid(aid, uid, "pre");
			Article nextArticle = articleDAO.getArticleByAidAndUid(aid, uid, "next");
//			System.out.println(preArticle.getAid() + ", " + nextArticle.getAid());

			request.setAttribute("article", article);
			request.setAttribute("thisUser", thisUser);
			request.setAttribute("preArticle", preArticle);
			request.setAttribute("nextArticle", nextArticle);

			request.getRequestDispatcher("/GetArticleCommentListServlet").forward(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

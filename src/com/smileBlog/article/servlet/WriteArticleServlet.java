package com.smileBlog.article.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 2017/6/4.
 */
//@WebServlet(name = "WriteArticleServlet")
public class WriteArticleServlet extends HttpServlet
{

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("writeArticle/post");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("writeArticle/get");

		request.getRequestDispatcher("/jsp/writeArticle.jsp").forward(request, response);
	}
}

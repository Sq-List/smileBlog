package com.smileBlog.user.servlet;

import com.smileBlog.article.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by asus on 2017/6/9.
 */
//@WebServlet(name = "GetUserListServlet")
public class GetUserListServlet extends HttpServlet
{

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println(request.getRequestURI());
//		如果请求的是文章列表
		if(request.getRequestURI().indexOf("rticle") != -1)
		{
			System.out.println(request.getParameter("articleList"));
//			List<Article> articleList = (List)request.getParameter("articleList");
		}
		else
		{

		}
	}
}

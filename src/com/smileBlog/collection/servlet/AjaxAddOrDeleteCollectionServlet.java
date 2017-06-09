package com.smileBlog.collection.servlet;

import com.smileBlog.collection.dao.CollectionDAO;
import com.smileBlog.collection.entity.Collection;
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
 * Created by asus on 2017/6/6.
 */
//@WebServlet(name = "AjaxAddOrDeleteCollectionServlet")
public class AjaxAddOrDeleteCollectionServlet extends HttpServlet
{
	CollectionDAO collectionDAO = new CollectionDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String operate = request.getParameter("operate");
		int aid = Integer.parseInt(request.getParameter("aid"));
		User user = (User)request.getSession().getAttribute("user");
		int operateUid = user.getUid();
		String nickname = user.getNickname();
		int uid = Integer.parseInt(request.getAttribute("uid").toString());

		request.setAttribute("operate", operate);
//		request.setAttribute("classification", "collection");
		request.setAttribute("aid", aid);

		Collection collection = new Collection();
		collection.setAid(aid);
		collection.setOperateUid(operateUid);
		collection.setUid(uid);

		if(operate.equalsIgnoreCase("add"))
		{
			String title = request.getAttribute("title").toString();
			collection.setTitle(title);
			collection.setOperateNickname(nickname);

			try
			{
//				更新文章表的收藏数和收藏表
				if(collectionDAO.add(collection) == 1)
				{
//					out.print("true");
					request.getRequestDispatcher("/UpdateMessageNumberServlet").forward(request, response);
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
		else if(operate.equalsIgnoreCase("delete"))
		{
			try
			{
//				更新文章表的收藏数和收藏表
				if(collectionDAO.delect(collection) == 1)
				{
//					out.print("true");
					request.getRequestDispatcher("/UpdateMessageNumberServlet").forward(request, response);
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
		doPost(request, response);
	}
}

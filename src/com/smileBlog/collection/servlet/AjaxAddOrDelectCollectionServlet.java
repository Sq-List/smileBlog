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
//@WebServlet(name = "AjaxCollectionAddOrDelectServlet")
public class AjaxCollectionAddOrDelectServlet extends HttpServlet
{
	CollectionDAO collectionDAO = new CollectionDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String operate = request.getParameter("operate");
		int aid = Integer.parseInt(request.getParameter("aid"));
		int uid = ((User)request.getSession().getAttribute("user")).getUid();
		Collection collection = new Collection(uid, aid);

		if(operate.equalsIgnoreCase("add"))
		{
			try
			{
				if(collectionDAO.add(collection) == 1)
				{
					out.print("true");
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
		else if(operate.equalsIgnoreCase("delect"))
		{
			try
			{
				if(collectionDAO.delect(collection) == 1)
				{
					out.print("true");
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

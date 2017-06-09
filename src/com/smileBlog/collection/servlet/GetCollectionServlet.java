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
import java.sql.SQLException;

/**
 * Created by asus on 2017/6/8.
 */
//@WebServlet(name = "GetCollectionServlet")
public class GetCollectionServlet extends HttpServlet
{
	CollectionDAO collectionDAO = new CollectionDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		int aid = Integer.parseInt(request.getParameter("aid"));
		int uid = ((User)request.getSession().getAttribute("user")).getUid();

		try
		{
			int collectionFlag = collectionDAO.selectCollectionByUidAndAid(uid, aid);

			request.setAttribute("collectionFlag", collectionFlag);

			request.getRequestDispatcher("/jsp/article.jsp").forward(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
}

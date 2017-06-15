package com.smileBlog.collection.servlet;

import com.smileBlog.collection.dao.CollectionDAO;
import com.smileBlog.collection.entity.Collection;
import com.smileBlog.user.entity.User;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by asus on 2017/6/12.
 */
//@WebServlet(name = "GetCollectionMessageServlet")
public class GetCollectionMessageServlet extends HttpServlet
{
	CollectionDAO collectionDAO = new CollectionDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int uid = ((User)request.getSession().getAttribute("user")).getUid();
		System.out.println(uid);

		try
		{
			List<Collection> collectionList = collectionDAO.selectByUid(uid);

			JSONArray list = new JSONArray();
			list.add(collectionList);

			out.print(list);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

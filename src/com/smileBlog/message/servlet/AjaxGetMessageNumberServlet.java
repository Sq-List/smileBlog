package com.smileBlog.message.servlet;

import com.smileBlog.message.dao.MessageDAO;
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
 * Created by asus on 2017/6/8.
 */
//@WebServlet(name = "AjaxGetMessageNumberServlet")
public class AjaxGetMessageNumberServlet extends HttpServlet
{
	MessageDAO messageDAO = new MessageDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		User user = ((User)request.getSession().getAttribute("user"));


		int uid = user.getUid();
		try
		{
			int number = messageDAO.selectByUid(uid);

			out.print(number);
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

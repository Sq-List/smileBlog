package com.smileBlog.message.servlet;

import com.smileBlog.message.dao.MessageDAO;

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
//@WebServlet(name = "AddMessageServlet")
public class AddMessageServlet extends HttpServlet
{
	MessageDAO messageDAO = new MessageDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();

		int uid = Integer.parseInt(request.getParameter("uid"));
		try
		{
			if(messageDAO.add(uid) == 1)
			{
				out.write("<script language='javascript'>alert('register success!');window.location.href='"+request.getContextPath()+"/jsp/home.jsp';</script>");
			}
			else
			{
				out.write("<script language='javascript'>alert('register fail!');window.location.href='"+request.getContextPath()+"/jsp/home.jsp';</script>");
			}
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

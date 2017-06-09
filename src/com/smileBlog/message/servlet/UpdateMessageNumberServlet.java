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
//@WebServlet(name = "UpdateMessageNumberServlet")
public class UpdateMessageNumberServlet extends HttpServlet
{
	MessageDAO messageDAO = new MessageDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String operate = request.getParameter("operate");
		String classification = request.getParameter("classification");
		int uid = Integer.parseInt(request.getAttribute("uid").toString());

//		int num = 0;
		System.out.println("updateMessageNumberServlet/post");

		try
		{
			if(operate.equalsIgnoreCase("delete"))
			{
				int num = messageDAO.selectNmuberByUid(uid, classification);
				System.out.println(num);
				if(num > 0)
				{
					if(messageDAO.updateByUid(uid, classification, operate) == 1)
					{
						System.out.println(true);
						out.print("true");
					}
					else
					{
						System.out.println(false);
						out.print("false");
					}
				}
			}
			else
			{
				if(messageDAO.updateByUid(uid, classification, operate) == 1)
				{
					out.print("true");
				}
				else
				{
					out.print("false");
				}
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
		doPost(request, response);
	}
}

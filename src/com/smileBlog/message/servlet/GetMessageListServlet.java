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
 * Created by asus on 2017/6/9.
 */
//@WebServlet(name = "GetMessageListServlet")
public class GetMessageListServlet extends HttpServlet
{
	MessageDAO messageDAO = new MessageDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("GetMessageListServlet/post");
//		ajax
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int uid = ((User)request.getSession().getAttribute("user")).getUid();

//		更新某类消息数量为0
		String classification = request.getParameter("classification");
		System.out.println(classification);

		try
		{
			if(messageDAO.updateNumberOByUid(uid, classification) == 1)
			{
				request.getRequestDispatcher("/Get" + classification.substring(0, 1).toUpperCase() + classification.substring(1) + "MessageServlet").forward(request, response);
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
		System.out.println("GetMessageListServlet/get");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int uid = ((User)request.getSession().getAttribute("user")).getUid();

//		更新某类消息数量为0
		String classification = "comment";

		try
		{
			if(messageDAO.updateNumberOByUid(uid, classification) == 1)
			{
				request.getRequestDispatcher("jsp/new.jsp").forward(request, response);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

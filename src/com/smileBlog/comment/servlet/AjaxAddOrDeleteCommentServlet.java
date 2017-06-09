package com.smileBlog.comment.servlet;

import com.smileBlog.comment.dao.CommentDAO;
import com.smileBlog.comment.entity.Comment;
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
//@WebServlet(name = "AjaxAddOrDeleteCommentServlet")
public class AjaxAddOrDeleteCommentServlet extends HttpServlet
{
	CommentDAO commentDAO = new CommentDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		int aid = Integer.parseInt(request.getParameter("aid"));
		User user = (User)request.getSession().getAttribute("user");
		int operateUid = user.getUid();
		String nickname = user.getNickname();
		int uid = Integer.parseInt(request.getAttribute("uid").toString());
		String operate = request.getParameter("operate");

		request.setAttribute("operate", operate);
//		request.setAttribute("classification", "comment");
		request.setAttribute("aid", aid);

		Comment comment = new Comment();
		comment.setAid(aid);
		comment.setOperateUid(operateUid);
		comment.setUid(uid);

		if(operate.equalsIgnoreCase("add"))
		{
			String content = request.getParameter("commentContent");
			String title = request.getAttribute("title").toString();

			comment.setContent(content);
			comment.setTitle(title);
			comment.setOperateNickname(nickname);

			try
			{
				if(commentDAO.add(comment) == 1)
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
			int comid = Integer.parseInt(request.getParameter("comid"));

			comment.setComid(comid);

			try
			{
				if(commentDAO.delect(comment) == 1)
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

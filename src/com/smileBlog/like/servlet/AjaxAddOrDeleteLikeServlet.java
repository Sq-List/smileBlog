package com.smileBlog.like.servlet;

import com.smileBlog.like.dao.LikeDAO;
import com.smileBlog.like.entity.Like;
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
//@WebServlet(name = "AjaxAddOrDeleteLikeServlet")
public class AjaxAddOrDeleteLikeServlet extends HttpServlet
{
	LikeDAO likeDAO = new LikeDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String operate = request.getParameter("operate");
		request.setAttribute("operate", operate);
//		request.setAttribute("classification", "like");

		int aid = Integer.parseInt(request.getParameter("aid"));
		User user = (User)request.getSession().getAttribute("user");
		int operateUid = user.getUid();
		String nickname = user.getNickname();
		int uid = Integer.parseInt(request.getAttribute("uid").toString());

		Like like = new Like();
		like.setAid(aid);
		like.setOperateUid(operateUid);
		like.setUid(uid);

		if(operate.equalsIgnoreCase("add"))
		{
			String title = request.getAttribute("title").toString();

			try
			{
//				更新文章表的点赞数和点赞表
				if(likeDAO.add(like) == 1)
				{
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
				if(likeDAO.delect(like) == 1)
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

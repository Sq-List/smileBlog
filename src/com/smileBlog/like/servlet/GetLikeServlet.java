package com.smileBlog.like.servlet;

import com.smileBlog.like.dao.LikeDAO;
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
//@WebServlet(name = "GetLikeServlet")
public class GetLikeServlet extends HttpServlet
{
	LikeDAO likeDAO = new LikeDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		int aid = Integer.parseInt(request.getParameter("aid"));
		int uid = ((User)request.getSession().getAttribute("user")).getUid();

		try
		{
			int likeFlag = likeDAO.selectByUidAndAid(uid, aid);

			request.setAttribute("likeFlag", likeFlag);

			request.getRequestDispatcher("/GetCollectionServlet").forward(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("GetLikeServlet/GET");
		doPost(request, response);
	}
}

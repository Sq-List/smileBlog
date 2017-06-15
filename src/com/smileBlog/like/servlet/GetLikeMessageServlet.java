package com.smileBlog.like.servlet;

import com.smileBlog.like.dao.LikeDAO;
import com.smileBlog.like.entity.Like;
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
//@WebServlet(name = "GetLikeMessageServlet")
public class GetLikeMessageServlet extends HttpServlet
{
	LikeDAO likeDAO = new LikeDAO();

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

		try
		{
			List<Like> likeList = likeDAO.selectByUid(uid);

			JSONArray list = new JSONArray();
			list.add(likeList);

			out.print(list);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

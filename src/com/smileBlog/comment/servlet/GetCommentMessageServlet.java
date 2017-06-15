package com.smileBlog.comment.servlet;

import com.smileBlog.comment.dao.CommentDAO;
import com.smileBlog.comment.entity.Comment;
import com.smileBlog.user.dao.UserDAO;
import com.smileBlog.user.entity.User;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/6/9.
 */
//@WebServlet(name = "GetCommentMessageServlet")
public class GetCommentMessageServlet extends HttpServlet
{
	CommentDAO commentDAO = new CommentDAO();
	UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
//		ajax
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int uid = ((User)request.getSession().getAttribute("user")).getUid();

		try
		{
			List<Comment> commentList = commentDAO.selectByUid(uid);

			JSONArray list = new JSONArray();
			list.add(commentList);

			out.print(list);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

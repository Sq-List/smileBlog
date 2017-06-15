package com.smileBlog.user.servlet;

import com.smileBlog.user.dao.UserDAO;
import com.smileBlog.user.entity.User;
import net.sf.json.JSONObject;

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
 * Created by asus on 2017/6/8.
 */
//@WebServlet(name = "SearchUserServlet")
public class SearchUserServlet extends HttpServlet
{
	UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("SearchUserServlet/post");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String str = request.getParameter("search");

		try
		{
			List<User> userList = userDAO.selectUserLikeNickname(str);

			out.print(JSONObject.fromObject(userList));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("SearchUserServlet/get");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String str = request.getParameter("search");
		System.out.println(str);

		try
		{
			List<User> userList = userDAO.selectUserLikeNickname(str);

			request.setAttribute("userList", userList);

			request.getRequestDispatcher("/jsp/search.jsp").forward(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

package com.smileBlog.user.servlet;

import com.smileBlog.user.dao.UserDAO;
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
//@WebServlet(name = "UpdateUserArticleNumberServlet")
public class UpdateUserArticleNumberServlet extends HttpServlet
{
	UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		int uid = ((User)request.getSession().getAttribute("user")).getUid();

		try
		{
			if(userDAO.updateArticleNumberByUid(uid) == 1)
			{
				out.write("<script language='javascript'>alert('publish success!');window.location.href='"+request.getContextPath()+"/index';</script>");
			}
			else
			{
				out.write("<script language='javascript'>alert('publish fail!');window.location.href='"+request.getContextPath()+"/index';</script>");
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

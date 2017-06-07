package com.smileBlog.tool.servlet;

import com.smileBlog.tool.dao.ToolDAO;
import com.smileBlog.tool.entity.Tool;
import com.smileBlog.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/6/6.
 */
//@WebServlet(name = "GetToolListServlet")
public class GetToolListServlet extends HttpServlet
{
	ToolDAO toolDAO = new ToolDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");

		int uid = ((User)request.getSession().getAttribute("user")).getUid();

		List<Tool> toolList;
		try
		{
			toolList = toolDAO.selectToolByUid(uid);

			request.setAttribute("toolList", toolList);

			request.getRequestDispatcher("/jsp/tool.jsp").forward(request, response);
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

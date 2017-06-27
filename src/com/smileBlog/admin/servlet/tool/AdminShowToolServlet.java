package com.smileBlog.admin.servlet.tool;

import com.smileBlog.admin.dao.AdminDAO;
import com.smileBlog.admin.entity.Tool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by asus on 2017/6/24.
 */
public class AdminShowToolServlet extends HttpServlet
{
	AdminDAO adminDAO = new AdminDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("adminShowTool/post");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("adminShowTool/get");
		List<Tool> toolList;
		try
		{
			toolList = adminDAO.showTool();

			request.setAttribute("toolList", toolList);

			request.getRequestDispatcher("/jsp/administrator.jsp").forward(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

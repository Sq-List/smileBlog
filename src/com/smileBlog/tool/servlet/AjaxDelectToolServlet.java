package com.smileBlog.tool.servlet;

import com.smileBlog.tool.dao.ToolDAO;
import com.smileBlog.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by asus on 2017/6/8.
 */
//@WebServlet(name = "AjaxDelectToolServlet")
public class AjaxDelectToolServlet extends HttpServlet
{
	ToolDAO toolDAO = new ToolDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;character=utf-8;");
		PrintWriter out = response.getWriter();

		String filename = request.getParameter("filename");
		System.out.println(filename);
		int uid = ((User)request.getSession().getAttribute("user")).getUid();

		try
		{
			String src = toolDAO.selectSrcByUidAndName(filename, uid);
			File file;
			if(src != null)
			{
				file = new File(src);
				if(toolDAO.delectByUidAndName(filename, uid) == 1)
				{
					if(file.delete())
					{
						out.print("true");
					}
					else
					{
						out.print("file delect fail!");
					}
				}
				else
				{
					out.print("data base delect fail!");
				}
			}
			else
			{
				out.print("file don't exit!");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

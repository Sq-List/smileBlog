package com.smileBlog.noUse;

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

/**
 * Created by asus on 2017/6/4.
 */
//@WebServlet(name = "AjaxGetUserServlet")
public class AjaxGetUserServlet extends HttpServlet
{
//	UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//
//		String uid = request.getParameter("uid");
//		User user = (User)request.getSession().getAttribute("user");
//
//		JSONObject jsonObject = new JSONObject();
//
//		if(uid.equalsIgnoreCase((user.getUid() + "")))
//		{
//			jsonObject.put("user", user);
//			jsonObject.put("flag", "true");
//
//			out.print(jsonObject);
//
//		}
//		else
//		{
//			try
//			{
//				user = userDAO.selectUserByUid(Integer.parseInt(uid));
//
//				jsonObject.put("user", user);
//				jsonObject.put("flag", "false");
//
//				out.print(jsonObject);
//			}
//			catch (SQLException e)
//			{
//				e.printStackTrace();
//			}
//		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}
}

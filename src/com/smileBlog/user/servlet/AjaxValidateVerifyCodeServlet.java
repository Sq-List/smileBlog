package com.smileBlog.user.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by asus on 2017/5/30.
 */
//@WebServlet("/AjaxValidateVerifyCodeServlet")
public class AjaxValidateVerifyCodeServlet extends HttpServlet
{

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String verifyCode = request.getParameter("verifyCode");
		String vcode = (String) request.getSession().getAttribute("vCode");

		try
		{
			out.println(verifyCode.equalsIgnoreCase(vcode));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}
}

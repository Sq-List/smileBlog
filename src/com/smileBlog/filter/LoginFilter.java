package com.smileBlog.filter;

import com.smileBlog.user.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by asus on 2017/6/3.
 */
//@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter
{

	public void destroy()
	{
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws ServletException, IOException
	{
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;

		String requestUri = request.getRequestURI();
		System.out.println(requestUri);

//		如果请求页面为登录页面或验证码则通过
		if (requestUri.indexOf("home") != -1 || requestUri.indexOf("Servlet") != -1 || requestUri.endsWith(".css") || requestUri.endsWith(".js") || requestUri.endsWith(".jpg") || requestUri.endsWith(".html") || requestUri.endsWith(".png"))
		{
			System.out.println("未被拦截");
			chain.doFilter(req, resp);
		}
		else
		{
			User user = (User) request.getSession().getAttribute("user");
			//请求过滤
			if(user == null)
			{
				System.out.println("拦截");
				PrintWriter out = response.getWriter();
//				将请求的页面存下来
				request.getSession().setAttribute("oldRequest", request.getRequestURL().toString());
				out.write("<script language='javascript'>alert('login first!');window.location.href='"+request.getContextPath()+"/home';</script>");
			}
			else
			{
				System.out.println("已登录");
				chain.doFilter(req, resp);
			}
		}


	}

	public void init(FilterConfig config) throws ServletException
	{

	}

}

package com.smileBlog.filter;

import cn.itcast.filter.GetRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by asus on 2017/6/8.
 */
@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter
{

	public void destroy()
	{
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws ServletException, IOException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		/**
		 * tomcat的 server.xml文件里
		 * <Connector port="80" protocol="HTTP/1.1"
		 * connectionTimeout="20000"  redirectPort="8443" URIEncoding="UTF-8"/>
		 * 后边的URIEncoding就是设置get方法编码的如果没有指定URL接收的编码类型，自动会用ISO-8859-1编码
		 * 所以这里对get不进行编码
		 */
		req.setCharacterEncoding("utf-8");//处理post请求编码
		chain.doFilter(request, resp);
	}

	public void init(FilterConfig config) throws ServletException
	{

	}

}

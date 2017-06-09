package com.smileBlog.tool.servlet;

import com.smileBlog.tool.dao.ToolDAO;
import com.smileBlog.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;

/**
 * Created by asus on 2017/6/7.
 */
//@WebServlet(name = "DownloadToolServlet")
public class DownloadToolServlet extends HttpServlet
{
	ToolDAO toolDAO = new ToolDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String fileName = request.getParameter("filename");
		int uid = ((User)request.getSession().getAttribute("user")).getUid();
//		int uid = 1;
		System.out.println(uid + ", " + fileName);

		try
		{
			String src = toolDAO.selectSrcByUidAndName(fileName, uid);
			System.out.println(src);

			if(src != null)
			{
				/**
				 * IE的话，通过URLEncoder对filename进行UTF8编码。
				 * 而其他的浏览器（firefox、chrome、safari、opera），则要通过字节转换成ISO8859-1了。
				 */
				if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
				{
					fileName = URLEncoder.encode(fileName, "UTF-8");
				}
				else
				{
					fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				}

//				设置文件MIME类型
				response.setContentType(getServletContext().getMimeType(fileName));

//				设置Content-Disposition
				/**
				 * 服务端向客户端游览器发送文件时，如果是浏览器支持的文件类型，
				 * 一般会默认使用浏览器打开，比如txt、jpg等，会直接在浏览器中显示，
				 * 如果需要提示用户保存，就要利用Content-Disposition进行一下处理，
				 * 关键在于一定要加上attachment
				 * 这样浏览器会提示保存还是打开，即使选择打开，也会使用相关联的程序比如记事本打开
				 */
				response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

				InputStream inputStream = new FileInputStream(src);
				OutputStream outputStream = response.getOutputStream();

//				写文件
				int b;
				while((b = inputStream.read()) != -1)
				{
					outputStream.write(b);
				}

				inputStream.close();
				outputStream.close();
			}
			else
			{
				response.setContentType("text/html;");
				response.getWriter().print("<script>alert('file do not exit!');</script>");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
}

package com.smileBlog.user.servlet;

import com.smileBlog.user.dao.UserDAO;
import com.smileBlog.user.entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by asus on 2017/6/12.
 */
//@WebServlet(name = "UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet
{
	UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("UpdateUserInfoServlet/post");

		response.setContentType("text/html;character=utf-8");
		PrintWriter out = response.getWriter();

//		首先判断form的enctype是不是multipart/form-data
		if(ServletFileUpload.isMultipartContent(request))
		{
//			实例化一个硬盘文件工厂，用来配置上传组件ServletFileUpload
			DiskFileItemFactory factory = new DiskFileItemFactory();

//			设置文件存放的临时文件夹，这个文件要存在，若不存在这创建
			File tmpFileDir = new File("F:/IDEA/Project/out/artifacts/Project_war_exploded/image/userHeadPic/tmp/");

			if(!tmpFileDir.isDirectory() && tmpFileDir.exists() == false)
			{

				tmpFileDir.mkdirs();
			}
			System.out.println(tmpFileDir.getAbsolutePath());
			factory.setRepository(tmpFileDir);

//			设置最大占用的内存
			factory.setSizeThreshold(1024000);

//			创建ServeltFileUpload对象
			ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
			servletFileUpload.setHeaderEncoding("utf-8");

//			设置单个文件最大值的byte  100MB
			servletFileUpload.setFileSizeMax(104857600);

//			设置所有上传文件的总和最大值byte  100MB
			servletFileUpload.setSizeMax(104857600);

//			声明一个FileItem类型的队列
			List<FileItem> itemList = null;

//			获取提交的表单中的所有项
			try
			{
				itemList = servletFileUpload.parseRequest(request);
			}
			catch (FileUploadBase.SizeLimitExceededException e)
			{
//				若上传文件超过了最大值，则返回消息
				out.print("SizeLimit");
			}
			catch (FileUploadException e)
			{
				e.printStackTrace();
			}

			if(itemList != null)
			{
				File fileDir = new File("F:/IDEA/Project/out/artifacts/Project_war_exploded/image/userHeadPic");

				if(!tmpFileDir.isDirectory() && fileDir.exists() == false)
				{
					fileDir.mkdirs();
				}
				System.out.println(fileDir.getAbsolutePath());

				User user = (User)request.getSession().getAttribute("user");
				int uid = user.getUid();


				for(int i = 0; i < itemList.size(); i++)
				{
					FileItem item = itemList.get(i);

					if(item.isFormField())
					{
						String name = item.getFieldName();
						String value = item.getString();
						System.out.println(name);
						System.out.println(value);

						if(name.equalsIgnoreCase("nickname"))
						{
							user.setNickname(value);
						}
						else if(name.equalsIgnoreCase("label"))
						{
							user.setLable(value);
						}
					}
					else if(!item.isFormField())
					{
						System.out.println(item.getName());
						if(item.getName() == "" || item.getName().length() <= 0)
						{
							continue;
						}

						String endStr = item.getName().substring(item.getName().lastIndexOf('.'));
//						System.out.println(item.getName().substring(item.getName().lastIndexOf('.')));

						BufferedInputStream inputStream = new BufferedInputStream(item.getInputStream());
						BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(fileDir.getAbsolutePath() + "\\" + uid + endStr)));
						Streams.copy(inputStream, outputStream, true);

						user.setHeadPic("./image/userHeadPic/" + uid + endStr);
						System.out.println(user.getHeadPic());
					}
				}

				try
				{
					if(userDAO.updateUserInfo(user) == 1)
					{
						((User)request.getSession().getAttribute("user")).setHeadPic(user.getHeadPic());
						out.write("<script language='javascript'>alert('update success!');window.location.href='"+request.getContextPath()+"/index';</script>");
					}
					else
					{
						out.write("<script language='javascript'>alert('update fail!');window.location.href='"+request.getContextPath()+"/index';</script>");
					}
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				out.print("noFile");
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}
}

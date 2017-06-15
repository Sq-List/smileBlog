package com.smileBlog.tool.servlet;

import com.smileBlog.tool.dao.ToolDAO;
import com.smileBlog.tool.entity.Tool;
import com.smileBlog.user.entity.User;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
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
 * Created by asus on 2017/6/6.
 */
//@WebServlet(name = "AjaxUploadServlet")
public class AjaxUploadServlet extends HttpServlet
{
	ToolDAO toolDAO = new ToolDAO();
	Long beginTime = System.currentTimeMillis();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;character=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

//		首先判断form的enctype是不是multipart/form-data
		if(ServletFileUpload.isMultipartContent(request))
		{
//			实例化一个硬盘文件工厂，用来配置上传组件ServletFileUpload
			DiskFileItemFactory factory = new DiskFileItemFactory();

//			设置文件存放的临时文件夹，这个文件要存在，若不存在这创建
			File tmpFileDir = new File("F:/IDEA/Project/web/WEB-INF/fileUpload/tmp/");

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

//			创建监听器
			ProgressListener progressListener = new ProgressListener()
			{
				@Override
				public void update(long bytesRead, long contentLength, int i)
				{
					System.out.println("上传的进度是" + (double)bytesRead * 100 / contentLength);
//					在session上添加一个进度的值，用于网页显示
					request.getSession().setAttribute("progress", ((double)bytesRead * 100 / contentLength));
//					request.getSession().setAttribute("");

//					BigDecimal br = new BigDecimal(bytesRead).divide(
//							new BigDecimal(1024), 2, BigDecimal.ROUND_HALF_UP);
//					BigDecimal cl = new BigDecimal(contentLength).divide(
//							new BigDecimal(1024), 2, BigDecimal.ROUND_HALF_UP);
//
//					// 剩余字节数
//					BigDecimal ll = cl.subtract(br);
//					System.out.print("剩余" + ll + "KB");
//					// 上传百分比
//					BigDecimal per = br.multiply(new BigDecimal(100)).divide(
//							cl, 2, BigDecimal.ROUND_HALF_UP);
//					System.out.print("已经完成" + per + "%");
//					request.getSession().setAttribute("progress", per);
//					// 上传用时
//					Long nowTime = System.currentTimeMillis();
//					Long useTime = (nowTime - beginTime) / 1000;
//					System.out.print("已经用时" + useTime + "秒");
//					// 上传速度
//					BigDecimal speed = new BigDecimal(0);
//					if (useTime != 0) {
//						speed = br.divide(new BigDecimal(useTime), 2,
//								BigDecimal.ROUND_HALF_UP);
//					}
//					System.out.print("上传速度为" + speed + "KB/S");
//					// 大致剩余时间
//					BigDecimal ltime = new BigDecimal(0);
//					if (!speed.equals(new BigDecimal(0))) {
//						ltime = ll.divide(speed, 0, BigDecimal.ROUND_HALF_UP);
////						request.getSession().setAttribute("lastTime", ltime);
//					}
//					System.out.println("大致剩余时间为" + ltime + "秒");
				}
			};

//			给servletFileUpload添加监听器
			servletFileUpload.setProgressListener(progressListener);

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
				File fileDir = new File("F:/IDEA/Project/web/WEB-INF/fileUpload/");

				if(!tmpFileDir.isDirectory() && fileDir.exists() == false)
				{
					fileDir.mkdirs();
				}
				System.out.println(fileDir.getAbsolutePath());

				Tool tool = new Tool();
				int uid = ((User)request.getSession().getAttribute("user")).getUid();
				tool.setUid(uid);
//				tool.setUid(1);

				for(int i = 0; i < itemList.size(); i++)
				{
					FileItem item = itemList.get(i);

					if(item.isFormField())
					{
						System.out.println(item.getFieldName());
						System.out.println(item.getName());
					}
					else if(!item.isFormField())
					{
						System.out.println(item.getName());
						tool.setSrc(fileDir.getAbsolutePath() + "\\" + item.getName());
						tool.setName(item.getName());

						BufferedInputStream inputStream = new BufferedInputStream(item.getInputStream());
						BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(fileDir.getAbsolutePath() + "\\" + item.getName())));
						Streams.copy(inputStream, outputStream, true);

						try
						{
							if(toolDAO.addTool(tool) == 1)
							{
								System.out.println("add tool already.");
								tool = toolDAO.selectByNameAndUid(item.getName(), uid);

								out.print(JSONObject.fromObject(tool));
							}
							else
							{
								out.print("false");
							}
						}
						catch (SQLException e)
						{
							e.printStackTrace();
						}
					}
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
		response.getWriter().print(request.getSession().getAttribute("progress"));
//		response.getWriter().print(request.getSession().getAttribute("lastTime"));
	}
}

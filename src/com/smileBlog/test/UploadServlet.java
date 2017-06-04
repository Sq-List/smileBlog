package com.smileBlog.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;


/**
 * Created by asus on 2017/6/3.
 */
//@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		// 设置字符编码为UTF-8, 这样支持汉字显示
		response.setCharacterEncoding("UTF-8");
		Writer o = response.getWriter();

		/**
		 * 首先判断form的enctype是不是multipart/form-data
		 * 同时也判断了form的提交方式是不是post
		 * 方法：isMultipartContent(request)
		 */

		if(ServletFileUpload.isMultipartContent(request)){
			request.setCharacterEncoding("utf-8");

			// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
			DiskFileItemFactory factory =  new DiskFileItemFactory();

			//设置文件存放的临时文件夹，这个文件夹要真实存在
			File fileDir = new File("F:/IDEA/Project/fileUpload/tmp/");
			if(fileDir.isDirectory() && fileDir.exists()==false){
				fileDir.mkdir();
			}
			factory.setRepository(fileDir);

			//设置最大占用的内存
			factory.setSizeThreshold(1024000);

			//创建ServletFileUpload对象
			ServletFileUpload sfu = new ServletFileUpload(factory);
			sfu.setHeaderEncoding("utf-8");

			//设置单个文件最大值byte
			sfu.setFileSizeMax(102400000);

			//所有上传文件的总和最大值byte
			sfu.setSizeMax(204800000);

			List<FileItem> items =  null;

			try {
				items = sfu.parseRequest(request);
			}catch (SizeLimitExceededException e) {
				System.out.println("文件大小超过了最大值");
			} catch(FileUploadException e) {
				e.printStackTrace();
			}

			//取得items的迭代器
			Iterator<FileItem> iter = items==null?null:items.iterator();

			//图片上传后存放的路径目录
			File images = new File("F:/IDEA/Project/fileUpload/");
			if(images.exists()==false){
				images.mkdirs();
			}
			//迭代items
			while(iter!=null && iter.hasNext()){
				FileItem item = (FileItem) iter.next();

				//如果传过来的是普通的表单域
				if(item.isFormField()){
					System.out.print("普通的表单域:");
					System.out.print(new String(item.getFieldName()) + "  ");
					System.out.println(new String(item.getString("UTF-8")));
				}
				//文件域
				else if(!item.isFormField()){
					System.out.println("源图片:" + item.getName());
//					String fileName = item.getName().substring(item.getName().lastIndexOf("\\"));
					String fileName = item.getName();
					BufferedInputStream in = new BufferedInputStream(item.getInputStream());
					//文件存储在D:/upload/images目录下,这个目录也得存在
					BufferedOutputStream out = new BufferedOutputStream(
							new FileOutputStream(new File(images.getAbsolutePath()+ fileName)));
					Streams.copy(in, out, true);
					o.write("文件上传成功");
				}
			}
		}else {
			System.out.println("表单的enctype 类型错误");
		}
	}

}

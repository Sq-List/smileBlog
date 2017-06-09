package com.smileBlog.comment.servlet;

import com.smileBlog.comment.dao.CommentDAO;
import com.smileBlog.comment.entity.Comment;
import com.smileBlog.user.dao.UserDAO;
import com.smileBlog.user.entity.User;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/6/9.
 */
//@WebServlet(name = "GetCommentMessageServlet")
public class GetCommentMessageServlet extends HttpServlet
{
	CommentDAO commentDAO = new CommentDAO();
	UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
//		ajax
		int uid = ((User)request.getSession().getAttribute("user")).getUid();

		try
		{
			List<Comment> commentList = commentDAO.selectByUid(uid);
			List<User> userList = new ArrayList<>();
			for(int i = 0; i < commentList.size(); i++)
			{
				User newUser = userDAO.selectPicAndNicknameByUid(commentList.get(i).getUid());
				userList.add(newUser);
			}

			request.setAttribute("userList", userList);
			request.setAttribute("commentList", commentList);

//			JSONObject
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

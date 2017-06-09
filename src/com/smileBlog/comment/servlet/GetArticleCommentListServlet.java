package com.smileBlog.comment.servlet;

import com.smileBlog.comment.dao.CommentDAO;
import com.smileBlog.comment.entity.Comment;
import com.smileBlog.user.dao.UserDAO;
import com.smileBlog.user.entity.User;

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
 * Created by asus on 2017/6/8.
 */
//@WebServlet(name = "GetArticleCommentListServlet")
public class GetArticleCommentListServlet extends HttpServlet
{
	CommentDAO commentDAO = new CommentDAO();
	UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		int aid = Integer.parseInt(request.getParameter("aid"));

		try
		{
			List<Comment> commentList = commentDAO.selectByAid(aid);
			List<User> userList = new ArrayList<>();
			for(int i = 0; i < commentList.size(); i++)
			{
				User newUser = userDAO.selectPicAndNicknameByUid(commentList.get(i).getUid());
				userList.add(newUser);
			}

			request.setAttribute("userList", userList);
			request.setAttribute("commentList", commentList);

			if(request.getSession().getAttribute("user") != null)
			{
				System.out.println("user != null");
				request.getRequestDispatcher("/GetLikeServlet").forward(request, response);
			}
			else
			{
				System.out.println("user == null");
				request.getRequestDispatcher("/jsp/article.jsp").forward(request, response);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("getCommentListServlet/GET");
		doPost(request, response);
	}
}

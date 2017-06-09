package com.smileBlog.user.entity;

/**
 * Created by asus on 2017/5/29.
 */
public class User
{
	private int uid;
	private String username;
	private String password;
	private String nickname;
	private String lable;
	private String headPic;
	private int articleNumber;
	//	private int messageNumber;

	public int getArticleNumber()
	{
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber)
	{
		this.articleNumber = articleNumber;
	}

	public int getUid()
	{
		return uid;
	}

	public void setUid(int uid)
	{
		this.uid = uid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getLable()
	{
		return lable;
	}

	public void setLable(String lable)
	{
		this.lable = lable;
	}

	public String getHeadPic()
	{
		return headPic;
	}

	public void setHeadPic(String headPic)
	{
		this.headPic = headPic;
	}
}


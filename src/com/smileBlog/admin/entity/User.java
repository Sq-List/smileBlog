package com.smileBlog.admin.entity;

/**
 * Created by asus on 2017/6/18.
 */
public class User
{
	private int uid;
	private String nickname;
	private String headPic;
	private String label;
	private int status;
	private int articleNumber;

	public int getUid()
	{
		return uid;
	}

	public void setUid(int uid)
	{
		this.uid = uid;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getHeadPic()
	{
		return headPic;
	}

	public void setHeadPic(String headPic)
	{
		this.headPic = headPic;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getArticleNumber()
	{
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber)
	{
		this.articleNumber = articleNumber;
	}
}

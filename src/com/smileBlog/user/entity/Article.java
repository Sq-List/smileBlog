package com.smileBlog.user.entity;

import java.util.Date;

/**
 * Created by asus on 2017/6/4.
 */
public class Article
{
	private int aid;
	private int ownuid;
	private String title;
	private Date createTime;

	public int getAid()
	{
		return aid;
	}

	public void setAid(int aid)
	{
		this.aid = aid;
	}

	public int getOwnuid()
	{
		return ownuid;
	}

	public void setOwnuid(int ownuid)
	{
		this.ownuid = ownuid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
}

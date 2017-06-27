package com.smileBlog.admin.entity;

import java.util.Date;

/**
 * Created by asus on 2017/6/18.
 */
public class Tool
{
	private int tid;
	private int uid;
	private String nickname;
	private String name;
	private String src;
	private Date createTime;

	public int getTid()
	{
		return tid;
	}

	public void setTid(int tid)
	{
		this.tid = tid;
	}

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
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

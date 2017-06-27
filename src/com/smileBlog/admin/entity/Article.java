package com.smileBlog.admin.entity;

import java.util.Date;

/**
 * Created by asus on 2017/6/18.
 */
public class Article
{
	private int aid;
	private int ownuid;
	private String ownNickname;
	private String title;
	private String contentTxt;
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

	public String getOwnNickname()
	{
		return ownNickname;
	}

	public void setOwnNickname(String ownNickname)
	{
		this.ownNickname = ownNickname;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContentTxt()
	{
		return contentTxt;
	}

	public void setContentTxt(String contentTxt)
	{
		this.contentTxt = contentTxt;
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

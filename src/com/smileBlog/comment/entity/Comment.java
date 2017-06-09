package com.smileBlog.comment.entity;

import java.util.Date;

/**
 * Created by asus on 2017/6/8.
 */
public class Comment
{
	private int comid;
	private int operateUid;
	private String operateNickname;
	private int aid;
	private String title;
	private int uid;
	private Date createTime;
	private String content;

	public int getComid()
	{
		return comid;
	}

	public void setComid(int comid)
	{
		this.comid = comid;
	}

	public int getOperateUid()
	{
		return operateUid;
	}

	public void setOperateUid(int operateUid)
	{
		this.operateUid = operateUid;
	}

	public String getOperateNickname()
	{
		return operateNickname;
	}

	public void setOperateNickname(String operateNickname)
	{
		this.operateNickname = operateNickname;
	}

	public int getAid()
	{
		return aid;
	}

	public void setAid(int aid)
	{
		this.aid = aid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getUid()
	{
		return uid;
	}

	public void setUid(int uid)
	{
		this.uid = uid;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
}

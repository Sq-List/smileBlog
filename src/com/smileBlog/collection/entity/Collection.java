package com.smileBlog.collection.entity;

/**
 * Created by asus on 2017/6/6.
 */
public class Collection
{
	private int uid;
	private int aid;

	public Collection()
	{

	}

	public Collection(int uid, int aid)
	{
		this.aid = aid;
		this.uid = uid;
	}

	public int getUid()
	{
		return uid;
	}

	public void setUid(int uid)
	{
		this.uid = uid;
	}

	public int getAid()
	{
		return aid;
	}

	public void setAid(int aid)
	{
		this.aid = aid;
	}
}

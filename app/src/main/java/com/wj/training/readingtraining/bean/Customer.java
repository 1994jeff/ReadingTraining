package com.wj.training.readingtraining.bean;

import java.io.Serializable;
import java.util.Date;

public class Customer extends Bean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String no;
	private String name;
	private String address;
	private String mobile;
	private int level;
	private Date createTime;
	
	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return super.getToken();
	}
	
	@Override
	public void setToken(String token) {
		// TODO Auto-generated method stub
		super.setToken(token);
	}
	
	public int getId() {
		return id;
	}
	public String getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getMobile() {
		return mobile;
	}
	public int getLevel() {
		return level;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [id=");
		builder.append(id);
		builder.append(", no=");
		builder.append(no);
		builder.append(", name=");
		builder.append(name);
		builder.append(", address=");
		builder.append(address);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", level=");
		builder.append(level);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
}

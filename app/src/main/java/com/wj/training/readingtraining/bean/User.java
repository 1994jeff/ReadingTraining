package com.example.data.bean;

import java.io.Serializable;
import java.util.Date;

public class User extends Bean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userNo;
	private String name;
	private String psd;
	private String auth;//权限
	private String status;

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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", psd=");
		builder.append(psd);
		builder.append(", auth=");
		builder.append(auth);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
}

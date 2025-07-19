package com.skk.pojo;

public class SignUp {
	private int userId;
	private String userFname,userLname,userEmailId,userPass,userCpass, userLocation;
	private long userContactNo;
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserFname() {
		return userFname;
	}
	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}
	public String getUserLname() {
		return userLname;
	}
	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserCpass() {
		return userCpass;
	}
	public void setUserCpass(String userCpass) {
		this.userCpass = userCpass;
	}
	public long getUserContactNo() {
		return userContactNo;
	}
	public void setUserContactNo(long userContactNo) {
		this.userContactNo = userContactNo;
	}
	
}
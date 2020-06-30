package com.atr.model;

public class User {
	
	private String userId;
	private String lastName;
	private String firstName;
	private String name;
	private String phone;
	private String ext;
	private Integer securityLevel;
	private Boolean reportingAllowed;
	private Boolean active;
	

	public User(String userid, String lastName, String firstName, String name, String phone, String ext,
			Integer securityLevel, Boolean reportingAllowed, Boolean active) {
		super();
		this.userId = userid;
		this.lastName = lastName;
		this.firstName = firstName;
		this.name = name;
		this.phone = phone;
		this.ext = ext;
		this.securityLevel = securityLevel;
		this.reportingAllowed = reportingAllowed;
		this.active = active;
	}

	public User() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userid) {
		this.userId = userid;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Integer getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(Integer securityLevel) {
		this.securityLevel = securityLevel;
	}

	public Boolean getReportingAllowed() {
		return reportingAllowed;
	}

	public void setReportingAllowed(Boolean reportingAllowed) {
		this.reportingAllowed = reportingAllowed;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}

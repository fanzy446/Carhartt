package com.carhartt.man.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Manager entity. @author MyEclipse Persistence Tools
 */

public class Manager implements java.io.Serializable {

	// Fields

	private Integer managerId;
	private String managerName;
	private String managerPass;
	private Short managerType;
	private String employeeName;
	private String remark;
	private Set announcements = new HashSet(0);
	private Set notifications = new HashSet(0);
	// Constructors

	public Set getNotifications() {
		return notifications;
	}

	public void setNotifications(Set notifications) {
		this.notifications = notifications;
	}

	/** default constructor */
	public Manager() {
	}

	/** minimal constructor */
	public Manager(String managerName, String managerPass) {
		this.managerName = managerName;
		this.managerPass = managerPass;
	}

	/** full constructor */
	public Manager(String managerName, String managerPass, Short managerType,
			String employeeName, String remark, Set announcements) {
		this.managerName = managerName;
		this.managerPass = managerPass;
		this.managerType = managerType;
		this.employeeName = employeeName;
		this.remark = remark;
		this.announcements = announcements;
	}

	// Property accessors

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPass() {
		return this.managerPass;
	}

	public void setManagerPass(String managerPass) {
		this.managerPass = managerPass;
	}

	public Short getManagerType() {
		return this.managerType;
	}

	public void setManagerType(Short managerType) {
		this.managerType = managerType;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(Set announcements) {
		this.announcements = announcements;
	}

}
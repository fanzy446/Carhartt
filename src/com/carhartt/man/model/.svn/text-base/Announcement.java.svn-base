package com.carhartt.man.model;

import java.sql.Timestamp;

/**
 * Announcement entity. @author MyEclipse Persistence Tools
 */

public class Announcement implements java.io.Serializable {

	// Fields

	private Integer announceId;
	private Manager manager;
	private String announceTitle;
	private String announceContent;
	private Timestamp announceTime;
	private String remark;

	// Constructors

	/** default constructor */
	public Announcement() {
	}

	/** minimal constructor */
	public Announcement(Manager manager, String announceTitle,
			String announceContent) {
		this.manager = manager;
		this.announceTitle = announceTitle;
		this.announceContent = announceContent;
	}

	/** full constructor */
	public Announcement(Manager manager, String announceTitle,
			String announceContent, Timestamp announceTime, String remark) {
		this.manager = manager;
		this.announceTitle = announceTitle;
		this.announceContent = announceContent;
		this.announceTime = announceTime;
		this.remark = remark;
	}

	// Property accessors

	public Integer getAnnounceId() {
		return this.announceId;
	}

	public void setAnnounceId(Integer announceId) {
		this.announceId = announceId;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getAnnounceTitle() {
		return this.announceTitle;
	}

	public void setAnnounceTitle(String announceTitle) {
		this.announceTitle = announceTitle;
	}

	public String getAnnounceContent() {
		return this.announceContent;
	}

	public void setAnnounceContent(String announceContent) {
		this.announceContent = announceContent;
	}

	public Timestamp getAnnounceTime() {
		return this.announceTime;
	}

	public void setAnnounceTime(Timestamp announceTime) {
		this.announceTime = announceTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
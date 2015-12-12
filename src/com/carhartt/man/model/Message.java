package com.carhartt.man.model;

import java.sql.Timestamp;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer messageId;
	private User user;
	private Short mesgType;
	private String mesgTitle;
	private String mesgContent;
	private Timestamp mesgTime;
	private String remark;
	private Integer userId;
	private short isreply;
	// Constructors



	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(User user, String mesgTitle, String mesgContent) {
		this.user = user;
		this.mesgTitle = mesgTitle;
		this.mesgContent = mesgContent;
	}

	/** full constructor */
	public Message(User user, Short mesgType, String mesgTitle,
			String mesgContent, Timestamp mesgTime, String remark) {
		this.user = user;
		this.mesgType = mesgType;
		this.mesgTitle = mesgTitle;
		this.mesgContent = mesgContent;
		this.mesgTime = mesgTime;
		this.remark = remark;
	}

	// Property accessors

	public Integer getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Short getMesgType() {
		return this.mesgType;
	}

	public void setMesgType(Short mesgType) {
		this.mesgType = mesgType;
	}

	public String getMesgTitle() {
		return this.mesgTitle;
	}

	public void setMesgTitle(String mesgTitle) {
		this.mesgTitle = mesgTitle;
	}

	public String getMesgContent() {
		return this.mesgContent;
	}

	public void setMesgContent(String mesgContent) {
		this.mesgContent = mesgContent;
	}

	public Timestamp getMesgTime() {
		return this.mesgTime;
	}

	public void setMesgTime(Timestamp mesgTime) {
		this.mesgTime = mesgTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public short getIsreply() {
		return isreply;
	}

	public void setIsreply(short isreply) {
		this.isreply = isreply;
	}

}
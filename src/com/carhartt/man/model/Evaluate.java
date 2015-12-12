package com.carhartt.man.model;

import java.sql.Timestamp;

/**
 * Evaluate entity. @author MyEclipse Persistence Tools
 */

public class Evaluate implements java.io.Serializable {

	// Fields

	private Integer evaluateId;
	private User user;
	private Item item;
	private String evaluateTitle;
	private String evaluateContent;
	private Timestamp evaluateTime;
	private Short evaluateState;
	private String remark;

	// Constructors

	/** default constructor */
	public Evaluate() {
	}

	/** minimal constructor */
	public Evaluate(User user, Item item, String evaluateTitle,
			String evaluateContent, Short evaluateState) {
		this.user = user;
		this.item = item;
		this.evaluateTitle = evaluateTitle;
		this.evaluateContent = evaluateContent;
		this.evaluateState = evaluateState;
	}

	/** full constructor */
	public Evaluate(User user, Item item, String evaluateTitle,
			String evaluateContent, Timestamp evaluateTime,
			Short evaluateState, String remark) {
		this.user = user;
		this.item = item;
		this.evaluateTitle = evaluateTitle;
		this.evaluateContent = evaluateContent;
		this.evaluateTime = evaluateTime;
		this.evaluateState = evaluateState;
		this.remark = remark;
	}

	// Property accessors

	public Integer getEvaluateId() {
		return this.evaluateId;
	}

	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getEvaluateTitle() {
		return this.evaluateTitle;
	}

	public void setEvaluateTitle(String evaluateTitle) {
		this.evaluateTitle = evaluateTitle;
	}

	public String getEvaluateContent() {
		return this.evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public Timestamp getEvaluateTime() {
		return this.evaluateTime;
	}

	public void setEvaluateTime(Timestamp evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public Short getEvaluateState() {
		return this.evaluateState;
	}

	public void setEvaluateState(Short evaluateState) {
		this.evaluateState = evaluateState;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
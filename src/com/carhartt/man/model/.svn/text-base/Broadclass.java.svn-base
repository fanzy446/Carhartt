package com.carhartt.man.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Broadclass entity. @author MyEclipse Persistence Tools
 */

public class Broadclass implements java.io.Serializable {

	// Fields

	private Integer broadClassId;
	private String broadClassName;
	private String remark;
	private transient Set subclasses = new HashSet(0);
	private transient Set items = new HashSet(0);

	// Constructors

	/** default constructor */
	public Broadclass() {
	}

	/** minimal constructor */
	public Broadclass(String broadClassName) {
		this.broadClassName = broadClassName;
	}

	/** full constructor */
	public Broadclass(String broadClassName, String remark, Set subclasses,
			Set items) {
		this.broadClassName = broadClassName;
		this.remark = remark;
		this.subclasses = subclasses;
		this.items = items;
	}

	// Property accessors

	public Integer getBroadClassId() {
		return this.broadClassId;
	}

	public void setBroadClassId(Integer broadClassId) {
		this.broadClassId = broadClassId;
	}

	public String getBroadClassName() {
		return this.broadClassName;
	}

	public void setBroadClassName(String broadClassName) {
		this.broadClassName = broadClassName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getSubclasses() {
		return this.subclasses;
	}

	public void setSubclasses(Set subclasses) {
		this.subclasses = subclasses;
	}

	public Set getItems() {
		return this.items;
	}

	public void setItems(Set items) {
		this.items = items;
	}

}
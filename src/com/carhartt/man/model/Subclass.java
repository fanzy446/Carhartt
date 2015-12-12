package com.carhartt.man.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Subclass entity. @author MyEclipse Persistence Tools
 */

public class Subclass implements java.io.Serializable {

	// Fields

	private Integer subClassId;
	private Broadclass broadclass;
	private String subClassName;
	private String remark;
	private Set items = new HashSet(0);

	// Constructors

	/** default constructor */
	public Subclass() {
	}

	/** minimal constructor */
	public Subclass(Broadclass broadclass, String subClassName) {
		this.broadclass = broadclass;
		this.subClassName = subClassName;
	}

	/** full constructor */
	public Subclass(Broadclass broadclass, String subClassName, String remark,
			Set items) {
		this.broadclass = broadclass;
		this.subClassName = subClassName;
		this.remark = remark;
		this.items = items;
	}

	// Property accessors

	public Integer getSubClassId() {
		return this.subClassId;
	}

	public void setSubClassId(Integer subClassId) {
		this.subClassId = subClassId;
	}

	public Broadclass getBroadclass() {
		return this.broadclass;
	}

	public void setBroadclass(Broadclass broadclass) {
		this.broadclass = broadclass;
	}

	public String getSubClassName() {
		return this.subClassName;
	}

	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getItems() {
		return this.items;
	}

	public void setItems(Set items) {
		this.items = items;
	}

}
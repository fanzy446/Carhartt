package com.carhartt.man.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Consignee entity. @author MyEclipse Persistence Tools
 */

public class Consignee implements java.io.Serializable {

	// Fields

	private Integer consigneeId;
	private User user;
	private String consigneeName;
	private String fullAddress;
	private String postcode;
	private String consigneePhone;
	private String consigneeTel;
	private String consigneeEmail;
	private String consigneeMesg;
	private String remark;
	private Set orders = new HashSet(0);
	
	// Constructors

	/** default constructor */
	public Consignee() {
	}

	/** minimal constructor */
	public Consignee(User user, String consigneeName, String fullAddress,
			String postcode, String consigneePhone) {
		this.user = user;
		this.consigneeName = consigneeName;
		this.fullAddress = fullAddress;
		this.postcode = postcode;
		this.consigneePhone = consigneePhone;
	}

	/** full constructor */
	public Consignee(User user, String consigneeName, String fullAddress,
			String postcode, String consigneePhone, String consigneeTel,
			String consigneeEmail, String consigneeMesg, String remark,
			Set orders) {
		this.user = user;
		this.consigneeName = consigneeName;
		this.fullAddress = fullAddress;
		this.postcode = postcode;
		this.consigneePhone = consigneePhone;
		this.consigneeTel = consigneeTel;
		this.consigneeEmail = consigneeEmail;
		this.consigneeMesg = consigneeMesg;
		this.remark = remark;
		this.orders = orders;
	}

	// Property accessors

	public Integer getConsigneeId() {
		return this.consigneeId;
	}

	public void setConsigneeId(Integer consigneeId) {
		this.consigneeId = consigneeId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConsigneeName() {
		return this.consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getFullAddress() {
		return this.fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getConsigneePhone() {
		return this.consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneeTel() {
		return this.consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getConsigneeEmail() {
		return this.consigneeEmail;
	}

	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}

	public String getConsigneeMesg() {
		return this.consigneeMesg;
	}

	public void setConsigneeMesg(String consigneeMesg) {
		this.consigneeMesg = consigneeMesg;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}
}
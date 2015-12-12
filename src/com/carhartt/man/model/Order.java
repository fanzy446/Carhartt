package com.carhartt.man.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private Consignee consignee;
	private User user;
	private Float totalPrice;
	private Timestamp createTime;
	private Short orderState;
	private Timestamp deliveryTime;
	private Timestamp receivingTime;
	private Short evaluationGrade;
	private String extraEstimate;
	private String remark;
	private Set orderdetialses = new HashSet(0);
	private Integer userId;

	// Constructors



	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Consignee consignee, User user, Float totalPrice,
			Short orderState, Short evaluationGrade) {
		this.consignee = consignee;
		this.user = user;
		this.totalPrice = totalPrice;
		this.orderState = orderState;
		this.evaluationGrade = evaluationGrade;
	}

	/** full constructor */
	public Order(Consignee consignee, User user, Float totalPrice,
			Timestamp createTime, Short orderState, Timestamp deliveryTime,
			Timestamp receivingTime, Short evaluationGrade,
			String extraEstimate, String remark, Set orderdetialses) {
		this.consignee = consignee;
		this.user = user;
		this.totalPrice = totalPrice;
		this.createTime = createTime;
		this.orderState = orderState;
		this.deliveryTime = deliveryTime;
		this.receivingTime = receivingTime;
		this.evaluationGrade = evaluationGrade;
		this.extraEstimate = extraEstimate;
		this.remark = remark;
		this.orderdetialses = orderdetialses;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Consignee getConsignee() {
		return this.consignee;
	}

	public void setConsignee(Consignee consignee) {
		this.consignee = consignee;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Short getOrderState() {
		return this.orderState;
	}

	public void setOrderState(Short orderState) {
		this.orderState = orderState;
	}

	public Timestamp getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Timestamp getReceivingTime() {
		return this.receivingTime;
	}

	public void setReceivingTime(Timestamp receivingTime) {
		this.receivingTime = receivingTime;
	}

	public Short getEvaluationGrade() {
		return this.evaluationGrade;
	}

	public void setEvaluationGrade(Short evaluationGrade) {
		this.evaluationGrade = evaluationGrade;
	}

	public String getExtraEstimate() {
		return this.extraEstimate;
	}

	public void setExtraEstimate(String extraEstimate) {
		this.extraEstimate = extraEstimate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getOrderdetialses() {
		return this.orderdetialses;
	}

	public void setOrderdetialses(Set orderdetialses) {
		this.orderdetialses = orderdetialses;
	}

}
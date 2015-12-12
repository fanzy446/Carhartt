package com.carhartt.man.model;

/**
 * Orderdetials entity. @author MyEclipse Persistence Tools
 */

public class Orderdetials implements java.io.Serializable {

	// Fields

	private Integer orderDetialId;
	private Order order;
	private Item item;
	private String itemDesc;
	private Integer itemCount;
	private String remark;

	// Constructors

	/** default constructor */
	public Orderdetials() {
	}

	/** minimal constructor */
	public Orderdetials(Order order, Item item, Integer itemCount) {
		this.order = order;
		this.item = item;
		this.itemCount = itemCount;
	}

	/** full constructor */
	public Orderdetials(Order order, Item item, String itemDesc,
			Integer itemCount, String remark) {
		this.order = order;
		this.item = item;
		this.itemDesc = itemDesc;
		this.itemCount = itemCount;
		this.remark = remark;
	}

	// Property accessors

	public Integer getOrderDetialId() {
		return this.orderDetialId;
	}

	public void setOrderDetialId(Integer orderDetialId) {
		this.orderDetialId = orderDetialId;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getItemDesc() {
		return this.itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Integer getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
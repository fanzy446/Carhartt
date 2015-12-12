package com.carhartt.man.model;

import java.sql.Timestamp;

/**
 * Shoppingcart entity. @author MyEclipse Persistence Tools
 */

public class Shoppingcart implements java.io.Serializable {

	// Fields

	private Integer cartId;
	private User user;
	private Item item;
	private Integer purchaseNum;
	private Float itemPrice;
	private Timestamp addTime;
	private String itemDesc;
	private String remark;

	// Constructors

	/** default constructor */
	public Shoppingcart() {
	}

	/** minimal constructor */
	public Shoppingcart(User user, Item item, Integer purchaseNum,
			Float itemPrice) {
		this.user = user;
		this.item = item;
		this.purchaseNum = purchaseNum;
		this.itemPrice = itemPrice;
	}

	/** full constructor */
	public Shoppingcart(User user, Item item, Integer purchaseNum,
			Float itemPrice, Timestamp addTime, String itemDesc, String remark) {
		this.user = user;
		this.item = item;
		this.purchaseNum = purchaseNum;
		this.itemPrice = itemPrice;
		this.addTime = addTime;
		this.itemDesc = itemDesc;
		this.remark = remark;
	}

	// Property accessors

	public Integer getCartId() {
		return this.cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
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

	public Integer getPurchaseNum() {
		return this.purchaseNum;
	}

	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public Float getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(Float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getItemDesc() {
		return this.itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
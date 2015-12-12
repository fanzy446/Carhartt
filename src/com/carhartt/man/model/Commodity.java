package com.carhartt.man.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class Commodity implements Serializable {
	private String itemId;
	private String itemName;
	private String broadClassName;
	private String subClassName;
	private Broadclass broadclass;
	private Subclass subclass;
	private String itemPubDate;
	private String itemPhoto;
	private Float itemPrice;
	private String itemCount;
	private String itemUnit;
	private String itemDesc;
	private String itemSale;
	private String remark;
	
	public Commodity(String itemId, String itemName, String broadClassName,
			String subClassName, String itemPubDate, String itemPhoto,
			Float itemPrice, String itemCount, String itemUnit,
			String itemDesc, String itemSale, String remark) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.broadClassName = broadClassName;
		this.subClassName = subClassName;
		this.itemPubDate = itemPubDate;
		this.itemPhoto = itemPhoto;
		this.itemPrice = itemPrice;
		this.itemCount = itemCount;
		this.itemUnit = itemUnit;
		this.itemDesc = itemDesc;
		this.itemSale = itemSale;
		this.remark = remark;
	}
	
	public Commodity(String itemId, String itemName, Broadclass broadclass,
			Subclass subclass, String itemPubDate, String itemPhoto,
			Float itemPrice, String itemCount, String itemUnit,
			String itemDesc, String itemSale, String remark) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.broadclass = broadclass;
		this.subclass = subclass;
		this.itemPubDate = itemPubDate;
		this.itemPhoto = itemPhoto;
		this.itemPrice = itemPrice;
		this.itemCount = itemCount;
		this.itemUnit = itemUnit;
		this.itemDesc = itemDesc;
		this.itemSale = itemSale;
		this.remark = remark;
	}
	
	
	
	public Commodity() {
		super();
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBroadClassName() {
		return broadClassName;
	}
	public void setBroadClassName(String broadClassName) {
		this.broadClassName = broadClassName;
	}
	public String getSubClassName() {
		return subClassName;
	}
	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}

	public Float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemPubDate() {
		return itemPubDate;
	}
	public void setItemPubDate(String itemPubDate) {
		this.itemPubDate = itemPubDate;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getItemPhoto() {
		return itemPhoto;
	}
	public void setItemPhoto(String itemPhoto) {
		this.itemPhoto = itemPhoto;
	}
	public String getItemSale() {
		return itemSale;
	}
	public void setItemSale(String itemSale) {
		this.itemSale = itemSale;
	}

	public Broadclass getBroadclass() {
		return broadclass;
	}

	public void setBroadclass(Broadclass broadclass) {
		this.broadclass = broadclass;
	}

	public Subclass getSubclass() {
		return subclass;
	}

	public void setSubclass(Subclass subclass) {
		this.subclass = subclass;
	}

}

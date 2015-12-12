package com.carhartt.man.model;

import java.sql.Timestamp;

/**
 * Favorites entity. @author MyEclipse Persistence Tools
 */

public class Favorites implements java.io.Serializable {

	// Fields

	private Integer favoriteId;
	private User user;
	private Item item;
	private Timestamp favoriteTime;
	private String remark;

	// Constructors

	/** default constructor */
	public Favorites() {
	}

	/** minimal constructor */
	public Favorites(User user, Item item) {
		this.user = user;
		this.item = item;
	}

	/** full constructor */
	public Favorites(User user, Item item, Timestamp favoriteTime, String remark) {
		this.user = user;
		this.item = item;
		this.favoriteTime = favoriteTime;
		this.remark = remark;
	}

	// Property accessors

	public Integer getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
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

	public Timestamp getFavoriteTime() {
		return this.favoriteTime;
	}

	public void setFavoriteTime(Timestamp favoriteTime) {
		this.favoriteTime = favoriteTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
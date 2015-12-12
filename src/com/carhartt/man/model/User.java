package com.carhartt.man.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private Short userRole;
	private Timestamp registerTime;
	private Short userState;
	private String remark;
	private Set favoriteses = new HashSet(0);
	private Set userinfos = new HashSet(0);
	private Set consignees = new HashSet(0);
	private Set evaluates = new HashSet(0);
	private Set orders = new HashSet(0);
	private Set messages = new HashSet(0);
	private Set shoppingcarts = new HashSet(0);
	private Set notifications = new HashSet(0);
	// Constructors

	public Set getNotifications() {
		return notifications;
	}

	public void setNotifications(Set notifications) {
		this.notifications = notifications;
	}

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String userEmail, String userPassword, Timestamp registerTime,
			Short userState) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.registerTime = registerTime;
		this.userState = userState;
	}

	/** full constructor */
	public User(String userName, String userEmail, String userPassword,
			Short userRole, Timestamp registerTime, Short userState,
			String remark, Set favoriteses, Set userinfos, Set consignees,
			Set evaluates, Set orders, Set messages, Set shoppingcarts) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.registerTime = registerTime;
		this.userState = userState;
		this.remark = remark;
		this.favoriteses = favoriteses;
		this.userinfos = userinfos;
		this.consignees = consignees;
		this.evaluates = evaluates;
		this.orders = orders;
		this.messages = messages;
		this.shoppingcarts = shoppingcarts;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Short getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Short userRole) {
		this.userRole = userRole;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public Short getUserState() {
		return this.userState;
	}

	public void setUserState(Short userState) {
		this.userState = userState;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getFavoriteses() {
		return this.favoriteses;
	}

	public void setFavoriteses(Set favoriteses) {
		this.favoriteses = favoriteses;
	}

	public Set getUserinfos() {
		return this.userinfos;
	}

	public void setUserinfos(Set userinfos) {
		this.userinfos = userinfos;
	}

	public Set getConsignees() {
		return this.consignees;
	}

	public void setConsignees(Set consignees) {
		this.consignees = consignees;
	}

	public Set getEvaluates() {
		return this.evaluates;
	}

	public void setEvaluates(Set evaluates) {
		this.evaluates = evaluates;
	}

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

	public Set getShoppingcarts() {
		return this.shoppingcarts;
	}

	public void setShoppingcarts(Set shoppingcarts) {
		this.shoppingcarts = shoppingcarts;
	}

}
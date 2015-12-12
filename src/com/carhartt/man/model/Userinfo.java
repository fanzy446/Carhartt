package com.carhartt.man.model;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

public class Userinfo implements java.io.Serializable {

	// Fields

	private Integer userInfoId;
	private User user;
	private String passwHint;
	private String passAnswer;
	private String realName;
	private String idCard;
	private String userPhone;
	private String userGender;
	private Integer userCredits;
	private Integer userId;

	// Constructors



	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(User user, String userGender, Integer userCredits) {
		this.user = user;
		this.userGender = userGender;
		this.userCredits = userCredits;
	}

	/** full constructor */
	public Userinfo(User user, String passwHint, String passAnswer,
			String realName, String idCard, String userPhone,
			String userGender, Integer userCredits) {
		this.user = user;
		this.passwHint = passwHint;
		this.passAnswer = passAnswer;
		this.realName = realName;
		this.idCard = idCard;
		this.userPhone = userPhone;
		this.userGender = userGender;
		this.userCredits = userCredits;
	}

	// Property accessors

	public Integer getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPasswHint() {
		return this.passwHint;
	}

	public void setPasswHint(String passwHint) {
		this.passwHint = passwHint;
	}

	public String getPassAnswer() {
		return this.passAnswer;
	}

	public void setPassAnswer(String passAnswer) {
		this.passAnswer = passAnswer;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Integer getUserCredits() {
		return this.userCredits;
	}

	public void setUserCredits(Integer userCredits) {
		this.userCredits = userCredits;
	}

}
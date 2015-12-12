package com.carhartt.man.model;
// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Advertisement entity. @author MyEclipse Persistence Tools
 */

public class Advertisement implements java.io.Serializable {

	// Fields

	private Integer advertisementId;
	private Timestamp startTime;
	private Timestamp endTime;
	private String adphoto;
	private String url;
	private Set adinadps = new HashSet(0);

	// Constructors

	/** default constructor */
	public Advertisement() {
	}

	/** full constructor */
	public Advertisement(Timestamp startTime, Timestamp endTime,
			String adphoto, String url, Set adinadps) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.adphoto = adphoto;
		this.url = url;
		this.adinadps = adinadps;
	}

	// Property accessors

	public Integer getAdvertisementId() {
		return this.advertisementId;
	}

	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getAdphoto() {
		return this.adphoto;
	}

	public void setAdphoto(String adphoto) {
		this.adphoto = adphoto;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getAdinadps() {
		return this.adinadps;
	}

	public void setAdinadps(Set adinadps) {
		this.adinadps = adinadps;
	}

}
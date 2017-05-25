package com.sendbooth.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_DETAILS_ID")
	private int userDetailsId;

	@Column(name = "USER_AWS_SECRET_KEY")
	private String userAwsSecretKey;
	
	@Column(name = "USER_AWS_SECRET_ID")
	private String userAwsSecretId;

	@OneToOne
	private MarketingPackage marketingPackge;

	@OneToOne
	private User user;

	public int getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(int userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public MarketingPackage getMarketingPackge() {
		return marketingPackge;
	}

	public void setMarketingPackge(MarketingPackage marketingPackge) {
		this.marketingPackge = marketingPackge;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserAwsSecretKey() {
		return userAwsSecretKey;
	}

	public void setUserAwsSecretKey(String userAwsSecretKey) {
		this.userAwsSecretKey = userAwsSecretKey;
	}

	public String getUserAwsSecretId() {
		return userAwsSecretId;
	}

	public void setUserAwsSecretId(String userAwsSecretId) {
		this.userAwsSecretId = userAwsSecretId;
	}
}

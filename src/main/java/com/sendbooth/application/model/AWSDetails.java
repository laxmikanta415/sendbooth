package com.sendbooth.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AWS_DETAILS")
public class AWSDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AWS_DETAILS_ID")
	private int awsDetailsId;
	
	@Column(name="AWS_SECRET_KEY")
	private String awsSecretKey;
	
	@Column(name="AWS_SECRET_ID")
	private String awsSecretId;
	
	@ManyToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAwsDetailsId() {
		return awsDetailsId;
	}

	public void setAwsDetailsId(int awsDetailsId) {
		this.awsDetailsId = awsDetailsId;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}

	public String getAwsSecretId() {
		return awsSecretId;
	}

	public void setAwsSecretId(String awsSecretId) {
		this.awsSecretId = awsSecretId;
	}
	
}

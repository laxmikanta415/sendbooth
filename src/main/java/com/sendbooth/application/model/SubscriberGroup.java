package com.sendbooth.application.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIBER_GROUP")
public class SubscriberGroup {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SUBSCRIBER_GROUP_ID")
	private int subscriberGroupId;
	
	@Column(name="SUBSCRIBER_GROUP_NAME")
	private String subscriberGroupName;
	
	@ManyToOne
	private User user;

	public int getSubscriberGroupId() {
		return subscriberGroupId;
	}

	public void setSubscriberGroupId(int subscriberGroupId) {
		this.subscriberGroupId = subscriberGroupId;
	}

	public String getSubscriberGroupName() {
		return subscriberGroupName;
	}

	public void setSubscriberGroupName(String subscriberGroupName) {
		this.subscriberGroupName = subscriberGroupName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}

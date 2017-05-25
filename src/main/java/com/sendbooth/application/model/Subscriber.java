package com.sendbooth.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIBER")
public class Subscriber {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SUBSCRIBER_ID")
	private int subscriberId;
	
	@Column(name="SUBSCRIBER_NAME")
	private String subscriberName;
	
	@Column(name="SUBSCRIBER_EMAIL")
	private String subscriberEmail;
	
	@ManyToOne
	private SubscriberGroup subscriberGroup;
	
	@ManyToOne
	private User user;

	public int getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(int subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public String getSubscriberEmail() {
		return subscriberEmail;
	}

	public void setSubscriberEmail(String subscriberEmail) {
		this.subscriberEmail = subscriberEmail;
	}

	public SubscriberGroup getSubscriberGroup() {
		return subscriberGroup;
	}

	public void setSubscriberGroup(SubscriberGroup subscriberGroup) {
		this.subscriberGroup = subscriberGroup;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

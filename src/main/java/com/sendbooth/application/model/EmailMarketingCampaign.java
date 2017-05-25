package com.sendbooth.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMAIL_MARKETING_CAMPAIGN")
public class EmailMarketingCampaign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMAIL_MARKETING_CAMPAIGN_ID")
	private int emailMarketingCampaignid;

	@Column(name = "EMAIL_MARKETING_CAMPAIGN_NAME")
	private String emailMarketingCampaignName;

	@OneToOne
	private SubscriberGroup subscriberGroup;

	@ManyToOne
	private User user;


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

	public int getEmailMarketingCampaignid() {
		return emailMarketingCampaignid;
	}

	public void setEmailMarketingCampaignid(int emailMarketingCampaignid) {
		this.emailMarketingCampaignid = emailMarketingCampaignid;
	}

	public String getEmailMarketingCampaignName() {
		return emailMarketingCampaignName;
	}

	public void setEmailMarketingCampaignName(String emailMarketingCampaignName) {
		this.emailMarketingCampaignName = emailMarketingCampaignName;
	}


}

package com.sendbooth.application.service;

import java.util.List;

import com.sendbooth.application.model.EmailMarketingCampaign;
import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.User;

public interface EmailMarketingCampaignService {

	public List<EmailMarketingCampaign> findEmailMarketingCampaignByUser(User user);

	void saveEmailMarketingCampaign(EmailMarketingCampaign emailMarketingCampaign);
	
	public EmailMarketingCampaign findEmailMarketingCampaignByEmailMarketingCampaignid(int emailMarketingCampaignId);
	
	public void startEmailMarketingCampaignService(List<Subscriber> subscriberList,User user);
}

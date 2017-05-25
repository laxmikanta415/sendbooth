package com.sendbooth.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sendbooth.application.model.EmailMarketingCampaign;
import com.sendbooth.application.model.User;

@Repository("emailMarketingCampaignRepository")
public interface EmailMarketingCampaignRepository extends JpaRepository<EmailMarketingCampaign, Long> {

	public List<EmailMarketingCampaign> findEmailMarketingCampaignByUser(User user);

	public EmailMarketingCampaign findEmailMarketingCampaignByEmailMarketingCampaignid(int emailMarketingCampaignId);
	 
}
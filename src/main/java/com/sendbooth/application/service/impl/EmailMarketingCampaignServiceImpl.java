package com.sendbooth.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.sendbooth.application.model.AWSDetails;
import com.sendbooth.application.model.EmailMarketingCampaign;
import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.User;
import com.sendbooth.application.repository.EmailMarketingCampaignRepository;
import com.sendbooth.application.service.AWSDetailsService;
import com.sendbooth.application.service.AmazonSESService;
import com.sendbooth.application.service.AmazonSESUserService;
import com.sendbooth.application.service.EmailMarketingCampaignService;

@Service("emailMarketingCampaignService")
public class EmailMarketingCampaignServiceImpl implements EmailMarketingCampaignService {
	@Autowired
	private EmailMarketingCampaignRepository emailMarketingCampaignRepository;
	@Autowired
	private AWSDetailsService awsDetailsService;
	@Autowired
	private AmazonSESUserService amazonSESUserService;

	@Override
	public List<EmailMarketingCampaign> findEmailMarketingCampaignByUser(User user) {
		List<EmailMarketingCampaign> emailMarketingCampaignList = emailMarketingCampaignRepository
				.findEmailMarketingCampaignByUser(user);
		return emailMarketingCampaignList;
	}

	@Override
	public void saveEmailMarketingCampaign(EmailMarketingCampaign emailMarketingCampaign) {
		try {

			emailMarketingCampaignRepository.save(emailMarketingCampaign);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public EmailMarketingCampaign findEmailMarketingCampaignByEmailMarketingCampaignid(int emailMarketingCampaignId) {
		EmailMarketingCampaign emailMarketingCampaign = emailMarketingCampaignRepository
				.findEmailMarketingCampaignByEmailMarketingCampaignid(emailMarketingCampaignId);
		return emailMarketingCampaign;
	}

	@Override
	public void startEmailMarketingCampaignService(List<Subscriber> subscriberList, User user) {
		AWSDetails awsDetails = awsDetailsService.findByUser(user);
		AWSCredentials credentials = new BasicAWSCredentials(awsDetails.getAwsSecretId(), awsDetails.getAwsSecretKey());
		
		for (Subscriber subscriber : subscriberList) {
			amazonSESUserService.sendMail(subscriber.getSubscriberEmail(),credentials);
		}

	}

}

package com.sendbooth.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesResult;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.sendbooth.application.service.AmazonSESUserService;

@Service("amazonSESUserService")
public class AmazonSESUserServiceImpl implements AmazonSESUserService {

	static final String FROM = "info@sendbooth.com";
	static final String TO = "hayssam.1988@gmail.com";
	static final String BODY = "This email was sent through Amazon SES by using the AWS SDK for Java.";
	static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";
	private static final String RETERN_PATH = "webdesignfreetutorials@gmail.com";
	// private static final String TO = "nobody@i88.ca";
	// private static final String FROM = "somebody@i88.ca";
	// private static final String BODY = "This is an email by java from Amazon
	// Simple Email Service!";
	// private static final String SUBJECT = "A trying mail from Amazon SES by
	// Java";


	public void sendMail(String toEmailId, AWSCredentials credentials) {

		Destination destination = new Destination().withToAddresses(new String[] { toEmailId });

		Content subject = new Content().withData(SUBJECT);

		Content textBody = new Content().withData(BODY);

		Body body = new Body().withText(textBody);

		Message message = new Message().withSubject(subject).withBody(body);

		SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
				.withMessage(message);

		try {
			System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
			ListVerifiedEmailAddressesResult list = client.listVerifiedEmailAddresses();
			
			Region REGION = Region.getRegion(Regions.US_EAST_1);

			client.setRegion(REGION);

			client.sendEmail(request);

			System.out.println("Email sent!");

		} catch (Exception ex) {

			System.out.println("The email was not sent.");

			System.out.println("Error message: " + ex.getMessage());

		}

	}
}

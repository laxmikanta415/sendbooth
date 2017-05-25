package com.sendbooth.application.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.sendbooth.application.service.AmazonSESService;

@Service("amazonSESService")
public class AmazonSESServiceImpl implements AmazonSESService {

	static final String FROM = "laxmikantanayak415@gmail.com";
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

	@Autowired
	private AWSCredentials awsCredentials;

	public void sendMail(String toEmailId) {

		Destination destination = new Destination().withToAddresses(new String[] { toEmailId });

		Content subject = new Content().withData(SUBJECT);

		Content textBody = new Content().withData(BODY);

		Body body = new Body().withText(textBody);

		Message message = new Message().withSubject(subject).withBody(body);

		SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
				.withMessage(message);

		try {
			System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(awsCredentials);

			Region REGION = Region.getRegion(Regions.US_WEST_2);

			client.setRegion(REGION);

			client.sendEmail(request);

			System.out.println("Email sent!");

		} catch (Exception ex) {

			System.out.println("The email was not sent.");

			System.out.println("Error message: " + ex.getMessage());

		}

	}

	public void sendVerificationMail(AmazonSimpleEmailService ses, String from, String to, String subject, String body)
			throws IOException {

		/*
		 * Before you can send email via Amazon SES, you need to verify that you
		 * own the email address from which you'll be sending email. This will
		 * trigger a verification email, which will contain a link that you can
		 * click on to complete the verification process.
		 */
		verifyEmailAddress(ses, from);

		// RETERN_PATH is optional

		SendEmailRequest sendEmailRequest = new SendEmailRequest().withSource(from).withReturnPath(RETERN_PATH);

		List<String> toAddresses = new ArrayList<String>();
		toAddresses.add(to);

		Destination destination = new Destination().withToAddresses(toAddresses);

		sendEmailRequest.setDestination(destination);

		com.amazonaws.services.simpleemail.model.Message message

				= new com.amazonaws.services.simpleemail.model.Message(
						new Content().withCharset("UTF-8").withData(subject + " i88.ca"),
						new Body(new Content().withCharset("UTF-8").withData(body)));
		sendEmailRequest.setMessage(message);

		ses.sendEmail(sendEmailRequest);

	}

	/**
	 * Sends a request to Amazon Simple Email Service to verify the specified
	 * email address. This triggers a verification email, which will contain a
	 * link that you can click on to complete the verification process.
	 *
	 * @param ses
	 *            The Amazon Simple Email Service client to use when making
	 *            requests to Amazon SES.
	 * @param address
	 *            The email address to verify.
	 */
	public void verifyEmailAddress(AmazonSimpleEmailService ses, String address) {

		String domain = address.substring(address.indexOf("@") + 1);

		List<String> lids = ses.listIdentities().getIdentities();

		if (lids.contains(address) || lids.contains(domain)) {
			return;
		}

		ses.verifyEmailAddress(new VerifyEmailAddressRequest().withEmailAddress(address));
		System.out.println("Please check the email address " + address + " to verify it");
		System.exit(0);
	}
}

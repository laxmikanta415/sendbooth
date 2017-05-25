package com.sendbooth.application.service;

import java.io.IOException;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;

public interface AmazonSESService {

	public void sendMail(String toEmailId);

	public void sendVerificationMail(AmazonSimpleEmailService ses, String from, String to, String subject, String body)
			throws IOException;

	public void verifyEmailAddress(AmazonSimpleEmailService ses, String address);
}

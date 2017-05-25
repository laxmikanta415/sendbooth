package com.sendbooth.application.service;

import com.amazonaws.auth.AWSCredentials;

public interface AmazonSESUserService {

	public void sendMail(String toEmailId,AWSCredentials credentials);

}

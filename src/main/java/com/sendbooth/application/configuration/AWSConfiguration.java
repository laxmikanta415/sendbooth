package com.sendbooth.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

@Configuration
public class AWSConfiguration {

	
	@Value("${aws.accessKeyId}")
	private String accessKeyId;
	
	@Value("${aws.secretKey}")
	private String secretKey;
	
	@Bean("awsCredentials")
	public AWSCredentials awsCredentials() {

		AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretKey);
		return credentials;

	}

}
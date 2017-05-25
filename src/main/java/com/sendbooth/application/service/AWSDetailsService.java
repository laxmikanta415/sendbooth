package com.sendbooth.application.service;

import com.sendbooth.application.model.AWSDetails;
import com.sendbooth.application.model.User;

public interface AWSDetailsService {
	public AWSDetails findByUser(User user);

	public void save(AWSDetails awsDetails);
}

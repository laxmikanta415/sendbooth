package com.sendbooth.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendbooth.application.model.AWSDetails;
import com.sendbooth.application.model.User;
import com.sendbooth.application.repository.AWSDetailsRepository;
import com.sendbooth.application.service.AWSDetailsService;

@Service("awsDetailsService")
public class AWSDetailsServiceImpl implements AWSDetailsService {

	@Autowired
	private AWSDetailsRepository awsDetailsRepository;

	@Override
	public AWSDetails findByUser(User user) {
		return awsDetailsRepository.findByUser(user);
	}

	@Override
	public void save(AWSDetails awsDetails) {
		try{
			awsDetailsRepository.save(awsDetails);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}

package com.sendbooth.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sendbooth.application.model.AWSDetails;
import com.sendbooth.application.model.User;

@Repository("awsDetailsRepository")
public interface AWSDetailsRepository extends JpaRepository<AWSDetails, Long> {
	 public AWSDetails findByUser(User user);
}
package com.sendbooth.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sendbooth.application.model.UserDetails;

@Repository("userDetailsRepository")
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	
}
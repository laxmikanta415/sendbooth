package com.sendbooth.application.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sendbooth.application.model.MarketingPackage;
import com.sendbooth.application.model.Role;
import com.sendbooth.application.model.Subscriber;
import com.sendbooth.application.model.SubscriberGroup;
import com.sendbooth.application.model.User;
import com.sendbooth.application.model.UserDetails;
import com.sendbooth.application.repository.MarketingPackageRepository;
import com.sendbooth.application.repository.RoleRepository;
import com.sendbooth.application.repository.SubscriberGroupRepository;
import com.sendbooth.application.repository.SubscriberRepository;
import com.sendbooth.application.repository.UserDetailsRepository;
import com.sendbooth.application.repository.UserRepository;
import com.sendbooth.application.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setActive(1);
			Role userRole = roleRepository.findByRole("ADMIN");
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			userRepository.save(user);
			UserDetails userDetails = new UserDetails();
			userDetails.setUser(user);
			userDetailsRepository.save(userDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

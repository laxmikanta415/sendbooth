package com.sendbooth.application.service;

import com.sendbooth.application.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}

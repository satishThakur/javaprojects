package com.satish.service;

import com.satish.domain.User;

public interface UserMgmtService {
	
	public User createUser(User user);
	
	public User changeFirstName(long userId, String firstname);
	
	public User changeLastName(long userId, String lastName);
	
	public void logAllUsers();
	
	public void logUser(long id);
	
	public void deleteAll();

}

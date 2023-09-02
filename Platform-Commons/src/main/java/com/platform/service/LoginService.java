package com.platform.service;

import com.platform.exception.LoginException;
import com.platform.model.UserLogin;

public interface LoginService {

    public String login (UserLogin userLogin) throws LoginException;
	
	public String logout (String Key) throws LoginException;

}

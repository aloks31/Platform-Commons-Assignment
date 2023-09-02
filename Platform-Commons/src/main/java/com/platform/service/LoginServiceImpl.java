package com.platform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.exception.LoginException;
import com.platform.model.Admin;
import com.platform.model.CurrentUserSession;
import com.platform.model.UserLogin;
import com.platform.repository.AdminRepo;
import com.platform.repository.CurrentSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CurrentSessionRepo currentSessionRepo;


		@Override
	public String login(UserLogin userLogin) throws LoginException {

        List<Admin> admin = adminRepo.findAdminByMobile(userLogin.getMobileNumber());

		Admin existing = admin.get(0);

		if(existing == null) {
			throw new LoginException("Invalid MobileNumber!");
		}


		Optional<CurrentUserSession> optional =  currentSessionRepo.findByUserId(existing.getId());
		if(optional.isPresent()) {
			throw new LoginException("User Already Exists in the System.");
		}
		
		if(existing.getPassword().equals(userLogin.getPassword())) {
			
			String key= RandomString.make(6);
			
			CurrentUserSession currentUserSession = new CurrentUserSession(existing.getId(),key,LocalDateTime.now());
			
			currentSessionRepo.save(currentUserSession);

			return currentUserSession.toString();
		}

		throw new LoginException("Wrong password");
		
	}

	
	@Override
	public String logout(String key) throws LoginException {

        CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LoginException("Invalid Unique userId (Session Key).");
			
		}
		
		currentSessionRepo.delete(currentUserSession);
		
		return "Logged Out Successfully!";
	}

}

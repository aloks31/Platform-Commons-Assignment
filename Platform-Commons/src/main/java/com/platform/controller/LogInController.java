package com.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.exception.LoginException;
import com.platform.model.UserLogin;
import com.platform.service.LoginService;

@RestController
@RequestMapping("/admin")
public class LogInController {

	@Autowired
	private LoginService loginService;


	@PostMapping("/login")
	public ResponseEntity<String> loginMapping(@RequestBody UserLogin userLogin) throws LoginException {
		
		String output = loginService.login(userLogin);
		
		return new ResponseEntity<String>(output,HttpStatus.OK);
	}


	@PostMapping("/logout")
	public ResponseEntity<String> logoutMapping(@RequestParam String key) throws LoginException{
		
		String output = loginService.logout(key);
		
		return new ResponseEntity<String>(output,HttpStatus.OK);
	}
}
package com.quizpro.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizpro.dao.UserDAO;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	MailService mailService;
	
	public boolean verifyUser(String userId, String password) {
		return userDAO.verifyUser(userId, password);
	}
	
	public String generateOTP() {
		return String.valueOf(new Random().nextInt(1000000));
	}
	
	public void updateOTP(String userId, String otp) {
		userDAO.updateOTP(userId, otp);
	}
	
	public boolean verifyOTP(String userId, String otp) {
		return userDAO.verifyOTP(userId, otp);
	}
	
	public String getEmailByUserIdOrUsername(String userId) {
		return userDAO.getEmailByUserIdOrUsername(userId);
	}
	
	public void sendEmail(String to, String otp) {
		String from = "netproductionsinc888@gmail.com";
		String subject = "QuizPro OTP Code";
		String body = "Your QuizPro OTP Code is "+otp+" Thank You";
		mailService.sendMail(from, to, subject, body);
	}
	
	public boolean updatePassword(String userIdOrUsername, String newPassword) {
	    return userDAO.updatePassword(userIdOrUsername, newPassword);
	}

	public String getUsernameByUserId(Long userId) {
		return userDAO.findUsernameByUserId(userId);
	}
	
	public String getRoleByUserIdOrUsername(String userIdOrUsername) {
	    return userDAO.getRoleByUserIdOrUsername(userIdOrUsername);
	}
}

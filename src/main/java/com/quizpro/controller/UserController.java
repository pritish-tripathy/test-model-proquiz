package com.quizpro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.quizpro.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String getLoginPage() {
		return "LoginPage";
	}
	
	@GetMapping("/forgotPassword")
	public String showResetPasswordForm() {
	    return "ResetPassword";
	}
	
	@GetMapping("/backLogin")
	public String backToLoginPage() {
		return "LoginPage";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "LoginPage";
	}

	@PostMapping("/verifyUser")
	public String verifyUser(HttpServletRequest request, HttpSession session) {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		if (userService.verifyUser(userId, password)) {
			String otp = userService.generateOTP();
			userService.updateOTP(userId, otp);
			String email = userService.getEmailByUserIdOrUsername(userId);
			userService.sendEmail(email, otp);
			session.setAttribute("userId", userId);
			return "SubmitOTP";
		} else {
			request.setAttribute("errorMsg", "Invalid userId or Password");
			return "LoginPage";
		}
	}

	@PostMapping("/verifyOTP")
	public String verifyOTP(HttpServletRequest request, HttpSession session) {
	    String otp = request.getParameter("otp");
	    String userIdStr = (String) session.getAttribute("userId");
	    if (userService.verifyOTP(userIdStr, otp)) {
	        Long userIdLong = Long.parseLong(userIdStr);
	        String username = userService.getUsernameByUserId(userIdLong);
	        session.setAttribute("username", username);

	        // NEW: store role for conditional routing
	        String role = userService.getRoleByUserIdOrUsername(userIdStr);
	        session.setAttribute("role", role);
	        session.setAttribute("userIdLong", userIdLong);

	        if ("student".equalsIgnoreCase(role)) {
	            return "redirect:/student/dashboard";
	        }
	        // default (admin/teacher) goes to existing HomePage
	        return "HomePage";
	    } else {
	        request.setAttribute("error", "Invalid OTP");
	        return "SubmitOTP";
	    }
	}
	
	@PostMapping("/resetPassword")
	public String resetPassword(HttpServletRequest request) {
	    String userIdOrUsername = request.getParameter("userIdOrUsername");
	    String newPassword = request.getParameter("newPassword");

	    boolean updated = userService.updatePassword(userIdOrUsername, newPassword);
	    if (updated) {
	        request.setAttribute("msg", "Password successfully updated.");
	    } else {
	        request.setAttribute("errorMsg", "Failed to update password. User not found.");
	    }
	    return "ResetPassword";
	}
}

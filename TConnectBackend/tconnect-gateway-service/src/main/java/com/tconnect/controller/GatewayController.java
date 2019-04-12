package com.tconnect.controller;

import java.io.BufferedReader;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tconnect.config.EmailCofigs;
import com.tconnect.config.GatewayConsts;
import com.tconnect.entity.EmailDispacher;
import com.tconnect.entity.ResetPassword;
import com.tconnect.entity.TestEntity;
import com.tconnect.entity.UserEntity;
import com.tconnect.entity.UserStatus;
import com.tconnect.security.JwtTokenProvider;
import com.tconnect.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class GatewayController {

	Logger logger = LoggerFactory.getLogger(GatewayController.class);

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/login", "/logout" }, method = RequestMethod.GET)
	public UserStatus loginPageGet(HttpServletRequest req, Principal user,
			@RequestParam(value = "logout", required = false) String logout) {
		if (req.getRequestURI().equals("/login")) {
			logger.info(SecurityContextHolder.getContext().getAuthentication().getName() + " logged out");
		}

		if (logout != null) {
			return new UserStatus(GatewayConsts.STATUS_LOGGED_OUT, null, null);
		}

		try {
			Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities();
			String role = authorities.toArray()[0].toString();
			logger.info(user.getName() + " logged in");
			return new UserStatus(GatewayConsts.STATUS_LOGGED_IN, role,
					jwtTokenProvider.CreateToken(user.getName(), authorities));
		} catch (Exception e) {
			if (user == null)
				logger.info("login attempt fail");
			else
				logger.info(user.getName() + " login fail");
			return new UserStatus(GatewayConsts.STATUS_FAILED, null, null);
		}
	}

	@RequestMapping(value = "/isAuthorized", method = RequestMethod.GET)
	public UserStatus isUserAuthorized(Principal user) {
		return new UserStatus(GatewayConsts.STATUS_LOGGED_IN, null, null);
	}

	@RequestMapping(value = "/password/resetLink/generate", method = RequestMethod.POST)
	public UserStatus passwordRestLink(@RequestBody String email) throws MessagingException {
		UserEntity user = userService.findByEmail(email);

		if (user == null) {
			return new UserStatus(GatewayConsts.STATUS_EMAIL_NOT_REGISTERED, null, null);
		}

		ResetPassword rp = null;

		while (true) {
			rp = ResetPassword.buildToken();

			UserEntity userEntity = userService.findByToken(rp.getToken());
			if (userEntity == null) {
				break;
			}
		}

		user.setResetPassword(rp);

		userService.saveUser(user);

		EmailDispacher ed = new EmailDispacher(GatewayConsts.MAIL_ADDRESS, user.getEmail(), "password reset",
				EmailCofigs.passwordResetEmail(user.getEmail(), rp.getToken()));
		boolean status = ed.send();

		if (status) {
			logger.info("Password reset email sent to " + email + " with token = " + rp.getToken());
			return new UserStatus(GatewayConsts.STATUS_PASSWORD_RESET_EMAIL_SENT_SUCCESS, null, rp.getToken());
		} else {
			return new UserStatus(GatewayConsts.STATUS_PASSWORD_RESET_EMAIL_SENT_FAILURE, null, rp.getToken());
		}
	}

	@RequestMapping(value = "/password/change", method = RequestMethod.POST)
	public UserStatus loginPageGet1(HttpServletRequest req) throws JSONException {
		JSONObject jsonObject = readRequestBody(req);

		String token = jsonObject.getString("token");

		UserEntity user = userService.findByToken(token);

		if (user == null)
			return new UserStatus(GatewayConsts.STATUS_INVALID_TOKEN, null, null);

		if (System.currentTimeMillis()
				- user.getResetPassword().getTime() > GatewayConsts.MAX_RESET_PASSWORD_LINK_ACTIVE_TIME) {
			logger.info("Password reset link expired for " + user.getEmail());
			return new UserStatus(GatewayConsts.STATUS_LINK_EXPIRED, null, null);
		}

		String password = jsonObject.getString("password");

		if (!Pattern.matches(GatewayConsts.PATTERN_PASSWORD, password)) {
			return new UserStatus(GatewayConsts.STATUS_PASSWORD_NOT_VALID, null, null);
		}

		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setResetPassword(null);

		userService.saveUser(user);

		logger.info("Password changed successfully for " + user.getEmail() + " .");

		return new UserStatus(GatewayConsts.STATUS_PASSWORD_RESET_SUCCESSFUL, null, null);
	}

	public JSONObject readRequestBody(HttpServletRequest req) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
		}

		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jb.toString());
		} catch (JSONException e) {
			logger.warn("Error parsing JSON request string");
		}

		return jsonObject;
	}
	
	
	@RequestMapping("/testdb")
	public TestEntity dbconnection(HttpServletRequest req) {
		
		return userService.testdb(req.getParameter("n"), req.getParameter("o"));
	}
}

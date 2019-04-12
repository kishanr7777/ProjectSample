package com.tconnect.entity;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.tconnect.config.EmailCofigs;

public class EmailDispacher {
	private String sender;
	private String receiver;
	private String subject; 	
	private String body;

	JavaMailSender javaMailSender = EmailCofigs.buildJavaMailSender();

	Logger logger = LoggerFactory.getLogger(EmailDispacher.class);
	
	public EmailDispacher(String sender, String receiver, String subject, String body) {
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.body = body;
	}

	public boolean send() throws MessagingException {

		MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
		
		messageHelper.setFrom(sender);
		messageHelper.setTo(receiver);
		messageHelper.setSubject(subject);
		messageHelper.setText(body, true);

		try {
			javaMailSender.send(messageHelper.getMimeMessage());
		} catch (Exception e) {
			logger.warn("Reset Password link can't be sent to " + receiver + " reason is - " + e.getMessage());
			e.printStackTrace();
			return false;
		}

		logger.info("Reset Password link is sent to " + receiver);
		return true;

	}
}
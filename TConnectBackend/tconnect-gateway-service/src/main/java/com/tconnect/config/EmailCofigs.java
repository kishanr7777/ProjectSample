package com.tconnect.config;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailCofigs {

	public static JavaMailSenderImpl buildJavaMailSender() {
		JavaMailSenderImpl jmail = new JavaMailSenderImpl();

		jmail.setHost(GatewayConsts.HOST_SMTP);
		jmail.setPort(GatewayConsts.PORT_SMTP);
		jmail.setUsername(GatewayConsts.MAIL_ADDRESS);
		jmail.setPassword(GatewayConsts.MAIL_PASSWORD);
		jmail.setProtocol("smtp");
		
		return jmail;
	}

	public static String passwordResetEmail(String user, String token) {
		StringBuilder s = new StringBuilder();
		
		StringBuilder http = new StringBuilder();
		http.append("http://").append(GatewayConsts.ANGULAR_ADDRESS)
		 .append("/newPassword?token=").append(token);
		
		s.append("<html><head><style>")
			 .append("body{ font-family: 'arial'; margin: 0px;}")
			 .append(".container{ margin:5% 15%;}")
			 .append(".msg-container{ max-width: 500px; margin: auto; }")
			 .append(".container .msg-header{ font-size: 20px; margin-bottom: 20px;}")
			 .append(".container .msg-body{ margin-bottom: 20px; text-align: justify;}")
			 .append(".container .link-container{ text-align: center; margin-bottom: 20px;}")
			 .append(".container .link-container button{font-size: 18px; font-family: 'roboto'; ")
			 .append("width: 200PX; height: 50px; background: #032f8a; border: none; ")
			 .append("border-radius: 5px; color: #ffffff;}")
			 .append(".container .link-container button:hover{ background: #001642; }")
			 .append(".container .link-container a{ color: #4040bd; }")
			 .append(".container .link-container a:visited{ color: #4040bd; }")
			 .append(".container .link-container a:hover{ text-decoration: underline; color:blue; }")
			 .append(".container .msg-footer{ margin-bottom: 20px; font-size: 17px;}")
			 .append("</style></head><body>")
			 .append("<div class=\"container\">")
			 	.append("<div class=\"msg-container\">")
			 		.append("<div class=\"msg-header\">")
			 			.append("Hi ")
			 			.append("<span id=\"username\">").append(user).append(",</span>")
			 		.append("</div>")
			 		.append("<div class=\"msg-body\">")
			 			.append("Here is the link to reset your password of your tConnect account. ")
			 			.append("Click on below button to reset your password of your tConnect account.")
			 		.append("</div>")
			 		.append("<div class=\"link-container\">")
			 			.append("<a href=\"").append(http).append("\">")
			 				.append("<button>Reset Password</button>")
			 			.append("</a>")
			 		.append("</div>")
			 		.append("<div class=\"msg-body\">Or copy and paster link below in your browser to change you password. </div>")
			 		.append("<div class=\"link-container\">")
			 			.append("<a href=\"").append(http).append("\">").append(http).append("</a>")
			 		.append("</div>")
			 		.append("<div class=\"msg-body\">Note that below link will be expire after 10 minutes.</div>")
			 		.append("<div class=\"msg-footer\">")
			 			.append("Have a great day.")
			 		.append("</div>")
			 		.append("<div class=\"msg-footer\">")
			 			.append("Regards, tConnect.")
			 		.append("</div></div></div></body></html>");
		
		return s.toString();
	}
}

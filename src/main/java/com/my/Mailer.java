package com.my;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailer {

	public static void send(String to,String sub,String msg){
		String from="TrainTicket1200@gmail.com";
		String password="krfmliczulpjddkh";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		//props.put("mail.smtp.socketFactory.port", "465");
		//props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");//!
		props.put("mail.smtp.port", "587");//465


		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from,password);
					}
				});

		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			//send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {throw new RuntimeException(e);}

	}
}

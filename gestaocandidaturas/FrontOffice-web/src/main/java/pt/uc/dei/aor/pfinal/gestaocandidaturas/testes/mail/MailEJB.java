/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.pfinal.gestaocandidaturas.testes.mail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Bruno Costa
 * @author Pedro Pamplona
 */

public class MailEJB {
	public MailEJB() {
		sendEmailRemember("nuno40@hotmail.com");
	}

	public static void main(String[] args) {
		new MailEJB();
	}

	/**
	 * this method sends an email to destinationEmail and remembers the owner of
	 * the destinationEmail to self-evaluate the project given in the parameters
	 *
	 * @param destinationEmail
	 * @param p
	 */
	public void sendEmailRemember(String destinationEmail) {
		// OUR EMAIL SETTINGS
		String host = "smtp.gmail.com";// Gmail
		int port = 465;
		final String serviceUsername = "nunofrmoreira@gmail.com";
		final String servicePassword = "ArcaMlam26";// Our Gmail password

		Properties props = System.getProperties();
		props.put("mail.smtp.user", serviceUsername);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		// Destination of the email
		String from = serviceUsername;

		// Creating a javax.Session with the our properties
		Session session;
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(serviceUsername,
						servicePassword);
			}
		});

		try {

			Message message = new MimeMessage(session);
			// From: is our service
			message.setFrom(new InternetAddress(from));
			// To: destination given

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinationEmail));
			message.setSubject("Aviso");
			// Instead of simple text, a .html template should be added here!
			GregorianCalendar gc = new GregorianCalendar();

			gc.add(Calendar.DAY_OF_MONTH, 1);
			String data = new SimpleDateFormat().format(gc.getTime());

			message.setText("Este email é para lembrar que ainda não avaliou o projecto "

					+ ".\nA availiação a este projecto está aberta "
					+ "até à data " + data);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, serviceUsername, servicePassword);
			Transport.send(message, message.getAllRecipients());
			transport.close();
			System.out.println("envio OK!!!");

		} catch (MessagingException e) {
			System.out.println("Erro de envio");
			throw new RuntimeException(e);
		}

	}

}

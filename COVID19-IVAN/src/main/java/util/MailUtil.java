package util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * Classe encargarda de mandar el email de validacion.
 * @author @kalua66
 *
 */
public class MailUtil 
{
	/**
	 * Metodo para enviar un mail de validacion
	 * @param toEmail El email
	 * @param subject El asunto
	 * @param body El mensaje
	 */
	public static void sendEmail(String toEmail, String subject, String body)
	{
		try
	    {
			System.out.println("SSLEmail Start");
			
			Properties props = new Properties();
			
			props.put("mail.smtp.host", "mail.kalua66.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			Authenticator auth = new Authenticator() 
			{
				private String fromEmail = "test@kalua66.com";
				private String token = "T$eGm7p00C00orqaÑl12Y34y56"; 
				private final String EMPTY = ""; 
				protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(fromEmail, token.replaceAll("[G$70CxYyÑqqS89]", EMPTY));}
			};
			
			Session session = Session.getDefaultInstance(props, auth);
			
			MimeMessage msg = new MimeMessage(session);

			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
				
			msg.setFrom(new InternetAddress("test@kalua66.com", "## covidstats ##"));
			msg.setSubject(subject, "UTF-8");
			msg.setContent(body, "text/html; charset=utf-8");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			Transport.send(msg);  

			System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) 
		{
	    	e.printStackTrace();
	    	System.err.println("EMail NOT Sent:"+e.getMessage());
	    }
	}
}
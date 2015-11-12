package emailclient;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.util.*;

/**
 * This class is used as the backend for the actual sending of an email
 * @author Tom Nicklin
 *
 */
public class SendMailSMTP 
{
	
	public static void run() 
	{
		String username = Main.username;
		String password = "";
		String smtphost = "smtp.gmail.com";
		

		// Set all Properties
		// Get system properties
		Properties props = System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", smtphost);
		props.put("mail.smtp.port", "587");

		// Input password
		JPasswordField pwd = new JPasswordField(10);  
		int action = JOptionPane.showConfirmDialog(null, pwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);  
		if(action < 0) 
		{
			JOptionPane.showMessageDialog(null,"Cancel, X or escape key selected"); 
			System.exit(0); 
		}
		else 
			password = new String(pwd.getPassword());  

		// Set Property with username and password for authentication  
		props.setProperty("mail.user", username);
		props.setProperty("mail.password", password);

		//Establish a mail session
		Session session = Session.getDefaultInstance(props);

		try 
		{

			// Create a message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(Compose.to));
			message.setSubject(Compose.subject);
			message.setText(Compose.body);
			//If there are no CC's then skip it. This if seemed to decrease send time.
			if(Compose.cc != null)
			{
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(Compose.cc));
				message.saveChanges();
			}
			else
				message.saveChanges();
			
			/*
			 * For adding the attached file to the email. This time the if
			 * statement is used to stop the email attachment process if there
			 * is none. Other wise due to the way I've set it up it'll try to
			 * send file path and file name as null, and we fail an otherwise valid email.
			 */
			
			if(Compose.filename != null)
			{
				String file = Compose.filepath;
				System.out.println(file);
		        String fileName = Compose.filename;
		        System.out.println(fileName);
				
			    Multipart multipart = new MimeMultipart();
			 	BodyPart messageBodyPart = new MimeBodyPart();
			 	DataSource source = new FileDataSource(file);
			    messageBodyPart.setDataHandler(new DataHandler(source));
			 	messageBodyPart.setFileName(fileName);
			 	multipart.addBodyPart(messageBodyPart);
			 	
			 	BodyPart messageBodyPart2 = new MimeBodyPart();
			 	messageBodyPart2.setText(Compose.body);
			 	multipart.addBodyPart(messageBodyPart2);
			 	
			 	message.setContent(multipart);
			}
			else
			{
				message.setText(Compose.body);
				message.saveChanges();
			}
			
			//Send the message by javax.mail.Transport .			
			Transport tr = session.getTransport("smtp");			// Get Transport object from session		
			tr.connect(smtphost, username, password); 				// We need to connect
			tr.sendMessage(message, message.getAllRecipients()); 	// Send message

			//Notify the user everything functioned fine.
			JOptionPane.showMessageDialog(null, "Your mail has been sent.");

		} 
		catch (MessagingException e) 
		{
			//Notify the user that something isn't right, normally an incorrect email address.
			JOptionPane.showMessageDialog(null, "Something went wrong there, verify your information.");
			throw new RuntimeException(e);
		}
	}


}

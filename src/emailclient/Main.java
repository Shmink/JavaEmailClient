package emailclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.mail.Multipart;

import com.sun.mail.imap.IMAPFolder;

public class Main 
{
	
	public Main() {}
	
	public int messageCount;
	public static String username = "mrfakeemail22@gmail.com";
	
	public void run() throws MessagingException, IOException 
	{
		// TODO Auto-generated method stub
		IMAPFolder folder = null;
		Store store = null;


		//username = "mrfakeemail22@gmail.com";
		String password = "";

		// Step 1.1:  set mail user properties using Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		// Get user password using JPasswordField
		JPasswordField pwd = new JPasswordField(10);
		int action = JOptionPane.showConfirmDialog(null, pwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
		if(action < 0) {
			JOptionPane.showMessageDialog(null,"Cancel, X or escape key selected");
			System.exit(0);
		}
		else
			password = new String(pwd.getPassword());

		// Set Property with username and password for authentication
		props.setProperty("mail.user", username);
		props.setProperty("mail.password", password);

		//Step 1.2: Establish a mail session (java.mail.Session)
		Session session = Session.getDefaultInstance(props);

		try
		{
			// Step 2: Get the Store object from the mail session
			// A store needs to connect to the IMAP server
			store = session.getStore("imaps");
			store.connect("imap.googlemail.com",username, password);

			// Step 3: Choose a folder, in this case, we chose inbox
			folder = (IMAPFolder) store.getFolder("inbox");

			// Step 4: Open the folder
			if(!folder.isOpen())
				folder.open(Folder.READ_WRITE);

			// Step 5: Get messages from the folder
			// Get total number of message
			messageCount = folder.getMessageCount();
			System.out.println("No of Messages : " + folder.getMessageCount());
			// Get total number of unread message
			System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());

			int count = 0;
			Message messages[] = folder.getMessages();

			// Get all messages
			for(Message message:messages) {
				count++;

				// Get subject of each message
				System.out.println("The " + count + "th message is: " + message.getSubject());
				setSubject(message.getSubject());
				setDate(message.getReceivedDate());
				setSenders(message.getFrom()[0].toString());
				//System.out.println(message.g1etContentType());
				if(message.getContentType().contains("TEXT/PLAIN")) {
					System.out.println(message.getContent());
				}
				else
				{
					// How to get parts from multiple body parts of MIME message
					Multipart multipart = (Multipart) message.getContent();
					System.out.println("-----------" + multipart.getCount() + "----------------");
					for (int x = 0; x < multipart.getCount(); x++) {
						BodyPart bodyPart = multipart.getBodyPart(x);
						// If the part is a plan text message, then print it out.
						if(bodyPart.getContentType().contains("TEXT/PLAIN"))
						{
							System.out.println(bodyPart.getContentType());
							System.out.println(bodyPart.getContent().toString());
						}

					}
				}

				Flags mes_flag = message.getFlags();
				System.out.println("Has this message been read?  " + mes_flag.contains(Flag.SEEN));
				setSeenOrNot(mes_flag.contains(Flag.SEEN));
			}




		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (folder != null && folder.isOpen()) { folder.close(true); }
			if (store != null) { store.close(); }
		}

	}
	
	
	public ArrayList<String> subjects = new ArrayList<String>(messageCount);
	public void setSubject(String subject)
	{
		subjects.add(subject);
	}
	public ArrayList<String> dates = new ArrayList<String>(messageCount);
	public void setDate(Date date)
	{
		dates.add(date.toString());
	}
	public ArrayList<String> senders = new ArrayList<String>(messageCount);
	public void setSenders(String addresses)
	{
		senders.add(addresses);	
	}
	public ArrayList<Boolean> seen = new ArrayList<Boolean>(messageCount);
	public void setSeenOrNot(Boolean seenOrNot)
	{
		seen.add(seenOrNot);
	}
}

package emailclient;

import java.awt.EventQueue;

import javax.mail.MessagingException;
import javax.swing.*;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The main GUI class, this is essentially the main menu.
 * We display the emails that are in the inbox and offer 
 * buttons for other functionality in the client.
 * @author Tom Nicklin
 *
 */
public class GUI 
{

	private JFrame frame;
	private JTable table;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					GUI window = new GUI();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Call the main class so it runs and we have everything
	 * we need from there. List of emails from inbox.
	 */
	public Main main;
	public GUI() throws MessagingException, IOException 
	{
		main = new Main();
		main.run();
		initialize();
	}
	
	/**
	 * Set up the GUI.
	 */
	private void initialize() 
	{
		frame = new JFrame("Email client");
		frame.setBounds(100, 100, 552, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Make the UI look like that of Windows, Java default is ugly.
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		SwingUtilities.updateComponentTreeUI(frame);
		
		//Inbox button, made to refresh the emails and reload if new ones came in.
		JButton btnInbox = new JButton("Inbox");
		btnInbox.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnInbox.setBounds(25, 43, 89, 23);
		frame.getContentPane().add(btnInbox);
		
		//Compose button, opens the compose an email UI
		JButton btnComposse = new JButton("Compose");
		btnComposse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Compose.run();
			}
		});
		btnComposse.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnComposse.setBounds(160, 43, 89, 23);
		frame.getContentPane().add(btnComposse);
		
		//Junk button, made to show your emails in the junk folder
		JButton btnJunk = new JButton("Junk");
		btnJunk.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnJunk.setBounds(289, 43, 89, 23);
		frame.getContentPane().add(btnJunk);
		
		//Used to show the emails that you have sent
		JButton btnSent = new JButton("Sent");
		btnSent.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnSent.setBounds(420, 43, 89, 23);
		frame.getContentPane().add(btnSent);
		
		/*
		 * Displays the name of the user at the top of UI,
		 * was planning on having another dialog box to enter email
		 * address so it would work with every gmail account that 
		 * doesn't have 2-step verification.
		 */
		JLabel lblEmails = new JLabel(Main.username + " emails");
		lblEmails.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblEmails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmails.setBounds(10, 11, 516, 14);
		frame.getContentPane().add(lblEmails);
		
		//Makes the inbox scrollable if there are to many emails to view.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 77, 484, 310);
		frame.getContentPane().add(scrollPane);
		
		//Main table where all the emails are viewed.
		table = new JTable();
		//If there are no emails to view then have a label in the middle, rather than looking like somethings broke.
		if(main.messageCount == 0)
		{
			JLabel label = new JLabel("No emails :(");
			label.setFont(new Font("Consolas", Font.PLAIN, 11));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(170, 200, 200, 100);
			frame.getContentPane().add(label);
		}
		else //If not then gather all the data, it gets stored in a 2D array based on what I want and where I want it.
		{
			String[] nameTheColoumns  = {"Sender", "Subject", "Date", "Read?"};
			
			String[][] data = new String[main.messageCount][nameTheColoumns.length];
			for(int i = 0; i < main.messageCount; i++)
			{
			    String subject = main.subjects.get(i);
			    String sender = main.senders.get(i);
			    String date = main.dates.get(i);
			    String read = main.seen.get(i).toString();
			    String[] details = {sender, subject, date, read}; 
			    
			    data[i] = details;
			}
			//Now we can fill the table with the data and the coloumn names.
			table.setModel(new DefaultTableModel(data,nameTheColoumns));
		}
		scrollPane.setViewportView(table);
		
	}
}

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

public class GUI {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public Main main;
	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	public GUI() throws MessagingException, IOException {
		
		
		main = new Main();
		main.run();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 
	 */
	private void initialize() {
		frame = new JFrame("Email client");
		frame.setBounds(100, 100, 552, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SwingUtilities.updateComponentTreeUI(frame);
		
		JButton btnInbox = new JButton("Inbox");
		btnInbox.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnInbox.setBounds(25, 43, 89, 23);
		frame.getContentPane().add(btnInbox);
		
		JButton btnComposse = new JButton("Compose");
		btnComposse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Compose.run();
			}
		});
		btnComposse.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnComposse.setBounds(160, 43, 89, 23);
		frame.getContentPane().add(btnComposse);
		
		JButton btnJunk = new JButton("Junk");
		btnJunk.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnJunk.setBounds(289, 43, 89, 23);
		frame.getContentPane().add(btnJunk);
		
		JButton btnArchive = new JButton("Archive");
		btnArchive.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnArchive.setBounds(420, 43, 89, 23);
		frame.getContentPane().add(btnArchive);
		
		JLabel lblEmails = new JLabel(Main.username + " emails");
		lblEmails.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblEmails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmails.setBounds(10, 11, 516, 14);
		frame.getContentPane().add(lblEmails);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 77, 484, 310);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		if(main.messageCount == 0)
		{
			JLabel label = new JLabel("No emails :(");
			
			label.setFont(new Font("Consolas", Font.PLAIN, 11));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(170, 200, 200, 100);
			frame.getContentPane().add(label);
		}
		else
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
			
			table.setModel(new DefaultTableModel(data,nameTheColoumns));
		}
		scrollPane.setViewportView(table);
		
	}
}

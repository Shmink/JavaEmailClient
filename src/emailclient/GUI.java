package emailclient;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;
import javax.swing.JTable;
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

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
		
		JButton btnSent = new JButton("Sent");
		btnSent.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnSent.setBounds(160, 43, 89, 23);
		frame.getContentPane().add(btnSent);
		
		JButton btnJunk = new JButton("Junk");
		btnJunk.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnJunk.setBounds(289, 43, 89, 23);
		frame.getContentPane().add(btnJunk);
		
		JButton btnArchive = new JButton("Archive");
		btnArchive.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnArchive.setBounds(420, 43, 89, 23);
		frame.getContentPane().add(btnArchive);
		
		//Main emailname = new Main();
		JLabel lblEmails = new JLabel(Main.username + " emails");
		lblEmails.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblEmails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmails.setBounds(10, 11, 516, 14);
		frame.getContentPane().add(lblEmails);
		
		table = new JTable();
		table.setBounds(25, 92, 484, 285);
		frame.getContentPane().add(table);
	}
}

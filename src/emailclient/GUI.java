package emailclient;

import java.awt.EventQueue;

import javax.mail.MessagingException;
import javax.swing.*;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

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
		
		JLabel lblEmails = new JLabel(main.username + " emails");
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
			/**
			 *  List<int[]> rowList = new ArrayList<int[]>();

			    rowList.add(new int[] { 1, 2, 3 });
			    rowList.add(new int[] { 4, 5, 6 });
			    rowList.add(new int[] { 7, 8 });
			
			    for (int[] row : rowList) {
			        System.out.println("Row = " + Arrays.toString(row));
			    } // prints:
			      // Row = [1, 2, 3]
			      // Row = [4, 5, 6]
			      // Row = [7, 8]
			
			    System.out.println(rowList.get(1)[1]); // prints "5"
			 */
			
			ArrayList<Object[][]> rows = new ArrayList<Object[][]>();
			
			for (int i = 0; i < main.messageCount; i++) 
			{
				rows.add(new Object[][] {null,null,null,null});
			}
			//Object[][] rows = {null,null,null,null};
			
			
			table.setModel(new DefaultTableModel(
				new Object[][] 
				{
					{main.senders.get(0), main.subjects.get(0), main.dates.get(0), main.seen.get(0).toString()},
				},
				
				new String[] {
					"Sender", "Subject", "Date", "Read?"
				}
			));	
		}
		scrollPane.setViewportView(table);
		
	}
}

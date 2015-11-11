package emailclient;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Compose {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compose window = new Compose();
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
	public Compose() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 552, 448);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblTo.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblTo);
		
		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblSubject.setBounds(10, 36, 56, 14);
		frame.getContentPane().add(lblSubject);
		
		textField = new JTextField();
		textField.setFont(new Font("Consolas", Font.PLAIN, 11));
		textField.setBounds(76, 8, 450, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Consolas", Font.PLAIN, 11));
		textField_1.setBounds(76, 33, 450, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 61, 516, 2);
		frame.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 516, 285);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Consolas", Font.PLAIN, 13));
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		
		JButton btnAddAttachement = new JButton("Add attachement");
		btnAddAttachement.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnAddAttachement.setBounds(10, 375, 123, 23);
		frame.getContentPane().add(btnAddAttachement);
		
		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnSend.setBounds(437, 374, 89, 23);
		frame.getContentPane().add(btnSend);
		
		JLabel lblattachmentNames = new JLabel("[attachment names]");
		lblattachmentNames.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblattachmentNames.setBounds(143, 378, 111, 14);
		frame.getContentPane().add(lblattachmentNames);
		
		
	}
}

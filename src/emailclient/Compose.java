package emailclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Compose {

	private JFrame frmCompose;
	private JTextField toTxt;
	private JTextField subjectTxt;
	private JTextField ccText;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compose window = new Compose();
					window.frmCompose.setVisible(true);
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
	public static String to,subject,cc,body;
	
	private void initialize() {
		frmCompose = new JFrame();
		frmCompose.setTitle("Compose");
		frmCompose.setBounds(100, 100, 552, 448);
		frmCompose.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCompose.getContentPane().setLayout(null);
		
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
		
		SwingUtilities.updateComponentTreeUI(frmCompose);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblTo.setBounds(10, 11, 46, 14);
		frmCompose.getContentPane().add(lblTo);
		
		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblSubject.setBounds(10, 36, 56, 14);
		frmCompose.getContentPane().add(lblSubject);
		
		toTxt = new JTextField();
		toTxt.setFont(new Font("Consolas", Font.PLAIN, 11));
		toTxt.setBounds(76, 8, 450, 20);
		frmCompose.getContentPane().add(toTxt);
		toTxt.setColumns(10);
		
		subjectTxt = new JTextField();
		subjectTxt.setFont(new Font("Consolas", Font.PLAIN, 11));
		subjectTxt.setBounds(76, 33, 450, 20);
		frmCompose.getContentPane().add(subjectTxt);
		subjectTxt.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 516, 2);
		frmCompose.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 104, 516, 255);
		frmCompose.getContentPane().add(scrollPane);
		
		JTextArea bodyTxt = new JTextArea();
		bodyTxt.setFont(new Font("Consolas", Font.PLAIN, 13));
		scrollPane.setViewportView(bodyTxt);
		bodyTxt.setWrapStyleWord(true);
		bodyTxt.setLineWrap(true);
		
		JButton btnAddAttachement = new JButton("Add attachement");
		btnAddAttachement.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnAddAttachement.setBounds(10, 375, 123, 23);
		frmCompose.getContentPane().add(btnAddAttachement);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				to = toTxt.getText();
				subject = subjectTxt.getText();
				body = bodyTxt.getText();
				cc = ccText.getText();
				SendMailSMTP.run();
			}
		});
		btnSend.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnSend.setBounds(437, 374, 89, 23);
		frmCompose.getContentPane().add(btnSend);
		
		JLabel lblattachmentNames = new JLabel("[attachment names]");
		lblattachmentNames.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblattachmentNames.setBounds(143, 378, 111, 14);
		frmCompose.getContentPane().add(lblattachmentNames);
		
		ccText = new JTextField();
		ccText.setFont(new Font("Consolas", Font.PLAIN, 11));
		ccText.setBounds(76, 60, 450, 20);
		frmCompose.getContentPane().add(ccText);
		ccText.setColumns(10);
		
		JLabel lblCc = new JLabel("CC:");
		lblCc.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblCc.setBounds(10, 66, 46, 14);
		frmCompose.getContentPane().add(lblCc);
		
		
	}
}

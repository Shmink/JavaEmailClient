package emailclient;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * GUI class for the compose a new email.
 * @author Tom Nicklin
 *
 */
public class Compose {

	private JFrame frmCompose;
	private JTextField toTxt;
	private JTextField subjectTxt;
	private JTextField ccText;

	/**
	 * Gotta love that Java boiler plate code.
	 */
	public static void run() 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Compose window = new Compose();
					window.frmCompose.setVisible(true);
				} 
				catch (Exception e) 
				{
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
	public static String to,subject,cc,body,filepath,filename;
	public static File actualFile;
	JLabel lblattachmentNames = new JLabel("");
	String path,name;
	File newFile;
	
	
	//Set up the GUI
	private void initialize() 
	{
		frmCompose = new JFrame();
		frmCompose.setTitle("Compose");
		frmCompose.setBounds(100, 100, 552, 448);
		frmCompose.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCompose.getContentPane().setLayout(null);
		
		//Again we want a UI that's not ugly as sin.
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
		
		SwingUtilities.updateComponentTreeUI(frmCompose);
		
		//Labels to show the relevant text field to fill in.
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
		
		//Seperator just to break it up a little.
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 516, 2);
		frmCompose.getContentPane().add(separator);
		
		/*
		 * Scroll pane to the text area so if your email is bordering on
		 * being an essay you needn't worry, you can scroll to your hearts content.
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 104, 516, 255);
		frmCompose.getContentPane().add(scrollPane);
		
		//Write the body of the email in this text area.
		JTextArea bodyTxt = new JTextArea();
		bodyTxt.setFont(new Font("Consolas", Font.PLAIN, 13));
		scrollPane.setViewportView(bodyTxt);
		bodyTxt.setWrapStyleWord(true);
		bodyTxt.setLineWrap(true);
		
		//Add attachment button calls a method lower down.
		JButton btnAddAttachement = new JButton("Add attachement");
		btnAddAttachement.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println(bodyTxt.getText());
				System.out.println(body);
				promptForFile();
			}
		});
		btnAddAttachement.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnAddAttachement.setBounds(10, 375, 123, 23);
		frmCompose.getContentPane().add(btnAddAttachement);
		
		//Send button gathers all the info from text field and then calls the send mail class
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				body = bodyTxt.getText();
				to = toTxt.getText();
				System.out.println("- " + to);
				subject = subjectTxt.getText();
				cc = ccText.getText();
				filepath = path;
				filename = name;
				actualFile = newFile;
				SendMailSMTP.run();
			}
		});
		btnSend.setFont(new Font("Consolas", Font.PLAIN, 11));
		btnSend.setBounds(437, 374, 89, 23);
		frmCompose.getContentPane().add(btnSend);
		
		
		lblattachmentNames.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblattachmentNames.setBounds(143, 378, 248, 14);
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
	
	/**
	 * promptForFile is called when you click the 'Add attachment' button.
	 * It opens up a standard JFileChooser and will then save the file name
	 * and file path in variables to be used later.
	 * @return String - File name
	 */
	
	public String promptForFile()
	{
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			newFile = fileChooser.getSelectedFile();
			System.out.print(newFile + "\n");
			path = fileChooser.getSelectedFile().getAbsolutePath();
			name = fileChooser.getSelectedFile().getName();
			lblattachmentNames.setText(name);
		  	return fileChooser.getSelectedFile().getName();
		}
		else
		{
			return "";
		}
	}
}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import javax.xml.bind.DatatypeConverter;

import com.sun.glass.ui.Application;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;;

public class Login {

	private JFrame frmLogin;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JTextArea txtErrorMessages;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmLogin = new JFrame();
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setVisible(true);
		
		JLabel lblUsername = new JLabel("Benutzername:");
		lblUsername.setBounds(63, 39, 113, 14);
		frmLogin.getContentPane().add(lblUsername);
		
		txtErrorMessages = new JTextArea();
		txtErrorMessages.setFont(new Font("Monospaced", Font.BOLD, 16));
		txtErrorMessages.setForeground(new Color(220, 20, 60));
		txtErrorMessages.setBackground(SystemColor.menu);
		txtErrorMessages.setBounds(63, 118, 278, 51);
		frmLogin.getContentPane().add(txtErrorMessages);
		
		JButton btnLogin = new JButton("Einloggen");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Einloggen();
		}});
		btnLogin.setBounds(63, 182, 115, 23);
		frmLogin.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("Beenden");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmLogin.dispose();
			}
		});
		btnCancel.setBounds(331, 218, 89, 23);
		frmLogin.getContentPane().add(btnCancel);
		
		JButton btnRegistration = new JButton("Regiestrieren");
		btnRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				Registrieren registration = new Registrieren();
			}
		});
		btnRegistration.setBounds(226, 182, 115, 23);
		frmLogin.getContentPane().add(btnRegistration);
		
		JLabel lblPassword = new JLabel("Passwort: ");
		lblPassword.setBounds(63, 81, 89, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					Einloggen();
				}
			}
		});
		txtUser.setBounds(181, 36, 160, 20);
		frmLogin.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					Einloggen();
				}
			}
			
			
		});
		txtPassword.setBounds(181, 77, 160, 22);
		frmLogin.getContentPane().add(txtPassword);
	
	
	
}
private void Einloggen()
{
	boolean error = false;
	//Create connection to database
	Connect connection = new Connect();
	
	//Get the input username and password
	String username = txtUser.getText();

	//Password is encrypted in the database. We have to encrypt input as well in order to compare
	MessageDigest digest;
	try {
		digest = MessageDigest.getInstance("SHA-256");
		String passwordInput= String.valueOf(txtPassword.getPassword());
		digest.update(passwordInput.getBytes());
		byte[] passwordArray = digest.digest();
		
		String test = new String(DatatypeConverter.printHexBinary(passwordArray).toLowerCase());
		
		//Check if user is valid
		PreparedStatement myStmt;
		try {
			myStmt = connection.getConnection().prepareStatement("SELECT * FROM tbluser WHERE nutzername=? AND passwort=?");
			myStmt.setString(1, username);
			myStmt.setString(2, test);
			ResultSet myRs = myStmt.executeQuery();
			if(myRs.next()==true) {
					System.out.println("Login erfolgreich.");
					// Creates new user object
					User user = new User(myRs.getInt(1), myRs.getString(2), myRs.getString(3), myRs.getString(4), myRs.getString(5), myRs.getString(6),myRs.getBoolean(7));
					// Opens the main window
					ZitatAnzeige zitateAnzeigen = new ZitatAnzeige(user);
					// Closes the login frame
					frmLogin.dispose();
			}
			else {
				txtErrorMessages.setText("Nutzerdaten waren falsch.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (NoSuchAlgorithmException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
}

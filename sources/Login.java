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

import com.sun.glass.ui.Application;;

public class Login {

	private JFrame frmLogin;
	private JTextField txtUser;
	private JPasswordField txtPassword;

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
		
		JLabel lbltest = new JLabel("Benutzername:");
		lbltest.setBounds(63, 39, 113, 14);
		frmLogin.getContentPane().add(lbltest);
		
		JButton btnLogin = new JButton("Einloggen");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
							System.out.println("Nutzerdaten waren falsch.");
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
		});
		btnLogin.setBounds(63, 163, 115, 23);
		frmLogin.getContentPane().add(btnLogin);
		
		JButton btnAbbrechen = new JButton("Beenden");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmLogin.dispose();
			}
		});
		btnAbbrechen.setBounds(335, 227, 89, 23);
		frmLogin.getContentPane().add(btnAbbrechen);
		
		JButton btnRegiestrieren = new JButton("Regiestrieren");
		btnRegiestrieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				Registrieren registration = new Registrieren();
			}
		});
		btnRegiestrieren.setBounds(226, 163, 115, 23);
		frmLogin.getContentPane().add(btnRegiestrieren);
		
		JLabel lblPasswort = new JLabel("Passwort: ");
		lblPasswort.setBounds(63, 81, 89, 14);
		frmLogin.getContentPane().add(lblPasswort);
		
		txtUser = new JTextField();
		txtUser.setBounds(181, 36, 160, 20);
		frmLogin.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(181, 77, 160, 22);
		frmLogin.getContentPane().add(txtPassword);
		
		
	}
}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbltest = new JLabel("Benutzername:");
		lbltest.setBounds(63, 39, 113, 14);
		frame.getContentPane().add(lbltest);
		
		JButton btnLogin = new JButton("Einloggen");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Create connection to database
				Connect connection = new Connect();
				
				//Get the input username and password
				String username = txtUser.getText();
				String password = txtPassword.getText();
				
				//Check if user is valid
				PreparedStatement myStmt;
				try {
					myStmt = connection.getConnection().prepareStatement("SELECT * FROM tbluser WHERE nutzername=? AND passwort=?");
					myStmt.setString(1, username);
					myStmt.setString(2, password);
					ResultSet myRs = myStmt.executeQuery();
					System.out.println("test");
					if(myRs.next()==true) {
							System.out.println("Login erfolgreich.");
							User user = new User(myRs.getInt(1), myRs.getString(2), myRs.getString(3));
					}
					else {
						System.out.println("Nutzerdaten waren falsch.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnLogin.setBounds(63, 163, 115, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnAbbrechen = new JButton("Beenden");
		btnAbbrechen.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnAbbrechen);
		
		JButton btnRegiestrieren = new JButton("Regiestrieren");
		btnRegiestrieren.setBounds(226, 163, 115, 23);
		frame.getContentPane().add(btnRegiestrieren);
		
		JLabel lblPasswort = new JLabel("Passwort: ");
		lblPasswort.setBounds(63, 81, 89, 14);
		frame.getContentPane().add(lblPasswort);
		
		txtUser = new JTextField();
		txtUser.setBounds(181, 36, 160, 20);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(181, 77, 160, 22);
		frame.getContentPane().add(txtPassword);
		
		
	}
}

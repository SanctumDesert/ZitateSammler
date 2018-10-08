import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Registrieren {

	private JFrame frame;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordRepeat;
	private JTextField txtUsername;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrieren window = new Registrieren();
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
	public Registrieren() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Connect conn = new Connect();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBenutzername = new JLabel("Benutzername:");
		lblBenutzername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBenutzername.setBounds(59, 53, 89, 14);
		frame.getContentPane().add(lblBenutzername);
		
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVorname.setBounds(59, 88, 89, 14);
		frame.getContentPane().add(lblVorname);
		
		JLabel lblNachname = new JLabel("Nachname:");
		lblNachname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNachname.setBounds(59, 121, 89, 14);
		frame.getContentPane().add(lblNachname);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setBounds(59, 152, 89, 14);
		frame.getContentPane().add(lblMail);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswort.setBounds(59, 197, 89, 14);
		frame.getContentPane().add(lblPasswort);
		
		JLabel lblPasswortWiederholen = new JLabel("Passwort wiederholen:");
		lblPasswortWiederholen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswortWiederholen.setBounds(17, 221, 131, 14);
		frame.getContentPane().add(lblPasswortWiederholen);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(156, 194, 148, 17);
		frame.getContentPane().add(txtPassword);
		
		txtPasswordRepeat = new JPasswordField();
		txtPasswordRepeat.setBounds(156, 219, 148, 17);
		frame.getContentPane().add(txtPasswordRepeat);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(156, 50, 148, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(156, 85, 148, 20);
		frame.getContentPane().add(txtFirstName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(156, 118, 148, 20);
		frame.getContentPane().add(txtLastName);
		
		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(156, 149, 148, 20);
		frame.getContentPane().add(txtMail);
		
		JButton btnRegister = new JButton("Registrieren");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean validInput = true;
				//Check if all fields are set.
				if(txtUsername.getText()==null) {
					System.out.println("Bitte gib einen Nutzernamen ein.");
					validInput = false;
				}
				else {
					PreparedStatement myStmt;
					try {
						myStmt = conn.getConnection().prepareStatement("SELECT * FROM tbluser WHERE nutzername=?");
						myStmt.setString(1, txtUsername.getText());
						ResultSet myRs = myStmt.executeQuery();
						if(myRs.next()) {
							System.out.println("Dieser Nutzername ist bereits vergeben.");
							validInput = false;
						}
						else {
							System.out.println("Dieser Nutzername ist frei.");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(txtFirstName.getText()==null) {
					System.out.println("Bitte gib einen Vornamen ein.");
					validInput = false;
				}
				if(txtLastName.getText()==null) {
					System.out.println("Bitte gib einen Nachnamen ein.");
					validInput = false;
				}
				if(txtMail.getText()==null) {
					System.out.println("Bitte gib eine Mailadresse ein.");
					validInput = false;
				}
				else {
					PreparedStatement myStmt;
					try {
						myStmt = conn.getConnection().prepareStatement("SELECT * FROM tbluser WHERE mail=?");
						myStmt.setString(1, txtMail.getText());
						ResultSet myRs = myStmt.executeQuery();
						if(myRs.next()) {
							System.out.println("Diese Mailadresse wird bereits verwendet.");
							validInput = false;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(txtPassword.getPassword()==null) {
					System.out.println("Bitte gib ein Passwort ein.");
					validInput = false;
				}
				if(txtPasswordRepeat.getPassword()==null) {
					System.out.println("Bitte wiederhole dein Passwort.");
					validInput = false;
				}
				if(!Arrays.equals(txtPassword.getPassword(),txtPasswordRepeat.getPassword())) {
					System.out.println("Die Passw�rter stimmen nicht �berein.");
					validInput = false;
				}
				if(txtPassword.getPassword().length<6) {
					System.out.println("Das Passwort muss mindestens 6 Zeichen lang sein.");
					validInput = false;
				}
				boolean uppercase=false;
				boolean lowercase=false;
				boolean special=false;
				char[] password = txtPassword.getPassword();
				for(int i=0; i < password.length; i++) {
					if(password[i]>='A' && password[i]<='Z') {
						uppercase=true;
					}
					if(password[i]>='a' && password[i]<='z') {
						lowercase=true;
					}
					if(password[i]=='�' || password[i]=='!' || password[i]=='�' || password[i]=='$' || password[i]=='%' || password[i]=='&') {
						special=true;
					}
					if(uppercase==true && lowercase==true && special == true) {
						break;
					}
				}
				if(uppercase==false) {
					System.out.println("Das Passwort muss mindestens einen Kleinbuchstaben enthalten.");
					validInput = false;
				}
				if(lowercase==false) {
					System.out.println("Das passwort muss mindestens einen Gro�buchstaben enthalten.");
					validInput=false;
				}
				if(special==false) {
					System.out.println("Das Passowrt muss mindestens ein g�ltiges Sonderzeichen enthalten.");
					System.out.println("G�ltige Sonderzeichen sind �, !, �, $, %, &");
					validInput=false;
				}
			}
		});
		btnRegister.setBounds(69, 292, 114, 23);
		frame.getContentPane().add(btnRegister);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(241, 292, 114, 23);
		frame.getContentPane().add(btnCancel);
	}
}

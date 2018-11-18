import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.xml.bind.DatatypeConverter;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class Registrieren {

	private JFrame frame;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordRepeat;
	private JTextField txtUsername;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtMail;

	public Registrieren() {
		initialize();
	}

	private void initialize() {
		Connect conn = new Connect();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblBenutzername = new JLabel("Benutzername:");
		lblBenutzername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBenutzername.setBounds(59, 26, 107, 15);
		frame.getContentPane().add(lblBenutzername);
		
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVorname.setBounds(77, 53, 89, 15);
		frame.getContentPane().add(lblVorname);
		
		JLabel lblNachname = new JLabel("Nachname:");
		lblNachname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNachname.setBounds(77, 80, 89, 15);
		frame.getContentPane().add(lblNachname);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setBounds(77, 107, 89, 15);
		frame.getContentPane().add(lblMail);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswort.setBounds(77, 134, 90, 15);
		frame.getContentPane().add(lblPasswort);
		
		JLabel lblPasswortWiederholen = new JLabel("Passwort wiederholen:");
		lblPasswortWiederholen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswortWiederholen.setBounds(1, 162, 165, 15);
		frame.getContentPane().add(lblPasswortWiederholen);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(181, 132, 150, 20);
		frame.getContentPane().add(txtPassword);
		
		txtPasswordRepeat = new JPasswordField();
		txtPasswordRepeat.setBounds(181, 159, 150, 20);
		frame.getContentPane().add(txtPasswordRepeat);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(181, 23, 150, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(181, 50, 150, 20);
		frame.getContentPane().add(txtFirstName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(181, 77, 150, 20);
		frame.getContentPane().add(txtLastName);
		
		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(181, 104, 150, 20);
		frame.getContentPane().add(txtMail);
		
		JTextArea txtErrorMessages = new JTextArea();
		txtErrorMessages.setEditable(false);
		txtErrorMessages.setLineWrap(true);
		txtErrorMessages.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtErrorMessages.setBackground(SystemColor.menu);
		txtErrorMessages.setForeground(new Color(220, 20, 60));
		txtErrorMessages.setBounds(59, 188, 295, 115);
		frame.getContentPane().add(txtErrorMessages);
		
		JButton btnRegister = new JButton("Registrieren");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder builder = new StringBuilder();
				txtErrorMessages.setText("");
				boolean validInput = true;
				boolean validMail = false;
				//Check if all fields are set.
				if(txtUsername.getText().equals("")) {
					builder.append("Bitte geben Sie einen Nutzernamen ein.\n");
					validInput = false;
				}
				else {
					PreparedStatement myStmt;
					try {
						myStmt = conn.getConnection().prepareStatement("SELECT * FROM tbluser WHERE nutzername = ?");
						myStmt.setString(1, txtUsername.getText());
						ResultSet myRs = myStmt.executeQuery();
						if(myRs.next()) {
							builder.append("Dieser Nutzername ist bereits vergeben.\n");
							validInput = false;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(txtFirstName.getText().equals("")) {
					builder.append("Bitte geben Sie einen Vornamen ein.\n");
					validInput = false;
				}
				if(txtLastName.getText().equals("")) {
					builder.append("Bitte geben Sie einen Nachnamen ein.\n");
					validInput = false;
				}
				if(txtMail.getText().equals("")) {
					builder.append("Bitte geben Sie eine Mailadresse ein.\n");
					validInput = false;
				}
				else {
					PreparedStatement myStmt;
					try {
						myStmt = conn.getConnection().prepareStatement("SELECT * FROM tbluser WHERE mail = ?");
						myStmt.setString(1, txtMail.getText());
						ResultSet myRs = myStmt.executeQuery();
						if(myRs.next()) {
							builder.append("Diese Mailadresse wird bereits verwendet.\n");
							validInput = false;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(txtPassword.getPassword()==null) {
					builder.append("Bitte geben Sie ein Passwort ein.\n");
					validInput = false;
				}
				if(txtPasswordRepeat.getPassword()==null) {
					builder.append("Bitte wiederholen Sie ihr Passwort.\n");
					validInput = false;
				}
				if(!Arrays.equals(txtPassword.getPassword(),txtPasswordRepeat.getPassword())) {
					builder.append("Die Passwörter stimmen nicht überein.\n");
					validInput = false;
				}
				if(txtPassword.getPassword().length<6) {
					builder.append("Das Passwort muss mindestens 6 Zeichen lang sein.\n");
					validInput = false;
				}
				if(txtMail.getText().toLowerCase().matches("[a-z0-9][-a-z0-9_+.]*[a-z0-9]@[a-z0-9][-a-z0-9.]*[a-z0-9][.](com|de)")) {
					validMail = true;
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
					if(password[i]=='!' || password[i]=='$' || password[i]=='%' || password[i]=='&') {
						special=true;
					}
					if(uppercase==true && lowercase==true && special == true) {
						break;
					}
				}
				if(uppercase==false) {
					builder.append("Das Passwort muss mindestens einen Kleinbuchstaben enthalten.\n");
					validInput = false;
				}
				if(lowercase==false) {
					builder.append("Das Passwort muss mindestens einen Großbuchstaben enthalten.\n");
					validInput=false;
				}
				if(special==false) {
					builder.append("Das Passowrt muss mindestens ein gueltiges Sonderzeichen enthalten.\n");
					builder.append("Gueltige Sonderzeichen sind !, $, %, &\n");
					validInput=false;
				}if(validMail==false) {
					builder.append("Die angegeben Mailadresse folgt nicht dem validen Mailpattern.\n");
					builder.append("Beispiel: xxx.yyy@zzz.com\n");
					validInput=false;
				}
				
				
				MessageDigest digest;
				PreparedStatement myStmt;
				try {
					//Password has to be encrypted in the database.
					digest = MessageDigest.getInstance("SHA-256");
					String passwordInput= String.valueOf(txtPassword.getPassword());
					digest.update(passwordInput.getBytes());
					byte[] passwordArray = digest.digest();
					String passwordStr = new String(DatatypeConverter.printHexBinary(passwordArray).toLowerCase());
					
					myStmt = conn.getConnection().prepareStatement("INSERT INTO tblUser (nutzername, vorname, nachname, passwort, mail, admin)" +
																   "VALUES(?, ?, ?, ?, ?, ?)");
					myStmt.setString(1, txtUsername.getText());
					myStmt.setString(2, txtFirstName.getText());
					myStmt.setString(3, txtLastName.getText());
					myStmt.setString(4, passwordStr);
					myStmt.setString(5, txtMail.getText());
					myStmt.setBoolean(6, false);
					
					if(validInput==true) {
						myStmt.executeUpdate();
						builder.append("User hinzugefügt\n");
						frame.dispose();
						new Login();
					}
				}
				catch (SQLException | NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				txtErrorMessages.setText(builder.toString());
			}
		});
		
		btnRegister.setBounds(86, 313, 114, 25);
		frame.getContentPane().add(btnRegister);
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(240, 313, 114, 25);
		frame.getContentPane().add(btnCancel);
	}
}

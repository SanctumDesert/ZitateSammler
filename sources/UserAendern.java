import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.xml.bind.DatatypeConverter;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.Color;

public class UserAendern {

	private JFrame frmAenderung;
	private JTextField txtMail;
	private JPasswordField txtPwOld;
	private JPasswordField txtPwNew;
	private JPasswordField txtPwCheck;
	private JButton btnAbbrechen;
	private JLabel lblNachname;
	private JTextField txtLastName;

	public UserAendern(User user) {
		initialize(user);
	}

	private void initialize(User user) {
		
		Connect connection = new Connect();
		
		frmAenderung = new JFrame();

		frmAenderung.setTitle("Account");
		frmAenderung.setBounds(100, 100, 450, 384);
		frmAenderung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAenderung.getContentPane().setLayout(null);

		frmAenderung.setVisible(true);
		
		JLabel lblNeueMailadresse = new JLabel("neue Mailadresse:");
		lblNeueMailadresse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNeueMailadresse.setBounds(40, 71, 135, 14);
		frmAenderung.getContentPane().add(lblNeueMailadresse);

		JLabel lblAltesPasswort = new JLabel("altes Passwort:");
		lblAltesPasswort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAltesPasswort.setBounds(40, 116, 135, 14);
		frmAenderung.getContentPane().add(lblAltesPasswort);

		JLabel lblNeuesPasswort = new JLabel("neues Passwort:");
		lblNeuesPasswort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNeuesPasswort.setBounds(40, 141, 135, 14);
		frmAenderung.getContentPane().add(lblNeuesPasswort);

		JLabel lblPasswortWiederholen = new JLabel("Passwort wiederholen:");
		lblPasswortWiederholen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswortWiederholen.setBounds(15, 166, 160, 14);
		frmAenderung.getContentPane().add(lblPasswortWiederholen);

		txtMail = new JTextField();
		txtMail.setBounds(190, 68, 150, 20);
		frmAenderung.getContentPane().add(txtMail);
		txtMail.setColumns(10);

		txtPwOld = new JPasswordField();
		txtPwOld.setBounds(190, 114, 150, 20);
		frmAenderung.getContentPane().add(txtPwOld);

		JTextArea txtErrorMessage = new JTextArea();
		txtErrorMessage.setLineWrap(true);
		txtErrorMessage.setWrapStyleWord(true);
		txtErrorMessage.setForeground(Color.RED);
		txtErrorMessage.setBackground(SystemColor.menu);
		txtErrorMessage.setEditable(false);
		txtErrorMessage.setBounds(15, 191, 398, 41);
		frmAenderung.getContentPane().add(txtErrorMessage);

		txtPwNew = new JPasswordField();
		txtPwNew.setBounds(190, 139, 150, 20);
		frmAenderung.getContentPane().add(txtPwNew);

		txtPwCheck = new JPasswordField();
		txtPwCheck.setBounds(190, 164, 150, 20);
		frmAenderung.getContentPane().add(txtPwCheck);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean validInput = true;
				StringBuilder builder = new StringBuilder();

				if(txtLastName.getText().equals("")) {
					builder.append("Sie müssen einen Nachname angeben.\n");
					validInput = false;
				}
				// Checks if the input mailadress is empty
				if(txtMail.getText().equals("")) {
					builder.append("Bitte gib eine Mailadresse ein.\n");
					validInput = false;
				}
				else {
					// Check if mailadress is already taken
					try {
						PreparedStatement myStmt = connection.getConnection().prepareStatement("SELECT Mail FROM tbluser WHERE mail = ?");
						myStmt.setString(1, txtMail.getText());
						
						ResultSet myRs = myStmt.executeQuery();
						if(myRs.next() && !myRs.getString(1).toLowerCase().equals(user.mail.toLowerCase())) {
							builder.append("Diese Mailadresse wird bereits verwendet.\n");
							validInput = false;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				MessageDigest digest;

				String password_old = null;
				try {
					PreparedStatement myStmt = connection.getConnection().prepareStatement("SELECT Passwort FROM tbluser WHERE ID = ?");
					myStmt.setInt(1, user.ID);
					ResultSet myRs = myStmt.executeQuery();
					if(myRs.next()) {
						password_old = myRs.getString("Passwort");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					
					//Password has to be encrypted in the database.
					digest = MessageDigest.getInstance("SHA-256");
					String passwordInput= String.valueOf(txtPwOld.getPassword());
					digest.update(passwordInput.getBytes());
					byte[] passwordArray = digest.digest();
					String passwordStr = new String(DatatypeConverter.printHexBinary(passwordArray).toLowerCase());
					if(txtPwOld.getPassword().length > 0 || txtPwCheck.getPassword().length > 0 || txtPwNew.getPassword().length > 0) {
						if(!password_old.equals(passwordStr))
						{
							validInput = false;
							builder.append("Bitte geben Sie Ihr richtiges Passwort ein.\n");
						}
						// Checks if the new password is empty. This is not allowed.
						if(txtPwNew.getPassword().length == 0 && txtPwOld.getPassword().length > 0) {
							builder.append("Bitte geben Sie ein Passwort ein.\n");
							validInput = false;
						}
						// Checks if the new repeated password is empty. This is not allowed.
						if(txtPwCheck.getPassword().length == 0 && txtPwOld.getPassword().length > 0) {
							builder.append("Bitte wiederholen Sie Ihr Passwort.\n");
							validInput = false;
						}
					}
					
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}

				// Checks if the mailadress follows the correct pattern.
				if(!txtMail.getText().toLowerCase().matches("[a-z0-9][-a-z0-9_+.]*[a-z0-9]@[a-z0-9][-a-z0-9.]*[a-z0-9][.](com|de)")) {
					validInput = false;
					builder.append("Die angegeben Mailadresse folgt nicht dem validen Mailpattern.\n");
					builder.append("Beispiel: xxx.yyy@zzz.com\n");
				}

				boolean uppercase=false;
				boolean lowercase=false;
				boolean special=false;
				char[] password = txtPwNew.getPassword();
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
				if(!Arrays.equals(txtPwNew.getPassword(),txtPwCheck.getPassword())) {
					builder.append("Die Passwörter stimmen nicht überein.\n");
					validInput = false;
				}

				if(validInput)
				{
					try {
						digest = MessageDigest.getInstance("SHA-256");
						String passwordInput= String.valueOf(txtPwNew.getPassword());
						digest.update(passwordInput.getBytes());
						byte[] passwordArray = digest.digest();
						String passwordStr = new String(DatatypeConverter.printHexBinary(passwordArray).toLowerCase());
						
						if(txtPwOld.getPassword().length == 0)
						{
							PreparedStatement myStmt = connection.getConnection().prepareStatement("Update tbluser SET Nachname = ?, Mail = ? WHERE ID = ?");
							myStmt.setString(1, txtLastName.getText());
							myStmt.setString(2, txtMail.getText());
							myStmt.setInt(3, user.ID);
							myStmt.executeUpdate();
							user.nachname = txtLastName.getText();
							user.mail = txtMail.getText();
							builder.append("Die Daten wurden erfolgreich geaendert.");
						}
						else
						{
							PreparedStatement myStmt = connection.getConnection().prepareStatement("Update tbluser SET Nachname = ? , Mail = ?, Passwort = ? WHERE ID = ?");
							myStmt.setString(1, txtLastName.getText());
							myStmt.setString(2, txtMail.getText());
							myStmt.setString(3, passwordStr);
							myStmt.setInt(4, user.ID);
							myStmt.executeUpdate();
							user.passwort = passwordStr;
							user.nachname = txtLastName.getText();
							user.mail = txtMail.getText();
							builder.append("Die Daten wurden erfolgreich geaendert.");
						}
					} catch (SQLException | NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
				}
				txtErrorMessage.setText(builder.toString());
			}
		});
		btnSpeichern.setBounds(40, 248, 141, 28);
		frmAenderung.getContentPane().add(btnSpeichern);

		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ZitatAnzeige(user);
				frmAenderung.dispose();
			}
		});
		btnAbbrechen.setBounds(196, 248, 141, 28);
		frmAenderung.getContentPane().add(btnAbbrechen);

		lblNachname = new JLabel("Nachname:");
		lblNachname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNachname.setBounds(82, 35, 93, 14);
		frmAenderung.getContentPane().add(lblNachname);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(190, 32, 150, 20);
		frmAenderung.getContentPane().add(txtLastName);


		frmAenderung.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

				try {					
					PreparedStatement myStmt = connection.getConnection().prepareStatement("SELECT Nachname, Mail FROM tbluser WHERE ID = ?");
					myStmt.setInt(1, user.ID);
					ResultSet myRs = myStmt.executeQuery();
					
					while(myRs.next())
					{
						txtLastName.setText(myRs.getString("Nachname"));
						txtMail.setText(myRs.getString("Mail"));
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
	}
}

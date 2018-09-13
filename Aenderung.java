import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Aenderung {

	private JFrame frmAenderung;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JButton btnAbbrechen;
	private JLabel lblNachname;
	private JTextField textField_1;
	private JButton btnAdminbereich;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aenderung window = new Aenderung();
					window.frmAenderung.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aenderung() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAenderung = new JFrame();
		frmAenderung.setTitle("Aenderung");
		frmAenderung.setBounds(100, 100, 450, 328);
		frmAenderung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAenderung.getContentPane().setLayout(null);
		
		JLabel lblNeueMailadresse = new JLabel("neue Mailadresse:");
		lblNeueMailadresse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNeueMailadresse.setBounds(57, 71, 93, 14);
		frmAenderung.getContentPane().add(lblNeueMailadresse);
		
		JLabel lblAltesPasswort = new JLabel("altes Passwort:");
		lblAltesPasswort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAltesPasswort.setBounds(57, 116, 93, 14);
		frmAenderung.getContentPane().add(lblAltesPasswort);
		
		JLabel lblNeuesPasswort = new JLabel("neues Passwort:");
		lblNeuesPasswort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNeuesPasswort.setBounds(57, 141, 93, 14);
		frmAenderung.getContentPane().add(lblNeuesPasswort);
		
		JLabel lblPasswortWiederholen = new JLabel("Passwort wiederholen:");
		lblPasswortWiederholen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPasswortWiederholen.setBounds(32, 166, 118, 14);
		frmAenderung.getContentPane().add(lblPasswortWiederholen);
		
		textField = new JTextField();
		textField.setBounds(160, 68, 118, 20);
		frmAenderung.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 116, 118, 17);
		frmAenderung.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(160, 138, 118, 17);
		frmAenderung.getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(160, 163, 118, 17);
		frmAenderung.getContentPane().add(passwordField_2);
		
		JButton btnnderungenSpeichern = new JButton("\u00C4nderungen speichern");
		btnnderungenSpeichern.setBounds(57, 222, 141, 28);
		frmAenderung.getContentPane().add(btnnderungenSpeichern);
		
		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(231, 222, 141, 28);
		frmAenderung.getContentPane().add(btnAbbrechen);
		
		lblNachname = new JLabel("Nachname:");
		lblNachname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNachname.setBounds(57, 32, 93, 14);
		frmAenderung.getContentPane().add(lblNachname);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 29, 118, 20);
		frmAenderung.getContentPane().add(textField_1);
		
		btnAdminbereich = new JButton("Adminbereich");
		btnAdminbereich.setBounds(327, 100, 97, 23);
		frmAenderung.getContentPane().add(btnAdminbereich);
	}

}

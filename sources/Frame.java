import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Frame {

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
					Frame window = new Frame();
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
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Connect connection = new Connect();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbltest = new JLabel("Benutzername:");
		lbltest.setBounds(41, 39, 73, 14);
		frame.getContentPane().add(lbltest);
		
		JButton btn = new JButton("Einloggen");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn.setText("TestText");
				lbltest.setText("Es lebt... ES LEBT!!!");
			}
		});
		btn.setBounds(63, 163, 115, 23);
		frame.getContentPane().add(btn);
		
		JButton btnAbbrechen = new JButton("Beenden");
		btnAbbrechen.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnAbbrechen);
		
		JButton btnRegiestrieren = new JButton("Regiestrieren");
		btnRegiestrieren.setBounds(226, 163, 115, 23);
		frame.getContentPane().add(btnRegiestrieren);
		
		JLabel lblPasswort = new JLabel("Passwort: ");
		lblPasswort.setBounds(63, 81, 51, 14);
		frame.getContentPane().add(lblPasswort);
		
		textField = new JTextField();
		textField.setBounds(124, 36, 122, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(124, 78, 122, 20);
		frame.getContentPane().add(textField_1);
		
		
	}
}

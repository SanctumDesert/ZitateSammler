import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ZitateHinzufuegen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ZitateHinzufuegen window = new ZitateHinzufuegen();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ZitateHinzufuegen() {
		//ZitateHinzufuegen window = new ZitateHinzufuegen();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame();
		
		frame.setBounds(100, 100, 489, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JComboBox cbSubject = new JComboBox();
		cbSubject.setEditable(true);
		cbSubject.setBounds(63, 73, 98, 20);
		frame.getContentPane().add(cbSubject);
		
		JComboBox cbClass = new JComboBox();
		cbClass.setEditable(true);
		cbClass.setBounds(171, 73, 98, 20);
		frame.getContentPane().add(cbClass);
		
		JComboBox cbTeacher = new JComboBox();
		cbTeacher.setEditable(true);
		cbTeacher.setBounds(279, 73, 98, 20);
		frame.getContentPane().add(cbTeacher);
		
		JLabel label = new JLabel("Zitat hinzuf\u00FCgen");
		label.setBounds(63, 35, 98, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Kurs");
		label_1.setBounds(63, 58, 70, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Klasse");
		label_2.setBounds(171, 58, 70, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Lehrer");
		label_3.setBounds(279, 58, 70, 14);
		frame.getContentPane().add(label_3);
		
		JButton button = new JButton("Zitate hinzuf\u00FCgen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Get all inputs
				
				
				
			}
		});
		button.setBounds(63, 290, 117, 23);
		frame.getContentPane().add(button);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(227, 131, 150, 96);
		frame.getContentPane().add(editorPane);
		
		JComboBox cbSpeaker = new JComboBox();
		cbSpeaker.setEditable(true);
		cbSpeaker.setBounds(63, 130, 98, 20);
		frame.getContentPane().add(cbSpeaker);
		
		JLabel label_4 = new JLabel("Sprecher");
		label_4.setBounds(63, 115, 70, 14);
		frame.getContentPane().add(label_4);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ZitatAnzeige zitatAnzeige = new ZitatAnzeige();
				
			}
		});
		btnAbbrechen.setBounds(260, 290, 117, 23);
		frame.getContentPane().add(btnAbbrechen);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				try {
					Connection connection = DriverManager.getConnection("jdbc:mysql://169.254.207.248:3306/zitatedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", "Lukas", "root");
					System.out.println("Connection successfull!");
					Statement myStmt = connection.createStatement();
					ResultSet myRs = myStmt.executeQuery("SELECT Klasse FROM tblklassen");
					while(myRs.next()) {
						cbClass.addItem(myRs.getString("Klasse"));
					}
					myRs = myStmt.executeQuery("SELECT Kurs FROM tblkurs");
					while(myRs.next()) {
						cbSubject.addItem(myRs.getString("Kurs"));
					}
					myRs = myStmt.executeQuery("SELECT DISTINCT Lehrer FROM tblkurs");
					while(myRs.next()) {
						cbTeacher.addItem(myRs.getString("Lehrer"));
					}
					cbSubject.setSelectedIndex(-1);
					cbClass.setSelectedIndex(-1);
					cbTeacher.setSelectedIndex(-1);
					cbSpeaker.setSelectedIndex(-1);
				}
				catch(Exception e){
					System.out.println(e);
				}
			}
		});
	}
}

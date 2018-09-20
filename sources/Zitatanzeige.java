import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;

public class Zitatanzeige {

	private JFrame frmHauptmenue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zitatanzeige window = new Zitatanzeige();
					window.frmHauptmenue.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Zitatanzeige() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHauptmenue = new JFrame();
		
		frmHauptmenue.setTitle("Hauptmen\u00FC");
		frmHauptmenue.setBounds(100, 100, 503, 575);
		frmHauptmenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHauptmenue.getContentPane().setLayout(null);
		
		JComboBox cbSubject = new JComboBox();
		cbSubject.setBounds(20, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbSubject);
		
		JComboBox cbClass = new JComboBox();
		cbClass.setBounds(128, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbClass);
		
		JComboBox cbTeacher = new JComboBox();
		cbTeacher.setBounds(236, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbTeacher);
		
		JLabel lblZitateSuchen = new JLabel("Zitate suchen:");
		lblZitateSuchen.setBounds(20, 30, 70, 14);
		frmHauptmenue.getContentPane().add(lblZitateSuchen);
		
		JLabel lblKurs = new JLabel("Kurs");
		lblKurs.setBounds(20, 53, 70, 14);
		frmHauptmenue.getContentPane().add(lblKurs);
		
		JLabel lblKlasse = new JLabel("Klasse");
		lblKlasse.setBounds(128, 53, 70, 14);
		frmHauptmenue.getContentPane().add(lblKlasse);
		
		JLabel lblLehrer = new JLabel("Lehrer");
		lblLehrer.setBounds(236, 53, 70, 14);
		frmHauptmenue.getContentPane().add(lblLehrer);
		
		JButton btnZitateAnzeigen = new JButton("Zitate anzeigen");
		btnZitateAnzeigen.setBounds(20, 127, 119, 23);
		frmHauptmenue.getContentPane().add(btnZitateAnzeigen);
		
		JList list = new JList();
		list.setBounds(10, 176, 467, 254);
		frmHauptmenue.getContentPane().add(list);
		
		JButton btnZitatLschen = new JButton("Zitat l\u00F6schen");
		btnZitatLschen.setBounds(20, 441, 105, 23);
		frmHauptmenue.getContentPane().add(btnZitatLschen);
		
		JButton btnZitatndern = new JButton("Zitat \u00E4ndern");
		btnZitatndern.setBounds(346, 441, 105, 23);
		frmHauptmenue.getContentPane().add(btnZitatndern);
		
		JButton btnSchliessen = new JButton("Schliessen");
		btnSchliessen.setBounds(346, 502, 105, 23);
		frmHauptmenue.getContentPane().add(btnSchliessen);
		
		JButton btnNichtSchliessen = new JButton("Account");
		btnNichtSchliessen.setBounds(20, 502, 105, 23);
		frmHauptmenue.getContentPane().add(btnNichtSchliessen);
		
		JButton btnZitateHinzufgen = new JButton("Zitat Hinzuf\u00FCgen");
		btnZitateHinzufgen.setBounds(332, 127, 119, 23);
		frmHauptmenue.getContentPane().add(btnZitateHinzufgen);
		
		JButton btnNewButton = new JButton("Export");
		btnNewButton.setBounds(183, 441, 105, 23);
		frmHauptmenue.getContentPane().add(btnNewButton);
		
		JComboBox cbSpeaker = new JComboBox();
		cbSpeaker.setBounds(353, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbSpeaker);
		
		JLabel lblSprecher = new JLabel("Sprecher");
		lblSprecher.setBounds(353, 53, 70, 14);
		frmHauptmenue.getContentPane().add(lblSprecher);
		
		frmHauptmenue.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					Connection connection = DriverManager.getConnection("jdbc:mysql://169.254.207.248:3306/zitatedb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", "Lukas", "root");
					System.out.println("Connection successfull!");
					Statement myStmt = connection.createStatement();
					ResultSet myRs = myStmt.executeQuery("SELECT Klasse FROM tblklassen");
					while(myRs.next()) {
						cbClass.addItem(myRs.getString("Klasse"));
					}
				}
				catch(Exception e){
					System.out.println(e);
				}

			}
		});

	}
}

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ZitatAnzeige {

	private JFrame frmHauptmenue;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZitatAnzeige window = new ZitatAnzeige();
					window.frmHauptmenue.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public ZitatAnzeige(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frmHauptmenue = new JFrame();
		
		Connect connection = new Connect();
		
		frmHauptmenue.setTitle("Hauptmen\u00FC");
		frmHauptmenue.setBounds(100, 100, 503, 575);
		frmHauptmenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHauptmenue.getContentPane().setLayout(null);
		frmHauptmenue.setVisible(true);
		
		JComboBox<String> cbSubject = new JComboBox<String>();
		cbSubject.setEditable(true);
		cbSubject.setBounds(20, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbSubject);
		
		JComboBox<String> cbClass = new JComboBox<String>();
		cbClass.setEditable(true);
		cbClass.setBounds(128, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbClass);
		
		JComboBox<String> cbTeacher = new JComboBox<String>();
		cbTeacher.setEditable(true);
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
		
		@SuppressWarnings("rawtypes")
		JList list = new JList();
		list.setBounds(10, 176, 467, 254);
		frmHauptmenue.getContentPane().add(list);
		
		JButton btnZitatLschen = new JButton("Zitat l\u00F6schen");
		btnZitatLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//list.getSelectedValue()
			}
		});
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
		btnZitateHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//frmHauptmenue.setVisible(false);
				frmHauptmenue.dispose();
				@SuppressWarnings("unused")
				ZitateHinzufuegen addZitat = new ZitateHinzufuegen(user);
				
			}
		});
		btnZitateHinzufgen.setBounds(332, 127, 119, 23);
		frmHauptmenue.getContentPane().add(btnZitateHinzufgen);
		
		JButton btnNewButton = new JButton("Export");
		btnNewButton.setBounds(183, 441, 105, 23);
		frmHauptmenue.getContentPane().add(btnNewButton);
		
		JComboBox<String> cbSpeaker = new JComboBox<String>();
		cbSpeaker.setEditable(true);
		cbSpeaker.setBounds(353, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbSpeaker);
		
		JLabel lblSprecher = new JLabel("Sprecher");
		lblSprecher.setBounds(353, 53, 70, 14);
		frmHauptmenue.getContentPane().add(lblSprecher);
		
		frmHauptmenue.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					Statement myStmt = connection.getConnection().createStatement();
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

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.xml.bind.DatatypeConverter;

import com.sun.imageio.spi.InputStreamImageInputStreamSpi;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public ZitateHinzufuegen(User user) {
		//ZitateHinzufuegen window = new ZitateHinzufuegen();
		
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
	
		frame = new JFrame();
		
		Connect connection = new Connect();
		
		frame.setBounds(100, 100, 489, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JComboBox<String> cbSubject = new JComboBox<String>();
		cbSubject.setEditable(true);
		cbSubject.setBounds(63, 73, 98, 20);
		frame.getContentPane().add(cbSubject);
		
		JComboBox<String> cbClass = new JComboBox<String>();
		cbClass.setEditable(true);
		cbClass.setBounds(171, 73, 98, 20);
		frame.getContentPane().add(cbClass);
		
		JComboBox<String> cbTeacher = new JComboBox<String>();
		cbTeacher.setEditable(true);
		cbTeacher.setBounds(279, 73, 98, 20);
		frame.getContentPane().add(cbTeacher);
		
		JComboBox<String> cbSpeaker = new JComboBox<String>();
		cbSpeaker.setEditable(true);
		cbSpeaker.setBounds(63, 130, 98, 20);
		frame.getContentPane().add(cbSpeaker);
		
		JEditorPane txtInputQuote = new JEditorPane();
		txtInputQuote.setBounds(227, 131, 150, 96);
		frame.getContentPane().add(txtInputQuote);
		
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
				String inputSubject="";
				if(cbSubject.getSelectedItem()!=null) inputSubject = cbSubject.getSelectedItem().toString();
				
				String inputClass="";
				if(cbClass.getSelectedItem()!=null) inputClass = cbClass.getSelectedItem().toString();
				
				String inputTeacher="";
				if(cbTeacher.getSelectedItem()!=null) inputTeacher = cbTeacher.getSelectedItem().toString();
				
				String inputSpeaker="";
				if(cbSpeaker.getSelectedItem()!=null) inputSpeaker = cbSpeaker.getSelectedItem().toString();
				
				String inputQuote="";
				if(txtInputQuote.getText()!=null) inputQuote = txtInputQuote.getText();
				
				PreparedStatement myStmt;
				try {
	
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tblUser WHERE nutzername = ?");
					myStmt.setString(1, inputSpeaker);
					ResultSet result = myStmt.executeQuery();
					int speakerID = -1;
					if(result.next()) speakerID = result.getInt(1);
					
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tblKurs WHERE kurs = ? AND lehrer = ?");
					myStmt.setString(1, inputSubject);
					myStmt.setString(2, inputTeacher);
					result = myStmt.executeQuery();
					int subjectID = -1;
					if(result.next()) subjectID = result.getInt(1);
					
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tblKlassen WHERE klasse = ?");
					myStmt.setString(1, inputClass);
					result = myStmt.executeQuery();
					int classID = -1;
					if(result.next()) classID = result.getInt(1);
					
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tblLehrer WHERE name = ?");
					myStmt.setString(1, inputTeacher);
					result = myStmt.executeQuery();
					int teacherID = -1;
					if(result.next()) teacherID = result.getInt(1);
					
					System.out.println("SpeakerID: " + speakerID + " SubjectID: " + subjectID + " ClassID: " + classID + " TeacherID: " + teacherID + " Quote: " + inputQuote);
					if(speakerID == -1 || subjectID == -1 || classID == -1 || teacherID == -1 || inputQuote.equals("")) {
						// --- FIX ME --- Add Error message to logfield
							System.out.println("Bitte füllen Sie alle Felder aus.");
					}
					else {
						boolean userInClass = false;
						myStmt = connection.getConnection().prepareStatement("SELECT id FROM user_kurs_map WHERE kursid = ? AND userid = ?");
						
						myStmt.setInt(1, subjectID);
						myStmt.setInt(2, user.ID);
						result = myStmt.executeQuery();
						
						if(result.next()) userInClass = true;
						
						if(userInClass) {
							myStmt = connection.getConnection().prepareStatement("INSERT INTO tblZitate (deleted, urheberid, sprecherid, kursid, datum, zitat, klasseid, lehrerid)" +
																		  		 "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
							myStmt.setInt(1, 0);
							myStmt.setInt(2, user.ID);
							myStmt.setInt(3, speakerID);
							myStmt.setInt(4, subjectID);
							myStmt.setLong(5, System.currentTimeMillis());
							myStmt.setString(6, inputQuote);
							myStmt.setInt(7, classID);
							myStmt.setInt(8, teacherID);
							
							myStmt.executeUpdate();
							// --- FIX ME --- Add message to logfield
							System.out.println("Zitat erfolgreich hinzugefügt.");
						}
						else {
							// --- FIX ME--- Add message to logfield
							System.out.println("Sie sind nicht in diesem Kurs.");
						}
					}
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setBounds(63, 290, 117, 23);
		frame.getContentPane().add(button);
		
		JLabel label_4 = new JLabel("Sprecher");
		label_4.setBounds(63, 115, 70, 14);
		frame.getContentPane().add(label_4);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ZitatAnzeige zitatAnzeige = new ZitatAnzeige(user);
				
			}
		});
		btnAbbrechen.setBounds(260, 290, 117, 23);
		frame.getContentPane().add(btnAbbrechen);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
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
					myRs = myStmt.executeQuery("SELECT DISTINCT nutzername FROM tbluser");
					while(myRs.next()) {
						cbSpeaker.addItem(myRs.getString("nutzername"));
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

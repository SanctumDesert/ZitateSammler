import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class ZitateHinzufuegen {

	private JFrame frmZitatHinzufgen;

	public ZitateHinzufuegen(User user) {
		initialize(user);
	}

	private void initialize(User user) {
	
		frmZitatHinzufgen = new JFrame();
		frmZitatHinzufgen.setTitle("Zitat hinzuf\u00FCgen");
		
		Connect connection = new Connect();
		
		frmZitatHinzufgen.setBounds(100, 100, 597, 453);
		frmZitatHinzufgen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZitatHinzufgen.getContentPane().setLayout(null);
		frmZitatHinzufgen.setVisible(true);
		
		JComboBox<String> cbSubject = new JComboBox<String>();
		cbSubject.setEditable(true);
		cbSubject.setBounds(63, 73, 98, 20);
		frmZitatHinzufgen.getContentPane().add(cbSubject);
		
		JComboBox<String> cbClass = new JComboBox<String>();
		cbClass.setEditable(true);
		cbClass.setBounds(171, 73, 98, 20);
		frmZitatHinzufgen.getContentPane().add(cbClass);
				
		JComboBox<String> cbTeacher = new JComboBox<String>();
		cbTeacher.setEditable(true);
		cbTeacher.setBounds(279, 73, 98, 20);
		frmZitatHinzufgen.getContentPane().add(cbTeacher);
		
		JComboBox<String> cbSpeaker = new JComboBox<String>();
		cbSpeaker.setEditable(true);
		cbSpeaker.setBounds(389, 73, 98, 20);
		frmZitatHinzufgen.getContentPane().add(cbSpeaker);
		
		JEditorPane txtQuote = new JEditorPane();
		txtQuote.setBounds(63, 106, 424, 96);
		frmZitatHinzufgen.getContentPane().add(txtQuote);
		
		JLabel label = new JLabel("Zitat hinzuf\u00FCgen");
		label.setBounds(63, 35, 98, 14);
		frmZitatHinzufgen.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Kurs");
		label_1.setBounds(63, 58, 70, 14);
		frmZitatHinzufgen.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Klasse");
		label_2.setBounds(171, 58, 70, 14);
		frmZitatHinzufgen.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Lehrer");
		label_3.setBounds(279, 58, 70, 14);
		frmZitatHinzufgen.getContentPane().add(label_3);
		
		JTextArea txtErrorMessages = new JTextArea();
		txtErrorMessages.setForeground(new Color(255, 0, 0));
		txtErrorMessages.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtErrorMessages.setEditable(false);
		txtErrorMessages.setBackground(SystemColor.menu);
		txtErrorMessages.setBounds(63, 233, 424, 96);
		frmZitatHinzufgen.getContentPane().add(txtErrorMessages);
		
		JButton btnAdd = new JButton("Zitate hinzuf\u00FCgen");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtErrorMessages.setText("");
				StringBuilder builder = new StringBuilder();
				
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
				if(txtQuote.getText()!=null) inputQuote = txtQuote.getText();
				
				
				PreparedStatement myStmt;
				try {
					
					//Gets the ID from the chosen speaker
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tblUser WHERE nutzername = ?");
					myStmt.setString(1, inputSpeaker);
					ResultSet result = myStmt.executeQuery();
					int speakerID = -1;
					if(result.next()) speakerID = result.getInt(1);
					
					// Gets the Subject ID from the chosen teacher and course
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM lehrer_kurs_map WHERE kursid = (SELECT id FROM tblkurs WHERE kurs = ?) AND lehrerid = (SELECT id FROM tbllehrer WHERE name = ?)");
					myStmt.setString(1, inputSubject);
					myStmt.setString(2, inputTeacher);
					result = myStmt.executeQuery();
					int subjectID = -1;
					if(result.next()) subjectID = result.getInt(1);
					
					// Gets the ID from the chosen class
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tblKlassen WHERE klasse = ?");
					myStmt.setString(1, inputClass);
					result = myStmt.executeQuery();
					int classID = -1;
					if(result.next()) classID = result.getInt(1);
					
					// Gets the ID from the chosen teacher
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tblLehrer WHERE name = ?");
					myStmt.setString(1, inputTeacher);
					result = myStmt.executeQuery();
					int teacherID = -1;
					if(result.next()) teacherID = result.getInt(1);
					
					if(txtQuote.getText().length() > 1000) {
						builder.append("Das Zitat darf nicht l�nger als 1000 Zeichen sein.");
					}
					else {
						//Check if ComboBoxes and TextBox are filled
						if(speakerID == -1 || subjectID == -1 || classID == -1 || teacherID == -1 || inputQuote.equals("")) {
								builder.append("Bitte f�llen Sie alle Felder aus.");
						}
						else {
							boolean userInClass = false;
							myStmt = connection.getConnection().prepareStatement("SELECT id FROM user_kurs_map WHERE kursid = ? AND userid = ?");
							
							myStmt.setInt(1, subjectID);
							myStmt.setInt(2, user.ID);
							result = myStmt.executeQuery();
							
							//Check if user is in selected class
							if(result.next()) userInClass = true;
							
							
							if(userInClass) {
								myStmt = connection.getConnection().prepareStatement("INSERT INTO tblZitate (urheberid, sprecherid, kursid, datum, zitat, klasseid, lehrerid)" +
																			  		 "VALUES(?, ?, ?, ?, ?, ?, ?)");
								myStmt.setInt(1, user.ID);
								myStmt.setInt(2, speakerID);
								myStmt.setInt(3, subjectID);
								myStmt.setLong(4, System.currentTimeMillis());
								myStmt.setString(5, inputQuote);
								myStmt.setInt(6, classID);
								myStmt.setInt(7, teacherID);
								
								myStmt.executeUpdate();
								txtQuote.setText("");
								builder.append("Zitat erfolgreich hinzugef�gt.");
							}
							else {
								builder.append("Sie sind nicht in diesem Kurs.");
							}
						}
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				txtErrorMessages.setText(builder.toString());
			}
		});
		btnAdd.setBounds(85, 349, 156, 23);
		frmZitatHinzufgen.getContentPane().add(btnAdd);
		
		JLabel label_4 = new JLabel("Sprecher");
		label_4.setBounds(389, 58, 70, 14);
		frmZitatHinzufgen.getContentPane().add(label_4);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				//Close window and switch to the QuoteViewer
				frmZitatHinzufgen.dispose();
				ZitatAnzeige zitatAnzeige = new ZitatAnzeige(user);
				
			}
		});
		btnAbbrechen.setBounds(303, 349, 156, 23);
		frmZitatHinzufgen.getContentPane().add(btnAbbrechen);
		
		frmZitatHinzufgen.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					//Fill ComboBoxes with Values from the Database
					Statement myStmt = connection.getConnection().createStatement();
					ResultSet myRs = myStmt.executeQuery("SELECT Klasse FROM tblklassen");
					while(myRs.next()) {
						cbClass.addItem(myRs.getString("Klasse"));
					}
					myRs = myStmt.executeQuery("SELECT Kurs FROM tblkurs");
					while(myRs.next()) {
						cbSubject.addItem(myRs.getString("Kurs"));
					}
					myRs = myStmt.executeQuery("SELECT DISTINCT name FROM tbllehrer");
					while(myRs.next()) {
						cbTeacher.addItem(myRs.getString("name"));
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

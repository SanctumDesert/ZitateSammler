import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;

public class ZitatAendern {

	private JFrame frame;

	public ZitatAendern(User user, int zitatID) {
		initialize(user, zitatID);
	}

	private void initialize(User user, int zitatID) {
		
		Connect connection = new Connect();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 599, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JComboBox<String> cbSubject = new JComboBox<String>();
		cbSubject.setEditable(true);
		cbSubject.setBounds(25, 59, 98, 20);
		frame.getContentPane().add(cbSubject);
		
		JComboBox<String> cbClass = new JComboBox<String>();
		cbClass.setEditable(true);
		cbClass.setBounds(133, 59, 98, 20);
		frame.getContentPane().add(cbClass);
		
		JComboBox<String> cbTeacher = new JComboBox<String>();
		cbTeacher.setEditable(true);
		cbTeacher.setBounds(241, 59, 98, 20);
		frame.getContentPane().add(cbTeacher);
		
		JLabel lblZitatAendern = new JLabel("Zitat aendern");
		lblZitatAendern.setBounds(25, 21, 98, 14);
		frame.getContentPane().add(lblZitatAendern);
		
		JLabel label_1 = new JLabel("Kurs");
		label_1.setBounds(25, 44, 70, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Klasse");
		label_2.setBounds(133, 44, 70, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Lehrer");
		label_3.setBounds(241, 44, 70, 14);
		frame.getContentPane().add(label_3);
		
		JEditorPane ePZitat = new JEditorPane();
		ePZitat.setBounds(25, 117, 427, 96);
		frame.getContentPane().add(ePZitat);
		
		JComboBox<String> cbSpeaker = new JComboBox<String>();
		cbSpeaker.setEditable(true);
		cbSpeaker.setBounds(354, 59, 98, 20);
		frame.getContentPane().add(cbSpeaker);
		
		JLabel label_4 = new JLabel("Sprecher");
		label_4.setBounds(354, 44, 70, 14);
		frame.getContentPane().add(label_4);
		
		JButton btnSave = new JButton("Speichern");
		btnSave.setToolTipText("Speichern");btnSave.setBounds(78, 276, 125, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.setToolTipText("Abbrechen");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new ZitatAnzeige(user);
			}
		});
		btnCancel.setBounds(327, 276, 125, 23);
		frame.getContentPane().add(btnCancel);
		
		JTextArea txtErrorLog = new JTextArea();
		txtErrorLog.setBackground(SystemColor.menu);
		txtErrorLog.setForeground(new Color(255, 0, 0));
		txtErrorLog.setEditable(false);
		txtErrorLog.setBounds(25, 229, 427, 31);
		frame.getContentPane().add(txtErrorLog);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Selects the chosen course by its unique name
					PreparedStatement myStmt = connection.getConnection().prepareStatement("SELECT id FROM tblkurs WHERE kurs = ?");
					myStmt.setString(1, cbSubject.getEditor().getItem().toString());
					System.out.println(cbSubject.getEditor().getItem().toString());
					ResultSet result = myStmt.executeQuery();
					int subjectid = 0;
					if(result.next()) subjectid = result.getInt(1);
					
					// Selects the chosen class by its unique name
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tblklassen WHERE klasse = ?");
					System.out.println(cbClass.getEditor().getItem().toString());
					myStmt.setString(1, cbClass.getEditor().getItem().toString());
					result = myStmt.executeQuery();
					int classid = 0;
					if(result.next()) classid = result.getInt(1);
					
					// Selects the chosen teacher by his/her unique name
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tbllehrer WHERE name = ?");
					myStmt.setString(1, cbTeacher.getEditor().getItem().toString());
					result = myStmt.executeQuery();
					int teacherid = 0;
					if(result.next()) teacherid = result.getInt(1);
					
					// Selects the chosen user by his/her unique username
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM tbluser WHERE nutzername = ?");
					myStmt.setString(1, cbSpeaker.getEditor().getItem().toString());
					result = myStmt.executeQuery();
					int userid = 0;
					if(result.next()) userid = result.getInt(1);
					
					// Checks if the chosen user is in the chosen course
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM user_kurs_map WHERE kursid = ? AND userid = ?");
					
					myStmt.setInt(1, subjectid);
					myStmt.setInt(2, userid);
					ResultSet result1 = myStmt.executeQuery();
					
					// Checks if the chosen teacher gives the chosen course
					myStmt = connection.getConnection().prepareStatement("SELECT id FROM lehrer_kurs_map WHERE kursid = ? AND lehrerid = ?");
					
					myStmt.setInt(1, subjectid);
					myStmt.setInt(2, teacherid);
					ResultSet result2 = myStmt.executeQuery();
					
					// If the chosen user is in the chosen course and the chosen teacher gives the chosen course the update query will be executed
					if(result1.next() == true && result2.next() == true) {
						myStmt = connection.getConnection().prepareStatement("UPDATE tblzitate SET KursID = ? , KlasseID = ?, LehrerID = ?, SprecherID = ?, zitat = ? WHERE id = ?");
						myStmt.setInt(1, subjectid);
						myStmt.setInt(2, classid);
						myStmt.setInt(3, teacherid);
						myStmt.setInt(4, userid);
						myStmt.setString(5, ePZitat.getText());
						myStmt.setInt(6, zitatID);
						myStmt.executeUpdate();
					}
					else {
						txtErrorLog.setText("Bitte überprüfen Sie Ihre Eingaben.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				//Create connection to database
				Connect connection = new Connect();
				
				// Fills the comboboxes with all values
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
					myRs = myStmt.executeQuery("SELECT DISTINCT name FROM tbllehrer");
					while(myRs.next()) {
						cbTeacher.addItem(myRs.getString("name"));
					}
					myRs = myStmt.executeQuery("SELECT nutzername FROM tbluser");
					while(myRs.next()) {
						cbSpeaker.addItem(myRs.getString("nutzername"));
					}
					cbSubject.setSelectedIndex(-1);
					cbClass.setSelectedIndex(-1);
					cbTeacher.setSelectedIndex(-1);
					cbSpeaker.setSelectedIndex(-1);
					
					// Fills the textfield with the quote that has been chosen to be changed
					myRs = myStmt.executeQuery("SELECT z.ID, Zitat, u.nutzername, k.Kurs, c.Klasse, t.name as Lehrer FROM zitatedb.tblzitate as z\r\n" + 
						       "JOIN tbluser as u on u.ID = z.SprecherID\r\n" + 
						       "JOIN tblKurs as k on z.KursID = k.ID\r\n" +
						       "JOIN tbllehrer as t on z.lehrerid = t.ID\r\n" +
						       "JOIN tblklassen as c ON z.KlasseID = c.ID WHERE z.ID = " + String.valueOf(zitatID));
					while(myRs.next())
					{
						cbClass.getEditor().setItem(myRs.getString("Klasse"));
						cbSpeaker.getEditor().setItem(myRs.getString("nutzername"));
						cbSubject.getEditor().setItem(myRs.getString("Kurs"));
						cbTeacher.getEditor().setItem(myRs.getString("Lehrer"));
						ePZitat.setText(myRs.getString("Zitat").toString());
					}
					
					
				}
				catch(Exception e){
					System.out.println(e);
				}
			}
		});
	}
	}

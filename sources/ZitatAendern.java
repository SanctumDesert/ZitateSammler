import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.sun.istack.internal.FragmentContentHandler;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ZitatAendern {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ZitatAendern window = new ZitatAendern();
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
	public ZitatAendern(User user, int zitatID) {
		initialize(user, zitatID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user, int zitatID) {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 402, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JComboBox cbSubject = new JComboBox();
		cbSubject.setEditable(true);
		cbSubject.setBounds(25, 59, 98, 20);
		frame.getContentPane().add(cbSubject);
		
		JComboBox cbClass = new JComboBox();
		cbClass.setEditable(true);
		cbClass.setBounds(133, 59, 98, 20);
		frame.getContentPane().add(cbClass);
		
		JComboBox cbTeacher = new JComboBox();
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
		ePZitat.setBounds(189, 117, 150, 96);
		frame.getContentPane().add(ePZitat);
		
		JComboBox cbSpeaker = new JComboBox();
		cbSpeaker.setEditable(true);
		cbSpeaker.setBounds(25, 116, 98, 20);
		frame.getContentPane().add(cbSpeaker);
		
		JLabel label_4 = new JLabel("Sprecher");
		label_4.setBounds(25, 101, 70, 14);
		frame.getContentPane().add(label_4);
		
		JButton button_1 = new JButton("Speichern");
//		button_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					Statement myStmt = connection.getConnection().createStatement();
//									
//					ResultSet myRs = myStmt.executeQuery("Update tblZitate z.ID, Zitat, u.Vorname, u.Nachname, k.Kurs, c.Klasse, t.name as Lehrer FROM zitatedb.tblzitate as z\r\n" + 
//						       "JOIN tbluser as u on u.ID = z.SprecherID\r\n" + 
//						       "JOIN tblKurs as k on z.KursID = k.ID\r\n" +
//						       "JOIN tbllehrer as t on z.lehrerid = t.ID\r\n" +
//						       "JOIN tblklassen as c ON z.KlasseID = c.ID WHERE z.ID = " + String.valueOf(zitatID));
//					while(myRs.next())
//					{
//						cbClass.getEditor().setItem(myRs.getString("Klasse"));
//						cbSpeaker.getEditor().setItem(myRs.getString("Vorname") + myRs.getString("Nachname"));
//						cbSubject.getEditor().setItem(myRs.getString("Kurs"));
//						cbTeacher.getEditor().setItem(myRs.getString("Lehrer"));
//						ePZitat.setText(myRs.getString("Zitat").toString());
//					}
//					
//					
//				}
			
//		});
//		}
		button_1.setBounds(25, 276, 89, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Abbrechen");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				ZitatAnzeige addQuote = new ZitatAnzeige(user);
			}
		});
		button_2.setBounds(250, 276, 89, 23);
		frame.getContentPane().add(button_2);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				//Create connection to database
				Connect connection = new Connect();
				
				//Get the input username and password
				
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
					myRs = myStmt.executeQuery("SELECT Klasse FROM tblklassen");
					while(myRs.next()) {
						cbClass.addItem(myRs.getString("Klasse"));
					}
					cbSubject.setSelectedIndex(-1);
					cbClass.setSelectedIndex(-1);
					cbTeacher.setSelectedIndex(-1);
					cbSpeaker.setSelectedIndex(-1);
					
					myRs = myStmt.executeQuery("SELECT z.ID, Zitat, u.Vorname, u.Nachname, k.Kurs, c.Klasse, t.name as Lehrer FROM zitatedb.tblzitate as z\r\n" + 
						       "JOIN tbluser as u on u.ID = z.SprecherID\r\n" + 
						       "JOIN tblKurs as k on z.KursID = k.ID\r\n" +
						       "JOIN tbllehrer as t on z.lehrerid = t.ID\r\n" +
						       "JOIN tblklassen as c ON z.KlasseID = c.ID WHERE z.ID = " + String.valueOf(zitatID));
					while(myRs.next())
					{
						cbClass.getEditor().setItem(myRs.getString("Klasse"));
						cbSpeaker.getEditor().setItem(myRs.getString("Vorname") + myRs.getString("Nachname"));
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



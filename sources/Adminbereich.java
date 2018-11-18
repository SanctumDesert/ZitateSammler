import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Adminbereich {

	private JFrame frame;
	private JTextField txtSubject;
	private JTextField txtTeacher;
	private JTextField txtClass;

	public Adminbereich(User user) {
		initialize(user);
	}

	private void initialize(User user) {
		frame = new JFrame();
		
		Connect connection = new Connect();
		
		frame.setBounds(100, 100, 658, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JComboBox<String> cbUser = new JComboBox<String>();
		cbUser.setBounds(15, 354, 135, 25);
		frame.getContentPane().add(cbUser);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setBounds(15, 324, 106, 14);
		frame.getContentPane().add(lblUser);
		
		JButton btnUserDelete = new JButton("User l\u00F6schen");
		btnUserDelete.setBounds(164, 353, 149, 25);
		frame.getContentPane().add(btnUserDelete);
		
		JLabel lblFach = new JLabel("Fach");
		lblFach.setBounds(15, 48, 46, 14);
		frame.getContentPane().add(lblFach);
		
		JLabel lblLehrer = new JLabel("Lehrer");
		lblLehrer.setBounds(15, 112, 46, 14);
		frame.getContentPane().add(lblLehrer);
		
		JLabel lblKlasse = new JLabel("Klasse");
		lblKlasse.setBounds(15, 184, 46, 14);
		frame.getContentPane().add(lblKlasse);
		
		JButton btnAddCourse = new JButton("Kurs Hinzuf\u00FCgen");
		btnAddCourse.setBounds(164, 144, 149, 25);
		frame.getContentPane().add(btnAddCourse);
		
		JButton btnDone = new JButton("Fertig");
		btnDone.setBounds(487, 344, 134, 35);
		frame.getContentPane().add(btnDone);
		
		txtSubject = new JTextField();
		txtSubject.setBounds(15, 70, 134, 25);
		frame.getContentPane().add(txtSubject);
		txtSubject.setColumns(10);
		
		txtTeacher = new JTextField();
		txtTeacher.setBounds(15, 142, 134, 25);
		frame.getContentPane().add(txtTeacher);
		txtTeacher.setColumns(10);
		
		txtClass = new JTextField();
		txtClass.setBounds(15, 214, 134, 25);
		frame.getContentPane().add(txtClass);
		txtClass.setColumns(10);
		
		JTextArea txtLogField = new JTextArea();
		txtLogField.setLineWrap(true);
		txtLogField.setWrapStyleWord(true);
		txtLogField.setForeground(new Color(255, 0, 0));
		txtLogField.setBackground(SystemColor.menu);
		txtLogField.setEditable(false);
		txtLogField.setBounds(372, 16, 249, 271);
		frame.getContentPane().add(txtLogField);
		
		JLabel lblCourse = new JLabel("Kurs");
		lblCourse.setBounds(15, 256, 134, 20);
		frame.getContentPane().add(lblCourse);
		
		JComboBox<String> cbCourse = new JComboBox<String>();
		cbCourse.setBounds(15, 281, 135, 25);
		frame.getContentPane().add(cbCourse);
		
		JButton btnAddUserToCourse = new JButton("User zu Kurs hinzuf\u00FCgen");
		btnAddUserToCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbCourse.getSelectedIndex() != -1 && cbUser.getSelectedIndex() != -1) {
					try {
						PreparedStatement myStmt = connection.getConnection().prepareStatement("INSERT INTO user_kurs_map (kursid, userid)"
																							 + "VALUES (?, ?)");
						
						PreparedStatement getCourseID = connection.getConnection().prepareStatement("SELECT id FROM tblkurs WHERE kurs = ?");
						getCourseID.setString(1, cbCourse.getSelectedItem().toString());
						ResultSet myRs = getCourseID.executeQuery();
						myRs.next();
						int courseID = myRs.getInt(1);
						myStmt.setInt(1, courseID);
						
						PreparedStatement getUserID = connection.getConnection().prepareStatement("SELECT id FROM tbluser WHERE nutzername = ?");
						getUserID.setString(1, cbUser.getSelectedItem().toString());
						myRs = getUserID.executeQuery();
						myRs.next();
						int userID = myRs.getInt(1);
						myStmt.setInt(2, userID);
						
						PreparedStatement isUserInCourse = connection.getConnection().prepareStatement("SELECT * FROM user_kurs_map WHERE userid = ? AND kursid = ?");
						isUserInCourse.setInt(1, userID);
						isUserInCourse.setInt(2, courseID);
						myRs = isUserInCourse.executeQuery();
						
						if(!myRs.next()) {
							myStmt.executeUpdate();
							txtLogField.setText("User \"" + cbUser.getSelectedItem().toString() + "\" wurde erfolgreich zu Kurs \"" + cbCourse.getSelectedItem().toString() + "\" hinzugef�gt.");
						}
						else {
							txtLogField.setText("Dieser User ist bereits in diesem Kurs.");
						}
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else {
					txtLogField.setText("Bitte w�hlen Sie sowohl einen Kurs, als auch einen Nutzer aus.");
				}
			}
		});
		btnAddUserToCourse.setBounds(164, 256, 205, 25);
		frame.getContentPane().add(btnAddUserToCourse);
		
		JButton btnDeleteUserFromCourse = new JButton("User aus Kurs entfernen");
		btnDeleteUserFromCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreparedStatement getCourseID = connection.getConnection().prepareStatement("SELECT id FROM tblkurs WHERE kurs = ?");
					getCourseID.setString(1, cbCourse.getSelectedItem().toString());
					ResultSet myRs = getCourseID.executeQuery();
					myRs.next();
					int courseID = myRs.getInt(1);
					
					PreparedStatement getUserID = connection.getConnection().prepareStatement("SELECT id FROM tbluser WHERE nutzername = ?");
					getUserID.setString(1, cbUser.getSelectedItem().toString());
					myRs = getUserID.executeQuery();
					myRs.next();
					int userID = myRs.getInt(1);
					
					PreparedStatement isUserInCourse = connection.getConnection().prepareStatement("SELECT * FROM user_kurs_map WHERE userid = ? AND kursid = ?");
					isUserInCourse.setInt(1, userID);
					isUserInCourse.setInt(2, courseID);
					myRs = isUserInCourse.executeQuery();
					
					if(myRs.next()) {
						PreparedStatement deleteUser = connection.getConnection().prepareStatement("DELETE FROM user_kurs_map WHERE userid = ? AND kursid = ?");
						deleteUser.setInt(1, userID);
						deleteUser.setInt(2, courseID);
						deleteUser.executeUpdate();
						txtLogField.setText("Der User \"" + cbUser.getSelectedItem().toString() + "\" wurde aus dem Kurs \"" + cbCourse.getSelectedItem().toString() + "\" erfolgreich entfernt.");
					}
					else {
						txtLogField.setText("Der User \"" + cbUser.getSelectedItem().toString() + "\" ist nicht im Kurs \"" + cbCourse.getSelectedItem().toString() + "\"  und kann somit nicht aus ihm entfernt werden.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnDeleteUserFromCourse.setBounds(164, 297, 205, 25);
		frame.getContentPane().add(btnDeleteUserFromCourse);
		
		btnUserDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtLogField.setText("Moechten Sie wirklich den User: " + cbUser.getSelectedItem().toString() + " loeschen?\n\n Wenn ja, bitte mit dem \"Bestaetigen\" Button bestaetigen.");
				btnUserDelete.setText("Bestaetigen");
				btnUserDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							PreparedStatement myStmt = connection.getConnection().prepareStatement("Update tbluser SET deleted = 1 WHERE nutzername = ?");
							myStmt.setString(1, cbUser.getSelectedItem().toString());
							myStmt.execute();
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new ZitatAnzeige(user);
			}
		});
		
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtClass.getText().equals("") || txtSubject.getText().equals("") || txtTeacher.getText().equals("")) {
					txtLogField.setText("Alle Felder m�ssen ausgef�llt sein.");
				}
				else {
					try {
						PreparedStatement myStmt = connection.getConnection().prepareStatement("INSERT INTO tblkurs(Kurs) VALUES(?)");
						String courseName = txtClass.getText() + "-" + txtSubject.getText();
						myStmt.setString(1, courseName);
						myStmt.executeUpdate();
						
						myStmt = connection.getConnection().prepareStatement("INSERT INTO lehrer_kurs_map(lehrerid, kursid) VALUES(?, ?)");
						PreparedStatement getTeacherID = connection.getConnection().prepareStatement("SELECT id FROM tbllehrer WHERE id = ?");
						getTeacherID.setInt(1, Integer.parseInt(txtTeacher.getText()));
						ResultSet myRs = getTeacherID.executeQuery();
						int teacherID = 0;
						while(myRs.next()) {
							teacherID = myRs.getInt(1);
						}
						
						PreparedStatement getCourseID = connection.getConnection().prepareStatement("SELECT id FROM tblkurs WHERE kurs = ?");
						getCourseID.setString(1, txtClass.getText() + "-" + txtSubject.getText());
						myRs = getCourseID.executeQuery();
						int courseID = 0;
						while(myRs.next()) {
							courseID = myRs.getInt(1);
						}
						
						if(teacherID == 0) {
							txtLogField.setText("Ein Lehrer mit dieser ID existiert nicht.");
						}
						else if(courseID == 0) {
							txtLogField.setText("Ein Fach mit diesem Namen existiert nicht.");
						}
						else {
							myStmt.setInt(1, teacherID);
							myStmt.setInt(2, courseID);
							myStmt.executeUpdate();
							txtLogField.setText("Kurs erfolgreich hinzugefuegt!");
						}
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Statement myStmt;
				try {
					myStmt = connection.getConnection().createStatement();
					ResultSet myRs = myStmt.executeQuery("SELECT nutzername FROM tbluser WHERE deleted = 0 AND admin = 0");
					while(myRs.next()) {
						cbUser.addItem(myRs.getString("nutzername"));
					}
					myRs = myStmt.executeQuery("SELECT kurs FROM tblkurs");
					while(myRs.next()) {
						cbCourse.addItem(myRs.getString("kurs"));
					}
					
					cbUser.setSelectedIndex(-1);
					cbCourse.setSelectedIndex(-1);
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}

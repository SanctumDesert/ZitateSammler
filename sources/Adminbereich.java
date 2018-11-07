import java.awt.EventQueue;

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

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adminbereich window = new Adminbereich();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 * @param user 
	 */
	public Adminbereich(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		
		Connect connection = new Connect();
		
		frame.setBounds(100, 100, 658, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JComboBox cbUser = new JComboBox();
		cbUser.setBounds(15, 311, 134, 20);
		frame.getContentPane().add(cbUser);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setBounds(15, 281, 46, 14);
		frame.getContentPane().add(lblUser);
		
		JButton btnUserDelete = new JButton("User l\u00F6schen");
		btnUserDelete.setBounds(164, 310, 134, 23);
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
		btnAddCourse.setBounds(164, 144, 159, 23);
		frame.getContentPane().add(btnAddCourse);
		
		JButton btnDone = new JButton("Fertig");
		btnDone.setBounds(442, 304, 134, 35);
		frame.getContentPane().add(btnDone);
		
		txtSubject = new JTextField();
		txtSubject.setBounds(15, 70, 134, 26);
		frame.getContentPane().add(txtSubject);
		txtSubject.setColumns(10);
		
		txtTeacher = new JTextField();
		txtTeacher.setBounds(15, 142, 134, 26);
		frame.getContentPane().add(txtTeacher);
		txtTeacher.setColumns(10);
		
		txtClass = new JTextField();
		txtClass.setBounds(15, 214, 134, 26);
		frame.getContentPane().add(txtClass);
		txtClass.setColumns(10);
		
		JTextArea txtLogField = new JTextArea();
		txtLogField.setLineWrap(true);
		txtLogField.setWrapStyleWord(true);
		txtLogField.setForeground(new Color(255, 0, 0));
		txtLogField.setBackground(SystemColor.menu);
		txtLogField.setEditable(false);
		txtLogField.setBounds(364, 16, 257, 271);
		frame.getContentPane().add(txtLogField);
		
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				ZitatAnzeige zitatAnzeige = new ZitatAnzeige(user);
			}
		});
		
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtClass.getText().equals("") || txtSubject.getText().equals("") || txtTeacher.getText().equals("")) {
					txtLogField.setText("Alle Felder müssen ausgefüllt sein.");
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
						// TODO Auto-generated catch block
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
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}

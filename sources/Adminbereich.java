import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class Adminbereich {

	private JFrame frame;

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
		cbUser.setBounds(15, 216, 134, 20);
		frame.getContentPane().add(cbUser);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setBounds(15, 199, 46, 14);
		frame.getContentPane().add(lblUser);
		
		JButton btnUserDelete = new JButton("User l\u00F6schen");
		btnUserDelete.setBounds(164, 215, 134, 23);
		frame.getContentPane().add(btnUserDelete);
		
		JComboBox cbSubject = new JComboBox();
		cbSubject.setBounds(15, 73, 134, 20);
		frame.getContentPane().add(cbSubject);
		
		JComboBox cbTeacher = new JComboBox();
		cbTeacher.setBounds(164, 73, 134, 20);
		frame.getContentPane().add(cbTeacher);
		
		JComboBox cbClass = new JComboBox();
		cbClass.setBounds(313, 73, 134, 20);
		frame.getContentPane().add(cbClass);
		
		JLabel lblFach = new JLabel("Fach");
		lblFach.setBounds(15, 48, 46, 14);
		frame.getContentPane().add(lblFach);
		
		JLabel lblLehrer = new JLabel("Lehrer");
		lblLehrer.setBounds(164, 48, 46, 14);
		frame.getContentPane().add(lblLehrer);
		
		JLabel lblKlasse = new JLabel("Klasse");
		lblKlasse.setBounds(313, 48, 46, 14);
		frame.getContentPane().add(lblKlasse);
		
		JButton btnAddCourse = new JButton("Kurs Hinzuf\u00FCgen");
		btnAddCourse.setBounds(462, 72, 159, 23);
		frame.getContentPane().add(btnAddCourse);
		
		JButton btnDone = new JButton("Fertig");
		btnDone.setBounds(462, 215, 110, 23);
		frame.getContentPane().add(btnDone);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Statement myStmt = connection.getConnection().createStatement();
				ResultSet myRs = myStmt.executeQuery("SELECT nutzernamen FROM tbluser");
				while(myRs.next()) {
					cbClass.addItem(myRs.getString("Klasse"));
				}
			}
		});
	}
}

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
				String inputSubject = cbSubject.getSelectedItem().toString();
				String inputClass = cbClass.getSelectedItem().toString();
				String inputTeacher = cbTeacher.getSelectedItem().toString();
				String inputSpeaker = cbSpeaker.getSelectedItem().toString();
				
				
			}
		});
		button.setBounds(63, 290, 117, 23);
		frame.getContentPane().add(button);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(227, 131, 150, 96);
		frame.getContentPane().add(editorPane);
		
		JLabel label_4 = new JLabel("Sprecher");
		label_4.setBounds(63, 115, 70, 14);
		frame.getContentPane().add(label_4);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
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

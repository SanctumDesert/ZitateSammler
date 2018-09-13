import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Adminbereich {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public Adminbereich() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 658, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(98, 216, 134, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setBounds(42, 219, 46, 14);
		frame.getContentPane().add(lblUser);
		
		JButton btnLschen = new JButton("l\u00F6schen");
		btnLschen.setBounds(242, 215, 110, 23);
		frame.getContentPane().add(btnLschen);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(42, 73, 134, 20);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(186, 73, 134, 20);
		frame.getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(330, 73, 134, 20);
		frame.getContentPane().add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(474, 73, 134, 20);
		frame.getContentPane().add(comboBox_4);
		
		JLabel lblFach = new JLabel("Fach");
		lblFach.setBounds(42, 48, 46, 14);
		frame.getContentPane().add(lblFach);
		
		JLabel lblLehrer = new JLabel("Lehrer");
		lblLehrer.setBounds(186, 48, 46, 14);
		frame.getContentPane().add(lblLehrer);
		
		JLabel lblKlasse = new JLabel("Klasse");
		lblKlasse.setBounds(330, 48, 46, 14);
		frame.getContentPane().add(lblKlasse);
		
		JLabel lblJahrgang = new JLabel("Jahrgang");
		lblJahrgang.setBounds(474, 48, 56, 14);
		frame.getContentPane().add(lblJahrgang);
		
		JButton btnKursHinzufgen = new JButton("Kurs Hinzuf\u00FCgen");
		btnKursHinzufgen.setBounds(474, 120, 110, 23);
		frame.getContentPane().add(btnKursHinzufgen);
		
		JButton btnNewButton = new JButton("Fertig");
		btnNewButton.setBounds(474, 338, 110, 23);
		frame.getContentPane().add(btnNewButton);
	}
}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class ZitateHinzufuegen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZitateHinzufuegen window = new ZitateHinzufuegen();
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
	public ZitateHinzufuegen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 489, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(63, 73, 98, 20);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(171, 73, 98, 20);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(279, 73, 98, 20);
		frame.getContentPane().add(comboBox_2);
		
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
		button.setBounds(63, 290, 117, 23);
		frame.getContentPane().add(button);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(227, 131, 150, 96);
		frame.getContentPane().add(editorPane);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(63, 130, 98, 20);
		frame.getContentPane().add(comboBox_3);
		
		JLabel label_4 = new JLabel("Sprecher");
		label_4.setBounds(63, 115, 70, 14);
		frame.getContentPane().add(label_4);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(260, 290, 117, 23);
		frame.getContentPane().add(btnAbbrechen);
	}
}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class ZitatAendern {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZitatAendern window = new ZitatAendern();
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
	public ZitatAendern() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 402, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(25, 59, 98, 20);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(133, 59, 98, 20);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(241, 59, 98, 20);
		frame.getContentPane().add(comboBox_2);
		
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
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(189, 117, 150, 96);
		frame.getContentPane().add(editorPane);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(25, 116, 98, 20);
		frame.getContentPane().add(comboBox_3);
		
		JLabel label_4 = new JLabel("Sprecher");
		label_4.setBounds(25, 101, 70, 14);
		frame.getContentPane().add(label_4);
		
		JButton button_1 = new JButton("Speichern");
		button_1.setBounds(25, 276, 89, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Abbrechen");
		button_2.setBounds(250, 276, 89, 23);
		frame.getContentPane().add(button_2);
	}

}

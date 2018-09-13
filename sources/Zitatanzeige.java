import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;

public class Zitatanzeige {

	private JFrame frmHauptmenue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zitatanzeige window = new Zitatanzeige();
					window.frmHauptmenue.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Zitatanzeige() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHauptmenue = new JFrame();
		frmHauptmenue.setTitle("Hauptmen\u00FC");
		frmHauptmenue.setBounds(100, 100, 503, 575);
		frmHauptmenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHauptmenue.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(55, 61, 98, 20);
		frmHauptmenue.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(163, 61, 98, 20);
		frmHauptmenue.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(271, 61, 98, 20);
		frmHauptmenue.getContentPane().add(comboBox_2);
		
		JLabel lblZitateSuchen = new JLabel("Zitate suchen:");
		lblZitateSuchen.setBounds(55, 23, 70, 14);
		frmHauptmenue.getContentPane().add(lblZitateSuchen);
		
		JLabel lblKurs = new JLabel("Kurs");
		lblKurs.setBounds(55, 46, 70, 14);
		frmHauptmenue.getContentPane().add(lblKurs);
		
		JLabel lblKlasse = new JLabel("Klasse");
		lblKlasse.setBounds(163, 46, 70, 14);
		frmHauptmenue.getContentPane().add(lblKlasse);
		
		JLabel lblLehrer = new JLabel("Lehrer");
		lblLehrer.setBounds(271, 46, 70, 14);
		frmHauptmenue.getContentPane().add(lblLehrer);
		
		JButton btnZitateAnzeigen = new JButton("Zitate anzeigen");
		btnZitateAnzeigen.setBounds(55, 120, 119, 23);
		frmHauptmenue.getContentPane().add(btnZitateAnzeigen);
		
		JList list = new JList();
		list.setBounds(10, 176, 467, 254);
		frmHauptmenue.getContentPane().add(list);
		
		JButton btnZitatLschen = new JButton("Zitat l\u00F6schen");
		btnZitatLschen.setBounds(20, 441, 105, 23);
		frmHauptmenue.getContentPane().add(btnZitatLschen);
		
		JButton btnZitatndern = new JButton("Zitat \u00E4ndern");
		btnZitatndern.setBounds(346, 441, 105, 23);
		frmHauptmenue.getContentPane().add(btnZitatndern);
		
		JButton btnSchliessen = new JButton("Schliessen");
		btnSchliessen.setBounds(346, 502, 105, 23);
		frmHauptmenue.getContentPane().add(btnSchliessen);
		
		JButton btnNichtSchliessen = new JButton("Account");
		btnNichtSchliessen.setBounds(20, 502, 105, 23);
		frmHauptmenue.getContentPane().add(btnNichtSchliessen);
		
		JButton btnZitateHinzufgen = new JButton("Zitat Hinzuf\u00FCgen");
		btnZitateHinzufgen.setBounds(250, 120, 119, 23);
		frmHauptmenue.getContentPane().add(btnZitateHinzufgen);
		
		JButton btnNewButton = new JButton("Export");
		btnNewButton.setBounds(183, 441, 105, 23);
		frmHauptmenue.getContentPane().add(btnNewButton);
	}
}

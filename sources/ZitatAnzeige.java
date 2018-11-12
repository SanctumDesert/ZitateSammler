import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
//import com.lowagie.text.Document;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.pdf.PdfContentByte;
//import com.lowagie.text.pdf.PdfWriter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.FileOutputStream;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ZitatAnzeige {

	private JFrame frmHauptmenue;
	private JTable table;
	private int zitatID;
	DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZitatAnzeige window = new ZitatAnzeige();
					window.frmHauptmenue.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public ZitatAnzeige(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frmHauptmenue = new JFrame();
		
		Connect connection = new Connect();
		
		frmHauptmenue.setTitle("Hauptmen\u00FC");
		frmHauptmenue.setBounds(100, 100, 492, 651);
		frmHauptmenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHauptmenue.getContentPane().setLayout(null);
		frmHauptmenue.setVisible(true);
		
		JComboBox<String> cbSubject = new JComboBox<String>();
		cbSubject.setBounds(20, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbSubject);
		
		JComboBox<String> cbClass = new JComboBox<String>();
		cbClass.setBounds(128, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbClass);
		
		JComboBox<String> cbTeacher = new JComboBox<String>();
		cbTeacher.setBounds(236, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbTeacher);
		
		JLabel lblZitateSuchen = new JLabel("Zitate suchen:");
		lblZitateSuchen.setBounds(20, 30, 70, 14);
		frmHauptmenue.getContentPane().add(lblZitateSuchen);
		
		JLabel lblKurs = new JLabel("Kurs");
		lblKurs.setBounds(20, 53, 70, 14);
		frmHauptmenue.getContentPane().add(lblKurs);
		
		JLabel lblKlasse = new JLabel("Klasse");
		lblKlasse.setBounds(128, 53, 70, 14);
		frmHauptmenue.getContentPane().add(lblKlasse);
		
		JLabel lblLehrer = new JLabel("Lehrer");
		lblLehrer.setBounds(236, 53, 70, 14);
		frmHauptmenue.getContentPane().add(lblLehrer);
		
		
		
		JButton btnZitatLschen = new JButton("Zitat l\u00F6schen");
		btnZitatLschen.setToolTipText("Zitat l\u00F6schen");
		btnZitatLschen.setBounds(20, 441, 119, 25);
		frmHauptmenue.getContentPane().add(btnZitatLschen);
		
		JButton btnZitatndern = new JButton("Zitat \u00E4ndern");
		btnZitatndern.setToolTipText("Zitat \u00E4ndern");
		btnZitatndern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmHauptmenue.dispose();
				ZitatAendern changeQuote = new ZitatAendern(user, zitatID);
			}
		});
		btnZitatndern.setBounds(332, 441, 119, 25);
		frmHauptmenue.getContentPane().add(btnZitatndern);
		
		JButton btnSchliessen = new JButton("Schliessen");
		btnSchliessen.setToolTipText("Schliessen");
		btnSchliessen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmHauptmenue.dispose();
			}
		});
		btnSchliessen.setBounds(346, 502, 105, 25);
		frmHauptmenue.getContentPane().add(btnSchliessen);
		
		JButton btnNichtSchliessen = new JButton("Account");
		btnNichtSchliessen.setToolTipText("Account");
		btnNichtSchliessen.setBounds(20, 502, 105, 25);
		frmHauptmenue.getContentPane().add(btnNichtSchliessen);
		
		JButton btnZitateHinzufgen = new JButton("Zitat Hinzuf\u00FCgen");
		btnZitateHinzufgen.setToolTipText("Zitat Hinzuf\u00FCgen");
		btnZitateHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//frmHauptmenue.setVisible(false);
				frmHauptmenue.dispose();
				@SuppressWarnings("unused")
				ZitateHinzufuegen addZitat = new ZitateHinzufuegen(user);
				
			}
		});
		btnZitateHinzufgen.setBounds(332, 127, 119, 25);
		frmHauptmenue.getContentPane().add(btnZitateHinzufgen);
		
		JButton btnExport = new JButton("Export");
		btnExport.setToolTipText("Export");
		btnExport.setBounds(183, 441, 105, 25);
		frmHauptmenue.getContentPane().add(btnExport);
		
		JComboBox<String> cbSpeaker = new JComboBox<String>();
		cbSpeaker.setBounds(353, 68, 98, 20);
		frmHauptmenue.getContentPane().add(cbSpeaker);
		
		JLabel lblSprecher = new JLabel("Sprecher");
		lblSprecher.setBounds(353, 53, 70, 14);
		frmHauptmenue.getContentPane().add(lblSprecher);
		
		if(user.admin==true) {
			JButton btnAdmin = new JButton("Adminbereich");
			btnAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Adminbereich adminbereich = new Adminbereich(user);
					frmHauptmenue.dispose();
				}
			});
			btnAdmin.setToolTipText("Adminbereich");
			btnAdmin.setBounds(183, 556, 105, 25);
			frmHauptmenue.getContentPane().add(btnAdmin);
		}
		
		JButton btnZitateAnzeigen = new JButton("Zitate anzeigen");
		btnZitateAnzeigen.setToolTipText("Zitat anzeigen");
		btnZitateAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean firstFilter = true;
					model = (DefaultTableModel) table.getModel();
					if (model.getRowCount() > 0) {
					    for (int i = model.getRowCount() - 1; i > -1; i--) {
					        model.removeRow(i);
					    }
					}
					String qry = "SELECT z.ID, Zitat, u.Vorname, u.Nachname, k.Kurs, c.Klasse, t.name as Lehrer FROM zitatedb.tblzitate as z\r\n" + 
						       "JOIN tbluser as u on u.ID = z.SprecherID\r\n" + 
						       "JOIN tblKurs as k on z.KursID = k.ID\r\n" +
						       "JOIN tbllehrer as t on z.lehrerid = t.ID\r\n" +
						       "JOIN tblklassen as c ON z.KlasseID = c.ID";
					if(cbSubject.getSelectedIndex() > 0)
					{
						if(firstFilter)
						{
							firstFilter = false;
							qry += " WHERE k.Kurs = '" + cbSubject.getSelectedItem().toString() + "'";
						}
					}
					if(cbClass.getSelectedIndex() > 0)
					{
						if(firstFilter)
						{
							firstFilter = false;
							qry += " WHERE c.Klasse = '" + cbClass.getSelectedItem().toString() + "'";
						}
						else
						{
							qry += " AND c.Klasse = '" + cbClass.getSelectedItem().toString() + "'";
						}
					}
					if(cbTeacher.getSelectedIndex() > 0)
					{
						if(firstFilter)
						{
							firstFilter = false;
							qry += " WHERE t.name = '" + cbTeacher.getSelectedItem().toString() + "'";
						}
						else
						{
							qry += " AND t.name = '" + cbTeacher.getSelectedItem().toString() + "'";
						}
					}
					if(cbSpeaker.getSelectedIndex() > 0)
					{
						if(firstFilter)
						{
							firstFilter = false;
							qry += " WHERE z.SprecherID = (SELECT ID FROM tbluser WHERE Nutzername = '" + cbSpeaker.getSelectedItem().toString() + "')";
						}
						else
						{
							qry += " AND z.SprecherID = (SELECT ID FROM tbluser WHERE Nutzername = '" + cbSpeaker.getSelectedItem().toString() + "')";
						}
					}
					System.out.println(qry);
					Statement myStmt = connection.getConnection().createStatement();
					ResultSet myRs = myStmt.executeQuery(qry);
					while(myRs.next()) {
						model.addRow(new Object[] {myRs.getString("ID"), myRs.getString("Zitat"), myRs.getString("Vorname") + " " + myRs.getString("Nachname"), myRs.getString("Kurs"), myRs.getString("Lehrer"),  myRs.getString("Klasse")});
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnZitateAnzeigen.setBounds(20, 127, 119, 25);
		frmHauptmenue.getContentPane().add(btnZitateAnzeigen);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRowIndex = table.getSelectedRow();
				zitatID = Integer.parseInt(model.getValueAt(selectedRowIndex , 0).toString());
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Zitat", "Sprecher", "Kurs", "Lehrer", "Klasse"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, String.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 162, 431, 268);
		frmHauptmenue.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		JComboBox cbFormat = new JComboBox();
		cbFormat.setBounds(183, 500, 105, 26);
		frmHauptmenue.getContentPane().add(cbFormat);
		
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbFormat.getSelectedIndex() > -1) {
					if(cbFormat.getSelectedItem().toString().equals("Text")) {
						if(model.getRowCount() > 0) {
							exportToText();
						}
					}
					else if(cbFormat.getSelectedItem().toString().equals("CSV")) {
						exportToCSV();
					}
				}
			}
		});
		
		frmHauptmenue.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					Statement myStmt = connection.getConnection().createStatement();
					ResultSet myRs = myStmt.executeQuery("SELECT Klasse FROM tblklassen");
					cbClass.addItem("");
					while(myRs.next()) {
						cbClass.addItem(myRs.getString("Klasse"));
					}
					myRs = myStmt.executeQuery("SELECT Kurs FROM tblkurs");
					cbSubject.addItem("");
					while(myRs.next()) {
						cbSubject.addItem(myRs.getString("Kurs"));
					}
					myRs = myStmt.executeQuery("SELECT DISTINCT name FROM tbllehrer");
					cbTeacher.addItem("");
					while(myRs.next()) {
						cbTeacher.addItem(myRs.getString("name"));
					}
					myRs = myStmt.executeQuery("SELECT nutzername FROM tbluser");
					cbSpeaker.addItem("");
					while(myRs.next()) {
						cbSpeaker.addItem(myRs.getString("nutzername"));
					}
					cbFormat.addItem("Text");
					cbFormat.addItem("CSV");
					cbFormat.setSelectedIndex(0);
					
					cbSubject.setSelectedIndex(0);
					cbClass.setSelectedIndex(0);
					cbTeacher.setSelectedIndex(0);
					cbSpeaker.setSelectedIndex(0);
				}
				catch(Exception e){
					System.out.println(e);
				}

			}
		});
		
		

		frmHauptmenue.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				scrollPane.setSize(frmHauptmenue.getWidth()-60, scrollPane.getHeight());
			}
		});
	}
	
	public void exportToText() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String fileName = "ZitatExport" + dateFormat.format(System.currentTimeMillis()) + ".txt";
			
			FileWriter file = new FileWriter(new File(System.getProperty("user.home") + "/Desktop/" + fileName));
			System.out.println(model.getRowCount());
			for(int i = 0; i < model.getRowCount(); i++) {
				file.append("\"" + model.getValueAt(i, 1) + "\" gesprochen von " + model.getValueAt(i, 2) + System.getProperty("line.separator"));
			}
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exportToCSV() {
		
	}
}

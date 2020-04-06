package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


import com.cakir.config.Item;
import com.cakir.model.Mitarbeiter;
import com.cakir.model.Sortieraktion;
import com.cakir.model.Stunden;
import com.cakir.model.StundenDetails;
import com.cakir.serviceImpl.MitarbeiterServiceImpl;
import com.cakir.serviceImpl.StundenDetailsServiceImpl;
import com.cakir.serviceImpl.StundenServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.TabExpander;
import javax.swing.text.TableView;
import javax.swing.ListSelectionModel;

public class StundeAddJFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxMitarbeiter, comboBoxVon_3, comboBoxVon_2, comboBoxVon_1, comboBoxBis_1, comboBoxBis_2, comboBoxBis_3;
	private JLabel lblInfo;
	
	
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	StundenServiceImpl stundenService = new StundenServiceImpl();
	StundenDetailsServiceImpl stundenDetailsService = new StundenDetailsServiceImpl();
	public JTable tableVonBis;
	private long stundenId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StundeAddJFrame frame = new StundeAddJFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StundeAddJFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 999, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 51, 964, 106);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel label = new JLabel("MITARBEITER :");
		label.setBounds(10, 51, 85, 14);
		panel.add(label);
		
		comboBoxMitarbeiter = new JComboBox();
		comboBoxMitarbeiter.setBounds(97, 48, 152, 20);
		getComboBoxList(comboBoxMitarbeiter);
		panel.add(comboBoxMitarbeiter);
		
		comboBoxVon_1 = new JComboBox();
		comboBoxVon_1.setBounds(312, 48, 58, 20);
		getUhrZeitList(comboBoxVon_1);
		panel.add(comboBoxVon_1);
		
		JLabel label_1 = new JLabel("von:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(259, 51, 46, 14);
		panel.add(label_1);
		
		comboBoxBis_1 = new JComboBox();
		
		comboBoxBis_1.setEnabled(false);
		comboBoxBis_1.setBounds(414, 48, 58, 20);
		getUhrZeitList(comboBoxBis_1);
		panel.add(comboBoxBis_1);
		
		JLabel label_2 = new JLabel("bis:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(361, 51, 46, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("von:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(496, 51, 46, 14);
		panel.add(label_3);
		
		comboBoxVon_2 = new JComboBox();
		comboBoxVon_2.setBounds(549, 48, 58, 20);
		getUhrZeitList(comboBoxVon_2);
		panel.add(comboBoxVon_2);
		
		JLabel label_4 = new JLabel("bis:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(598, 51, 46, 14);
		panel.add(label_4);
		
		comboBoxBis_2 = new JComboBox();
		comboBoxBis_2.setEnabled(false);
		comboBoxBis_2.setBounds(651, 48, 58, 20);
		getUhrZeitList(comboBoxBis_2);
		panel.add(comboBoxBis_2);
		
		comboBoxBis_3 = new JComboBox();
		comboBoxBis_3.setEnabled(false);
		comboBoxBis_3.setBounds(884, 48, 58, 20);
		getUhrZeitList(comboBoxBis_3);
		panel.add(comboBoxBis_3);
		
		JLabel label_5 = new JLabel("bis:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(831, 51, 46, 14);
		panel.add(label_5);
		
		comboBoxVon_3 = new JComboBox();
		comboBoxVon_3.setBounds(782, 48, 58, 20);
		getUhrZeitList(comboBoxVon_3);
		panel.add(comboBoxVon_3);
		
		JLabel label_6 = new JLabel("von:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(729, 51, 46, 14);
		panel.add(label_6);
		
		JLabel lblrunde = new JLabel("1.Runde");
		lblrunde.setHorizontalAlignment(SwingConstants.CENTER);
		lblrunde.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblrunde.setBounds(281, 23, 191, 14);
		panel.add(lblrunde);
		
		JLabel lblrunde_1 = new JLabel("2.Runde");
		lblrunde_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblrunde_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblrunde_1.setBounds(518, 23, 191, 14);
		panel.add(lblrunde_1);
		
		JLabel lblrunde_2 = new JLabel("3.Runde");
		lblrunde_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblrunde_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblrunde_2.setBounds(751, 23, 191, 14);
		panel.add(lblrunde_2);
		
		JLabel lblNewLabel_1 = new JLabel("Sie müssen die Stunden erst für 1.Runde dann für 2. und für 3.Runde eingeben.");
		lblNewLabel_1.setForeground(new Color(32, 178, 170));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(281, 81, 661, 14);
		panel.add(lblNewLabel_1);
		
		
		comboBoxVon_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxVon_1.getSelectedItem() != "-- : --") {
					comboBoxBis_1.setEnabled(true);
					if(comboBoxBis_1.getSelectedItem() != "-- : --") {
						String bis1 = (String) comboBoxBis_1.getSelectedItem();
						int zeitBis1 = 0;
						if(bis1 != "-- : --") zeitBis1 = Integer.parseInt(bis1.replace(":", ""));
						
						String von1 = (String) comboBoxVon_1.getSelectedItem();
						int zeitVon1 = 0;
						if(von1 != "-- : --") zeitVon1 =Integer.parseInt(von1.replace(":", ""));
						
						if((zeitVon1 > zeitBis1) || ((zeitVon1 == zeitBis1) && zeitVon1 != 0) ) {
							JOptionPane.showMessageDialog(null, "Uhrzeit bis soll spätere Zeitpunkt als Uhrzeit von sein. ", "UHRZEIT FEHLER", JOptionPane.WARNING_MESSAGE);
							comboBoxVon_1.setSelectedIndex(1);
						} 
					}
				}
				else {
					comboBoxBis_1.setSelectedIndex(96);
					comboBoxBis_1.setEnabled(false);
				}
				
				
			}
		});
		
		comboBoxBis_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bis1 = (String) comboBoxBis_1.getSelectedItem();
				int zeitBis1 = 0;
				if(bis1 != "-- : --") zeitBis1 = Integer.parseInt(bis1.replace(":", ""));
				
				String von1 = (String) comboBoxVon_1.getSelectedItem();
				int zeitVon1 = 0;
				if(von1 != "-- : --") zeitVon1 =Integer.parseInt(von1.replace(":", ""));
				
				if(zeitVon1 > zeitBis1) {
					JOptionPane.showMessageDialog(null, "Uhrzeit bis soll spätere Zeitpunkt als Uhrzeit von sein. ", "UHRZEIT FEHLER", JOptionPane.WARNING_MESSAGE);
					comboBoxBis_1.setSelectedIndex(96);
				}
				
			}
		});
		
		comboBoxVon_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxVon_2.getSelectedItem() != "-- : --") {
					comboBoxBis_2.setEnabled(true);
					if(comboBoxBis_2.getSelectedItem() != "-- : --") {
						String bis2 = (String) comboBoxBis_2.getSelectedItem();
						int zeitBis2 = 0;
						if(bis2 != "-- : --") zeitBis2 = Integer.parseInt(bis2.replace(":", ""));
						
						String von2 = (String) comboBoxVon_2.getSelectedItem();
						int zeitVon2 = 0;
						if(von2 != "-- : --") zeitVon2 =Integer.parseInt(von2.replace(":", ""));
						
						if((zeitVon2 > zeitBis2) || ((zeitVon2 == zeitBis2) && zeitVon2 != 0) ) {
							JOptionPane.showMessageDialog(null, "Uhrzeit bis soll spätere Zeitpunkt als Uhrzeit von sein. ", "UHRZEIT FEHLER", JOptionPane.WARNING_MESSAGE);
							comboBoxVon_2.setSelectedIndex(1);
						} 
					}
				}
				else {
					comboBoxBis_2.setSelectedIndex(96);
					comboBoxBis_2.setEnabled(false);
				}
				
				
			}
		});
		
		comboBoxBis_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bis2 = (String) comboBoxBis_2.getSelectedItem();
				int zeitBis2 = 0;
				if(bis2 != "-- : --") zeitBis2 = Integer.parseInt(bis2.replace(":", ""));
				
				String von2 = (String) comboBoxVon_2.getSelectedItem();
				int zeitVon2 = 0;
				if(von2 != "-- : --") zeitVon2 =Integer.parseInt(von2.replace(":", ""));
				
				if(zeitVon2 > zeitBis2) {
					JOptionPane.showMessageDialog(null, "Uhrzeit bis soll spätere Zeitpunkt als Uhrzeit von sein. ", "UHRZEIT FEHLER", JOptionPane.WARNING_MESSAGE);
					comboBoxBis_2.setSelectedIndex(96);
				}
				
			}
		});
		
		
		comboBoxVon_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxVon_3.getSelectedItem() != "-- : --") {
					comboBoxBis_3.setEnabled(true);
					if(comboBoxBis_3.getSelectedItem() != "-- : --") {
						String bis3 = (String) comboBoxBis_3.getSelectedItem();
						int zeitBis3 = 0;
						if(bis3 != "-- : --") zeitBis3 = Integer.parseInt(bis3.replace(":", ""));
						
						String von3 = (String) comboBoxVon_3.getSelectedItem();
						int zeitVon3 = 0;
						if(von3 != "-- : --") zeitVon3 =Integer.parseInt(von3.replace(":", ""));
						
						if((zeitVon3 > zeitBis3) || ((zeitVon3 == zeitBis3) && zeitVon3 != 0) ) {
							JOptionPane.showMessageDialog(null, "Uhrzeit bis soll spätere Zeitpunkt als Uhrzeit von sein. ", "UHRZEIT FEHLER", JOptionPane.WARNING_MESSAGE);
							comboBoxVon_3.setSelectedIndex(1);
						} 
					}
				}
				else {
					comboBoxBis_3.setSelectedIndex(96);
					comboBoxBis_3.setEnabled(false);
				}
				
				
			}
		});
		
		comboBoxBis_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bis3 = (String) comboBoxBis_3.getSelectedItem();
				int zeitBis3 = 0;
				if(bis3 != "-- : --") zeitBis3 = Integer.parseInt(bis3.replace(":", ""));
				
				String von3 = (String) comboBoxVon_3.getSelectedItem();
				int zeitVon3 = 0;
				if(von3 != "-- : --") zeitVon3 =Integer.parseInt(von3.replace(":", ""));
				
				if(zeitVon3 > zeitBis3) {
					JOptionPane.showMessageDialog(null, "Uhrzeit bis soll spätere Zeitpunkt als Uhrzeit von sein. ", "UHRZEIT FEHLER", JOptionPane.WARNING_MESSAGE);
					comboBoxBis_3.setSelectedIndex(96);
				}
				
			}
		});
		
		
		JButton btnHinzufugen = new JButton("IN TABELLE HINZUFÜGEN");
		
		
		btnHinzufugen.setBackground(SystemColor.inactiveCaption);
		btnHinzufugen.setBounds(226, 192, 235, 23);
		btnHinzufugen.setBorder(null);
		contentPane.add(btnHinzufugen);
		
		JButton btnZuruck = new JButton("ZURÜCK");
		
		btnZuruck.setBorder(null);
		btnZuruck.setBackground(SystemColor.inactiveCaption);
		btnZuruck.setBounds(494, 192, 143, 23);
		contentPane.add(btnZuruck);
		
		JLabel lblNewLabel = new JLabel("SORTIERAKTION INFO :");
		lblNewLabel.setBounds(10, 21, 137, 14);
		contentPane.add(lblNewLabel);
		
		lblInfo = new JLabel("");
		lblInfo.setForeground(new Color(32, 178, 170));
		lblInfo.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblInfo.setBounds(142, 21, 832, 14);
		contentPane.add(lblInfo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 238, 964, 334);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 944, 312);
		panel_1.add(scrollPane);
		
		tableVonBis = new JTable();
		tableVonBis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableVonBis.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "Mitarbeiter", "von", "bis", "von", "bis", "von", "bis"
			}
		){
				Class[] columnTypes = new Class[] {
					 Object.class,  Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int row , int column) {return false;}
		});
		final DefaultTableModel model = (DefaultTableModel) tableVonBis.getModel();
		setJTableColumnsWidth(tableVonBis, 932, 0, 40, 10, 10, 10, 10, 10, 10);
		tableAnsicht(tableVonBis, 0);
		scrollPane.setViewportView(tableVonBis);
		
		JButton btnLschen = new JButton("MARKIERTE LÖSCHEN");
		
		btnLschen.setBorder(null);
		btnLschen.setBackground(SystemColor.inactiveCaption);
		btnLschen.setBounds(20, 583, 199, 23);
		contentPane.add(btnLschen);
		
		JButton btnAlleLschen = new JButton("ALLE LÖSCHEN");
		btnAlleLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
			}
		});
		btnAlleLschen.setBorder(null);
		btnAlleLschen.setBackground(SystemColor.inactiveCaption);
		btnAlleLschen.setBounds(254, 583, 169, 23);
		contentPane.add(btnAlleLschen);
		
		JButton btnSpeichern = new JButton("SPEICHERN");
		
		btnSpeichern.setBorder(null);
		btnSpeichern.setBackground(SystemColor.inactiveCaption);
		btnSpeichern.setBounds(468, 583, 169, 23);
		contentPane.add(btnSpeichern);
		
		JButton btnReset = new JButton("RESET");
		
		btnReset.setBorder(null);
		btnReset.setBackground(SystemColor.inactiveCaption);
		btnReset.setBounds(669, 192, 143, 23);
		contentPane.add(btnReset);
		
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowCount = model.getRowCount();
				
				if(rowCount < 1 && stundenId != 0) {
					
					JOptionPane.showMessageDialog(null, "Fügen Sie einen/mehrere Eintrag/Einträge hinzu.", "KEIN EINTRAG", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					for(int m = 0 ; m < rowCount ; m++) {
						
						Mitarbeiter mitarbeiter = mitarbeiterService.findById((long) model.getValueAt(m, 0));
						
						Stunden stunden = stundenService.findById(stundenId);
						
						StundenDetails details = new StundenDetails.StundenDetailsBuilder()
								.mitarbeiter(mitarbeiter)
								.stunden(stunden)
								.von_1((String) model.getValueAt(m, 2))
								.bis_1((String) model.getValueAt(m, 3))
								.von_2((String) model.getValueAt(m, 4))
								.bis_2((String) model.getValueAt(m, 5))
								.von_3((String) model.getValueAt(m, 6))
								.bis_3((String) model.getValueAt(m, 7))
								.build();
						
						stundenDetailsService.speichern(details);
						
					}
					
				JOptionPane.showMessageDialog(null, "Alle Daten wurden erfolgreich gespeichert.", "Speichern", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				}
				
			}
		});
		
		btnLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableVonBis.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Sie haben keine Zeile markiert.", "FEHLER", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					
					model.removeRow(tableVonBis.getSelectedRow());
					
				}
			}
		});
		
		btnZuruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnHinzufugen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String leerUhrzeit = "-- : --";
				boolean fehler = false;
				String von1 = (String) comboBoxVon_1.getSelectedItem();
				String bis1 = (String) comboBoxBis_1.getSelectedItem();
				String von2 = (String) comboBoxVon_2.getSelectedItem();
				String bis2 = (String) comboBoxBis_2.getSelectedItem();
				String von3 = (String) comboBoxVon_3.getSelectedItem();
				String bis3 = (String) comboBoxBis_3.getSelectedItem();
				
				if(von1 == leerUhrzeit ) {					
					fehler = true;
				} else if(bis1 == leerUhrzeit) {	
					fehler = true;
				}
				
				if(von2 != leerUhrzeit && bis2 == leerUhrzeit) {					
					fehler = true;
				} 
				
				if(von3 != leerUhrzeit && bis3 == leerUhrzeit) {					
					fehler = true;
				} 
				
				if(fehler) {
					JOptionPane.showMessageDialog(null, "Uhrzeit ist nicht stimmt.", "UHRZEIT FEHLER", JOptionPane.ERROR_MESSAGE);
				} else {
					Item<Object> itemMitarbeiter = (Item<Object>) comboBoxMitarbeiter.getSelectedItem();
					Mitarbeiter mitarbeiter = (Mitarbeiter) itemMitarbeiter.getValue();
					
					DefaultTableModel model = (DefaultTableModel) tableVonBis.getModel();
					
					model.addRow(new Object[] {mitarbeiter.getId(), mitarbeiter.getVorname()+" "+mitarbeiter.getNachname(),
							comboBoxVon_1.getSelectedItem(), comboBoxBis_1.getSelectedItem(), comboBoxVon_2.getSelectedItem(),
							comboBoxBis_2.getSelectedItem(), comboBoxVon_3.getSelectedItem(), comboBoxBis_3.getSelectedItem()});
					
					
					
					
					comboBoxVon_1.setSelectedIndex(0);
					comboBoxBis_1.setSelectedIndex(0);
					comboBoxVon_2.setSelectedIndex(0);
					comboBoxBis_2.setSelectedIndex(0);
					comboBoxVon_3.setSelectedIndex(0);
					comboBoxBis_3.setSelectedIndex(0);
					comboBoxMitarbeiter.setSelectedIndex(0);
				}
				
				
				
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxVon_1.setSelectedIndex(0);
				comboBoxBis_1.setSelectedIndex(0);
				comboBoxVon_2.setSelectedIndex(0);
				comboBoxBis_2.setSelectedIndex(0);
				comboBoxVon_3.setSelectedIndex(0);
				comboBoxBis_3.setSelectedIndex(0);
				comboBoxMitarbeiter.setSelectedIndex(0);
			}
		});
	}
	public void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages) {
		double total = 0;
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			total += percentages[i];
		}

		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
		}
	}

	public void tableAnsicht(JTable table, int removeColumn) {

		table.setRowHeight(25);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.DARK_GRAY);
		header.setForeground(Color.white);
		header.setFont(new Font("Tahoma", Font.BOLD, 15));

		table.setIntercellSpacing(new Dimension(5, 10));
		table.removeColumn(table.getColumnModel().getColumn(removeColumn));
	}
private void getComboBoxList(JComboBox comboBox) {
		
		List<Mitarbeiter> listMitarbeiter = mitarbeiterService.alleMitarbeiter();
		
		for(Mitarbeiter mitarbeiter : listMitarbeiter) {
			
			comboBox.addItem(new Item<Object>(mitarbeiter, mitarbeiter.getVorname()+" "+mitarbeiter.getNachname()));
		}
		
	}
	
	private void getUhrZeitList(JComboBox comboBox) {
		
		comboBox.addItem("-- : --");
		
		for(int stundeBeginn = 00 ; stundeBeginn < 24 ; stundeBeginn++) {
			
			for(int minBeginn = 00 ; minBeginn < 60 ; minBeginn=minBeginn+15) {
				
				comboBox.addItem(leftPad(stundeBeginn, 2)+":"+leftPad(minBeginn, 2));
			}
		}
		
		
	}
	
	public static String leftPad(int n, int padding) {
	    return String.format("%0" + padding + "d", n);
	}
	public void updateAktion(long id, Sortieraktion aktion) {
		
		lblInfo.setText("ID : "+aktion.getId()+" - "+"KUNDE : "+aktion.getKunde().getName()+" - GRUND : "+aktion.getGrund());
		
		stundenId = id;
		
		
	}
}
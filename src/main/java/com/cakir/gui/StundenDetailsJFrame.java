package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.cakir.model.Mitarbeiter;
import com.cakir.model.Sortieraktion;
import com.cakir.model.Stunden;
import com.cakir.model.StundenDetails;
import com.cakir.serviceImpl.MitarbeiterServiceImpl;
import com.cakir.serviceImpl.SortieraktionServiceImpl;
import com.cakir.serviceImpl.StundenDetailsServiceImpl;
import com.cakir.serviceImpl.StundenServiceImpl;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class StundenDetailsJFrame extends JFrame {

	private JPanel contentPane;
	private JTable tableDetails;
	private JLabel lblKunde, lblAktionId, lblDatum, lblStundenId;
	

	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	StundenServiceImpl stundenService = new StundenServiceImpl();
	StundenDetailsServiceImpl stundenDetailsService = new StundenDetailsServiceImpl();
	SortieraktionServiceImpl aktionService = new SortieraktionServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StundenDetailsJFrame frame = new StundenDetailsJFrame();
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
	 * @param id 
	 */
	public StundenDetailsJFrame() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 107, 744, 299);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 724, 277);
		panel.add(scrollPane);
		
		tableDetails = new JTable();
		tableDetails.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "Mitarbeiter", "von", "bis", "von", "bis", "von", "bis"
			}
		));
		final DefaultTableModel model = (DefaultTableModel) tableDetails.getModel();
		setJTableColumnsWidth(tableDetails, 713, 0, 40, 10, 10, 10, 10, 10, 10);
		tableAnsicht(tableDetails, 0);
		scrollPane.setViewportView(tableDetails);
		
		JLabel lblNewLabel = new JLabel("DATUM :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 25, 133, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SORTIERAKTION ID :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 50, 133, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("KUNDE :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 75, 133, 14);
		contentPane.add(lblNewLabel_2);
		
		lblDatum = new JLabel("");
		lblDatum.setForeground(new Color(32, 178, 170));
		lblDatum.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDatum.setBounds(153, 25, 477, 14);
		contentPane.add(lblDatum);
		
		lblAktionId = new JLabel("");
		lblAktionId.setForeground(new Color(32, 178, 170));
		lblAktionId.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblAktionId.setBounds(153, 50, 477, 14);
		contentPane.add(lblAktionId);
		
		lblKunde = new JLabel("");
		lblKunde.setForeground(new Color(32, 178, 170));
		lblKunde.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblKunde.setBounds(153, 75, 477, 14);
		contentPane.add(lblKunde);
		
		JButton btnLoschen = new JButton("MARKIERTE ZEILE LÖSCHEN");
		btnLoschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tableDetails.getSelectedRow() == -1) {
					
				
					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

										
					if(JOptionPane.showConfirmDialog(null, "Möchten Sie die Daten wirklich löschen?", "LÖSCHEN", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					{
						
						if(stundenDetailsService.delete((long) model.getValueAt(tableDetails.getSelectedRow(), 0))) {
							getTableList(Long.parseLong(lblStundenId.getText()));
						} else {
							JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten", "FEHLER", JOptionPane.WARNING_MESSAGE);
							
						}
						
					}
						
			}
			}
		});
		btnLoschen.setBackground(SystemColor.inactiveCaption);
		btnLoschen.setBounds(20, 417, 197, 23);
		btnLoschen.setBorder(null);
		contentPane.add(btnLoschen);
		
		JButton btnAlleLschen = new JButton("ALLE LÖSCHEN");
		btnAlleLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (JOptionPane.showConfirmDialog(null, "Möchten Sie alle Daten löschen?", "LÖSCHEN", JOptionPane.YES_NO_OPTION)) {
				case JOptionPane.YES_OPTION:
					
					Long stundenId = Long.valueOf(lblStundenId.getText());
					
					if(stundenDetailsService.deleteAll(stundenId)) {
						
						JOptionPane.showMessageDialog(null, "Die Daten wurden erfolgreich gelöscht.", "LÖSCHEN", JOptionPane.INFORMATION_MESSAGE);
						model.setRowCount(0);
						
						
					} else {
						JOptionPane.showMessageDialog(null, "Die Daten wurden nicht gelöscht. Ein Fehler ist aufgetreten.", "LÖSCHEN", JOptionPane.WARNING_MESSAGE);
					}
					
					break;

				default:
					break;
				}
			}
		});
		btnAlleLschen.setBorder(null);
		btnAlleLschen.setBackground(SystemColor.inactiveCaption);
		btnAlleLschen.setBounds(256, 417, 197, 23);
		contentPane.add(btnAlleLschen);
		
		lblStundenId = new JLabel("");
		lblStundenId.setBounds(97, 0, 46, 14);
		lblStundenId.setVisible(false);
		contentPane.add(lblStundenId);
		
		JButton btnNeuEintrag = new JButton("NEUE STUNDE HINZUFÜGEN");
		btnNeuEintrag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sortieraktion aktion = aktionService.findById(lblAktionId.getText());
				StundeAddJFrame sFrame = new StundeAddJFrame();
				sFrame.setLocationRelativeTo(null);
				sFrame.updateAktion(Long.parseLong(lblStundenId.getText()), aktion);
				sFrame.setVisible(true);
			}
		});
		btnNeuEintrag.setBorder(null);
		btnNeuEintrag.setBackground(SystemColor.inactiveCaption);
		btnNeuEintrag.setBounds(492, 417, 231, 23);
		contentPane.add(btnNeuEintrag);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTableList(Long.parseLong(lblStundenId.getText()));
			}
		});
		btnRefresh.setToolTipText("Aktualisieren");
		btnRefresh.setIcon(new ImageIcon(StundenDetailsJFrame.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		btnRefresh.setBorder(null);
		btnRefresh.setBackground(SystemColor.inactiveCaption);
		btnRefresh.setBounds(654, 73, 89, 23);
		contentPane.add(btnRefresh);
		
		
	}

	public void getTableList(long id) {
		
		
		Stunden stunden = stundenService.findById(id);
		
		lblStundenId.setText(String.valueOf(stunden.getId()));
		lblDatum.setText(stunden.getDatum());
		lblAktionId.setText(stunden.getAktion().getId());
		lblKunde.setText(stunden.getAktion().getKunde().getName());
		
		List<StundenDetails> listStundenDetails = stundenDetailsService.findByStunden(id);
		DefaultTableModel model = (DefaultTableModel) tableDetails.getModel();
		model.setRowCount(0);
		if (listStundenDetails != null) {
			for (StundenDetails details : listStundenDetails) {

				model.addRow(new Object[] {details.getId(), details.getMitarbeiter().getVorname()+" "+details.getMitarbeiter().getNachname(), details.getVon_1(), details.getBis_1(),
						details.getVon_2(), details.getBis_2(), details.getVon_3(), details.getBis_3()});
				
			}
		}
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}

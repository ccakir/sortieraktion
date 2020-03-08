package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cakir.model.Kunde;
import com.cakir.model.Mitarbeiter;
import com.cakir.serviceImpl.KundeServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class KundeJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfAdresse;
	private JTextField tfPlz;
	private JTextField tfStadt;
	private JTextField tfUidnummer;
	private JLabel lblId;
	private JLabel lblStern;
	private JLabel lblFelder;
	private JButton btnSpeichern;
	private JComboBox comboBox;
	
	KundeServiceImpl kundeService = new KundeServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KundeJFrame frame = new KundeJFrame();
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
	@SuppressWarnings("unchecked")
	public KundeJFrame() {
		setTitle("KUNDE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 729, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID :");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(39, 55, 117, 14);
		lblId.setVisible(false);
		contentPane.add(lblId);
		
		JLabel lblName = new JLabel("NAME :");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(39, 80, 117, 14);
		contentPane.add(lblName);
		
		JLabel lblAdresse = new JLabel("ADRESSE :");
		lblAdresse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresse.setBounds(39, 105, 117, 14);
		contentPane.add(lblAdresse);
		
		JLabel lblPlz = new JLabel("PLZ :");
		lblPlz.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlz.setBounds(39, 130, 117, 14);
		contentPane.add(lblPlz);
		
		JLabel lblStadt = new JLabel("STADT :");
		lblStadt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStadt.setBounds(39, 155, 117, 14);
		contentPane.add(lblStadt);
		
		JLabel lblLand = new JLabel("LAND :");
		lblLand.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLand.setBounds(39, 180, 117, 14);
		contentPane.add(lblLand);
		
		JLabel lblUidnummer = new JLabel("UIDNUMMER :");
		lblUidnummer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUidnummer.setBounds(39, 205, 117, 14);
		contentPane.add(lblUidnummer);
		
		tfId = new JTextField();
		tfId.setBounds(166, 52, 86, 20);
		tfId.setBorder(null);
		tfId.setVisible(false);
		tfId.setEnabled(false);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBorder(null);
		tfName.setBounds(166, 77, 259, 20);
		contentPane.add(tfName);
		
		tfAdresse = new JTextField();
		tfAdresse.setColumns(10);
		tfAdresse.setBorder(null);
		tfAdresse.setBounds(166, 102, 259, 20);
		contentPane.add(tfAdresse);
		
		tfPlz = new JTextField();
		tfPlz.setColumns(10);
		tfPlz.setBorder(null);
		tfPlz.setBounds(166, 127, 86, 20);
		contentPane.add(tfPlz);
		
		tfStadt = new JTextField();
		tfStadt.setColumns(10);
		tfStadt.setBorder(null);
		tfStadt.setBounds(166, 152, 139, 20);
		contentPane.add(tfStadt);
		
		tfUidnummer = new JTextField();
		tfUidnummer.setColumns(10);
		tfUidnummer.setBorder(null);
		tfUidnummer.setBounds(166, 202, 139, 20);
		contentPane.add(tfUidnummer);
		
		comboBox = new JComboBox();
		comboBox.setBounds(166, 177, 139, 20);
		comboBox.addItem("ÖSTERREICH");
		contentPane.add(comboBox);
		
		lblStern = new JLabel("*");
		lblStern.setBounds(435, 80, 46, 14);
		contentPane.add(lblStern);
		
		lblFelder = new JLabel("* Erforderliche Felder");
		lblFelder.setBounds(166, 233, 139, 14);
		contentPane.add(lblFelder);
		
		btnSpeichern = new JButton("SPEICHERN");
		
		btnSpeichern.setBackground(SystemColor.inactiveCaption);
		btnSpeichern.setBounds(163, 258, 105, 23);
		contentPane.add(btnSpeichern);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setBackground(SystemColor.inactiveCaption);
		
		btnReset.setBounds(292, 258, 105, 23);
		contentPane.add(btnReset);
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);

			comboBox.addItem(obj.getDisplayCountry(Locale.GERMAN));
			

		}
		
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfName.getText().equals("")) {
					lblStern.setForeground(Color.RED);
					lblFelder.setForeground(Color.RED);
					JOptionPane.showMessageDialog(null, "Geben Sie einen Name ein.", "", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					lblStern.setForeground(Color.BLACK);
					lblFelder.setForeground(Color.BLACK);
					Kunde kunde = new Kunde.KundeBuilder()
							.name(tfName.getText())
							.adresse(tfAdresse.getText())
							.plz(tfPlz.getText())
							.stadt(tfStadt.getText())
							.land(String.valueOf(comboBox.getSelectedItem()))
							.uidnummer(tfUidnummer.getText())
							.build();
					
					if(!tfId.getText().isEmpty()) {
						
						//Kunde update
						kunde.setId(Long.parseLong(tfId.getText()));
						if(kundeService.update(kunde)) {
							JOptionPane.showMessageDialog(null, "Kunde wurde erfolgreich aktualisiert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.", "UPDATE", JOptionPane.INFORMATION_MESSAGE);
							
							getLeereFelder();
							tfId.setText(null);
							tfId.setVisible(false);
							lblId.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Kunde wurde nicht aktualisiert.\n Ein Fehler ist aufgetreten", "FEHLER", JOptionPane.ERROR_MESSAGE);
							
						}
						
					} else {
						//Kunde speichern
						if(kundeService.speichern(kunde)) {
							JOptionPane.showMessageDialog(null, "Kunde wurde erfolgreich gespeichert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.", "SPEICHERN", JOptionPane.INFORMATION_MESSAGE);
							
							getLeereFelder();
							
						} else {
							
							JOptionPane.showMessageDialog(null, "Kunde wurde nicht gespeichert.\nEin Fehler ist aufgetreten", "FEHLER", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLeereFelder();
			}
		});
	}

	protected void getLeereFelder() {
		tfName.setText("");
		tfAdresse.setText("");
		tfPlz.setText("");
		tfStadt.setText("");
		tfUidnummer.setText("");
		comboBox.setSelectedIndex(0);
	}
	public void updateFelder(Kunde updateKunde) {
		
		lblId.setVisible(true);
		tfId.setVisible(true);
		
		tfId.setText(String.valueOf(updateKunde.getId()));
		tfName.setText(updateKunde.getName());
		tfAdresse.setText(updateKunde.getAdresse());
		tfPlz.setText(updateKunde.getPlz());
		tfStadt.setText(updateKunde.getStadt());
		tfUidnummer.setText(updateKunde.getUidnummer());
		comboBox.setSelectedItem(updateKunde.getLand());
	}
}

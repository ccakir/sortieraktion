package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cakir.model.Kontakt;
import com.cakir.model.Kunde;
import com.cakir.model.Teil;
import com.cakir.serviceImpl.KundeServiceImpl;
import com.cakir.serviceImpl.TeilServiceImpl;
import com.cakir.config.Item;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeilJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfTeilename;
	private JTextField tfTeilenummer;
	private JLabel lblId, lblStern1, lblStern2, lblStern3, lblErforderlich;
	private JComboBox<Item<Object>> comboBoxKunde;
	
	TeilServiceImpl teilService = new TeilServiceImpl();
	KundeServiceImpl kundeService = new KundeServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeilJFrame frame = new TeilJFrame();
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
	public TeilJFrame() {
		setTitle("TEIL");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID :");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setVisible(false);
		lblId.setBounds(57, 41, 100, 14);
		contentPane.add(lblId);
		
		JLabel lblTeilename = new JLabel("TEILENAME :");
		lblTeilename.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeilename.setBounds(57, 66, 100, 14);
		contentPane.add(lblTeilename);
		
		JLabel lblTeilenummer = new JLabel("TEILENUMMER :");
		lblTeilenummer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeilenummer.setBounds(57, 91, 100, 14);
		contentPane.add(lblTeilenummer);
		
		JLabel lblKunde = new JLabel("KUNDE :");
		lblKunde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKunde.setBounds(57, 116, 100, 14);
		contentPane.add(lblKunde);
		
		tfId = new JTextField();
		tfId.setBounds(167, 38, 86, 20);
		tfId.setEnabled(false);
		tfId.setVisible(false);
		contentPane.add(tfId);
		tfId.setBorder(null);
		tfId.setColumns(10);
		
		tfTeilename = new JTextField();
		tfTeilename.setColumns(10);
		tfTeilename.setBorder(null);
		tfTeilename.setBounds(167, 63, 157, 20);
		contentPane.add(tfTeilename);
		
		tfTeilenummer = new JTextField();
		tfTeilenummer.setColumns(10);
		tfTeilenummer.setBorder(null);
		tfTeilenummer.setBounds(167, 88, 157, 20);
		contentPane.add(tfTeilenummer);
		
		lblStern1 = new JLabel("*");
		lblStern1.setBounds(334, 66, 46, 14);
		contentPane.add(lblStern1);
		
		lblStern2 = new JLabel("*");
		lblStern2.setBounds(334, 91, 46, 14);
		contentPane.add(lblStern2);
		
		comboBoxKunde = new JComboBox<Item<Object>>();
		comboBoxKunde.setForeground(Color.BLACK);
		comboBoxKunde.setBounds(167, 113, 241, 20);
		contentPane.add(comboBoxKunde);
		
		comboBoxKundeList();
		
		lblStern3 = new JLabel("*");
		lblStern3.setBounds(418, 116, 46, 14);
		contentPane.add(lblStern3);
		
		lblErforderlich = new JLabel("* Erforderliche Felder");
		lblErforderlich.setBounds(166, 144, 139, 14);
		contentPane.add(lblErforderlich);
		
		JButton btnSpeichern = new JButton("SPEICHERN");
		
		btnSpeichern.setBackground(SystemColor.inactiveCaption);
		btnSpeichern.setBounds(167, 184, 105, 23);
		contentPane.add(btnSpeichern);
		
		JButton btnReset = new JButton("RESET");
		
		btnReset.setBackground(SystemColor.inactiveCaption);
		btnReset.setBounds(303, 184, 105, 23);
		contentPane.add(btnReset);
		
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				if(tfTeilename.getText().equals("") || tfTeilenummer.getText().equals("") || comboBoxKunde.getSelectedIndex() == 0) {
					lblStern1.setForeground(Color.RED);
					lblStern2.setForeground(Color.RED);
					lblStern3.setForeground(Color.RED);
					lblErforderlich.setForeground(Color.RED);
					JOptionPane.showMessageDialog(null, "Füllen Sie Pflichtfelder aus.", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					lblStern1.setForeground(Color.BLACK);
					lblStern2.setForeground(Color.BLACK);
					lblStern3.setForeground(Color.BLACK);
					lblErforderlich.setForeground(Color.BLACK);
					
					@SuppressWarnings("unchecked")
					Item<Object> itemKunde = (Item<Object>) comboBoxKunde.getSelectedItem();
					Object kunde = itemKunde.getValue();
					
					Teil teil = new Teil.TeilBuilder()
							.teilename(tfTeilename.getText())
							.teilenummer(tfTeilenummer.getText())
							.kunde((Kunde) kunde)
							.build();
					
					if(!tfId.getText().isEmpty()) {
						
						//Teil update
						teil.setId(Long.parseLong(tfId.getText()));
						if(teilService.update(teil)) {
							JOptionPane.showMessageDialog(null, "Teil wurde erfolgreich aktualisiert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.", "UPDATE", JOptionPane.INFORMATION_MESSAGE);
							
							leereFelder();
							lblId.setVisible(false);
							tfId.setText(null);
							tfId.setVisible(false);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Teil wurde nicht aktualisiert.\n Ein Fehler ist aufgetreten", "FEHLER", JOptionPane.ERROR_MESSAGE);
							
						}
						
					} else {
						//Teil speichern
						
						if(teilService.speichern(teil)) {
							JOptionPane.showMessageDialog(null, "Teil wurde erfolgreich gespeichert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.", "SPEICHERN", JOptionPane.INFORMATION_MESSAGE);
							leereFelder();
						} else {
							JOptionPane.showMessageDialog(null, "Teil wurde nicht gespeichert.\n Ein Fehler ist aufgetreten", "FEHLER", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			
			}
		});
		
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				leereFelder();
			}
		});
	}

	protected void leereFelder() {
		
		tfTeilename.setText("");
		tfTeilenummer.setText("");
		comboBoxKunde.setSelectedIndex(0);
		
	}

	private void comboBoxKundeList() {


		comboBoxKunde.addItem(new Item<Object>("", "Wählen Sie einen Kunde aus."));
		
		List<Kunde> listKunde = kundeService.alleKunden();
		
		for( Kunde kunde : listKunde ) {
			
			comboBoxKunde.addItem(new Item<Object>(kunde, kunde.getName()));
		}
		
	}

	public void updateFelder(Teil updateTeil) {


		lblId.setVisible(true);
		tfId.setVisible(true);
		tfId.setText(String.valueOf(updateTeil.getId()));
		tfTeilename.setText(updateTeil.getTeilename());
		tfTeilenummer.setText(updateTeil.getTeilenummer());
		
		comboBoxKunde.addItem(new Item<Object>(updateTeil.getKunde(), updateTeil.getKunde().getName()));
		comboBoxKunde.setSelectedItem(new Item<Object>(updateTeil.getKunde(), updateTeil.getKunde().getName()));
		
	}
}

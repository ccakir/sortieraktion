package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cakir.model.Kontakt;
import com.cakir.serviceImpl.KontaktServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KontaktJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfVorname;
	private JTextField tfNachname;
	private JTextField tfEmail;
	private JTextField tfTel;
	private JTextField tfFax;
	private JLabel lblId;
	private JLabel lblStern1;
	private JLabel lblStern2;
	private JLabel lblErforderlich;
	private JButton btnSpeichern;
	private JButton btnReset;

	private static final String EMAIL_PATTERN = 
		    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private JLabel lblEmailValidation;
	
	KontaktServiceImpl kontaktService = new KontaktServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KontaktJFrame frame = new KontaktJFrame();
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
	public KontaktJFrame() {
		setTitle("KONTAKT");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 656, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID :");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(24, 47, 116, 14);
		lblId.setVisible(false);
		contentPane.add(lblId);
		
		JLabel lblVorname = new JLabel("VORNAME :");
		lblVorname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVorname.setBounds(24, 72, 116, 14);
		contentPane.add(lblVorname);
		
		JLabel lblNachname = new JLabel("NACHNAME :");
		lblNachname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNachname.setBounds(24, 98, 116, 14);
		contentPane.add(lblNachname);
		
		JLabel lblEmail = new JLabel("EMAIL :");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(24, 123, 116, 14);
		contentPane.add(lblEmail);
		
		JLabel lblTelefon = new JLabel("TELEFON :");
		lblTelefon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefon.setBounds(24, 148, 116, 14);
		contentPane.add(lblTelefon);
		
		JLabel lblFax = new JLabel("FAX :");
		lblFax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFax.setBounds(24, 173, 116, 14);
		contentPane.add(lblFax);
		
		tfId = new JTextField();
		tfId.setBounds(150, 44, 86, 20);
		tfId.setBorder(null);
		tfId.setEnabled(false);
		tfId.setVisible(false);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfVorname = new JTextField();
		tfVorname.setColumns(10);
		tfVorname.setBorder(null);
		tfVorname.setBounds(150, 69, 134, 20);
		contentPane.add(tfVorname);
		
		tfNachname = new JTextField();
		tfNachname.setColumns(10);
		tfNachname.setBorder(null);
		tfNachname.setBounds(150, 95, 134, 20);
		contentPane.add(tfNachname);
		
		tfEmail = new JTextField();
		
		tfEmail.setColumns(10);
		tfEmail.setBorder(null);
		tfEmail.setBounds(150, 120, 196, 20);
		contentPane.add(tfEmail);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		tfTel.setBorder(null);
		tfTel.setBounds(150, 145, 134, 20);
		contentPane.add(tfTel);
		
		tfFax = new JTextField();
		tfFax.setColumns(10);
		tfFax.setBorder(null);
		tfFax.setBounds(150, 170, 134, 20);
		contentPane.add(tfFax);
		
		lblStern1 = new JLabel("*");
		lblStern1.setBounds(294, 72, 46, 14);
		contentPane.add(lblStern1);
		
		lblStern2 = new JLabel("*");
		lblStern2.setBounds(294, 98, 46, 14);
		contentPane.add(lblStern2);
		
		lblErforderlich = new JLabel("* Erforderliche Felder");
		lblErforderlich.setBounds(150, 201, 139, 14);
		contentPane.add(lblErforderlich);
		
		btnSpeichern = new JButton("SPEICHERN");
		
		btnSpeichern.setBackground(SystemColor.inactiveCaption);
		btnSpeichern.setBounds(91, 236, 105, 23);
		contentPane.add(btnSpeichern);
		
		btnReset = new JButton("RESET");
		
		btnReset.setBackground(SystemColor.inactiveCaption);
		btnReset.setBounds(229, 236, 105, 23);
		contentPane.add(btnReset);
		
		lblEmailValidation = new JLabel("");
		lblEmailValidation.setBounds(356, 123, 134, 14);
		contentPane.add(lblEmailValidation);
		
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfVorname.getText().equals("") || tfNachname.getText().equals("")) {
					lblStern1.setForeground(Color.RED);
					lblStern2.setForeground(Color.RED);
					lblErforderlich.setForeground(Color.RED);
					JOptionPane.showMessageDialog(null, "Vorname und Nachname Felder sind Pflichtfelder.", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					lblStern1.setForeground(Color.BLACK);
					lblStern2.setForeground(Color.BLACK);
					lblErforderlich.setForeground(Color.BLACK);
					lblEmailValidation.setVisible(false);
					
					Kontakt kontakt = new Kontakt.KontaktBuilder()
							.vorname(tfVorname.getText())
							.nachname(tfNachname.getText())
							.tel(tfTel.getText())
							.fax(tfFax.getText())
							.email(tfEmail.getText())
							.build();
					if(!tfId.getText().isEmpty()) {
						
						//Kontakt update
						kontakt.setId(Long.parseLong(tfId.getText()));
						if(kontaktService.update(kontakt)) {
							JOptionPane.showMessageDialog(null, "Kontakt wurde erfolgreich aktualisiert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.", "UPDATE", JOptionPane.INFORMATION_MESSAGE);
							
							leereFelder();
							lblId.setVisible(false);
							tfId.setText(null);
							tfId.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Kontakt wurde nicht aktualisiert.\n Ein Fehler ist aufgetreten", "FEHLER", JOptionPane.ERROR_MESSAGE);
							
						}
						
					} else {
						//Kontakt speichern
						
						if(kontaktService.speichern(kontakt)) {
							JOptionPane.showMessageDialog(null, "Kontakt wurde erfolgreich gespeichert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.", "SPEICHERN", JOptionPane.INFORMATION_MESSAGE);
							leereFelder();
						} else {
							JOptionPane.showMessageDialog(null, "Kontakt wurde nicht gespeichert.\n Ein Fehler ist aufgetreten", "FEHLER", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(!tfEmail.getText().matches(EMAIL_PATTERN)) {
					
					lblEmailValidation.setVisible(true);
					lblEmailValidation.setText("Email ist ungültig!");
					lblEmailValidation.setForeground(Color.RED);
				} else {
					lblEmailValidation.setVisible(true);
					lblEmailValidation.setForeground(Color.GREEN);
					lblEmailValidation.setText("Email ist gültig.");
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
		
		tfVorname.setText("");
		tfNachname.setText("");
		tfTel.setText("");
		tfFax.setText("");
		tfEmail.setText("");
		lblEmailValidation.setVisible(false);
		
		
	}
	public void updateFelder(Kontakt updateKontakt) {
		
		lblId.setVisible(true);
		tfId.setVisible(true);
		tfId.setText(String.valueOf(updateKontakt.getId()));
		tfVorname.setText(updateKontakt.getVorname());
		tfNachname.setText(updateKontakt.getNachname());
		tfTel.setText(updateKontakt.getTel());
		tfFax.setText(updateKontakt.getFax());
		tfEmail.setText(updateKontakt.getEmail());
		
	}
}

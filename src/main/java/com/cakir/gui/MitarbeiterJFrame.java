package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cakir.model.Mitarbeiter;
import com.cakir.serviceImpl.MitarbeiterServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MitarbeiterJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfVorname;
	private JTextField tfNachname;
	private JTextField tfEmail;
	private JTextField tfTel;
	private JLabel lblFelder, lblStern1, lblStern, lblId, lblEmailValidation; 
	
	private static final String EMAIL_PATTERN = 
		    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MitarbeiterJFrame frame = new MitarbeiterJFrame();
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
	public MitarbeiterJFrame() {
		setTitle("NEUE MITARBEITER");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 518, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID :");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setVerticalAlignment(SwingConstants.TOP);
		lblId.setBounds(41, 37, 82, 14);
		lblId.setVisible(false);
		contentPane.add(lblId);
		
		JLabel lblNewLabel_1 = new JLabel("VORNAME :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(41, 62, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NACHNAME :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(41, 87, 82, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("EMAIL :");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(41, 112, 82, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("TELEFON :");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(41, 137, 82, 14);
		contentPane.add(lblNewLabel_4);
		
		tfId = new JTextField();
		tfId.setBounds(138, 34, 86, 20);
		tfId.setBorder(null);
		tfId.setVisible(false);
		tfId.setEnabled(false);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfVorname = new JTextField();
		tfVorname.setColumns(10);
		tfVorname.setBorder(null);
		tfVorname.setBounds(138, 59, 153, 20);
		contentPane.add(tfVorname);
		
		tfNachname = new JTextField();
		tfNachname.setColumns(10);
		tfNachname.setBorder(null);
		tfNachname.setBounds(138, 84, 153, 20);
		contentPane.add(tfNachname);
		
		tfEmail = new JTextField();
		
		
		tfEmail.setColumns(10);
		tfEmail.setBorder(null);
		tfEmail.setBounds(138, 109, 153, 20);
		contentPane.add(tfEmail);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		tfTel.setBorder(null);
		tfTel.setBounds(138, 134, 153, 20);
		contentPane.add(tfTel);
		
		JButton btnSpeichern = new JButton("SPEICHERN");
		
		btnSpeichern.setBackground(SystemColor.inactiveCaption);
		btnSpeichern.setBounds(60, 208, 105, 23);
		contentPane.add(btnSpeichern);
		
		JButton btnReset = new JButton("RESET");
		
		btnReset.setBackground(SystemColor.inactiveCaption);
		btnReset.setBounds(202, 208, 89, 23);
		contentPane.add(btnReset);
		
		lblStern = new JLabel("*");
		lblStern.setBounds(301, 62, 46, 14);
		contentPane.add(lblStern);
		
		lblStern1 = new JLabel("*");
		lblStern1.setBounds(301, 87, 46, 14);
		contentPane.add(lblStern1);
		
		lblFelder = new JLabel("* Erforderliche Felder");
		lblFelder.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblFelder.setForeground(Color.BLACK);
		lblFelder.setBounds(138, 168, 153, 14);
		contentPane.add(lblFelder);
		
		lblEmailValidation = new JLabel("Email ist ungültig!");
		lblEmailValidation.setBounds(301, 112, 113, 14);
		lblEmailValidation.setForeground(Color.RED);
		lblEmailValidation.setVisible(false);
		contentPane.add(lblEmailValidation);
		
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(!tfEmail.getText().matches(EMAIL_PATTERN)) {
					
					lblEmailValidation.setForeground(Color.RED);
					lblEmailValidation.setText("Email ist ungültig!");
					lblEmailValidation.setVisible(true);
					
				} else {
					lblEmailValidation.setText("Email ist gültig.");
					lblEmailValidation.setForeground(Color.GREEN);
					
				}
			}
		});
		
		
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfVorname.getText().equals("") || tfNachname.getText().equals("")) {
					lblFelder.setForeground(Color.RED);
					lblStern.setForeground(Color.RED);
					lblStern1.setForeground(Color.RED);
					
					JOptionPane.showMessageDialog(null, "Vorname und Nachname Felder sind Pflichtfelder.", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					lblFelder.setForeground(Color.BLACK);
					lblStern.setForeground(Color.BLACK);
					lblStern1.setForeground(Color.BLACK);
					lblEmailValidation.setVisible(false);
					Mitarbeiter mitarbeiter = new Mitarbeiter.MitarbeiterBuilder()
							.vorname(tfVorname.getText())
							.nachname(tfNachname.getText())
							.email(tfEmail.getText())
							.tel(tfTel.getText())
							.build();
					
					if(!tfId.getText().isEmpty()) {
						//Mitarbeiter update
						
						mitarbeiter.setId(Long.parseLong(tfId.getText()));
						if(mitarbeiterService.update(mitarbeiter)) {
							JOptionPane.showMessageDialog(null, "Mitarbeiter wurde erfolgreich aktualisiert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.", "UPDATE", JOptionPane.INFORMATION_MESSAGE);
							
							getLeereFelder();
							lblId.setVisible(false);
							tfId.setText(null);
							tfId.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Mitarbeiter wurde nicht aktualisiert.\n Ein Fehler ist aufgetreten", "FEHLER", JOptionPane.ERROR_MESSAGE);
							
						}
					} else {
						
						//Neue Mitarbeiter speichern
						if(mitarbeiterService.speichern(mitarbeiter)) {
							
							JOptionPane.showMessageDialog(null, "Mitarbeiter wurde erfolgreich gespeichert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.", "SPEICHERN", JOptionPane.INFORMATION_MESSAGE);
							
							getLeereFelder();
							
						} else {
							
							JOptionPane.showMessageDialog(null, "Mitarbeiter wurde nicht gespeichert.\n Ein Fehler ist aufgetreten", "FEHLER", JOptionPane.ERROR_MESSAGE);
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
		
		lblEmailValidation.setVisible(false);
		lblEmailValidation.setForeground(Color.RED);
		tfVorname.setText(null);
		tfNachname.setText(null);
		tfEmail.setText(null);
		tfTel.setText(null);
	}

	public void updateFelder(Mitarbeiter updateMitarbeiter) {
		
		lblId.setVisible(true);
		tfId.setVisible(true);
		
		tfId.setText(String.valueOf(updateMitarbeiter.getId()));
		tfVorname.setText(updateMitarbeiter.getVorname());
		tfNachname.setText(updateMitarbeiter.getNachname());
		tfEmail.setText(updateMitarbeiter.getEmail());
		tfTel.setText(updateMitarbeiter.getTel());
	}
}

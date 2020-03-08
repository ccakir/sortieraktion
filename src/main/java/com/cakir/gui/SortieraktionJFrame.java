package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class SortieraktionJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField textField;
	private JTextField tfGrund;
	private JTextField tfDunsnummer;
	private JTextField textField_1;
	private JTextField tfNormal;
	private JTextField tfRE;
	private JTextField tfZusatzkosten;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox chckBoxReturn;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JRadioButton rbtnFreigabeJa, rbtnFreigabeNein; 
	private JLabel lblFreigabe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortieraktionJFrame frame = new SortieraktionJFrame();
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
	public SortieraktionJFrame() {
		setTitle("SORTIERAKTION");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 760, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(73, 26, 123, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDatum = new JLabel("DATUM :");
		lblDatum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatum.setBounds(73, 51, 123, 14);
		contentPane.add(lblDatum);
		
		JLabel lblKunde = new JLabel("KUNDE :");
		lblKunde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKunde.setBounds(73, 76, 123, 14);
		contentPane.add(lblKunde);
		
		JLabel lblTeil = new JLabel("TEIL :");
		lblTeil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeil.setBounds(73, 101, 123, 14);
		contentPane.add(lblTeil);
		
		tfId = new JTextField();
		tfId.setBounds(206, 23, 86, 20);
		tfId.setBorder(null);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		textField = new JFormattedTextField(new Date());
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(206, 48, 86, 20);
		contentPane.add(textField);
		
		JComboBox comboBoxKunde = new JComboBox();
		comboBoxKunde.setBounds(205, 73, 311, 20);
		contentPane.add(comboBoxKunde);
		
		JComboBox comboBoxTeil = new JComboBox();
		comboBoxTeil.setBounds(206, 98, 192, 20);
		contentPane.add(comboBoxTeil);
		
		JLabel lblKontakt = new JLabel("KONTAKT :");
		lblKontakt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKontakt.setBounds(73, 126, 123, 14);
		contentPane.add(lblKontakt);
		
		JComboBox comboBoxKontakt = new JComboBox();
		comboBoxKontakt.setBounds(206, 123, 192, 20);
		contentPane.add(comboBoxKontakt);
		
		JLabel lblMitarbeiter = new JLabel("MITARBEITER :");
		lblMitarbeiter.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMitarbeiter.setBounds(73, 151, 123, 14);
		contentPane.add(lblMitarbeiter);
		
		JComboBox comboBoxMitarbeiter = new JComboBox();
		comboBoxMitarbeiter.setBounds(206, 148, 192, 20);
		contentPane.add(comboBoxMitarbeiter);
		
		JLabel lblGrund = new JLabel("GRUND :");
		lblGrund.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGrund.setBounds(73, 176, 123, 14);
		contentPane.add(lblGrund);
		
		tfGrund = new JTextField();
		tfGrund.setColumns(10);
		tfGrund.setBorder(null);
		tfGrund.setBounds(206, 173, 504, 20);
		contentPane.add(tfGrund);
		
		JLabel lblDunsnummer = new JLabel("DUNSNUMMER :");
		lblDunsnummer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDunsnummer.setBounds(73, 201, 123, 14);
		contentPane.add(lblDunsnummer);
		
		tfDunsnummer = new JTextField();
		tfDunsnummer.setColumns(10);
		tfDunsnummer.setBorder(null);
		tfDunsnummer.setBounds(206, 198, 133, 20);
		contentPane.add(tfDunsnummer);
		
		JLabel lblAnweisung = new JLabel("ANWEISUNG :");
		lblAnweisung.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnweisung.setBounds(73, 226, 123, 14);
		contentPane.add(lblAnweisung);
		
		JLabel lblStundensatzNormal = new JLabel("STUNDENSATZ NORMAL :");
		lblStundensatzNormal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStundensatzNormal.setBounds(27, 251, 169, 14);
		contentPane.add(lblStundensatzNormal);
		
		JLabel lblStundesatzRe = new JLabel("STUNDESATZ RE :");
		lblStundesatzRe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStundesatzRe.setBounds(73, 276, 123, 14);
		contentPane.add(lblStundesatzRe);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBounds(206, 223, 504, 20);
		contentPane.add(textField_1);
		
		tfNormal = new JTextField();
		tfNormal.setColumns(10);
		tfNormal.setBorder(null);
		tfNormal.setBounds(206, 248, 52, 20);
		contentPane.add(tfNormal);
		
		tfRE = new JTextField();
		tfRE.setColumns(10);
		tfRE.setBorder(null);
		tfRE.setBounds(206, 273, 52, 20);
		contentPane.add(tfRE);
		
		JLabel lblZusatzkosten = new JLabel("ZUSATZKOSTEN :");
		lblZusatzkosten.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZusatzkosten.setBounds(73, 301, 123, 14);
		contentPane.add(lblZusatzkosten);
		
		tfZusatzkosten = new JTextField();
		tfZusatzkosten.setColumns(10);
		tfZusatzkosten.setBorder(null);
		tfZusatzkosten.setBounds(206, 298, 52, 20);
		contentPane.add(tfZusatzkosten);
		
		JLabel lblTeileZurck = new JLabel("TEILE ZURÜCK :");
		lblTeileZurck.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeileZurck.setBounds(73, 326, 123, 14);
		contentPane.add(lblTeileZurck);
		
		chckBoxReturn = new JCheckBox("       Nein, der Kunde will die Teile nicht zurück.");
		chckBoxReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckBoxReturn.isSelected()) {
					chckBoxReturn.setText("       Ja, der Kunde will die Teile zurück.");
				} else {
					chckBoxReturn.setText("       Nein, der Kunde will die Teile nicht zurück.");
				}
				
			}
		});
		chckBoxReturn.setBounds(206, 322, 425, 23);
		contentPane.add(chckBoxReturn);
		
		JLabel lblStatus = new JLabel("STATUS :");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setBounds(73, 351, 123, 14);
		contentPane.add(lblStatus);
		
		JRadioButton rbtnOffen = new JRadioButton("offen");
		rbtnOffen.setSelected(true);
		buttonGroup.add(rbtnOffen);
		rbtnOffen.setBounds(206, 348, 59, 23);
		contentPane.add(rbtnOffen);
		
		JRadioButton rbtnGeschlossen = new JRadioButton("geschlossen");
		buttonGroup.add(rbtnGeschlossen);
		rbtnGeschlossen.setBounds(267, 347, 107, 23);
		contentPane.add(rbtnGeschlossen);
		
		JLabel lblKundeFreigabe = new JLabel("KUNDE FREIGABE  :");
		lblKundeFreigabe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKundeFreigabe.setBounds(73, 376, 123, 14);
		contentPane.add(lblKundeFreigabe);
		
		rbtnFreigabeJa = new JRadioButton("JA");
		
		rbtnFreigabeJa.setSelected(true);
		buttonGroup_1.add(rbtnFreigabeJa);
		rbtnFreigabeJa.setBounds(206, 372, 52, 23);
		contentPane.add(rbtnFreigabeJa);
		
		rbtnFreigabeNein = new JRadioButton("NEIN");
		
		buttonGroup_1.add(rbtnFreigabeNein);
		rbtnFreigabeNein.setBounds(267, 372, 52, 23);
		contentPane.add(rbtnFreigabeNein);
		
		lblFreigabe = new JLabel("Kunde hat für diese Sortieraktion freigegeben.");
		lblFreigabe.setForeground(Color.GREEN);
		lblFreigabe.setBounds(339, 376, 371, 14);
		contentPane.add(lblFreigabe);
		
		JPanel panelDetails = new JPanel();
		panelDetails.setBorder(new TitledBorder(null, "Sortieraktion Details", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelDetails.setBounds(27, 408, 687, 182);
		contentPane.add(panelDetails);
		panelDetails.setLayout(null);
		
		JLabel lblBeginnDerAktion = new JLabel("BEGINN DER AKTION :");
		lblBeginnDerAktion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBeginnDerAktion.setBounds(45, 22, 123, 14);
		panelDetails.add(lblBeginnDerAktion);
		
		JFormattedTextField tfBeginn = new JFormattedTextField(new Date());
		tfBeginn.setColumns(10);
		tfBeginn.setBorder(null);
		tfBeginn.setBounds(178, 19, 86, 20);
		panelDetails.add(tfBeginn);
		
		rbtnFreigabeNein.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbtnFreigabeNein.isSelected()) {
					lblFreigabe.setText("Kunde hat für diese Sortieraktion noch nicht freigegeben!");
					lblFreigabe.setForeground(Color.RED);
				}
			}
		});
		
		rbtnFreigabeJa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbtnFreigabeJa.isSelected()) {
					lblFreigabe.setText("Kunde hat für diese Sortieraktion freigegeben.");
					lblFreigabe.setForeground(Color.GREEN);
				}
			}
		});
	}
}

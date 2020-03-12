package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;

import com.cakir.config.Item;
import com.cakir.model.Breakpoint;
import com.cakir.model.Details;
import com.cakir.model.Kontakt;
import com.cakir.model.Kunde;
import com.cakir.model.Mitarbeiter;
import com.cakir.model.Sortieraktion;
import com.cakir.model.Teil;
import com.cakir.serviceImpl.BreakpointServiceImpl;
import com.cakir.serviceImpl.DetailsServiceImpl;
import com.cakir.serviceImpl.KontaktServiceImpl;
import com.cakir.serviceImpl.KundeServiceImpl;
import com.cakir.serviceImpl.MitarbeiterServiceImpl;
import com.cakir.serviceImpl.SortieraktionServiceImpl;
import com.cakir.serviceImpl.TeilServiceImpl;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SortieraktionJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfBeginn, tfDatum, tfBisZumDatum;
	private JTextField tfGrund;
	private JTextField tfDunsnummer;
	private JTextField tfAnweisung;
	private JTextField tfNormal;
	private JTextField tfRE;
	private JTextField tfZusatzkosten;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox chckBoxReturn, chckBoxZweiteBp, chckBoxErsteBp, chckBoxDritteBp;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JRadioButton rbtnFreigabeJa, rbtnFreigabeNein, rbtnOffen, rbtnGeschlossen;
	private JLabel lblFreigabe;
	private JTextField tfAnzahlTeile;
	private JTextField tfAnzahlStunde;
	private JTextField tfBisLieferung;
	private JTextField tfBisWiderruf;
	private JButton btnSpeichern;

	private JLabel lblNewLabel, lblSternDatum, lblSternGrund, lblSternId, lblSternKunde, lblSternTeil, lblSternKontakt,
			lblSternMitarbeiter, lblPflichtFelder;
	private JComboBox comboBoxKunde, comboBoxMitarbeiter, comboBoxKontakt, comboBoxTeil;
	private boolean IdStatus;

	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	KundeServiceImpl kundeService = new KundeServiceImpl();
	KontaktServiceImpl kontaktService = new KontaktServiceImpl();
	TeilServiceImpl teilService = new TeilServiceImpl();
	SortieraktionServiceImpl aktionService = new SortieraktionServiceImpl();
	DetailsServiceImpl detailsService = new DetailsServiceImpl();
	BreakpointServiceImpl bpservice = new BreakpointServiceImpl();

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
		setBounds(100, 100, 909, 737);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("ID :");
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

		tfDatum = new JFormattedTextField(new Date());
		tfDatum.setColumns(10);
		tfDatum.setBorder(null);
		tfDatum.setBounds(206, 48, 86, 20);
		contentPane.add(tfDatum);

		comboBoxKunde = new JComboBox<Item<Object>>();
		comboBoxKunde.setBounds(205, 73, 311, 20);
		getComboBoxKundeList();
		contentPane.add(comboBoxKunde);

		comboBoxTeil = new JComboBox<Item<Object>>();
		comboBoxTeil.setBounds(206, 98, 192, 20);
		getComboBoxTeilList();
		contentPane.add(comboBoxTeil);

		JLabel lblKontakt = new JLabel("KONTAKT :");
		lblKontakt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKontakt.setBounds(73, 126, 123, 14);
		contentPane.add(lblKontakt);

		comboBoxKontakt = new JComboBox<Item<Object>>();
		comboBoxKontakt.setBounds(206, 123, 310, 20);
		getComboBoxKontaktList();
		contentPane.add(comboBoxKontakt);

		JLabel lblMitarbeiter = new JLabel("MITARBEITER :");
		lblMitarbeiter.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMitarbeiter.setBounds(73, 151, 123, 14);
		contentPane.add(lblMitarbeiter);

		comboBoxMitarbeiter = new JComboBox<Item<Object>>();
		comboBoxMitarbeiter.setBounds(206, 148, 192, 20);
		getComboBoxMitarbeiterList();
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

		tfAnweisung = new JTextField();
		tfAnweisung.setColumns(10);
		tfAnweisung.setBorder(null);
		tfAnweisung.setBounds(206, 223, 504, 20);
		contentPane.add(tfAnweisung);

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
				if (chckBoxReturn.isSelected()) {
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

		rbtnOffen = new JRadioButton("offen");
		rbtnOffen.setSelected(true);
		buttonGroup.add(rbtnOffen);
		rbtnOffen.setBounds(206, 348, 59, 23);
		contentPane.add(rbtnOffen);

		rbtnGeschlossen = new JRadioButton("geschlossen");
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
		panelDetails.setBorder(
				new TitledBorder(null, "Sortieraktion Details", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelDetails.setBounds(27, 401, 687, 120);
		contentPane.add(panelDetails);
		panelDetails.setLayout(null);

		JLabel lblBeginnDerAktion = new JLabel("BEGINN DER AKTION :");
		lblBeginnDerAktion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBeginnDerAktion.setBounds(45, 30, 123, 14);
		panelDetails.add(lblBeginnDerAktion);

		tfBeginn = new JFormattedTextField();
		tfBeginn.setColumns(10);
		tfBeginn.setBorder(null);
		tfBeginn.setBounds(178, 27, 86, 20);
		panelDetails.add(tfBeginn);

		JLabel lblAnzahlVonTeilen = new JLabel("ANZAHL VON TEILEN :");
		lblAnzahlVonTeilen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnzahlVonTeilen.setBounds(45, 55, 123, 14);
		panelDetails.add(lblAnzahlVonTeilen);

		JLabel lblAnzahlVonStunden = new JLabel("ANZAHL VON STUNDEN :");
		lblAnzahlVonStunden.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnzahlVonStunden.setBounds(10, 80, 158, 14);
		panelDetails.add(lblAnzahlVonStunden);

		JLabel lblBisZumdatum = new JLabel("BIS ZUM (DATUM) :");
		lblBisZumdatum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBisZumdatum.setBounds(351, 30, 123, 14);
		panelDetails.add(lblBisZumdatum);

		JLabel lblBisLieferunglsnr = new JLabel("BIS LIEFERUNG (LS-NR.) :");
		lblBisLieferunglsnr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBisLieferunglsnr.setBounds(316, 55, 158, 14);
		panelDetails.add(lblBisLieferunglsnr);

		JLabel lblBisAufWiderruf = new JLabel("BIS AUF WIDERRUF :");
		lblBisAufWiderruf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBisAufWiderruf.setBounds(316, 80, 158, 14);
		panelDetails.add(lblBisAufWiderruf);

		tfAnzahlTeile = new JTextField();
		tfAnzahlTeile.setColumns(10);
		tfAnzahlTeile.setBorder(null);
		tfAnzahlTeile.setBounds(178, 52, 86, 20);
		panelDetails.add(tfAnzahlTeile);

		tfAnzahlStunde = new JTextField();
		tfAnzahlStunde.setColumns(10);
		tfAnzahlStunde.setBorder(null);
		tfAnzahlStunde.setBounds(178, 77, 86, 20);
		panelDetails.add(tfAnzahlStunde);

		tfBisZumDatum = new JFormattedTextField();
		tfBisZumDatum.setColumns(10);
		tfBisZumDatum.setBorder(null);
		tfBisZumDatum.setBounds(484, 27, 86, 20);
		panelDetails.add(tfBisZumDatum);

		tfBisLieferung = new JTextField();
		tfBisLieferung.setColumns(10);
		tfBisLieferung.setBorder(null);
		tfBisLieferung.setBounds(484, 52, 86, 20);
		panelDetails.add(tfBisLieferung);

		tfBisWiderruf = new JTextField();
		tfBisWiderruf.setColumns(10);
		tfBisWiderruf.setBorder(null);
		tfBisWiderruf.setBounds(484, 77, 86, 20);
		panelDetails.add(tfBisWiderruf);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Breakpoints", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(27, 532, 687, 59);
		contentPane.add(panel);
		panel.setLayout(null);

		chckBoxErsteBp = new JCheckBox("1. BREAKPONIT");
		chckBoxErsteBp.setBounds(45, 22, 126, 23);
		panel.add(chckBoxErsteBp);

		chckBoxZweiteBp = new JCheckBox("2. BREAKPONIT");
		chckBoxZweiteBp.setBounds(205, 22, 126, 23);
		panel.add(chckBoxZweiteBp);

		chckBoxDritteBp = new JCheckBox("3. BREAKPONIT");
		chckBoxDritteBp.setBounds(374, 22, 126, 23);
		panel.add(chckBoxDritteBp);

		lblPflichtFelder = new JLabel("* Erforderliche Felder");
		lblPflichtFelder.setBounds(27, 602, 149, 14);
		contentPane.add(lblPflichtFelder);

		lblSternKunde = new JLabel("*");
		lblSternKunde.setBounds(526, 76, 219, 14);
		contentPane.add(lblSternKunde);

		lblSternTeil = new JLabel("*");
		lblSternTeil.setBounds(408, 101, 321, 14);
		contentPane.add(lblSternTeil);

		lblSternKontakt = new JLabel("*");
		lblSternKontakt.setBounds(526, 126, 46, 14);
		contentPane.add(lblSternKontakt);

		lblSternMitarbeiter = new JLabel("*");
		lblSternMitarbeiter.setBounds(408, 151, 302, 14);
		contentPane.add(lblSternMitarbeiter);

		lblSternGrund = new JLabel("*");
		lblSternGrund.setBounds(720, 176, 149, 14);
		contentPane.add(lblSternGrund);

		lblSternDatum = new JLabel("*");
		lblSternDatum.setBounds(302, 51, 304, 14);
		contentPane.add(lblSternDatum);

		lblSternId = new JLabel("*");
		lblSternId.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblSternId.setBounds(302, 26, 518, 14);
		contentPane.add(lblSternId);

		btnSpeichern = new JButton("SPEICHERN");

		btnSpeichern.setBackground(SystemColor.inactiveCaption);
		btnSpeichern.setBounds(187, 639, 105, 23);
		contentPane.add(btnSpeichern);

		JButton btnReset = new JButton("RESET");
		btnReset.setBackground(SystemColor.inactiveCaption);
		btnReset.setBounds(339, 639, 105, 23);
		contentPane.add(btnReset);

		tfId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tfId.setText(tfId.getText().toUpperCase());
			}
		});

		tfId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if (aktionService.findById(tfId.getText()) != null
						|| tfId.getText().isEmpty() && tfId.isEnabled() == false) {

					lblSternId.setForeground(Color.RED);
					lblSternId.setText("ID Nummer ist vergeben oder ungültig. Geben Sie eine andere ID Nummer.");
					IdStatus = false;

				} else {

					lblSternId.setForeground(Color.GREEN);
					lblSternId.setText("ID Nummer ist in Ordnung.");
					IdStatus = true;

				}

			}
		});

		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (IdStatus || !tfId.isEnabled()) {

					boolean fehler = false;

					if (comboBoxKunde.getSelectedIndex() == 0) {
						lblSternKunde.setForeground(Color.RED);
						fehler = true;
					} else {
						lblSternKunde.setForeground(Color.BLACK);
					}
					if (comboBoxTeil.getSelectedIndex() == 0) {
						lblSternTeil.setForeground(Color.RED);
						fehler = true;
					} else {
						lblSternTeil.setForeground(Color.BLACK);
					}
					if (comboBoxKontakt.getSelectedIndex() == 0) {
						lblSternKontakt.setForeground(Color.RED);
						fehler = true;
					} else {
						lblSternKontakt.setForeground(Color.BLACK);
					}
					if (comboBoxMitarbeiter.getSelectedIndex() == 0) {
						lblSternMitarbeiter.setForeground(Color.RED);
						fehler = true;
					} else {
						lblSternMitarbeiter.setForeground(Color.BLACK);
					}
					if (tfGrund.getText().isEmpty()) {
						lblSternGrund.setForeground(Color.RED);
						fehler = true;
					} else {
						lblSternGrund.setForeground(Color.BLACK);
					}

					if (fehler) {
						lblPflichtFelder.setForeground(Color.RED);
						JOptionPane.showMessageDialog(null, "Füllen Sie * Pflichtfelder aus.", "FEHLER",
								JOptionPane.INFORMATION_MESSAGE);
					}

					else {

						lblPflichtFelder.setForeground(Color.BLACK);
						fehler = false;

						Item<Object> itemKunde = (Item<Object>) comboBoxKunde.getSelectedItem();
						Object kunde = itemKunde.getValue();

						Item<Object> itemTeil = (Item<Object>) comboBoxTeil.getSelectedItem();
						Object teil = itemTeil.getValue();

						Item<Object> itemKontakt = (Item<Object>) comboBoxKontakt.getSelectedItem();
						Object kontakt = itemKontakt.getValue();

						Item<Object> itemMitarbeiter = (Item<Object>) comboBoxMitarbeiter.getSelectedItem();
						Object mitarbeiter = itemMitarbeiter.getValue();

						Details details = new Details.DetailsBuilder().id(tfId.getText()).beginn(tfBeginn.getText())
								.anzahlStueck(tfAnzahlTeile.getText()).anzahlStunde(tfAnzahlStunde.getText())
								.bisDatum(tfBisZumDatum.getText()).bisLieferung(tfBisLieferung.getText())
								.bisWiderruf(tfBisWiderruf.getText()).build();

						Breakpoint bp = new Breakpoint.BreakpointBuilder().id(tfId.getText())
								.erste(chckBoxErsteBp.isSelected()).zweite(chckBoxZweiteBp.isSelected())
								.dritte(chckBoxDritteBp.isSelected()).build();

						Sortieraktion aktion = new Sortieraktion.SortieraktionBuilder().id(tfId.getText())
								.datum(tfDatum.getText()).kunde((Kunde) kunde).teil((Teil) teil)
								.kontaktPerson((Kontakt) kontakt).mitarbeiter((Mitarbeiter) mitarbeiter)
								.grund(tfGrund.getText()).dunsnummer(tfDunsnummer.getText())
								.anweisung(tfAnweisung.getText()).stundensatzNormal(tfNormal.getText())
								.zusatzkosten(tfZusatzkosten.getText())
								.stundesatzRE(tfRE.getText()).teileReturn(chckBoxReturn.isSelected())
								.offen(rbtnOffen.isSelected()).freigabe(rbtnFreigabeJa.isSelected()).build();
						
						switch (btnSpeichern.getText()) {
						case "SPEICHERN":
							
							if (aktionService.speichern(aktion, bp, details)) {
								JOptionPane.showMessageDialog(null,
										"Sortieraktion wurde erfolgreich gespeichert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.",
										"SPEICHERN", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null,
										"Sortieraktion wurde nicht gespeichert.\n Ein Fehler ist aufgetreten", "FEHLER",
										JOptionPane.ERROR_MESSAGE);
							}
							
							break;
							
						case "UPDATE":
							
							if (aktionService.update(aktion, bp, details)) {
								JOptionPane.showMessageDialog(null,
										"Sortieraktion wurde erfolgreich aktualisiert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.",
										"SPEICHERN", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null,
										"Sortieraktion wurde nicht aktualisiert.\n Ein Fehler ist aufgetreten", "FEHLER",
										JOptionPane.ERROR_MESSAGE);
							}
							
							break;

						default:
							break;
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

		rbtnFreigabeNein.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbtnFreigabeNein.isSelected()) {
					lblFreigabe.setText("Kunde hat für diese Sortieraktion noch nicht freigegeben!");
					lblFreigabe.setForeground(Color.RED);
				}
			}
		});

		rbtnFreigabeJa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbtnFreigabeJa.isSelected()) {
					lblFreigabe.setText("Kunde hat für diese Sortieraktion freigegeben.");
					lblFreigabe.setForeground(Color.GREEN);
				}
			}
		});
	}

	protected void leereFelder() {

		tfId.setText("");
		tfDatum.setText("");
		comboBoxKunde.setSelectedIndex(0);
		comboBoxTeil.setSelectedIndex(0);
		comboBoxKontakt.setSelectedIndex(0);
		comboBoxMitarbeiter.setSelectedIndex(0);
		tfGrund.setText("");
		tfDunsnummer.setText("");
		tfAnweisung.setText("");
		tfNormal.setText("");
		tfRE.setText("");
		tfZusatzkosten.setText("");
		chckBoxReturn.setSelected(false);
		chckBoxReturn.setText("       Nein, der Kunde will die Teile nicht zurück.");
		rbtnOffen.setSelected(true);
		rbtnGeschlossen.setSelected(false);
		rbtnFreigabeJa.setSelected(true);
		rbtnFreigabeNein.setSelected(false);
		lblFreigabe.setText("Kunde hat für diese Sortieraktion freigegeben.");
		lblFreigabe.setForeground(Color.GREEN);
		tfBeginn.setText("");
		tfAnzahlTeile.setText("");
		tfAnzahlStunde.setText("");
		tfBisZumDatum.setText("");
		tfBisLieferung.setText("");
		tfBisWiderruf.setText("");
		chckBoxErsteBp.setSelected(false);
		chckBoxZweiteBp.setSelected(false);
		chckBoxDritteBp.setSelected(false);
		lblSternId.setForeground(Color.BLACK);
		lblSternId.setText("*");

	}

	private void getComboBoxMitarbeiterList() {

		comboBoxMitarbeiter.addItem(new Item<Object>("", "Wählen Sie einen Mitarbeiter aus."));

		List<Mitarbeiter> listMitarbeiter = mitarbeiterService.alleMitarbeiter();

		for (Mitarbeiter mitarbeiter : listMitarbeiter) {

			comboBoxMitarbeiter
					.addItem(new Item<Object>(mitarbeiter, mitarbeiter.getVorname() + " " + mitarbeiter.getNachname()));
		}

	}

	private void getComboBoxTeilList() {

		comboBoxTeil.addItem(new Item<Object>("", "Wählen Sie ein Teil aus."));

		List<Teil> listTeil = teilService.alleTeile();

		for (Teil teil : listTeil) {

			comboBoxTeil.addItem(new Item<Object>(teil, teil.getTeilename() + " - " + teil.getTeilenummer()));
		}

	}

	private void getComboBoxKundeList() {

		comboBoxKunde.addItem(new Item<Object>("", "Wählen Sie einen Kunde aus."));

		List<Kunde> listKunde = kundeService.alleKunden();

		for (Kunde kunde : listKunde) {

			comboBoxKunde.addItem(new Item<Object>(kunde, kunde.getName()));
		}

	}

	private void getComboBoxKontaktList() {

		comboBoxKontakt.addItem(new Item<Object>("", "Wählen Sie eine Kontaktperson aus."));

		List<Kontakt> listKontakt = kontaktService.alleKontakte();

		for (Kontakt kontakt : listKontakt) {

			comboBoxKontakt.addItem(new Item<Object>(kontakt,
					kontakt.getVorname() + " " + kontakt.getNachname() + " - TEL: " + kontakt.getTel()));
		}
	}

	public void updateFelder(Sortieraktion updateAktion) {

		tfId.setText(updateAktion.getId());
		tfId.setEnabled(false);
		tfDatum.setText(updateAktion.getDatum());

		// ComboBoxSelectItem
		// Kunde
		comboBoxKunde.addItem(new Item<Object>(updateAktion.getKunde(), updateAktion.getKunde().getName()));
		comboBoxKunde.setSelectedItem(new Item<Object>(updateAktion.getKunde(), updateAktion.getKunde().getName()));
		// Teil
		comboBoxTeil.addItem(new Item<Object>(updateAktion.getTeil(),
				updateAktion.getTeil().getTeilename() + " - " + updateAktion.getTeil().getTeilenummer()));
		comboBoxTeil.setSelectedItem(new Item<Object>(updateAktion.getTeil(),
				updateAktion.getTeil().getTeilename() + " - " + updateAktion.getTeil().getTeilenummer()));
		// Kontakt
		comboBoxKontakt.addItem(new Item<Object>(updateAktion.getKontaktPerson(),
				updateAktion.getKontaktPerson().getVorname() + " " + updateAktion.getKontaktPerson().getNachname()
						+ " - TEL: " + updateAktion.getKontaktPerson().getTel()));
		comboBoxKontakt.setSelectedItem(new Item<Object>(updateAktion.getKontaktPerson(),
				updateAktion.getKontaktPerson().getVorname() + " " + updateAktion.getKontaktPerson().getNachname()
						+ " - TEL: " + updateAktion.getKontaktPerson().getTel()));
		// Mitarbeiter
		comboBoxMitarbeiter.addItem(new Item<Object>(updateAktion.getMitarbeiter(),
				updateAktion.getMitarbeiter().getVorname() + " " + updateAktion.getMitarbeiter().getNachname()));
		comboBoxMitarbeiter.setSelectedItem(new Item<Object>(updateAktion.getMitarbeiter(),
				updateAktion.getMitarbeiter().getVorname() + " " + updateAktion.getMitarbeiter().getNachname()));

		tfGrund.setText(updateAktion.getGrund());
		tfDunsnummer.setText(updateAktion.getDunsnummer());
		tfAnweisung.setText(updateAktion.getAnweisung());
		tfNormal.setText(updateAktion.getStundensatzNormal());
		tfRE.setText(updateAktion.getStundesatzRE());
		tfZusatzkosten.setText(updateAktion.getZusatzkosten());
		chckBoxReturn.setSelected(updateAktion.isTeileReturn());
		if (updateAktion.isTeileReturn())
			chckBoxReturn.setText("       Ja, der Kunde will die Teile zurück.");
		rbtnOffen.setSelected(!updateAktion.isOffen());
		rbtnGeschlossen.setSelected(!updateAktion.isOffen());
		if (updateAktion.isFreigabe()) {
			lblFreigabe.setText("Kunde hat für diese Sortieraktion freigegeben.");
			lblFreigabe.setForeground(Color.GREEN);
		} else {
			lblFreigabe.setText("Kunde hat für diese Sortieraktion noch nicht freigegeben!");
			lblFreigabe.setForeground(Color.RED);
		}
		rbtnFreigabeJa.setSelected(!updateAktion.isFreigabe());
		rbtnFreigabeNein.setSelected(!updateAktion.isFreigabe());

		Details details = detailsService.findById(updateAktion.getId());
		tfBeginn.setText(details.getBeginn());
		tfAnzahlTeile.setText(details.getAnzahlStueck());
		tfAnzahlStunde.setText(details.getAnzahlStunde());
		tfBisZumDatum.setText(details.getBisDatum());
		tfBisLieferung.setText(details.getBisLieferung());
		tfBisWiderruf.setText(details.getBisWiderruf());

		Breakpoint bp = bpservice.findById(updateAktion.getId());
		chckBoxErsteBp.setSelected(bp.isErste());
		chckBoxZweiteBp.setSelected(bp.isZweite());
		chckBoxDritteBp.setSelected(bp.isDritte());

		btnSpeichern.setText("UPDATE");
	}
}

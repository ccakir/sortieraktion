package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import com.cakir.config.ExcelExport;
import com.cakir.config.Item;
import com.cakir.model.Breakpoint;
import com.cakir.model.Details;
import com.cakir.model.Sortieraktion;
import com.cakir.serviceImpl.BreakpointServiceImpl;
import com.cakir.serviceImpl.DetailsServiceImpl;
import com.cakir.serviceImpl.SortieraktionServiceImpl;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SortieraktionAnsichtJFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblAktionId, lblAktionDatum, lblAktionKunde, lblAktionGrund, lblAktionMitarbeiter, lblAktionKontakt,
	lblAktionTeil, lblAktionZusatzkosten, lblAktionRE, lblAktionStundenNormal, lblAktionAnweisung, lblAktionDunsnummer, lblFreigabe,
	lblDetailsBisWiderruf, lblDetailsBisLieferung, lblDetailsBisDatum, lblDetailsAnzahlStunde, lblDetailsAnzahlTeile, lblDetailsBeginn;
	private JCheckBox checkBoxReturn, checkBox_1, checkBox_2, checkBox_3;
	private JRadioButton rbtnStatusOffen, rbtnStatusGeschlossen, rbtnFreigabeJA, rbtnFreigabeNein;
	
	SortieraktionServiceImpl sortieraktionService = new SortieraktionServiceImpl();
	DetailsServiceImpl detailsService = new DetailsServiceImpl();
	BreakpointServiceImpl breakpointService = new BreakpointServiceImpl();
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortieraktionAnsichtJFrame frame = new SortieraktionAnsichtJFrame(null, null);
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
	 * @param label2 
	 * @param button 
	 */
	public SortieraktionAnsichtJFrame(JButton button, String label2) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 821, 737);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(22, 11, 784, 676);
		contentPane.add(panel);
		
		JLabel label = new JLabel("ID :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(73, 26, 123, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("DATUM :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(73, 51, 123, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("KUNDE :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(73, 76, 123, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("TEIL :");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(73, 101, 123, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("KONTAKT :");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(73, 126, 123, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("MITARBEITER :");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(73, 151, 123, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("GRUND :");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(73, 176, 123, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("DUNSNUMMER :");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(73, 201, 123, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("ANWEISUNG :");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setBounds(73, 226, 123, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("STUNDENSATZ NORMAL :");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(27, 251, 169, 14);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("STUNDESATZ RE :");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setBounds(73, 276, 123, 14);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("ZUSATZKOSTEN :");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setBounds(73, 301, 123, 14);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("TEILE ZURÜCK :");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setBounds(73, 326, 123, 14);
		panel.add(label_12);
		
		checkBoxReturn = new JCheckBox("       Nein, der Kunde will die Teile nicht zurück.");
		checkBoxReturn.setBounds(206, 322, 425, 23);
		panel.add(checkBoxReturn);
		
		JLabel label_13 = new JLabel("STATUS :");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setBounds(73, 351, 123, 14);
		panel.add(label_13);
		
		rbtnStatusOffen = new JRadioButton("offen");
		rbtnStatusOffen.setEnabled(false);
		buttonGroup.add(rbtnStatusOffen);
		rbtnStatusOffen.setSelected(true);
		rbtnStatusOffen.setBounds(206, 348, 59, 23);
		panel.add(rbtnStatusOffen);
		
		rbtnStatusGeschlossen = new JRadioButton("geschlossen");
		rbtnStatusGeschlossen.setEnabled(false);
		buttonGroup.add(rbtnStatusGeschlossen);
		rbtnStatusGeschlossen.setBounds(267, 347, 107, 23);
		panel.add(rbtnStatusGeschlossen);
		
		JLabel label_14 = new JLabel("KUNDE FREIGABE  :");
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		label_14.setBounds(73, 376, 123, 14);
		panel.add(label_14);
		
		rbtnFreigabeJA = new JRadioButton("JA");
		rbtnFreigabeJA.setEnabled(false);
		buttonGroup_1.add(rbtnFreigabeJA);
		rbtnFreigabeJA.setSelected(true);
		rbtnFreigabeJA.setBounds(206, 372, 52, 23);
		panel.add(rbtnFreigabeJA);
		
		rbtnFreigabeNein = new JRadioButton("NEIN");
		rbtnFreigabeNein.setEnabled(false);
		buttonGroup_1.add(rbtnFreigabeNein);
		rbtnFreigabeNein.setBounds(267, 372, 52, 23);
		panel.add(rbtnFreigabeNein);
		
		lblFreigabe = new JLabel();
		lblFreigabe.setForeground(Color.GREEN);
		lblFreigabe.setBounds(339, 376, 371, 14);
		panel.add(lblFreigabe);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Sortieraktion Details", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_1.setBounds(27, 401, 687, 120);
		panel.add(panel_1);
		
		JLabel label_16 = new JLabel("BEGINN DER AKTION :");
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		label_16.setBounds(45, 30, 123, 14);
		panel_1.add(label_16);
		
		JLabel label_17 = new JLabel("ANZAHL VON TEILEN :");
		label_17.setHorizontalAlignment(SwingConstants.RIGHT);
		label_17.setBounds(45, 55, 123, 14);
		panel_1.add(label_17);
		
		JLabel label_18 = new JLabel("ANZAHL VON STUNDEN :");
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		label_18.setBounds(10, 80, 158, 14);
		panel_1.add(label_18);
		
		JLabel label_19 = new JLabel("BIS ZUM (DATUM) :");
		label_19.setHorizontalAlignment(SwingConstants.RIGHT);
		label_19.setBounds(351, 30, 123, 14);
		panel_1.add(label_19);
		
		JLabel label_20 = new JLabel("BIS LIEFERUNG (LS-NR.) :");
		label_20.setHorizontalAlignment(SwingConstants.RIGHT);
		label_20.setBounds(316, 55, 158, 14);
		panel_1.add(label_20);
		
		JLabel label_21 = new JLabel("BIS AUF WIDERRUF :");
		label_21.setHorizontalAlignment(SwingConstants.RIGHT);
		label_21.setBounds(316, 80, 158, 14);
		panel_1.add(label_21);
		
		lblDetailsBeginn = new JLabel("");
		lblDetailsBeginn.setBounds(178, 30, 135, 14);
		panel_1.add(lblDetailsBeginn);
		
		lblDetailsAnzahlTeile = new JLabel("");
		lblDetailsAnzahlTeile.setBounds(178, 55, 135, 14);
		panel_1.add(lblDetailsAnzahlTeile);
		
		lblDetailsAnzahlStunde = new JLabel("");
		lblDetailsAnzahlStunde.setBounds(178, 80, 135, 14);
		panel_1.add(lblDetailsAnzahlStunde);
		
		lblDetailsBisDatum = new JLabel("");
		lblDetailsBisDatum.setBounds(484, 30, 135, 14);
		panel_1.add(lblDetailsBisDatum);
		
		lblDetailsBisLieferung = new JLabel("");
		lblDetailsBisLieferung.setBounds(484, 55, 135, 14);
		panel_1.add(lblDetailsBisLieferung);
		
		lblDetailsBisWiderruf = new JLabel("");
		lblDetailsBisWiderruf.setBounds(484, 80, 135, 14);
		panel_1.add(lblDetailsBisWiderruf);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Breakpoints", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_2.setBounds(27, 532, 687, 59);
		panel.add(panel_2);
		
		checkBox_1 = new JCheckBox("1. BREAKPONIT");
		checkBox_1.setEnabled(false);
		checkBox_1.setBounds(45, 22, 126, 23);
		panel_2.add(checkBox_1);
		
		checkBox_2 = new JCheckBox("2. BREAKPONIT");
		checkBox_2.setEnabled(false);
		checkBox_2.setBounds(205, 22, 126, 23);
		panel_2.add(checkBox_2);
		
		checkBox_3 = new JCheckBox("3. BREAKPONIT");
		checkBox_3.setEnabled(false);
		checkBox_3.setBounds(374, 22, 126, 23);
		panel_2.add(checkBox_3);
		
		lblAktionId = new JLabel("");
		lblAktionId.setBounds(206, 26, 46, 14);
		panel.add(lblAktionId);
		
		lblAktionDatum = new JLabel("");
		lblAktionDatum.setBounds(206, 51, 459, 14);
		panel.add(lblAktionDatum);
		
		lblAktionKunde = new JLabel("");
		lblAktionKunde.setBounds(206, 76, 469, 14);
		panel.add(lblAktionKunde);
		
		lblAktionTeil = new JLabel("");
		lblAktionTeil.setBounds(206, 101, 469, 14);
		panel.add(lblAktionTeil);
		
		lblAktionKontakt = new JLabel("");
		lblAktionKontakt.setBounds(206, 126, 469, 14);
		panel.add(lblAktionKontakt);
		
		lblAktionMitarbeiter = new JLabel("");
		lblAktionMitarbeiter.setBounds(206, 151, 469, 14);
		panel.add(lblAktionMitarbeiter);
		
		lblAktionGrund = new JLabel("");
		lblAktionGrund.setBounds(206, 176, 469, 14);
		panel.add(lblAktionGrund);
		
		lblAktionDunsnummer = new JLabel("");
		lblAktionDunsnummer.setBounds(206, 201, 469, 14);
		panel.add(lblAktionDunsnummer);
		
		lblAktionAnweisung = new JLabel("");
		lblAktionAnweisung.setBounds(206, 226, 469, 14);
		panel.add(lblAktionAnweisung);
		
		lblAktionStundenNormal = new JLabel("");
		lblAktionStundenNormal.setBounds(206, 251, 469, 14);
		panel.add(lblAktionStundenNormal);
		
		lblAktionRE = new JLabel("");
		lblAktionRE.setBounds(206, 276, 469, 14);
		panel.add(lblAktionRE);
		
		lblAktionZusatzkosten = new JLabel("");
		lblAktionZusatzkosten.setBounds(206, 301, 469, 14);
		panel.add(lblAktionZusatzkosten);
		
		JButton btnExcelExport = new JButton("IN EXCEL EXPORTIEREN");
		
		btnExcelExport.setBackground(SystemColor.inactiveCaption);
		btnExcelExport.setBorder(null);
		btnExcelExport.setBounds(73, 612, 169, 23);
		panel.add(btnExcelExport);
		
		
		
		
		final Sortieraktion aktion = sortieraktionService.findById(label2);
		
		lblAktionId.setText(aktion.getId());
		lblAktionDatum.setText(aktion.getDatum());
		lblAktionKunde.setText(aktion.getKunde().getName());
		lblAktionTeil.setText(aktion.getTeil().getTeilename()+" - "+aktion.getTeil().getTeilenummer());
		lblAktionKontakt.setText(aktion.getKontaktPerson().getVorname()+" "+aktion.getKontaktPerson().getNachname());
		lblAktionMitarbeiter.setText(aktion.getMitarbeiter().getVorname()+" "+aktion.getMitarbeiter().getNachname());
		lblAktionGrund.setText(aktion.getGrund());
		lblAktionDunsnummer.setText(aktion.getDunsnummer());
		lblAktionAnweisung.setText(aktion.getAnweisung());
		lblAktionStundenNormal.setText(aktion.getStundensatzNormal());
		lblAktionRE.setText(aktion.getStundesatzRE());
		lblAktionZusatzkosten.setText(aktion.getZusatzkosten());
		
		if(aktion.isTeileReturn()) {
			checkBoxReturn.setSelected(true); checkBoxReturn.setEnabled(false); checkBoxReturn.setText("       JA, der Kunde will die Teile zurück.");
		} else {
			checkBoxReturn.setSelected(false); checkBoxReturn.setEnabled(false); checkBoxReturn.setText("       NEIN, der Kunde will die Teile nicht zurück.");
		}
		if(aktion.isOffen()) {
			rbtnStatusOffen.setSelected(true); rbtnStatusOffen.setEnabled(false); 
		}else {
			
			rbtnStatusGeschlossen.setSelected(true);
		}
		if(aktion.isFreigabe()) {
			rbtnFreigabeJA.setSelected(true);
			lblFreigabe.setForeground(Color.green);
			lblFreigabe.setText("Kunde hat für diese Sortieraktion freigegeben."); 
			}
		else {
			rbtnFreigabeNein.setSelected(true);
			lblFreigabe.setForeground(Color.RED);
			lblFreigabe.setText("Kunde hat für diese Sortieraktion noch nicht freigegeben."); 
		}
		
		final Details details = detailsService.findById(aktion.getId());
		
		lblDetailsBeginn.setText(details.getBeginn());
		lblDetailsAnzahlTeile.setText(details.getAnzahlStueck());
		lblDetailsAnzahlStunde.setText(details.getAnzahlStunde());
		lblDetailsBisDatum.setText(details.getBisDatum());
		lblDetailsBisLieferung.setText(details.getBisLieferung());
		lblDetailsBisWiderruf.setText(details.getBisWiderruf());
		
		final Breakpoint bp = breakpointService.findById(aktion.getId());
		
		if(bp.isErste()) checkBox_1.setSelected(true);
		if(bp.isZweite()) checkBox_2.setSelected(true);
		if(bp.isDritte()) checkBox_3.setSelected(true);
		
		btnExcelExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				String[] language = {"Deutsch", "Englisch"};
				
				String result = (String) JOptionPane.showInputDialog(null, "In Welcher Sprache möchten Sie die Daten Exportieren ", "SPRACHE WAHL",
						JOptionPane.QUESTION_MESSAGE, null, language, language[0]);
				
				
					ExcelExport.ExcelExport(aktion, details, bp, result);
					
								
					
				
			}
		});
		
	}

	
}

package com.cakir.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cakir.config.ButtonEditor;
import com.cakir.config.ButtonEditorStunden;
import com.cakir.config.ButtonRenderer;
import com.cakir.config.ButtonRendererStunden;
import com.cakir.model.Breakpoint;
import com.cakir.model.Kontakt;
import com.cakir.model.Kunde;
import com.cakir.model.Mitarbeiter;
import com.cakir.model.Sortieraktion;
import com.cakir.model.Stunden;
import com.cakir.model.StundenDetails;
import com.cakir.model.Teil;
import com.cakir.serviceImpl.BreakpointServiceImpl;
import com.cakir.serviceImpl.KontaktServiceImpl;
import com.cakir.serviceImpl.KundeServiceImpl;
import com.cakir.serviceImpl.MitarbeiterServiceImpl;
import com.cakir.serviceImpl.SortieraktionServiceImpl;
import com.cakir.serviceImpl.StundenDetailsServiceImpl;
import com.cakir.serviceImpl.StundenServiceImpl;
import com.cakir.serviceImpl.TeilServiceImpl;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Insets;
import javax.swing.border.TitledBorder;

public class Home extends JFrame {

	private JPanel contentPane;
	private static final Log logger = LogFactory.getLog(Home.class);
	private JTable tableMitarbeiter, tableKunde, tableoffeneSortieraktionen;
	private JTabbedPane tabbedPane;

	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	KundeServiceImpl kundeService = new KundeServiceImpl();
	KontaktServiceImpl kontaktService = new KontaktServiceImpl();
	TeilServiceImpl teilService = new TeilServiceImpl();
	SortieraktionServiceImpl aktionService = new SortieraktionServiceImpl();
	BreakpointServiceImpl bpService = new BreakpointServiceImpl();
	StundenServiceImpl stundenService = new StundenServiceImpl();
	StundenDetailsServiceImpl stundenDetailsService = new StundenDetailsServiceImpl();

	private JTable tableKontakt;
	private JTable tableTeil;
	private JTable tableAlleSortieraktionen;

	private int pageSize = 1500;
	private int tabSize = pageSize - 36;
	private int scrolPaneSize = pageSize - 61;
	private int tableSize = scrolPaneSize - 11;
	private JTable tableStunden;

	;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {


		setTitle("SORTIERAKTIONEN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 877);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(23, 35, 5, 0));
		menuBar.setBackground(SystemColor.inactiveCaptionBorder);
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Daten");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Sortieraktion anlegen");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Kunde hinzufügen");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KundeJFrame kFrame = new KundeJFrame();
				kFrame.setLocationRelativeTo(null);
				kFrame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Mitarbeiter hinzufügen");
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MitarbeiterJFrame mFrame = new MitarbeiterJFrame();
				mFrame.setLocationRelativeTo(null);
				mFrame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Kontakt hinzufügen");
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KontaktJFrame kFrame = new KontaktJFrame();
				kFrame.setLocationRelativeTo(null);
				kFrame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Teil hinzufügen");
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeilJFrame tFrame = new TeilJFrame();
				tFrame.setLocationRelativeTo(null);
				tFrame.setVisible(true);

			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);

		JMenu mnNewMenu_1 = new JMenu("Dokumente");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Sortieraktion Vorlage(Deutsch)");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final String aktionVorlageDE = "excelDE.xlsx";

				dokumentDownload(aktionVorlageDE);

			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Sortieraktion Vorlage(Englisch)");
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String aktionVorlageEN = "excelEN.xlsx";

				dokumentDownload(aktionVorlageEN);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Stundenerfassung(Deutsch)");
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String erfassungDE = "StundenerfassungDE.xlsx";

				dokumentDownload(erfassungDE);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Stundenerfassung(Englisch)");
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final String erfassungEN = "StundenerfassungEN.xlsx";

				dokumentDownload(erfassungEN);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_8);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Exit");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(mntmNewMenuItem_9);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 98, tabSize, 635);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(null);
		contentPane.add(tabbedPane);

		JPanel tabAlleSortieraktionen = new JPanel();
		tabbedPane.addTab("alle Sortieraktionen", null, tabAlleSortieraktionen, null);
		tabAlleSortieraktionen.setLayout(null);

		JScrollPane jScrollPane_Suspekt = new JScrollPane();
		jScrollPane_Suspekt.setBounds(10, 11, scrolPaneSize, 589);
		tabAlleSortieraktionen.add(jScrollPane_Suspekt);

		tableAlleSortieraktionen = new JTable();
		tableAlleSortieraktionen.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "", "ID", "Datum",
				"Kunde", "Teil", "Grund", "Mitarbeiter", "Freigabe", "Breakpoint", "Offen", "Ansicht" }));

		getTableList("sortieraktion", tableAlleSortieraktionen);

		jScrollPane_Suspekt.setViewportView(tableAlleSortieraktionen);

		setJTableColumnsWidth(tableAlleSortieraktionen, tableSize, 0, 7, 8, 12, 10, 20, 10, 7, 10, 7, 9);
		tableAnsicht(tableAlleSortieraktionen, 0);

		JPanel tabOffeneSortieraktionen = new JPanel();
		tabbedPane.addTab("offene Sortieraktionen", null, tabOffeneSortieraktionen, null);
		tabOffeneSortieraktionen.setLayout(null);

		JScrollPane jScrollPane_NA = new JScrollPane();
		jScrollPane_NA.setBounds(10, 11, scrolPaneSize, 589);
		tabOffeneSortieraktionen.add(jScrollPane_NA);

		tableoffeneSortieraktionen = new JTable();
		tableoffeneSortieraktionen.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "", "ID", "Datum",
				"Kunde", "Teil", "Grund", "Mitarbeiter", "Freigabe", "Breakpoint", "Offen", "Ansicht" }));

		getTableList("offenesortieraktion", tableoffeneSortieraktionen);

		jScrollPane_NA.setViewportView(tableoffeneSortieraktionen);
		setJTableColumnsWidth(tableoffeneSortieraktionen, tableSize, 0, 7, 8, 12, 10, 20, 10, 7, 10, 7, 9);
		tableAnsicht(tableoffeneSortieraktionen, 0);

		tableMitarbeiter = new JTable();
		tableMitarbeiter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMitarbeiter.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Vorname", "Nachname", "Email", "Telefon", "" }));
		tableAnsicht(tableMitarbeiter, 5);

		JPanel tabMitarbeiter = new JPanel();
		tabMitarbeiter.setBorder(null);
		tabbedPane.addTab("Mitarbeiter", null, tabMitarbeiter, null);
		tabMitarbeiter.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, scrolPaneSize, 589);
		setJTableColumnsWidth(tableMitarbeiter, tableSize, 10, 20, 20, 30, 20, 0);
		scrollPane_1.setViewportView(tableMitarbeiter);
		tabMitarbeiter.add(scrollPane_1);
		getTableList("mitarbeiter", tableMitarbeiter);

		JPanel tabTeile = new JPanel();
		tabbedPane.addTab("Teile", null, tabTeile, null);
		tabTeile.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, scrolPaneSize, 589);
		tabTeile.add(scrollPane);

		tableTeil = new JTable();
		tableTeil.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "", "Teilename", "Teilenummer", "Kunde", "" }));
		setJTableColumnsWidth(tableTeil, tableSize, 5, 25, 25, 50, 0);
		tableAnsicht(tableTeil, 4);
		scrollPane.setViewportView(tableTeil);
		getTableList("teil", tableTeil);

		tableKunde = new JTable();
		tableKunde.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableKunde.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Name", "Adresse", "Plz", "Stadt", "Land", "Uidnummer", "" }));
		setJTableColumnsWidth(tableKunde, tableSize, 5, 25, 25, 10, 12, 13, 10, 0);
		tableAnsicht(tableKunde, 7);

		JPanel tabKunde = new JPanel();
		tabKunde.setBorder(null);
		tabbedPane.addTab("Kunden", null, tabKunde, null);
		tabKunde.setLayout(null);

		JScrollPane scPane_Kunde = new JScrollPane();
		scPane_Kunde.setBounds(10, 11, scrolPaneSize, 589);
		scPane_Kunde.setViewportView(tableKunde);
		tabKunde.add(scPane_Kunde);
		getTableList("kunde", tableKunde);

		JPanel tabKontakt = new JPanel();
		tabbedPane.addTab("Kontakte", null, tabKontakt, null);
		tabKontakt.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, scrolPaneSize, 589);
		tabKontakt.add(scrollPane_2);

		tableKontakt = new JTable();
		tableKontakt.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "", "Vorname", "Nachname", "Telefon", "Fax", "Email", "" }));
		setJTableColumnsWidth(tableKontakt, tableSize, 6, 20, 20, 17, 17, 20, 0);
		tableAnsicht(tableKontakt, 6);
		scrollPane_2.setViewportView(tableKontakt);
		getTableList("kontakt", tableKontakt);

		JPanel tabStunden = new JPanel();
		tabbedPane.addTab("Stunden für Sortieraktionen", null, tabStunden, null);
		tabStunden.setLayout(null);
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 11, scrolPaneSize, 589);
		tabStunden.add(scrollPane_3);

		tableStunden = new JTable();

		tableStunden.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "", "Datum", "Sortieraktion ID",
				"Kunde", "Kontrolliert", "OK", "NOK", "NA", "Details" }));
		scrollPane_3.setViewportView(tableStunden);
		setJTableColumnsWidth(tableStunden, tableSize, 0, 8, 12, 25, 10, 10, 10, 10, 15);
		tableAnsicht(tableStunden, 0);
		getTableList("stunden", tableStunden);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 1464, 41);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnMitarbeiter = new JButton("MITARBEITER");
		btnMitarbeiter.setIcon(new ImageIcon("./icon/add.png"));

		btnMitarbeiter.setBackground(SystemColor.inactiveCaption);
		btnMitarbeiter.setBounds(10, 11, 141, 23);
		panel.add(btnMitarbeiter);

		JButton btnKunde = new JButton("KUNDE");

		btnKunde.setBackground(SystemColor.inactiveCaption);
		btnKunde.setIcon(new ImageIcon("./icon/add.png"));
		btnKunde.setBounds(180, 11, 141, 23);
		panel.add(btnKunde);

		JButton btnKontakt = new JButton("KONTAKT");
		btnKontakt.setIcon(new ImageIcon("./icon/add.png"));
		btnKontakt.setBackground(SystemColor.inactiveCaption);
		btnKontakt.setBounds(349, 11, 147, 23);
		panel.add(btnKontakt);

		JButton btnTeil = new JButton("TEIL");
		btnTeil.setIcon(new ImageIcon("./icon/add.png"));
		btnTeil.setBackground(SystemColor.inactiveCaption);
		btnTeil.setBounds(528, 11, 109, 23);
		panel.add(btnTeil);

		JButton btnAktion = new JButton("SORTIERAKTION");
		btnAktion.setIcon(new ImageIcon("./icon/add.png"));
		btnAktion.setBackground(SystemColor.inactiveCaption);
		btnAktion.setBounds(667, 11, 147, 23);
		panel.add(btnAktion);

		JButton btnStunden = new JButton("STUNDE FÜR SORTIERAKTION");
		btnStunden.setIcon(new ImageIcon("./icon/add.png"));
		btnStunden.setBackground(SystemColor.inactiveCaption);
		btnStunden.setBounds(850, 11, 229, 23);
		panel.add(btnStunden);

		JButton btnAktualisieren = new JButton("");
		btnAktualisieren
				.setIcon(new ImageIcon(Home.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));

		btnAktualisieren.setBackground(SystemColor.inactiveCaption);
		btnAktualisieren.setBounds(1420, 77, 54, 23);
		contentPane.add(btnAktualisieren);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 744, 981, 51);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnUpdate = new JButton("DATEN ÄNDERN");

		btnUpdate.setBackground(SystemColor.inactiveCaption);
		btnUpdate.setBounds(10, 11, 132, 23);
		panel_1.add(btnUpdate);

		JButton btnDelete = new JButton("LÖSCHEN");

		btnDelete.setBackground(SystemColor.inactiveCaption);
		btnDelete.setBounds(180, 11, 132, 23);
		panel_1.add(btnDelete);

		btnStunden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StundenJFrame sFrame = new StundenJFrame();
				sFrame.setLocationRelativeTo(null);
				sFrame.setVisible(true);
			}
		});

		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortieraktionJFrame sFrame = new SortieraktionJFrame();
				sFrame.setLocationRelativeTo(null);
				sFrame.setVisible(true);
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JTable activeTable = getActiveTable(tabbedPane.getSelectedIndex());

				if (activeTable.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					if (JOptionPane.showConfirmDialog(null, "Möchten Sie die Daten wirklich löschen?", "LÖSCHEN",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						switch (tabbedPane.getSelectedIndex()) {
						case 0:

							Sortieraktion sortieraktion = aktionService.findById(
									(String) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 1));
							boolean result_0 = aktionService.delete(sortieraktion);

							if (result_0) {
								JOptionPane.showMessageDialog(null, "Sortieraktion wurde erfolgreich gelöscht.",
										"LÖSCHEN", JOptionPane.INFORMATION_MESSAGE);
								getTableList("sortieraktion", activeTable);
							} else {
								JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten.", "FEHLER",
										JOptionPane.ERROR_MESSAGE);
							}
							break;

						case 1:

							Sortieraktion sortieraktionOffen = aktionService.findById(
									(String) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 1));
							boolean result_1 = aktionService.delete(sortieraktionOffen);

							if (result_1) {
								JOptionPane.showMessageDialog(null, "Sortieraktion wurde erfolgreich gelöscht.",
										"LÖSCHEN", JOptionPane.INFORMATION_MESSAGE);
								getTableList("sortieraktion", activeTable);
							} else {
								JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten.", "FEHLER",
										JOptionPane.ERROR_MESSAGE);
							}
							break;

						case 2:

							boolean result_2 = mitarbeiterService
									.delete((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 5));

							if (result_2) {
								JOptionPane.showMessageDialog(null, "Mitarbeiter wurde erfolgreich gelöscht.",
										"LÖSCHEN", JOptionPane.INFORMATION_MESSAGE);
								getTableList("mitarbeiter", activeTable);
							} else {
								JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten.", "FEHLER",
										JOptionPane.ERROR_MESSAGE);
							}
							break;

						case 3:

							boolean result_3 = teilService
									.delete((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 4));

							if (result_3) {
								JOptionPane.showMessageDialog(null, "Teil wurde erfolgreich gelöscht.", "LÖSCHEN",
										JOptionPane.INFORMATION_MESSAGE);
								getTableList("teil", activeTable);
							} else {
								JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten.", "FEHLER",
										JOptionPane.ERROR_MESSAGE);
							}
							break;

						case 4:

							boolean result_4 = kundeService
									.delete((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 7));

							if (result_4) {
								JOptionPane.showMessageDialog(null, "Kunde wurde erfolgreich gelöscht.", "LÖSCHEN",
										JOptionPane.INFORMATION_MESSAGE);
								getTableList("kunde", activeTable);
							} else {
								JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten.", "FEHLER",
										JOptionPane.ERROR_MESSAGE);
							}
							break;
						case 5:

							boolean result_5 = kontaktService
									.delete((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 6));

							if (result_5) {
								JOptionPane.showMessageDialog(null, "Kontakt wurde erfolgreich gelöscht.", "LÖSCHEN",
										JOptionPane.INFORMATION_MESSAGE);
								getTableList("kontakt", activeTable);
							} else {
								JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten.", "FEHLER",
										JOptionPane.ERROR_MESSAGE);
							}
							break;

						case 6:

							boolean result_6 = stundenService
									.delete((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 0));

							if (result_6) {
								JOptionPane.showMessageDialog(null, "Die Daten wurden erfolgreich gelöscht.", "LÖSCHEN",
										JOptionPane.INFORMATION_MESSAGE);
								getTableList("stunden", activeTable);
							} else {
								JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten.", "FEHLER",
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

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JTable activeTable = getActiveTable(tabbedPane.getSelectedIndex());

				if (activeTable.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, "Wählen Sie eine Zeile aus.", "FEHLER",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					switch (tabbedPane.getSelectedIndex()) {
					case 0:

						Sortieraktion updateAktion = aktionService
								.findById((String) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 1));
						SortieraktionJFrame aFrame = new SortieraktionJFrame();
						aFrame.setLocationRelativeTo(null);
						aFrame.updateFelder(updateAktion);
						aFrame.setVisible(true);

						break;

					case 1:

						Sortieraktion updateOffeneAktion = aktionService
								.findById((String) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 1));
						SortieraktionJFrame aOFrame = new SortieraktionJFrame();
						aOFrame.setLocationRelativeTo(null);
						aOFrame.updateFelder(updateOffeneAktion);
						aOFrame.setVisible(true);

						break;

					case 2:

						Mitarbeiter updateMitarbeiter = mitarbeiterService
								.findById((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 5));
						MitarbeiterJFrame mFrame = new MitarbeiterJFrame();
						mFrame.setLocationRelativeTo(null);
						mFrame.updateFelder(updateMitarbeiter);
						mFrame.setVisible(true);

						break;

					case 3:

						Teil updateTeil = teilService
								.findById((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 4));
						TeilJFrame tFrame = new TeilJFrame();
						tFrame.setLocationRelativeTo(null);
						tFrame.updateFelder(updateTeil);
						tFrame.setVisible(true);

						break;

					case 4:

						Kunde updateKunde = kundeService
								.findById((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 7));
						KundeJFrame kFrame = new KundeJFrame();
						kFrame.setLocationRelativeTo(null);
						kFrame.updateFelder(updateKunde);
						kFrame.setVisible(true);

						break;

					case 5:

						Kontakt updateKontakt = kontaktService
								.findById((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 6));
						KontaktJFrame kontaktJFrame = new KontaktJFrame();
						kontaktJFrame.setLocationRelativeTo(null);
						kontaktJFrame.updateFelder(updateKontakt);
						kontaktJFrame.setVisible(true);

						break;

					case 6:

						Stunden updateStunde = stundenService
								.findById((long) activeTable.getModel().getValueAt(activeTable.getSelectedRow(), 0));
						StundenJFrame sFrame = new StundenJFrame();
						sFrame.updateFelder(updateStunde);
						sFrame.setVisible(true);

						break;
					default:
						break;
					}

				}
			}

		});

		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (tabbedPane.getSelectedIndex()) {
				case 0:
					getTableList("sortieraktion", tableAlleSortieraktionen);
					break;

				case 1:
					getTableList("offenesortieraktion", tableoffeneSortieraktionen);
					break;

				case 2:
					getTableList("mitarbeiter", tableMitarbeiter);
					break;

				case 3:
					getTableList("teil", tableTeil);
					break;

				case 4:
					getTableList("kunde", tableKunde);
					break;

				case 5:
					getTableList("kontakt", tableKontakt);
					break;

				case 6:
					getTableList("stunden", tableStunden);
					break;

				default:
					break;
				}
			}
		});

		btnAktion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortieraktionJFrame sFrame = new SortieraktionJFrame();
				sFrame.setLocationRelativeTo(null);
				sFrame.setVisible(true);
			}
		});

		btnKontakt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KontaktJFrame kontaktFrame = new KontaktJFrame();
				kontaktFrame.setLocationRelativeTo(null);
				kontaktFrame.setVisible(true);
			}
		});

		btnMitarbeiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MitarbeiterJFrame mFrame = new MitarbeiterJFrame();
				mFrame.setLocationRelativeTo(null);
				mFrame.setVisible(true);
			}
		});

		btnKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KundeJFrame kFrame = new KundeJFrame();
				kFrame.setLocationRelativeTo(null);
				kFrame.setVisible(true);
			}
		});

		btnTeil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeilJFrame tFrame = new TeilJFrame();
				tFrame.setLocationRelativeTo(null);
				tFrame.setVisible(true);
			}
		});

		getAktionInfos();
	}

	private void getAktionInfos() {

		String offenRowCount = String.valueOf(tableoffeneSortieraktionen.getRowCount());

	}

	protected void dokumentDownload(String dokument) {

		File file = new File("./dokumente/" + dokument);

		boolean isFileUnlocked = file.renameTo(file);

		if (isFileUnlocked) {

			Desktop desktop = Desktop.getDesktop();

			try {
				desktop.open(file);
			} catch (IOException e1) {
				logger.error(e1.getMessage());
				e1.printStackTrace();
			}

		} else {

			JOptionPane.showMessageDialog(null,
					"Die Datei ist offen. Schliessen Sie die Datei und versuchen Sie nochmal.", "DATEI OFFEN",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	private JTable getActiveTable(int selectedIndex) {

		switch (selectedIndex) {
		case 0:
			return tableAlleSortieraktionen;
		case 1:
			return tableoffeneSortieraktionen;
		case 2:
			return tableMitarbeiter;
		case 3:
			return tableTeil;
		case 4:
			return tableKunde;
		case 5:
			return tableKontakt;
		case 6:
			return tableStunden;

		default:
			break;
		}
		return null;

	}

	private void getTableList(String name, JTable table) {

		int no = 1;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		switch (name) {
		case "sortieraktion":

			List<Sortieraktion> listAktion = aktionService.alleSortieraktionen();

			tableAlleSortieraktionen.getColumn("Ansicht").setCellRenderer(new ButtonRenderer());
			tableAlleSortieraktionen.getColumn("Ansicht").setCellEditor(new ButtonEditor(new JCheckBox()));

			tableAlleSortieraktionen.getColumn("Freigabe").setCellRenderer(new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					c.setForeground(value == "JA" ? Color.GREEN : Color.RED);
					return c;
				}

			});

			if (listAktion != null) {
				for (Sortieraktion aktion : listAktion) {

					Breakpoint bp = bpService.findById(aktion.getId());

					String bpStatus1 = "X", bpStatus2 = "X", bpStatus3 = "X", freigabe = "JA", status = "OFFEN";

					if (bp.isErste())
						bpStatus1 = "O";
					if (bp.isZweite())
						bpStatus2 = "O";
					if (bp.isDritte())
						bpStatus3 = "O";

					if (!aktion.isFreigabe())
						freigabe = "NEIN";

					if (!aktion.isOffen())
						status = "GESCHLOSSEN";

					model.addRow(new Object[] { no, aktion.getId(), aktion.getDatum(), aktion.getKunde().getName(),
							aktion.getTeil().getTeilename() + " - " + aktion.getTeil().getTeilenummer(),
							aktion.getGrund(),
							aktion.getMitarbeiter().getVorname() + " " + aktion.getMitarbeiter().getNachname(),
							freigabe, bpStatus1 + " - " + bpStatus2 + " - " + bpStatus3, status, aktion.getId() });
					no++;
				}
			}

			break;

		case "offenesortieraktion":

			List<Sortieraktion> listOffeneAktion = aktionService.offenSortieraktionen();

			tableoffeneSortieraktionen.getColumn("Ansicht").setCellRenderer(new ButtonRenderer());
			tableoffeneSortieraktionen.getColumn("Ansicht").setCellEditor(new ButtonEditor(new JCheckBox()));

			tableoffeneSortieraktionen.getColumn("Freigabe").setCellRenderer(new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					c.setForeground(value == "JA" ? Color.GREEN : Color.RED);
					return c;
				}

			});

			if (listOffeneAktion != null) {
				for (Sortieraktion aktion : listOffeneAktion) {

					Breakpoint bp = bpService.findById(aktion.getId());

					String bpStatus1 = "X", bpStatus2 = "X", bpStatus3 = "X", freigabe = "JA", status = "OFFEN";

					if (bp.isErste())
						bpStatus1 = "O";
					if (bp.isZweite())
						bpStatus2 = "O";
					if (bp.isDritte())
						bpStatus3 = "O";

					if (!aktion.isFreigabe())
						freigabe = "NEIN";

					if (!aktion.isOffen())
						status = "GESCHLOSSEN";

					model.addRow(new Object[] { no, aktion.getId(), aktion.getDatum(), aktion.getKunde().getName(),
							aktion.getTeil().getTeilename() + " - " + aktion.getTeil().getTeilenummer(),
							aktion.getGrund(),
							aktion.getMitarbeiter().getVorname() + " " + aktion.getMitarbeiter().getNachname(),
							freigabe, bpStatus1 + " - " + bpStatus2 + " - " + bpStatus3, status, aktion.getId() });
					no++;
				}
			}

			break;

		case "mitarbeiter":

			List<Mitarbeiter> listMitarbeiter = mitarbeiterService.alleMitarbeiter();

			if (listMitarbeiter != null) {
				for (Mitarbeiter mitarbeiter : listMitarbeiter) {

					model.addRow(new Object[] { no, mitarbeiter.getVorname(), mitarbeiter.getNachname(),
							mitarbeiter.getEmail(), mitarbeiter.getTel(), mitarbeiter.getId() });
					no++;
				}
			}

			break;

		case "kunde":

			List<Kunde> listKunden = kundeService.alleKunden();

			if (listKunden != null) {
				for (Kunde kunde : listKunden) {

					model.addRow(new Object[] { no, kunde.getName(), kunde.getAdresse(), kunde.getPlz(),
							kunde.getStadt(), kunde.getLand(), kunde.getUidnummer(), kunde.getId() });
					no++;
				}
			}

			break;

		case "kontakt":

			List<Kontakt> listKontakt = kontaktService.alleKontakte();

			if (listKontakt != null) {
				for (Kontakt kontakt : listKontakt) {

					model.addRow(new Object[] { no, kontakt.getVorname(), kontakt.getNachname(), kontakt.getTel(),
							kontakt.getFax(), kontakt.getEmail(), kontakt.getId() });
					no++;
				}
			}

			break;

		case "teil":

			List<Teil> listTeil = teilService.alleTeile();

			if (listTeil != null) {
				for (Teil teil : listTeil) {

					model.addRow(new Object[] { no, teil.getTeilename(), teil.getTeilenummer(),
							teil.getKunde().getName(), teil.getId() });
					no++;
				}
			}

			break;

		case "stunden":

			List<Stunden> listStunden = stundenService.alleStunden();

			tableStunden.getColumn("Details").setCellRenderer(new ButtonRendererStunden());
			tableStunden.getColumn("Details").setCellEditor(new ButtonEditorStunden(new JCheckBox()));

			if (listStunden != null) {
				for (Stunden stunden : listStunden) {

					model.addRow(new Object[] { stunden.getId(), stunden.getDatum(), stunden.getAktion().getId(),
							stunden.getAktion().getKunde().getName(), stunden.getChecked_quantity(),
							stunden.getOk_quantity(), stunden.getNok_quantity(), stunden.getRework_quantity(),
							"Details - ID: " + stunden.getId() });
					no++;
				}
			}

			break;

		default:
			break;
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

class ImageRendererOK extends DefaultTableCellRenderer {
	JLabel lbl = new JLabel();

	ImageIcon icon = new ImageIcon(getClass().getResource("/./images/ok1.png"));

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		lbl.setText((String) value);
		lbl.setIcon(icon);
		return lbl;
	}
}

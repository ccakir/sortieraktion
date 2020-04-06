package com.cakir.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.border.TitledBorder;

import com.cakir.config.Item;
import com.cakir.model.Kunde;
import com.cakir.model.Mitarbeiter;
import com.cakir.model.Sortieraktion;
import com.cakir.model.Stunden;
import com.cakir.serviceImpl.MitarbeiterServiceImpl;
import com.cakir.serviceImpl.SortieraktionServiceImpl;
import com.cakir.serviceImpl.StundenServiceImpl;

import javax.swing.ComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class StundenJFrame extends JFrame {

	private JPanel contentPane;
	
	SortieraktionServiceImpl sortieraktionService = new SortieraktionServiceImpl();
	StundenServiceImpl stundenService = new StundenServiceImpl();
	private JTextField tfId;
	private JTextField tfDatum;
	private JTextField tfMenge;
	private JTextField tfOk;
	private JTextField tfNok;
	private JTextField tfNacharbeit;
	private JLabel lblId ;
	private JComboBox comboBoxAktion;
	private JLabel lblMenge;
	private JLabel lblOk;
	private JLabel lblNok;
	private JLabel lblNacharbeit;
	private final String numericText = " * Geben Sie nur numerische Ziffern ein(0-9)";
	private JLabel lblFelder;
	private JButton btnSpeichern ;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StundenJFrame frame = new StundenJFrame();
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
	public StundenJFrame() {
		
		
        
        
        
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 781, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID :");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setVisible(false);
		lblId.setBounds(43, 42, 140, 14);
		contentPane.add(lblId);
		
		JLabel lblSortieraktion = new JLabel("DATUM :");
		lblSortieraktion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSortieraktion.setBounds(43, 67, 140, 14);
		contentPane.add(lblSortieraktion);
		
		JLabel label = new JLabel("SORTIERAKTION :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(43, 92, 140, 14);
		contentPane.add(label);
		
		JLabel lblMitarbeiterAnzahl = new JLabel("GEPRÜFTE MENGE :");
		lblMitarbeiterAnzahl.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMitarbeiterAnzahl.setBounds(43, 117, 140, 14);
		contentPane.add(lblMitarbeiterAnzahl);
		
		JLabel lblOkTeile = new JLabel("O.K. TEILE :");
		lblOkTeile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOkTeile.setBounds(43, 142, 140, 14);
		contentPane.add(lblOkTeile);
		
		JLabel lblNokTeile = new JLabel("N.O.K. TEILE :");
		lblNokTeile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNokTeile.setBounds(43, 167, 140, 14);
		contentPane.add(lblNokTeile);
		
		JLabel lblNachgearbeitet = new JLabel("NACHGEARBEITET :");
		lblNachgearbeitet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNachgearbeitet.setBounds(43, 192, 140, 14);
		contentPane.add(lblNachgearbeitet);
		
		tfId = new JTextField();
		tfId.setBounds(193, 39, 86, 20);
		tfId.setEnabled(false);
		tfId.setVisible(false);
		tfId.setBorder(null);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfDatum = new JFormattedTextField(new Date());
		tfDatum.setColumns(10);
		tfDatum.setBorder(null);
		tfDatum.setBounds(193, 64, 86, 20);
		contentPane.add(tfDatum);
		
		tfMenge = new JTextField();		
		tfMenge.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfMenge.getText().equals("0")) tfMenge.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfMenge.getText().equals("")) tfMenge.setText("0");
			}
		});
		tfMenge.setText("0");
		
		tfMenge.setColumns(10);
		tfMenge.setBorder(null);
		tfMenge.setBounds(193, 114, 86, 20);
		contentPane.add(tfMenge);
		
		tfOk = new JTextField();
		tfOk.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfOk.getText().equals("0")) tfOk.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfOk.getText().equals("")) tfOk.setText("0");
			}
		});
		tfOk.setText("0");
		
		tfOk.setColumns(10);
		tfOk.setBorder(null);
		tfOk.setBounds(193, 139, 86, 20);
		contentPane.add(tfOk);
		
		tfNok = new JTextField();
		tfNok.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfNok.getText().equals("0")) tfNok.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfNok.getText().equals("")) tfNok.setText("0");
			}
		});
		tfNok.setText("0");
		
		tfNok.setColumns(10);
		tfNok.setBorder(null);
		tfNok.setBounds(193, 164, 86, 20);
		contentPane.add(tfNok);
		
		tfNacharbeit = new JTextField();
		tfNacharbeit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfNacharbeit.getText().equals("0")) tfNacharbeit.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfNacharbeit.getText().equals("")) tfNacharbeit.setText("0");
			}
		});
		tfNacharbeit.setText("0");
		
		tfNacharbeit.setColumns(10);
		tfNacharbeit.setBorder(null);
		tfNacharbeit.setBounds(193, 189, 86, 20);
		contentPane.add(tfNacharbeit);
		
		comboBoxAktion = new JComboBox();
		comboBoxAktion.setBounds(193, 89, 392, 20);
		comboBoxKundeList();
		contentPane.add(comboBoxAktion);
		
		btnSpeichern = new JButton("SPEICHERN / DANACH  STUNDEN HINZUFÜGEN");
		btnSpeichern.setBackground(SystemColor.inactiveCaption);
		btnSpeichern.setBorder(null);
		btnSpeichern.setBounds(111, 261, 282, 23);
		contentPane.add(btnSpeichern);
		
		JButton btnReset = new JButton("RESET");
		
		btnReset.setBackground(SystemColor.inactiveCaption);
		btnReset.setBorder(null);
		btnReset.setBounds(435, 261, 101, 23);
		contentPane.add(btnReset);
		
		lblMenge = new JLabel("");
		lblMenge.setForeground(Color.RED);
		lblMenge.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblMenge.setBounds(289, 117, 296, 14);
		contentPane.add(lblMenge);
		
		lblOk = new JLabel("");
		lblOk.setForeground(Color.RED);
		lblOk.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblOk.setBounds(289, 142, 296, 14);
		contentPane.add(lblOk);
		
		lblNok = new JLabel("");
		lblNok.setForeground(Color.RED);
		lblNok.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNok.setBounds(289, 167, 296, 14);
		contentPane.add(lblNok);
		
		lblNacharbeit = new JLabel("");
		lblNacharbeit.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNacharbeit.setBounds(289, 192, 296, 14);
		contentPane.add(lblNacharbeit);
		
		lblFelder = new JLabel("");
		lblFelder.setForeground(Color.RED);
		lblFelder.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblFelder.setBounds(193, 220, 296, 14);
		contentPane.add(lblFelder);
		
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!isNumeric(tfMenge.getText()) || !isNumeric(tfOk.getText()) || !isNumeric(tfNok.getText()) || !isNumeric(tfNacharbeit.getText())) {
					lblFelder.setText(numericText);
				} else {
					
					lblFelder.setText("");
					
					Item<Object> itemAktion = (Item<Object>) comboBoxAktion.getSelectedItem();
					Object aktion = itemAktion.getValue();
					
					
					Stunden stunden = new Stunden.StundenBuilder()
							.datum(tfDatum.getText())
							.aktion((Sortieraktion)aktion)
							.checked_quantity(Integer.parseInt(tfMenge.getText()))
							.ok_quantity(Integer.parseInt(tfOk.getText()))
							.nok_quantity(Integer.parseInt(tfNok.getText()))
							.rework_quantity(Integer.parseInt(tfNacharbeit.getText()))
							.build();
					
					
					long stundeId;
					if(!tfId.getText().isEmpty()) {
						
						stundeId = Long.parseLong(tfId.getText());
						stunden.setId(stundeId);
						
						stundenService.update(stunden);
												
					} else {
						stundeId = stundenService.speichern(stunden);
					}
					
					
					if(stundeId != 0 && tfId.getText().isEmpty()) {
						dispose();
						StundeAddJFrame addFrame = new StundeAddJFrame();
						addFrame.setLocationRelativeTo(null);
						addFrame.updateAktion(stundeId, (Sortieraktion) aktion);
						addFrame.setVisible(true);
					} else if(stundeId != 0 && !tfId.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Die Daten wurden erfolgreich aktualisiert.\nBitte aktualisieren Sie die Tabelle im Hauptseite.", "UPDATE", JOptionPane.INFORMATION_MESSAGE);
						
						dispose();
					}
						else {
					
						
						JOptionPane.showMessageDialog(null, "Die Daten wurden nicht gespeichert. Ein Fehler ist aufgetreten.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
							
				}
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxAktion.setSelectedIndex(0);
				tfMenge.setText("0");
				tfOk.setText("0");
				tfNok.setText("0");
				tfNacharbeit.setText("0");
				tfDatum.setText("");
			}
		});
		
		tfMenge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String value = tfMenge.getText();
				
				int lang = value.length();
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					tfMenge.setEditable(true);
					lblMenge.setText("");
				} else {
					tfMenge.setEditable(true);
					lblMenge.setForeground(Color.RED);
					lblMenge.setText(" * Geben Sie nur numerische Ziffern ein(0-9)");

				}
			}
		});
		tfOk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String value = tfOk.getText();
				
				int lang = value.length();
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					tfOk.setEditable(true);
					lblOk.setText("");
				} else {
					tfOk.setEditable(true);
					lblOk.setForeground(Color.RED);
					lblOk.setText(numericText);

				}
			}
		});
		tfNok.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String value = tfNok.getText();
				
				int lang = value.length();
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					tfNok.setEditable(true);
					lblNok.setText("");
				} else {
					tfNok.setEditable(true);
					lblNok.setForeground(Color.RED);
					lblNok.setText(" * Geben Sie nur numerische Ziffern ein(0-9)");

				}
			}
		});
		tfNacharbeit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String value = tfNacharbeit.getText();
				
				int lang = value.length();
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					tfNacharbeit.setEditable(true);
					lblNacharbeit.setText("");
				} else {
					tfNacharbeit.setEditable(true);
					lblNacharbeit.setForeground(Color.RED);
					lblNacharbeit.setText(" * Geben Sie nur numerische Ziffern ein(0-9)");

				}
			}
		});
	}
	
	private void comboBoxKundeList() {


		
		
		List<Sortieraktion> listAktion = sortieraktionService.alleSortieraktionen();
		
		for( Sortieraktion aktion : listAktion ) {
			
			comboBoxAktion.addItem(new Item<Object>(aktion, aktion.getId()+" - "+aktion.getKunde().getName()));
		}
		
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

	public void updateFelder(Stunden updateStunde) {
		
		lblId.setVisible(true);
		tfId.setVisible(true);
		tfId.setText(String.valueOf(updateStunde.getId()));
		tfDatum.setText(updateStunde.getDatum());
		tfMenge.setText(String.valueOf(updateStunde.getChecked_quantity()));
		tfOk.setText(String.valueOf(updateStunde.getOk_quantity()));
		tfNok.setText(String.valueOf(updateStunde.getNok_quantity()));
		tfNacharbeit.setText(String.valueOf(updateStunde.getRework_quantity()));
		if(tfId.getText().isEmpty()) btnSpeichern.setText("SPEICHERN / DANACH  STUNDEN HINZUFÜGEN"); else btnSpeichern.setText("UPDATE");
		
		comboBoxAktion.addItem(new Item<Object>(updateStunde.getAktion(), updateStunde.getAktion().getId()+" - "+updateStunde.getAktion().getKunde().getName()));
		comboBoxAktion.setSelectedItem(new Item<Object>(updateStunde.getAktion(), updateStunde.getAktion().getId()+" - "+updateStunde.getAktion().getKunde().getName()));
		
	}
}


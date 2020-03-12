package com.cakir.config;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cakir.gui.SortieraktionAnsichtJFrame;
import com.cakir.model.Breakpoint;
import com.cakir.model.Details;
import com.cakir.model.Sortieraktion;

public class ExcelExport {
	
	
	
	public static void ExcelExport(Sortieraktion aktion, Details details, Breakpoint bp, String result) {
		
		String fileName, stundeRESatz = null, stundeNormalSatz = null, returnSatz = null;
		switch (result) {
		case "Englisch":
			fileName = "excelEN.xlsx";
			stundeNormalSatz = ",EUR / hour for normal workhours + 50% extra charge / hour for overtime and working on Saturdays\r\n" + 
					"+ 100% extra charge / hour for working at nights, on Sundays and on national holidays";
			stundeRESatz = "";
			returnSatz = "Our company needs the sorted out supplier related NOK parts:";
			break;

		default:
			fileName = "excelDE.xlsx";
			stundeNormalSatz = ",- EUR / normale Arbeitstunde\r\n" + 
					" + 50% Zuschlag / für Überstunden und Samstagsstunde\r\n" + 
					" + 100% Zuschlag / für Nachtstunden, Sonntagsstunden und Arbeiten an nationalen Feiertagen";
			stundeRESatz = ",-EUR / Supervising Stunden (für Überwachung und Einweisung von Sortier- und Nacharbeitspersonal,"
					+ " Beschaffung von Werkzeugen oder Vorrichtungen, Prüfplanerstellung, Organisation,"
					+ " Dokumentation, Analysen usw.). = ca. 1 Stunde pro Arbeitstag.";
			returnSatz = "Unsere Firma möchte alle bei der Sortieraktion anfallenden n.o.k. Teile zurück:";
			break;
		}
		
		File file = new File(fileName);
		
		boolean isFileUnlocked = file.renameTo(file);
		
		
		if(isFileUnlocked) {
			
			
		
		try {
			
			
			
			FileInputStream  inputStream = new FileInputStream(file);
			
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(inputStream);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
 
			//AKTION 
			//ID CELL
			XSSFCell cellId = sheet.getRow(2).getCell(1);
			cellId.setCellValue(aktion.getId());
			//Datum Cell
			XSSFCell cellDatum = sheet.getRow(2).getCell(3);
			cellDatum.setCellValue(aktion.getDatum());
			//Cell Kontakt
			XSSFCell cellKontaktName = sheet.getRow(6).getCell(1);
			cellKontaktName.setCellValue(aktion.getKontaktPerson().getVorname()+" "+aktion.getKontaktPerson().getNachname());
			//Cell Tel
			XSSFCell cellKontaktTel = sheet.getRow(6).getCell(3);
			cellKontaktTel.setCellValue(aktion.getKontaktPerson().getTel());
			//Cell Kunde
			XSSFCell cellKunde = sheet.getRow(4).getCell(1);
			cellKunde.setCellValue(aktion.getKunde().getName());
			//CellRechnungadresse
			XSSFCell cellRechnung = sheet.getRow(8).getCell(1);
			cellRechnung.setCellValue(aktion.getKunde().getName()+"\n"+aktion.getKunde().getAdresse());
			//CellTeilename
			XSSFCell cellTeil = sheet.getRow(10).getCell(1);
			cellTeil.setCellValue(aktion.getTeil().getTeilename());
			//CellTeilenummer
			XSSFCell cellTeilenummer = sheet.getRow(11).getCell(1);
			cellTeilenummer.setCellValue(aktion.getTeil().getTeilenummer());
			//Cell Grund
			XSSFCell cellGrund = sheet.getRow(19).getCell(1);
			cellGrund.setCellValue(aktion.getGrund());
			//Cell Anweisung
			XSSFCell cellAnweisung = sheet.getRow(20).getCell(1);
			cellAnweisung.setCellValue(aktion.getAnweisung());
			
			//Cell Stunde Normal
			
			XSSFCell cellStundeNormal = sheet.getRow(22).getCell(1);
			cellStundeNormal.setCellValue(aktion.getStundensatzNormal()+stundeNormalSatz);
			
			//Cell Stunde RE
			XSSFCell cellStundeRE = sheet.getRow(23).getCell(1);
			cellStundeRE.setCellValue(aktion.getStundesatzRE()+stundeRESatz);
			
			//Cell TeilReturn
			
			if(aktion.isTeileReturn()) returnSatz = returnSatz+" JA";
			else returnSatz = returnSatz+" NEIN";
			XSSFCell cellReturn = sheet.getRow(27).getCell(0);
			cellReturn.setCellValue(returnSatz);
			
			
			//DETAILS
			//Cell Beginn
			XSSFCell cellBeginn = sheet.getRow(12).getCell(2);
			cellBeginn.setCellValue(details.getBeginn());
			
			//Cell Teile
			XSSFCell cellAnzahlStueck = sheet.getRow(13).getCell(2);
			cellAnzahlStueck.setCellValue(details.getAnzahlStueck());
			//Cell Stunde
			XSSFCell cellAnzahlStunde = sheet.getRow(14).getCell(2);
			cellAnzahlStunde.setCellValue(details.getAnzahlStunde());
			//CellBis Datum
			XSSFCell cellAnzahlBisDatum = sheet.getRow(15).getCell(2);
			cellAnzahlBisDatum.setCellValue(details.getBisDatum());
			//CellBis Lieferung
			XSSFCell cellAnzahlBisLieferung = sheet.getRow(16).getCell(2);
			cellAnzahlBisLieferung.setCellValue(details.getBisLieferung());
			//CellBis Widerruf
			XSSFCell cellAnzahlBisWiderruf = sheet.getRow(17).getCell(2);
			cellAnzahlBisWiderruf.setCellValue(details.getBisWiderruf());
			
			
           
 
            try {
				inputStream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            FileOutputStream outputstream = new FileOutputStream(file);
            
            try {
				workbook.write(outputstream);
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
           
    		Desktop desktop = Desktop.getDesktop();
    		try {
    			desktop.open(file);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		
		
		} else {
			isFileUnlocked = true;
			JOptionPane.showMessageDialog(null, "Die Datei ist offen. Schliessen Sie die Datei und versuchen Sie nochmal.", "DATEI OFFEN", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	

}

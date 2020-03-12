package com.cakir.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cakir.model.Breakpoint;
import com.cakir.model.Details;
import com.cakir.model.Kontakt;
import com.cakir.model.Kunde;
import com.cakir.model.Mitarbeiter;
import com.cakir.model.Sortieraktion;
import com.cakir.model.Teil;
import com.cakir.mysqlConnection.DatabaseConnection;
import com.cakir.service.SortieraktionService;

public class SortieraktionServiceImpl implements SortieraktionService {

	private static final Log logger = LogFactory.getLog(SortieraktionServiceImpl.class);

	KundeServiceImpl kundeService = new KundeServiceImpl();
	KontaktServiceImpl kontaktService = new KontaktServiceImpl();
	BreakpointServiceImpl breakpointService = new BreakpointServiceImpl();
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	DetailsServiceImpl detailsService = new DetailsServiceImpl();
	TeilServiceImpl teilService = new TeilServiceImpl();

	private Connection conn;

	@Override
	public List<Sortieraktion> alleSortieraktionen() {

		List<Sortieraktion> listAktion = new ArrayList<Sortieraktion>();

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM aktion ORDER BY id DESC");

			while (rs.next()) {

				Kunde kunde = kundeService.findById(rs.getLong("kunde_id"));
				Kontakt kontakt = kontaktService.findById(rs.getLong("kontakt_id"));
				Teil teil = teilService.findById(rs.getLong("teil_id"));
				Details details = detailsService.findById(rs.getString("id"));
				Mitarbeiter mitarbeiter = mitarbeiterService.findById(rs.getLong("mitarbeiter_id"));
				Breakpoint breakpoint = breakpointService.findById(rs.getString("id"));

				Sortieraktion aktion = new Sortieraktion.SortieraktionBuilder()
						.id(rs.getString("id"))
						.datum(rs.getString("datum"))
						.kunde(kunde).dunsnummer(rs.getString("dunsnummer")).kontaktPerson(kontakt).teil(teil)
						.details(details).grund(rs.getString("grund")).anweisung(rs.getString("anweisung"))
						.stundensatzNormal(rs.getString("stundensatzNormal"))
						.stundesatzRE(rs.getString("stundensatzRE")).zusatzkosten(rs.getString("zusatzkosten"))
						.teileReturn(rs.getBoolean("teileReturn")).mitarbeiter(mitarbeiter)
						.offen(rs.getBoolean("offen")).freigabe(rs.getBoolean("freigabe")).breakpoint(breakpoint)
						.build();
				listAktion.add(aktion);

			}

			return listAktion;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());

				}
			}
		}
	}

	@Override
	public boolean speichern(Sortieraktion aktion, Breakpoint breakpoint, Details details) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			conn.setAutoCommit(false);
			PreparedStatement preparedStatement = conn.prepareStatement(
					"INSERT INTO aktion (datum, kunde_id, dunsnummer, kontakt_id, teil_id, grund, anweisung, stundensatzNormal, stundensatzRE, zusatzkosten, teileReturn, mitarbeiter_id, offen, freigabe, id)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, aktion.getDatum());
			preparedStatement.setLong(2, aktion.getKunde().getId());
			preparedStatement.setString(3, aktion.getDunsnummer());
			preparedStatement.setLong(4, aktion.getKontaktPerson().getId());
			preparedStatement.setLong(5, aktion.getTeil().getId());
			preparedStatement.setString(6, aktion.getGrund());
			preparedStatement.setString(7, aktion.getAnweisung());
			preparedStatement.setString(8, aktion.getStundensatzNormal());
			preparedStatement.setString(9, aktion.getStundesatzRE());
			preparedStatement.setString(10, aktion.getZusatzkosten());
			preparedStatement.setBoolean(11, aktion.isTeileReturn());
			preparedStatement.setLong(12, aktion.getMitarbeiter().getId());
			preparedStatement.setBoolean(13, aktion.isOffen());
			preparedStatement.setBoolean(14, aktion.isFreigabe());
			preparedStatement.setString(15, aktion.getId());
			preparedStatement.execute();
			
			
			
			PreparedStatement pStatement = conn.prepareStatement(
					"INSERT INTO details (id, beginn, anzahlStueck, anzahlStunde, bisDatum, bisLieferung, bisWiderruf) VALUES (?, ?, ?, ?, ?, ?, ?)");

			pStatement.setString(1, details.getId());
			pStatement.setString(2, details.getBeginn());
			pStatement.setString(3, details.getAnzahlStueck());
			pStatement.setString(4, details.getAnzahlStunde());
			pStatement.setString(5, details.getBisDatum());
			pStatement.setString(6, details.getBisLieferung());
			pStatement.setString(7, details.getBisWiderruf());

			pStatement.execute();
			
			PreparedStatement prStatement = conn.prepareStatement("INSERT INTO breakpoint (id, erste, zweite, dritte) VALUES (?, ?, ?, ?)");

			prStatement.setString(1, breakpoint.getId());
			prStatement.setBoolean(2, breakpoint.isErste());
			prStatement.setBoolean(3, breakpoint.isZweite());
			prStatement.setBoolean(4, breakpoint.isDritte());
			
			prStatement.execute();
			
			conn.commit();
			return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			try{
				 if(conn!=null)
		            conn.rollback();
		      }catch(SQLException se2){
		         se2.printStackTrace();
		      }
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());

				}
			}
		}
	}

	@Override
	public boolean update(Sortieraktion aktion, Breakpoint bp, Details details) {

		Kunde kunde = kundeService.findById(aktion.getKunde().getId());
		Kontakt kontakt = kontaktService.findById(aktion.getKontaktPerson().getId());
		Teil teil = teilService.findById(aktion.getTeil().getId());
		
		Mitarbeiter mitarbeiter = mitarbeiterService.findById(aktion.getMitarbeiter().getId());
		
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			conn.setAutoCommit(false);
			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE aktion SET datum=?, kunde_id=?, dunsnummer=?, kontakt_id=?, teil_id=?, grund=?, anweisung=?, stundensatzNormal=?, stundensatzRE=?, zusatzkosten=?, teileReturn=?, mitarbeiter_id=?, offen=?, freigabe=? WHERE id=?");

			preparedStatement.setString(1, aktion.getDatum());
			preparedStatement.setLong(2, kunde.getId());
			preparedStatement.setString(3, aktion.getDunsnummer());
			preparedStatement.setLong(4, kontakt.getId());
			preparedStatement.setLong(5, teil.getId());
			preparedStatement.setString(6, aktion.getGrund());
			preparedStatement.setString(7, aktion.getAnweisung());
			preparedStatement.setString(8, aktion.getStundensatzNormal());
			preparedStatement.setString(9, aktion.getStundesatzRE());
			preparedStatement.setString(10, aktion.getZusatzkosten());
			preparedStatement.setBoolean(11, aktion.isTeileReturn());
			preparedStatement.setLong(12, mitarbeiter.getId());
			preparedStatement.setBoolean(13, aktion.isOffen());
			preparedStatement.setBoolean(14, aktion.isFreigabe());
			preparedStatement.setString(15, aktion.getId());
			
			preparedStatement.executeUpdate();
			
			PreparedStatement prStatement = conn.prepareStatement("UPDATE breakpoint SET erste=?, zweite=?, dritte=? WHERE id=?");
			prStatement.setBoolean(1, bp.isErste());
			prStatement.setBoolean(2, bp.isZweite());
			prStatement.setBoolean(3, bp.isDritte());
			prStatement.setString(4, aktion.getId());
			prStatement.executeUpdate();
			
			
			PreparedStatement pStatement = conn.prepareStatement(
					"UPDATE details SET beginn=?, anzahlStueck=?, anzahlStunde=?, bisDatum=?, bisLieferung=?, bisWiderruf=? WHERE id=?");

			pStatement.setString(1, details.getBeginn());
			pStatement.setString(2, details.getAnzahlStueck());
			pStatement.setString(3, details.getAnzahlStunde());
			pStatement.setString(4, details.getBisDatum());
			pStatement.setString(5, details.getBisLieferung());
			pStatement.setString(6, details.getBisWiderruf());
			pStatement.setString(7, aktion.getId());

			pStatement.executeUpdate();
			
			conn.commit();
			return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			try{
				 if(conn!=null)
		            conn.rollback();
		      }catch(SQLException se2){
		         se2.printStackTrace();
		      }
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());

				}
			}
		}
	}

	@Override
	public boolean delete(Sortieraktion aktion) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM aktion WHERE id='"+aktion.getId()+"'");
			
			Statement stmt1 = conn.createStatement();
			stmt1.executeUpdate("DELETE FROM breakpoint WHERE id='"+aktion.getId()+"'");
			
			Statement stmt2 = conn.createStatement();
			stmt2.executeUpdate("DELETE FROM details WHERE id='"+aktion.getId()+"'");
			
			conn.commit();
			return true;
					
		} catch (SQLIntegrityConstraintViolationException e) {
			logger.error(e.getMessage());
			JOptionPane.showMessageDialog(null, "Diese Daten können Sie nicht löschen.\nDiese Daten werden in einer anderen Tabelle verwendet.", "FEHLER", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			try{
				 if(conn!=null)
		            conn.rollback();
		      }catch(SQLException se2){
		         se2.printStackTrace();
		      }
			return false;
		}  catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			try{
				 if(conn!=null)
		            conn.rollback();
		      }catch(SQLException se2){
		         se2.printStackTrace();
		      }
			return false;
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());

				}
			}
		}
	}

	@Override
	public Sortieraktion findById(String id) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM aktion WHERE id='"+id+"'");

			if (rs.next()) {

				Kunde kunde = kundeService.findById(rs.getLong("kunde_id"));
				Kontakt kontakt = kontaktService.findById(rs.getLong("kontakt_id"));
				Teil teil = teilService.findById(rs.getLong("teil_id"));
				Details details = detailsService.findById(rs.getString("id"));
				Mitarbeiter mitarbeiter = mitarbeiterService.findById(rs.getLong("mitarbeiter_id"));
				Breakpoint breakpoint = breakpointService.findById(rs.getString("id"));

				Sortieraktion aktion = new Sortieraktion.SortieraktionBuilder().id(rs.getString("id")).datum(rs.getString("datum"))
						.kunde(kunde).dunsnummer(rs.getString("dunsnummer")).kontaktPerson(kontakt).teil(teil)
						.details(details).grund(rs.getString("grund")).anweisung(rs.getString("anweisung"))
						.stundensatzNormal(rs.getString("stundensatzNormal"))
						.stundesatzRE(rs.getString("stundensatzRE")).zusatzkosten(rs.getString("zusatzkosten"))
						.teileReturn(rs.getBoolean("teileReturn")).mitarbeiter(mitarbeiter)
						.offen(rs.getBoolean("offen")).freigabe(rs.getBoolean("freigabe")).breakpoint(breakpoint)
						.build();
				
				return aktion;
			} else {
				logger.info("No Sortieraktion entry with id : "+id);
				return null;
			}

			

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());

				}
			}
		}
	}

	@Override
	public List<Sortieraktion> offenSortieraktionen() {
		
		List<Sortieraktion> listAktion = new ArrayList<Sortieraktion>();
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM aktion WHERE offen='"+0+"' ORDER BY id DESC");

			while (rs.next()) {

				Kunde kunde = kundeService.findById(rs.getLong("kunde_id"));
				Kontakt kontakt = kontaktService.findById(rs.getLong("kontakt_id"));
				Teil teil = teilService.findById(rs.getLong("teil_id"));
				Details details = detailsService.findById(rs.getString("id"));
				Mitarbeiter mitarbeiter = mitarbeiterService.findById(rs.getLong("mitarbeiter_id"));
				Breakpoint breakpoint = breakpointService.findById(rs.getString("id"));

				Sortieraktion aktion = new Sortieraktion.SortieraktionBuilder()
						.id(rs.getString("id"))
						.datum(rs.getString("datum"))
						.kunde(kunde).dunsnummer(rs.getString("dunsnummer")).kontaktPerson(kontakt).teil(teil)
						.details(details).grund(rs.getString("grund")).anweisung(rs.getString("anweisung"))
						.stundensatzNormal(rs.getString("stundensatzNormal"))
						.stundesatzRE(rs.getString("stundensatzRE")).zusatzkosten(rs.getString("zusatzkosten"))
						.teileReturn(rs.getBoolean("teileReturn")).mitarbeiter(mitarbeiter)
						.offen(rs.getBoolean("offen")).freigabe(rs.getBoolean("freigabe")).breakpoint(breakpoint)
						.build();
				listAktion.add(aktion);

			}

			return listAktion;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());

				}
			}
		}
	}

}

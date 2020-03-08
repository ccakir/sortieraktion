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
				Details details = detailsService.findById(rs.getLong("details_id"));
				Mitarbeiter mitarbeiter = mitarbeiterService.findById(rs.getLong("mitarbeiter_id"));
				Breakpoint breakpoint = breakpointService.findById(rs.getLong("breakpoint_id"));

				Sortieraktion aktion = new Sortieraktion.SortieraktionBuilder()
						.id(rs.getString("id"))
						.datum(rs.getString("datum"))
						.kunde(kunde).dunsnummer(rs.getString("dunsnummer")).kontaktPerson(kontakt).teil(teil)
						.details(details).grund(rs.getString("grund")).anweisung(rs.getString("anweisung"))
						.stundensatzNormal(rs.getBigDecimal("stundensatzNormal"))
						.stundesatzRE(rs.getBigDecimal("stundensatzRE")).zusatzkosten(rs.getString("zusatzkosten"))
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
	public boolean speichern(Sortieraktion aktion) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(
					"INSERT INTO aktion (datum, kunde_id, dunsnummer, kontakt_id, teil_id, details_id, grund, anweisung, stundensatzNormal, stundesatzRE, zusatzkosten, teileReturn, mitarbeiter_id, offen, freigabe, bereakpoint_id)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, aktion.getDatum());
			preparedStatement.setLong(2, aktion.getKunde().getId());
			preparedStatement.setString(3, aktion.getDunsnummer());
			preparedStatement.setLong(4, aktion.getKontaktPerson().getId());
			preparedStatement.setLong(5, aktion.getTeil().getId());
			preparedStatement.setLong(6, aktion.getDetails().getId());
			preparedStatement.setString(7, aktion.getGrund());
			preparedStatement.setString(8, aktion.getAnweisung());
			preparedStatement.setBigDecimal(9, aktion.getStundensatzNormal());
			preparedStatement.setBigDecimal(10, aktion.getStundesatzRE());
			preparedStatement.setString(11, aktion.getZusatzkosten());
			preparedStatement.setBoolean(12, aktion.isTeileReturn());
			preparedStatement.setLong(13, aktion.getMitarbeiter().getId());
			preparedStatement.setBoolean(14, aktion.isOffen());
			preparedStatement.setBoolean(15, aktion.isFreigabe());
			preparedStatement.setLong(16, aktion.getBreakpoint().getId());
			preparedStatement.execute();
			return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
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
	public boolean update(Sortieraktion aktion) {

		Kunde kunde = kundeService.findById(aktion.getKunde().getId());
		Kontakt kontakt = kontaktService.findById(aktion.getKontaktPerson().getId());
		Teil teil = teilService.findById(aktion.getTeil().getId());
		Details details = detailsService.findById(aktion.getDetails().getId());
		Mitarbeiter mitarbeiter = mitarbeiterService.findById(aktion.getMitarbeiter().getId());
		Breakpoint breakpoint = breakpointService.findById(aktion.getBreakpoint().getId());
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE SET aktion datum=?, kunde_id=?, dunsnummer=?, kontakt_id=?, teil_id=?, details_id=?, grund=?, anweisung=?, stundensatzNormal=?, stundesatzRE=?, zusatzkosten=?, teileReturn=?, mitarbeiter_id=?, offen=? freigabe=?, bereakpoint_id=? WHERE id=?");

			preparedStatement.setString(1, aktion.getDatum());
			preparedStatement.setLong(2, kunde.getId());
			preparedStatement.setString(3, aktion.getDunsnummer());
			preparedStatement.setLong(4, kontakt.getId());
			preparedStatement.setLong(5, teil.getId());
			preparedStatement.setLong(6, details.getId());
			preparedStatement.setString(7, aktion.getGrund());
			preparedStatement.setString(8, aktion.getAnweisung());
			preparedStatement.setBigDecimal(9, aktion.getStundensatzNormal());
			preparedStatement.setBigDecimal(10, aktion.getStundesatzRE());
			preparedStatement.setString(11, aktion.getZusatzkosten());
			preparedStatement.setBoolean(12, aktion.isTeileReturn());
			preparedStatement.setLong(13, mitarbeiter.getId());
			preparedStatement.setBoolean(14, aktion.isOffen());
			preparedStatement.setBoolean(15, aktion.isFreigabe());
			preparedStatement.setLong(16, breakpoint.getId());
			preparedStatement.setString(17, aktion.getId());
			
			if(preparedStatement.executeUpdate() == 1)	return true;
			else return false;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
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
			Statement stmt = conn.createStatement();
			
			int result = stmt.executeUpdate("DELETE FROM aktion WHERE id='"+aktion.getId()+"'");
			
			if(result == 1 ) {
				
				detailsService.delete(aktion.getDetails().getId());
				breakpointService.delete(aktion.getBreakpoint().getId());
				return true;
			}
			else {
				return false;
			}
			
					
		} catch (SQLIntegrityConstraintViolationException e) {
			logger.error(e.getMessage());
			JOptionPane.showMessageDialog(null, "Diese Daten können Sie nicht löschen.\nDiese Daten werden in einer anderen Tabelle verwendet.", "FEHLER", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}  catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
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
	public Sortieraktion findById(long id) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM aktion WHERE id='"+id+"'");

			if (rs.next()) {

				Kunde kunde = kundeService.findById(rs.getLong("kunde_id"));
				Kontakt kontakt = kontaktService.findById(rs.getLong("kontakt_id"));
				Teil teil = teilService.findById(rs.getLong("teil_id"));
				Details details = detailsService.findById(rs.getLong("details_id"));
				Mitarbeiter mitarbeiter = mitarbeiterService.findById(rs.getLong("mitarbeiter_id"));
				Breakpoint breakpoint = breakpointService.findById(rs.getLong("breakpoint_id"));

				Sortieraktion aktion = new Sortieraktion.SortieraktionBuilder().id(rs.getString("id")).datum(rs.getString("datum"))
						.kunde(kunde).dunsnummer(rs.getString("dunsnummer")).kontaktPerson(kontakt).teil(teil)
						.details(details).grund(rs.getString("grund")).anweisung(rs.getString("anweisung"))
						.stundensatzNormal(rs.getBigDecimal("stundensatzNormal"))
						.stundesatzRE(rs.getBigDecimal("stundensatzRE")).zusatzkosten(rs.getString("zusatzkosten"))
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
				Details details = detailsService.findById(rs.getLong("details_id"));
				Mitarbeiter mitarbeiter = mitarbeiterService.findById(rs.getLong("mitarbeiter_id"));
				Breakpoint breakpoint = breakpointService.findById(rs.getLong("breakpoint_id"));

				Sortieraktion aktion = new Sortieraktion.SortieraktionBuilder()
						.id(rs.getString("id"))
						.datum(rs.getString("datum"))
						.kunde(kunde).dunsnummer(rs.getString("dunsnummer")).kontaktPerson(kontakt).teil(teil)
						.details(details).grund(rs.getString("grund")).anweisung(rs.getString("anweisung"))
						.stundensatzNormal(rs.getBigDecimal("stundensatzNormal"))
						.stundesatzRE(rs.getBigDecimal("stundensatzRE")).zusatzkosten(rs.getString("zusatzkosten"))
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

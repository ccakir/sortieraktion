package com.cakir.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cakir.model.Mitarbeiter;
import com.cakir.model.Sortieraktion;
import com.cakir.model.Stunden;
import com.cakir.mysqlConnection.DatabaseConnection;
import com.cakir.service.StundenService;
import com.mysql.cj.xdevapi.PreparableStatement;

public class StundenServiceImpl implements StundenService {

	private static final Log logger = LogFactory.getLog(StundenServiceImpl.class);
	private Connection conn;

	SortieraktionServiceImpl aktionService = new SortieraktionServiceImpl();
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	private StundenDetailsServiceImpl stundenDetailsService;

	@Override
	public List<Stunden> alleStunden() {

		List<Stunden> listStunden = new ArrayList<Stunden>();

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM stunden ORDER BY id DESC");

			while (rs.next()) {

				Sortieraktion aktion = aktionService.findById(rs.getString("aktion"));

				Stunden stunden = new Stunden.StundenBuilder().id(rs.getLong("id")).aktion(aktion)
						.datum(rs.getString("datum")).checked_quantity(rs.getInt("checked_quantity"))
						.ok_quantity(rs.getInt("ok_quantity")).nok_quantity(rs.getInt("nok_quantity"))
						.rework_quantity(rs.getInt("rework_quantity")).build();

				listStunden.add(stunden);
			}
			return listStunden;

		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}

		catch (SQLException e) {
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
	public long speichern(Stunden stunden) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			String query = "INSERT INTO stunden (aktion, datum, checked_quantity, ok_quantity, nok_quantity, rework_quantity) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, stunden.getAktion().getId());
			ps.setString(2, stunden.getDatum());
			ps.setInt(3, stunden.getChecked_quantity());
			ps.setInt(4, stunden.getOk_quantity());
			ps.setInt(5, stunden.getNok_quantity());
			ps.setInt(6, stunden.getRework_quantity());
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				return rs.getLong(1);
			}
			return 0;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0;
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
	public boolean update(Stunden stunden) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement pStatement = conn.prepareStatement(
					"UPDATE stunden SET aktion=?, datum=?, checked_quantity=?, ok_quantity=?, nok_quantity=?, rework_quantity=? WHERE id=?");

			pStatement.setString(1, stunden.getAktion().getId());
			pStatement.setString(2, stunden.getDatum());
			pStatement.setInt(3, stunden.getChecked_quantity());
			pStatement.setInt(4, stunden.getOk_quantity());
			pStatement.setInt(5, stunden.getNok_quantity());
			pStatement.setInt(6, stunden.getRework_quantity());
			pStatement.setLong(7, stunden.getId());

			pStatement.executeUpdate();

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
	public boolean delete(long id) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			int result = stmt.executeUpdate("DELETE FROM stunden WHERE id='" + id + "'");

			if (result == 1)
				return true;
			else
				return false;

		} catch (SQLIntegrityConstraintViolationException e) {
			logger.error(e.getMessage());
			int antwort = 0;
			antwort = JOptionPane.showConfirmDialog(null,
					"Diese Daten werden in einer anderen Tabelle verwendet.\nWenn Sie die Daten trotzdem löschen möchten, werden andere Daten(evtl. Mitarbeiter Sortierstunden) auch gelöscht.",
					"WARNING", JOptionPane.YES_NO_CANCEL_OPTION);
			
			switch (antwort) {
			case JOptionPane.YES_OPTION:
				
				if(stundenDetailsService.deleteAll(id)) return true; 
				else return false;
			
			case JOptionPane.NO_OPTION:
				return false;
			default:
				return false;
				
			}

			
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
	public Stunden findById(long id) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM stunden WHERE id='" + id + "'");

			if (rs.next()) {

				Sortieraktion aktion = aktionService.findById(rs.getString("aktion"));

				Stunden stunden = new Stunden.StundenBuilder().id(rs.getLong("id")).aktion(aktion)
						.datum(rs.getString("datum")).checked_quantity(rs.getInt("checked_quantity"))
						.ok_quantity(rs.getInt("ok_quantity")).nok_quantity(rs.getInt("nok_quantity"))
						.rework_quantity(rs.getInt("rework_quantity")).build();

				return stunden;

			} else {
				logger.info("No Stunden entry with id : " + id);
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
	public List<Stunden> stundenByDatum(String monat, String jahr) {
		
		List<Stunden> listStunden = new ArrayList<Stunden>();
		String geteiltDatum[];
		try {
			conn = DatabaseConnection.getMySQLConnection();			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM stunden ORDER BY id DESC");
			
			while(rs.next()) {
				
				geteiltDatum = getMonat(rs.getString("datum"));
				System.out.println(geteiltDatum[1]+geteiltDatum[2]);
				System.out.println(monat+jahr);
				if(geteiltDatum[1].equals(monat) && geteiltDatum[2].equals(jahr)) {
					
					Sortieraktion aktion = aktionService.findById(rs.getString("aktion"));
					
					Stunden stunden = new Stunden.StundenBuilder()
							.id(rs.getLong("id")).aktion(aktion)
							.datum(rs.getString("datum")).checked_quantity(rs.getInt("checked_quantity"))
							.ok_quantity(rs.getInt("ok_quantity")).nok_quantity(rs.getInt("nok_quantity"))
							.rework_quantity(rs.getInt("rework_quantity")).build();

					listStunden.add(stunden);
				}
				
			}return listStunden;
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
	
	String[] getMonat(String datum) {
		
		return datum.split("\\.");
	}

}

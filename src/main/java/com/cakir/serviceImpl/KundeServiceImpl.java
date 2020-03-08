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

import com.cakir.model.Kunde;
import com.cakir.mysqlConnection.DatabaseConnection;
import com.cakir.service.KundeService;
import com.mysql.cj.protocol.Resultset;

public class KundeServiceImpl implements KundeService {

	private static final Log logger = LogFactory.getLog(KundeServiceImpl.class);
	private Connection conn;

	@Override
	public List<Kunde> alleKunden() {

		List<Kunde> listKunde = new ArrayList<Kunde>();

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM kunde ORDER BY id DESC");

			while (rs.next()) {
				Kunde kunde = new Kunde.KundeBuilder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.adresse(rs.getString("adresse"))
						.plz(rs.getString("plz"))
						.land(rs.getString("land"))
						.stadt(rs.getString("stadt"))
						.uidnummer(rs.getString("uidnummer"))
						.build();
				listKunde.add(kunde);
			}
			return listKunde;
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
	public boolean speichern(Kunde kunde) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(
					"INSERT INTO kunde (name, adresse, plz, land, stadt, uidnummer) VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, kunde.getName());
			preparedStatement.setString(2, kunde.getAdresse());
			preparedStatement.setString(3, kunde.getPlz());
			preparedStatement.setString(4, kunde.getLand());
			preparedStatement.setString(5, kunde.getStadt());
			preparedStatement.setString(6, kunde.getUidnummer());

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
	public boolean update(Kunde kunde) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement prStatement = conn.prepareStatement(
					"UPDATE kunde SET name=?, adresse=?, plz=?, land=?, stadt=?, uidnummer=? WHERE id=?");
			prStatement.setString(1, kunde.getName());
			prStatement.setString(2, kunde.getAdresse());
			prStatement.setString(3, kunde.getPlz());
			prStatement.setString(4, kunde.getLand());
			prStatement.setString(5, kunde.getStadt());
			prStatement.setString(6, kunde.getUidnummer());
			prStatement.setLong(7, kunde.getId());
			
			if(prStatement.executeUpdate() == 1) return true;
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
	public boolean delete(long id) {
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			int result = stmt.executeUpdate("DELETE FROM kunde WHERE id='"+id+"'");
			if(result == 1) return true;
			else return false;
		} catch (SQLIntegrityConstraintViolationException e) {
			logger.error(e.getMessage());
			JOptionPane.showMessageDialog(null, "Diese Daten können Sie nicht löschen.\nDiese Daten werden in einer anderen Tabelle verwendet.", "FEHLER", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}  catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
					
				}
			}
		}
	}

	@Override
	public Kunde findById(long id) {
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM kunde WHERE id='"+id+"'");
			
			if(rs.next()) {
				
				Kunde kunde = new Kunde.KundeBuilder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.adresse(rs.getString("adresse"))
						.plz(rs.getString("plz"))
						.land(rs.getString("land"))
						.stadt(rs.getString("stadt"))
						.uidnummer(rs.getString("uidnummer"))
						.build();
				
				return kunde;
			} else {
				logger.info("No entry with id : "+id);
				return null;
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
					
				}
			}
		}
	}

}

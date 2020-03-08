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
import com.cakir.mysqlConnection.DatabaseConnection;
import com.cakir.service.MitarbeiterService;

public class MitarbeiterServiceImpl implements MitarbeiterService {

	private static final Log logger = LogFactory.getLog(MitarbeiterServiceImpl.class);

	private Connection conn;

	@Override
	public List<Mitarbeiter> alleMitarbeiter() {

		List<Mitarbeiter> listMitarbeiter = new ArrayList<Mitarbeiter>();

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM mitarbeiter ORDER BY id DESC");

			while (rs.next()) {

				Mitarbeiter mitarbeiter = new Mitarbeiter.MitarbeiterBuilder().vorname(rs.getString("vorname"))
						.nachname(rs.getString("nachname")).email(rs.getString("email")).tel(rs.getString("tel")).id(rs.getLong("id"))
						.build();
				listMitarbeiter.add(mitarbeiter);
			}
			return listMitarbeiter;
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
	public boolean speichern(Mitarbeiter mitarbeiter) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			int result = stmt.executeUpdate("INSERT INTO mitarbeiter (vorname, nachname, email, tel)" + "VALUES ('"
					+ mitarbeiter.getVorname() + "', '" + mitarbeiter.getNachname() + "', '" + mitarbeiter.getEmail()
					+ "', '" + mitarbeiter.getTel() + "')");

			if (result == 1)
				return true;
			else
				return false;
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
	public boolean update(Mitarbeiter mitarbeiter) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("UPDATE mitarbeiter SET vorname=?, nachname=?, email=?, tel=? WHERE id=?");

			preparedStatement.setString(1, mitarbeiter.getVorname());
			preparedStatement.setString(2, mitarbeiter.getNachname());
			preparedStatement.setString(3, mitarbeiter.getEmail());
			preparedStatement.setString(4, mitarbeiter.getTel());
			preparedStatement.setLong(5, mitarbeiter.getId());
			
			int result = preparedStatement.executeUpdate();
			if(result == 1) return true;
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
			
			int result = stmt.executeUpdate("DELETE FROM mitarbeiter WHERE id='"+id+"'");
			
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
	public Mitarbeiter findById(long id) {
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM mitarbeiter WHERE id='"+id+"'");
			
			if(rs.next()) {
				
				Mitarbeiter mitarbeiter = new Mitarbeiter.MitarbeiterBuilder()
						.vorname(rs.getString("vorname"))
						.nachname(rs.getString("nachname"))
						.email(rs.getString("email"))
						.tel(rs.getString("tel"))
						.id(rs.getLong("id"))
						.build();
				return mitarbeiter;
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
/*
 * try { conn = DatabaseConnection.getMySQLConnection(); } catch (SQLException
 * e) { logger.error(e.getMessage()); e.printStackTrace(); } finally { if(conn
 * != null) { try { conn.close(); } catch (SQLException e) {
 * logger.error(e.getMessage());
 * 
 * } } }
 * 
 */

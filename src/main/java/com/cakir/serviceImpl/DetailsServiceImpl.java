package com.cakir.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cakir.model.Details;
import com.cakir.model.Kontakt;
import com.cakir.mysqlConnection.DatabaseConnection;
import com.cakir.service.DetailsService;

public class DetailsServiceImpl implements DetailsService {

	private static final Log logger = LogFactory.getLog(DetailsServiceImpl.class);

	private Connection conn;

	@Override
	public boolean speichern(Details details) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(
					"INSERT INTO details (id, beginn, anzahlStueck, anzahlStunde, bisDatum, bisLieferung, bisWiderruf) VALUES (?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, details.getId());
			preparedStatement.setString(2, details.getBeginn());
			preparedStatement.setString(3, details.getAnzahlStueck());
			preparedStatement.setString(4, details.getAnzahlStunde());
			preparedStatement.setString(5, details.getBisDatum());
			preparedStatement.setString(6, details.getBisLieferung());
			preparedStatement.setString(7, details.getBisWiderruf());

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
	public boolean update(Details details) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement pStatement = conn.prepareStatement(
					"UPDATE details SET beginn=?, anzahlStueck=?, anzahlStunde=?, bisDatum=?, bisLieferung=?, bisWiderruf=? WHERE id=?");

			pStatement.setString(1, details.getBeginn());
			pStatement.setString(2, details.getAnzahlStueck());
			pStatement.setString(3, details.getAnzahlStunde());
			pStatement.setString(4, details.getBisDatum());
			pStatement.setString(5, details.getBisLieferung());
			pStatement.setString(6, details.getBisWiderruf());
			pStatement.setString(7, details.getId());

			if (pStatement.executeUpdate() == 1)
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
	public boolean delete(String id) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			int result = stmt.executeUpdate("DELETE FROM details WHERE id='"+id+"'");
			
			if(result == 1 ) return true;
			else return false;
			
					
		}catch (SQLIntegrityConstraintViolationException e) {
			logger.error(e.getMessage());
			JOptionPane.showMessageDialog(null, "Diese Daten können Sie nicht löschen.\nDiese Daten werden in einer anderen Tabelle verwendet.", "FEHLER", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}   catch (SQLException e) {
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
	public Details findById(String id) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM details WHERE id='"+id+"'");
			
			if(rs.next()) {
				Details details = new Details.DetailsBuilder()
						.id(rs.getString("id"))
						.beginn(rs.getString("beginn"))
						.anzahlStueck(rs.getString("anzahlStueck"))
						.anzahlStunde(rs.getString("anzahlStunde"))
						.bisDatum(rs.getString("bisDatum"))
						.bisLieferung(rs.getString("bisLieferung"))
						.bisWiderruf(rs.getString("bisWiderruf"))
						.build();
				
						
				return details;
			} else {
				logger.info("No Details entry with id : "+id);
				return null;
			}
			
					
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
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

}

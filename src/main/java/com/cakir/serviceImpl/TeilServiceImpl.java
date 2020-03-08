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

import com.cakir.model.Kontakt;
import com.cakir.model.Kunde;
import com.cakir.model.Teil;
import com.cakir.mysqlConnection.DatabaseConnection;
import com.cakir.service.TeilService;

public class TeilServiceImpl implements TeilService {

	private static final Log logger = LogFactory.getLog(TeilServiceImpl.class);

	private Connection conn;

	KundeServiceImpl kundeService = new KundeServiceImpl();

	@Override
	public List<Teil> alleTeile() {

		List<Teil> listTeil = new ArrayList<Teil>();

		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM teil ORDER BY id DESC");

			while (rs.next()) {

				Kunde kunde = kundeService.findById(rs.getLong("kunde_id"));

				Teil teil = new Teil.TeilBuilder().id(rs.getLong("id")).teilename(rs.getString("teilename"))
						.teilenummer(rs.getString("teilenummer")).kunde(kunde).build();
				listTeil.add(teil);

			}

			return listTeil;

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
	public boolean speichern(Teil teil) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement preparedStatement = conn
					.prepareStatement("INSERT INTO teil (teilename, teilenummer, kunde_id)" + "VALUES (?, ?, ?)");

			preparedStatement.setString(1, teil.getTeilename());
			preparedStatement.setString(2, teil.getTeilenummer());
			preparedStatement.setLong(3, teil.getKunde().getId());

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
	public boolean update(Teil teil) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement pStatement = conn
					.prepareStatement("UPDATE teil SET teilename=?, teilenummer=?, kunde_id=? WHERE id=?");

			pStatement.setString(1, teil.getTeilename());
			pStatement.setString(2, teil.getTeilenummer());
			pStatement.setLong(3, teil.getKunde().getId());
			pStatement.setLong(4, teil.getId());
			
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
	public boolean delete(long id) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			int result = stmt.executeUpdate("DELETE FROM teil WHERE id='"+id+"'");
			
			if(result == 1 ) return true;
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
	public Teil findById(long id) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM teil WHERE id='"+id+"'");
			
			if(rs.next()) {
				
				Kunde kunde = kundeService.findById(rs.getLong("kunde_id"));
				
				Teil teil = new Teil.TeilBuilder()
						.id(rs.getLong("id"))
						.teilename(rs.getString("teilename"))
						.teilenummer(rs.getString("teilenummer"))
						.kunde(kunde)
						.build();
				
				return teil;
			} else {
				logger.info("No Teil entry with id : "+id);
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

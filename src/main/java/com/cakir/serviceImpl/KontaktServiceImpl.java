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
import com.cakir.mysqlConnection.DatabaseConnection;
import com.cakir.service.KontaktService;

public class KontaktServiceImpl implements KontaktService {
	
	private static final Log logger = LogFactory.getLog(KontaktServiceImpl.class);
	
	private Connection conn;

	@Override
	public List<Kontakt> alleKontakte() {
		
		List<Kontakt> listKontakt = new ArrayList<Kontakt>();
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM kontakt ORDER BY id DESC");
			
			while(rs.next()) {
				
				Kontakt  kontakt = new Kontakt.KontaktBuilder()
						.id(rs.getLong("id"))
						.vorname(rs.getString("vorname"))
						.nachname(rs.getString("nachname"))
						.tel(rs.getString("tel"))
						.fax(rs.getString("fax"))
						.email(rs.getString("email"))
						.build();
				listKontakt.add(kontakt);
			}
			
			return listKontakt;
					
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

	@Override
	public boolean speichern(Kontakt kontakt) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO kontakt (vorname, nachname, tel, fax, email)"
					+ "VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setString(1, kontakt.getVorname());
			preparedStatement.setString(2, kontakt.getNachname());
			preparedStatement.setString(3, kontakt.getTel());
			preparedStatement.setString(4, kontakt.getFax());
			preparedStatement.setString(5, kontakt.getEmail());
			
			preparedStatement.execute();
			return true;
			
					
		} catch (SQLException e) {
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
	public boolean update(Kontakt kontakt) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement pStatement = conn.prepareStatement("UPDATE kontakt SET vorname=?, nachname=?, tel=?, fax=?, email=? WHERE id=?");
			pStatement.setString(1, kontakt.getVorname());
			pStatement.setString(2, kontakt.getNachname());
			pStatement.setString(3, kontakt.getTel());
			pStatement.setString(4, kontakt.getFax());
			pStatement.setString(5, kontakt.getEmail());
			pStatement.setLong(6, kontakt.getId());
			
			if(pStatement.executeUpdate() == 1)	return true;
			else return false;
			
					
		} catch (SQLException e) {
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
	public boolean delete(long id) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			int result = stmt.executeUpdate("DELETE FROM kontakt WHERE id='"+id+"'");
			
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
	public Kontakt findById(long id) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM kontakt WHERE id='"+id+"'");
			
			if(rs.next()) {
				
				Kontakt  kontakt = new Kontakt.KontaktBuilder()
						.id(rs.getLong("id"))
						.vorname(rs.getString("vorname"))
						.nachname(rs.getString("nachname"))
						.tel(rs.getString("tel"))
						.fax(rs.getString("fax"))
						.email(rs.getString("email"))
						.build();
				return kontakt;
			} else {
				logger.info("No Kontakt entry with id : "+id);
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

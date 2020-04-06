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
import com.cakir.model.Stunden;
import com.cakir.model.StundenDetails;
import com.cakir.mysqlConnection.DatabaseConnection;
import com.cakir.service.StundenDetailsService;
import com.mysql.cj.xdevapi.PreparableStatement;

public class StundenDetailsServiceImpl implements StundenDetailsService{
	
	private static final Log logger = LogFactory.getLog(StundenDetailsServiceImpl.class);
	
	MitarbeiterServiceImpl mitarbeiterService = new MitarbeiterServiceImpl();
	StundenServiceImpl stundeService = new StundenServiceImpl();
	
	private Connection conn;

	@Override
	public boolean speichern(StundenDetails stundenDetails) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO stundendetails (stunden_id, mitarbeiter, von_1, bis_1, von_2, bis_2, von_3, bis_3)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setLong(1, stundenDetails.getStunden().getId());
			preparedStatement.setLong(2, stundenDetails.getMitarbeiter().getId());
			preparedStatement.setString(3, stundenDetails.getVon_1());
			preparedStatement.setString(4, stundenDetails.getBis_1());
			preparedStatement.setString(5, stundenDetails.getVon_2());
			preparedStatement.setString(6, stundenDetails.getBis_2());
			preparedStatement.setString(7, stundenDetails.getVon_3());
			preparedStatement.setString(8, stundenDetails.getBis_3());
			
			if(preparedStatement.execute()) return true; else return false;
			
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
			
			int result = stmt.executeUpdate("DELETE FROM stundendetails WHERE id='"+id+"'");
			
			if(result == 1) return true;
			else return false;
			
		}  catch (SQLIntegrityConstraintViolationException e) {
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
		}
		catch (SQLException e) {
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
	public List<StundenDetails> findByStunden(long stundeId) {
		
		List<StundenDetails> listDetails = new ArrayList<StundenDetails>();
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM stundendetails WHERE stunden_id='"+stundeId+"'");
			
			while(rs.next()) {
				
				Stunden stunden = stundeService.findById(rs.getLong("stunden_id"));
				Mitarbeiter mitarbeiter = mitarbeiterService.findById(rs.getLong("mitarbeiter"));
				
				StundenDetails stundeDetails = new StundenDetails.StundenDetailsBuilder()
						.id(rs.getLong("id"))
						.stunden(stunden)
						.mitarbeiter(mitarbeiter)
						.von_1(rs.getString("von_1"))
						.bis_1(rs.getString("bis_1"))
						.von_2(rs.getString("von_2"))
						.bis_2(rs.getString("bis_2"))
						.von_3(rs.getString("von_3"))
						.bis_3(rs.getString("bis_3"))
						.build();
				
				listDetails.add(stundeDetails);
			} 
			return listDetails;
			
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
	public boolean deleteAll(long stundenId) {
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			int result = stmt.executeUpdate("DELETE FROM stundendetails WHERE stunden_id='"+stundenId+"'");
			
			 return true;
			
			
		}  catch (SQLIntegrityConstraintViolationException e) {
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
		}
		catch (SQLException e) {
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

	

}

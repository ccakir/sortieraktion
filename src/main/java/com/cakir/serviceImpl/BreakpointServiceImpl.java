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

import com.cakir.model.Breakpoint;
import com.cakir.mysqlConnection.DatabaseConnection;
import com.cakir.service.BreakpointService;

public class BreakpointServiceImpl implements BreakpointService {

	private static final Log logger = LogFactory.getLog(BreakpointServiceImpl.class);

	private Connection conn;

	@Override
	public boolean speichern(Breakpoint breakpoint) {

		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement prStatement = conn.prepareStatement("INSERT INTO breakpoint (id, erste, zweite, dritte) VALUES (?, ?, ?, ?)");

			prStatement.setString(1, breakpoint.getId());
			prStatement.setBoolean(2, breakpoint.isErste());
			prStatement.setBoolean(3, breakpoint.isZweite());
			prStatement.setBoolean(4, breakpoint.isDritte());
			
			prStatement.execute();
			
				return true;
			

		} catch (SQLException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return false;
	}

	@Override
	public boolean update(Breakpoint breakpoint) {
		
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			PreparedStatement prStatement = conn.prepareStatement("UPDATE breakpoint SET erste=?, zweite=?, dritte=? WHERE id=?");
			prStatement.setBoolean(1, breakpoint.isErste());
			prStatement.setBoolean(2, breakpoint.isZweite());
			prStatement.setBoolean(3, breakpoint.isDritte());
			prStatement.setString(4, breakpoint.getId());
			int result = prStatement.executeUpdate();
			
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
					e.printStackTrace();
				}
			}
		}
		

	}

	@Override
	public boolean delete(String id) {
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate("DELETE FROM breakpoint WHERE id='"+id+"'");
			
			if(result == 1) return true;
			else return false;
		}catch (SQLIntegrityConstraintViolationException e) {
			logger.error(e.getMessage());
			JOptionPane.showMessageDialog(null, "Diese Daten können Sie nicht löschen.\nDiese Daten werden in einer anderen Tabelle verwendet.", "FEHLER", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}  
		catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public Breakpoint findById(String id) {
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM breakpoint WHERE id='"+id+"'");
			
			if(rs.next()) {
				
				Breakpoint bp = new Breakpoint.BreakpointBuilder()
						.id(rs.getString("id"))
						.erste(rs.getBoolean("erste"))
						.zweite(rs.getBoolean("zweite"))
						.dritte(rs.getBoolean("dritte"))
						.build();
				return bp;
			} else {
				logger.info("No entry with id : "+id);
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
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Breakpoint> alleBreakpoints() {
		
		List<Breakpoint> listBreakpoint = new ArrayList<Breakpoint>();
		
		try {
			conn = DatabaseConnection.getMySQLConnection();
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM breakpoint ORDER BY id DESC");
			
			while(rs.next()) {
				
				Breakpoint bp = new Breakpoint.BreakpointBuilder()
						.id(rs.getString("id"))
						.erste(rs.getBoolean("erste"))
						.zweite(rs.getBoolean("zweite"))
						.dritte(rs.getBoolean("dritte"))
						.build();
				listBreakpoint.add(bp);
			}
			return listBreakpoint;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}

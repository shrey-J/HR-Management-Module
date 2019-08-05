package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LeaveRecordModel;
import ui.LeavesTaken;
import utils.Constants;

public class LeaveRecordController {
	
	private LeaveRecordModel recordModel;
	private String employeeId;
	private LeavesTaken instance;
	
	public LeaveRecordController(String employeeId, LeavesTaken instance) {
		this.employeeId = employeeId;
		this.instance = instance;
		
		getLeaveRecord();
	}
	
	public void getLeaveRecord() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String query = "SELECT * FROM LeaveRecord WHERE EID = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				recordModel = new LeaveRecordModel(employeeId, rs.getString("CL"), rs.getString("PL"), rs.getString("CCL"), rs.getString("ODL"), rs.getString("OL"));
				instance.showLeaveRecord(true, recordModel);
			} else {
				instance.showLeaveRecord(false, null);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			instance.showSQLConnectionError();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			instance.showSQLConnectionError();
		} finally {
			try {
				if(conn != null)
					conn.close();
				if(stmt != null)
					stmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

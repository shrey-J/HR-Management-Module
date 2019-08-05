package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LeaveApplicationModel;
import ui.ApplicationStatusTS;
import utils.Constants;

public class ApplicationStatusTSController {
	private ApplicationStatusTS instance;
	private LeaveApplicationModel model;
	
	public ApplicationStatusTSController(ApplicationStatusTS instance, LeaveApplicationModel model) {
		this.instance = instance;
		this.model = model;
		getEmployeeDetails();
	}
	
	public void getEmployeeDetails() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String query = "SELECT Name, Designation, Department FROM Employee e, Teaching_Staff n WHERE e.EID = n.EID AND e.EID = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, model.getEmployeeId());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				model.setEmployeeName(rs.getString("Name"));
				model.setDesignation(rs.getString("Designation"));
				model.setEmployeeDept(rs.getString("Department"));
				instance.setData(model);
			} else {
				instance.showNoDataError(model.getEmployeeId());
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
			instance.showConnectionError();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			instance.showConnectionError();
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

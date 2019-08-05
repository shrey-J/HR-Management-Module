package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LeaveApplicationModel;
import ui.LeaveApplicationNTSReview;
import utils.Constants;

public class LeaveApplicationNTSReviewController {
	private LeaveApplicationNTSReview instance;
	private LeaveApplicationModel model;
	private int userType;
	
	public LeaveApplicationNTSReviewController(LeaveApplicationModel model, LeaveApplicationNTSReview instance, int userType) {
		this.model = model;
		this.instance = instance;
		this.userType = userType;
		getDepartment();
	}
	
	private void getDepartment() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String query = "SELECT Office FROM Non_Teaching_staff WHERE EID = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, model.getEmployeeId());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				model.setEmployeeDept(rs.getString("Office"));
				instance.setDepartment(model.getEmployeeDept());
			} else {
				instance.showConnectionError();
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
	
	public void approveApplication() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String query = null;
			conn.setAutoCommit(false);
			if(userType == Constants.REGISTRAR) {
				query = "Update Leave_Applications set Registrar_Status = ? WHERE AID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, Constants.APPROVED);
				stmt.setInt(2, model.getApplicationId());
				stmt.executeUpdate();
				conn.commit();
			} else if(userType == Constants.DIRECTOR) {
				query = "Update Leave_Applications set Director_Status = ?, Application_Status = ? WHERE AID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, Constants.APPROVED);
				stmt.setInt(2, Constants.APPROVED);
				stmt.setInt(3, model.getApplicationId());
				stmt.executeUpdate();
				query = "Select * from LeaveRecord WHERE EID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setString(1, model.getEmployeeId());
				ResultSet rs = stmt.executeQuery();
				String typeOfLeave = model.getTypeOfLeave();
				rs.next();
				String leaves = rs.getString(typeOfLeave);
				String newCount = null;
				rs.close();
				if(typeOfLeave.equals("PL") || typeOfLeave.equals("CL")) {
					int currentCount = Integer.parseInt(leaves.split("/")[0]);
					newCount = (currentCount + model.getTotalDays()) + "/" + leaves.split("/")[1];
					if(typeOfLeave.equals("PL")) {
						query = "UPDATE LeaveRecord SET PL = ? WHERE EID = ?;";
						stmt = conn.prepareStatement(query);
						stmt.setString(1, newCount);
						stmt.setString(2, model.getEmployeeId());
					} else {
						query = "UPDATE LeaveRecord SET CL = ? WHERE EID = ?;";
						stmt = conn.prepareStatement(query);
						stmt.setString(1, newCount);
						stmt.setString(2, model.getEmployeeId());
					}
				} else {
					int currentCount = Integer.parseInt(leaves);
					newCount = (currentCount + model.getTotalDays()) + "";
					query = "UPDATE LeaveRecord SET " + typeOfLeave + " = ? WHERE EID = ?;";
					stmt = conn.prepareStatement(query);
					stmt.setString(1, newCount);
					stmt.setString(2, model.getEmployeeId());
				}
				stmt.executeUpdate();
				conn.commit();
			}
			instance.showSuccess(1);
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				if(conn != null) {
					conn.rollback();
				}
			} catch(SQLException e1) {
				
			}
			instance.showFailure(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			instance.showFailure(1);
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
	
	public void rejectApplication() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String query = null;
			if(userType == Constants.REGISTRAR) {
				query = "Update Leave_Applications set Registrar_Status = ? WHERE AID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, Constants.REJECTED);
				stmt.setInt(2, model.getApplicationId());
				stmt.executeUpdate();
			} else if(userType == Constants.DIRECTOR) {
				query = "Update Leave_Applications set Director_Status = ?, Application_Status = ? WHERE AID = ?;";
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, Constants.REJECTED);
				stmt.setInt(2, Constants.REJECTED);
				stmt.setInt(3, model.getApplicationId());
				stmt.executeUpdate();
			}
			instance.showSuccess(0);
		} catch(SQLException e) {
			e.printStackTrace();
			instance.showFailure(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			instance.showFailure(0);
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

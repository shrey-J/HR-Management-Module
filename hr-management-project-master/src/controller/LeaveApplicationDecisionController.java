package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.LeaveApplicationModel;
import ui.LeaveApplicationDecision;
import utils.Constants;

public class LeaveApplicationDecisionController {
	private LeaveApplicationDecision instance;
	private int userType;
	private ArrayList<LeaveApplicationModel> applications;
	
	public LeaveApplicationDecisionController (int userType, LeaveApplicationDecision instance) {
		this.instance = instance;
		applications = new ArrayList<>();
		this.userType = userType;
	}
	
	public ArrayList<LeaveApplicationModel> getApplicationsData() {
		return applications;
	}
	
	public void getApplications() {
		Connection conn = null;
		PreparedStatement stmt = null;
		System.out.println(userType);
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String query = null;
			switch(userType) {
				case Constants.HOD_CSE:
					query = "SELECT AID, l.EID as EID, Application_Date, Type_Of_Leave, Reason_For_Leave, Start_Date, End_Date, HOD_Status, Registrar_Status, DOFA_Status, Director_Status, Application_Status, Name, Urgent, Designation FROM Leave_Applications l, Employee e, Teaching_Staff t WHERE l.EID = e.EID AND e.EID = t.EID AND t.Department = 'CSE' AND HOD_Status = 2 ORDER BY Urgent DESC, Application_Date DESC;";
					break;
				case Constants.HOD_ECE:
					query = "SELECT AID, l.EID as EID, Application_Date, Type_Of_Leave, Reason_For_Leave, Start_Date, End_Date, HOD_Status, Registrar_Status, DOFA_Status, Director_Status, Application_Status, Name, Urgent, Designation FROM Leave_Applications l, Employee e, Teaching_Staff t WHERE l.EID = e.EID AND e.EID = t.EID AND t.Department = 'ECE' AND HOD_Status = 2 ORDER BY Urgent DESC, Application_Date DESC;";
					break;
				case Constants.HOD_ME:
					query = "SELECT AID, l.EID as EID, Application_Date, Type_Of_Leave, Reason_For_Leave, Start_Date, End_Date, HOD_Status, Registrar_Status, DOFA_Status, Director_Status, Application_Status, Name, Urgent, Designation FROM Leave_Applications l, Employee e, Teaching_Staff t WHERE l.EID = e.EID AND e.EID = t.EID AND t.Department = 'ME' AND HOD_Status = 2 ORDER BY Urgent DESC, Application_Date DESC;";
					break;
				case Constants.REGISTRAR:
					query = "SELECT AID, l.EID as EID, Application_Date, Type_Of_Leave, Reason_For_Leave, Start_Date, End_Date, HOD_Status, Registrar_Status, DOFA_Status, Director_Status, Application_Status, Name, Urgent, Designation FROM Leave_Applications l, Employee e, Non_Teaching_staff n WHERE l.EID = e.EID AND e.EID = n.EID AND Registrar_Status = 2 ORDER BY Urgent DESC, Application_Date DESC;"; 
					break;
				case Constants.DOFA:
					query = "SELECT AID, l.EID as EID, Application_Date, Type_Of_Leave, Reason_For_Leave, Start_Date, End_Date, HOD_Status, Registrar_Status, DOFA_Status, Director_Status, Application_Status, Name, Urgent, Designation FROM Leave_Applications l, Employee e, Teaching_Staff t WHERE l.EID = e.EID AND e.EID = t.EID AND DOFA_Status = 2 ORDER BY Urgent DESC, Application_Date DESC;"; 
					break;
				case Constants.DIRECTOR: 
					query = "SELECT AID, l.EID as EID, Application_Date, Type_Of_Leave, Reason_For_Leave, Start_Date, End_Date, HOD_Status, Registrar_Status, DOFA_Status, Director_Status, Application_Status, Name, Urgent, Designation FROM Leave_Applications l, Employee e WHERE l.EID = e.EID AND Director_Status = 2 ORDER BY Urgent DESC, Application_Date DESC;"; 
					break;
				default:
					return;
			}
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			applications = new ArrayList<>();
			while(rs.next()) {
				LeaveApplicationModel model = new LeaveApplicationModel(rs.getString("EID"));
				model.setApplicationDate(rs.getDate("Application_Date").toLocalDate());
				model.setStartDate(rs.getDate("Start_Date").toLocalDate());
				model.setEndDate(rs.getDate("End_Date").toLocalDate());
				model.setTotalDays(model.getStartDate().until(model.getEndDate(), ChronoUnit.DAYS) + 1);
				model.setApplicationId(rs.getInt("AID"));
				model.setTypeOfLeave(rs.getString("Type_Of_Leave"));
				model.setLeaveReason(rs.getString("Reason_For_Leave"));
				model.setHodStatus(rs.getInt("HOD_Status"));
				model.setRegistrarStatus(rs.getInt("Registrar_Status"));
				model.setDofaStatus(rs.getInt("DOFA_Status"));
				model.setDirectorStatus(rs.getInt("Director_Status"));
				model.setApplicationStatus(rs.getInt("Application_Status"));
				model.setEmployeeName(rs.getString("Name"));
				model.setUrgent(rs.getInt("Urgent") == 1);
				model.setDesignation(rs.getString("Designation"));
				applications.add(model);
			}
//			instance.setApplications();
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

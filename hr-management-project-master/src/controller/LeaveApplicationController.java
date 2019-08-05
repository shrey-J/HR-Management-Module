package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import model.LeaveApplicationModel;
import model.LeaveRecordModel;
import ui.LeaveApplication;
import utils.Constants;

public class LeaveApplicationController {
	
	private LeaveApplicationModel model;
	private LeaveApplication instance;
	public boolean teachingStaff;
	
	public LeaveApplicationController(String employeeId, LeaveApplication instance) {
		this.instance = instance;
		model = new LeaveApplicationModel(employeeId);
		fetchEmployeeDetails();
	}
	
	public LeaveApplicationModel getModel() {
		return model;
	}
	
	public int sendApplicationToDatabase(String employeeId, LeaveApplicationModel model) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String query = "INSERT into Leave_Applications(EID, Application_Date, Type_Of_Leave, Reason_For_Leave, Start_Date, End_Date, HOD_Status, Registrar_Status, DOFA_Status, Director_Status, Application_Status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, employeeId);
			stmt.setDate(2, Date.valueOf(model.getApplicationDate()));
			stmt.setString(3, model.getTypeOfLeave());
			stmt.setString(4, model.getLeaveReason());
			stmt.setDate(5, Date.valueOf(model.getStartDate()));
			stmt.setDate(6, Date.valueOf(model.getEndDate()));
			if(teachingStaff) {
				stmt.setInt(7, Constants.PENDING);
				stmt.setInt(8, Constants.NOT_APPLICABLE);
				stmt.setInt(9, Constants.PENDING);
				stmt.setInt(10, Constants.PENDING);
				stmt.setInt(11, Constants.PENDING);
			} else {
				stmt.setInt(7, Constants.NOT_APPLICABLE);
				stmt.setInt(8, Constants.PENDING);
				stmt.setInt(9, Constants.NOT_APPLICABLE);
				stmt.setInt(10, Constants.PENDING);
				stmt.setInt(11, Constants.PENDING);
			}
			stmt.executeUpdate();
//			JOptionPane.showMessageDialog(this,
//			        "Your application was successfully submitted.",
//			        "Submission successful",
//			        JOptionPane.INFORMATION_MESSAGE);
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
			/*JOptionPane.showMessageDialog(this,
			        "Could not submit your application due to an error. Please try again.",
			        "Submission failed",
			        JOptionPane.ERROR_MESSAGE);*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
/*			JOptionPane.showMessageDialog(this,
			        "Could not submit your application due to an error. Please try again.",
			        "Submission failed",
			        JOptionPane.ERROR_MESSAGE);*/
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
	
	private void fetchEmployeeDetails() {
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try {
		Class.forName(Constants.JDBC_DRIVER);
		conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
		String query1 = "SELECT Title, Name, Designation, Office FROM Employee e, Non_Teaching_staff n WHERE e.EID = n.EID AND e.EID = ?;";
		String query2 = "SELECT Title, Name, Designation, Department FROM Employee e, Teaching_Staff t WHERE e.EID = t.EID AND e.EID = ?;";
		stmt1 = conn.prepareStatement(query1);
		stmt1.setString(1, model.getEmployeeId());
		stmt2 = conn.prepareStatement(query2);
		stmt2.setString(1, model.getEmployeeId());
		ResultSet rs1 = stmt1.executeQuery();
		ResultSet rs2 = stmt2.executeQuery();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if(rs1.next()) {
			model.setEmployeeName(rs1.getString("Name"));
			model.setDesignation(rs1.getString("Designation"));
			model.setEmployeeDept(rs1.getString("Office"));
			model.setApplicationDate(LocalDate.now());
			instance.setEmployeeData(model);
			teachingStaff = false;
//			setVisible(true);
		} else if(rs2.next()) {
			model.setEmployeeName(rs2.getString("Name"));
			model.setDesignation(rs2.getString("Designation"));
			model.setEmployeeDept(rs2.getString("Department"));
			model.setApplicationDate(LocalDate.now());
			instance.setEmployeeData(model);
			teachingStaff = true;
//			setVisible(true);
		} else {
			instance.setEmployeeData(null);
		}
		rs1.close();
		rs2.close();
	      stmt1.close();
	      stmt2.close();
	      conn.close();
		} catch (SQLException e) {
			instance.showMysqlError();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			instance.showMysqlError();
			e.printStackTrace();
		}finally{
		      //finally block used to close resources
		      try{
		         if(stmt1!=null)
		            stmt1.close();
		         if(stmt2!=null)
		        	 stmt2.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
}

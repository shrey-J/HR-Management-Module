package controller;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.AppraisalModel;
import model.Conference;
import model.ResearchPaper;
import ui.Appraisal;
import utils.Constants;

public class Appraisal_Controller {
	
	private AppraisalModel model;
	private Appraisal instance;
	
	public Appraisal_Controller(String employeeId, Appraisal instance) {
		model = new AppraisalModel();
		model.setEmployeeId(employeeId);
		this.instance = instance;
		getEmployeeData();
	}
	
	public void onAddPaper(ResearchPaper paper) {
		model.addPaper(paper);
	}
	
	public void onAddPaper(ResearchPaper paper, int index) {
		model.addPaper(paper, index);
	}
	
	public void onAddConference(Conference conference) {
		model.addConference(conference);
	}
	
	public void onAddConference(Conference conference, int index) {
		model.addConference(conference, index);
	}
	
	public ArrayList<ResearchPaper> getPapers() {
		return model.getPapers();
	}
	
	public ArrayList<Conference> getConferences() {
		return model.getConferences();
	}
	
	public void setHOD(String hod) {
		model.setHod(hod);
	}
	
	public void setFacultyMentor(String facultyMentor) {
		model.setFacultyMentor(facultyMentor);
	}
	
	private void getEmployeeData() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
		Class.forName(Constants.JDBC_DRIVER);
		conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
		String query = "SELECT Title, Name, Designation, Department FROM Employee e, Teaching_Staff t WHERE e.EID = t.EID AND e.EID = ?;";
		stmt = conn.prepareStatement(query);
		stmt.setString(1, model.getEmployeeId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			model.setEmployeeName(rs.getString("Title") + " " + rs.getString("Name"));
			model.setEmployeeDesignation(rs.getString("Designation"));
			model.setEmployeeDept(rs.getString("Department"));
			instance.setAppraisalData(true, model.getEmployeeName(), model.getEmployeeDept(), model.getEmployeeDesignation());
		} else {
			instance.setAppraisalData(false, null, null, null);
		}
		System.out.println(model);
		rs.close();
	      stmt.close();
	      conn.close();
		} catch (SQLException e) {
			instance.showSQLConnectionError();
		} catch (ClassNotFoundException e) {
			instance.showSQLConnectionError();
		}finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
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
	
	public void submitAppraisalForm() {
		Connection conn = null;
		PreparedStatement appraisalStmt = null;
		PreparedStatement paperStmt = null;
		PreparedStatement conferenceStmt = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			conn.setAutoCommit(false);
			String query1 = "Insert into Appraisal values(?, ?, ?);";
			appraisalStmt = conn.prepareStatement(query1);
			appraisalStmt.setString(1, model.getEmployeeId());
			appraisalStmt.setString(2, model.getHod());
			appraisalStmt.setString(3, model.getFacultyMentor());
			appraisalStmt.executeUpdate();
			
			String query2 = "Insert into Paper values(?, ?, ?, ?, ?, ?);";
			for(ResearchPaper paper: model.getPapers()) {
				DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				paperStmt = conn.prepareStatement(query2);
				paperStmt.setString(1, model.getEmployeeId());
				paperStmt.setString(2, paper.getTitle());
				paperStmt.setString(3, paper.getDescription());
				paperStmt.setDate(4, Date.valueOf((LocalDate.parse(paper.getDate(), parser))));
				paperStmt.setString(5, paper.getJournals());
				paperStmt.setInt(6, paper.isFirstAuthor() ? 1 : 0);
				paperStmt.executeUpdate();
			}
			
			String query3 = "Insert into Conference values(?, ?, ?, ?, ?, ?, ?);";
			for(Conference conference: model.getConferences()) {
				DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				conferenceStmt = conn.prepareStatement(query3);
				conferenceStmt.setString(1, model.getEmployeeId());
				conferenceStmt.setString(2, conference.getTopic());
				conferenceStmt.setString(3, conference.getDescription());
				conferenceStmt.setString(4, conference.getAttendedOrConducted());
				conferenceStmt.setDate(5, Date.valueOf((LocalDate.parse(conference.getStartDate(), parser))));
				conferenceStmt.setDate(6, Date.valueOf((LocalDate.parse(conference.getEndDate(), parser))));
				conferenceStmt.setInt(7, conference.isSpeaker() ? 1 : 0);
				conferenceStmt.executeUpdate();
			}
			conn.commit();
			instance.showSuccessMessage();
		} catch(SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			instance.showSQLConnectionError();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			instance.showSQLConnectionError();
		} finally {
			try {
				if(conn != null)
				conn.close();
				if(appraisalStmt != null)
				appraisalStmt.close();
				if(paperStmt != null)
				paperStmt.close();
				if(conferenceStmt != null)
				conferenceStmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}

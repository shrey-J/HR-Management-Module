package model;

import java.util.ArrayList;

public class AppraisalModel {
	private String employeeId;
	private String employeeName;
	private String employeeDept;
	private String employeeDesignation;
	private String hod;
	private String facultyMentor;
	private ArrayList<ResearchPaper> papers;
	private ArrayList<Conference> conferences;
	public AppraisalModel(String employeeId, String employeeName, String employeeDept, String employeeDesignation,
			String hod, String facultyMentor) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeDept = employeeDept;
		this.employeeDesignation = employeeDesignation;
		this.hod = hod;
		this.facultyMentor = facultyMentor;
		papers = new ArrayList<>();
		conferences = new ArrayList<>();
	}
	
	public AppraisalModel() {
		super();
		papers = new ArrayList<>();
		conferences = new ArrayList<>();
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeDept() {
		return employeeDept;
	}
	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}
	public String getEmployeeDesignation() {
		return employeeDesignation;
	}
	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}
	public String getHod() {
		return hod;
	}
	public void setHod(String hod) {
		this.hod = hod;
	}
	public String getFacultyMentor() {
		return facultyMentor;
	}
	public void setFacultyMentor(String facultyMentor) {
		this.facultyMentor = facultyMentor;
	}
	public ArrayList<ResearchPaper> getPapers() {
		return papers;
	}
	public void setPapers(ArrayList<ResearchPaper> papers) {
		this.papers = papers;
	}
	public ArrayList<Conference> getConferences() {
		return conferences;
	}
	public void setConferences(ArrayList<Conference> conferences) {
		this.conferences = conferences;
	}
	public void addPaper(ResearchPaper paper) {
		papers.add(paper);
	}
	public void addPaper(ResearchPaper paper, int index) {
		papers.add(index, paper);
	}
	public void addConference(Conference conference) {
		conferences.add(conference);
	}
	public void addConference(Conference conference, int index) {
		conferences.add(index, conference);
	}


	@Override
	public String toString() {
		return "AppraisalModel [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeDept="
				+ employeeDept + ", employeeDesignation=" + employeeDesignation + ", hod=" + hod + ", facultyMentor="
				+ facultyMentor + "]";
	}
}

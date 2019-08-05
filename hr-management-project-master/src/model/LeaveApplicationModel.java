/**
 * 
 */
package model;

import java.time.LocalDate;

/**
 * @author Kartik Shankhavaram
 *
 */
public class LeaveApplicationModel {
	private int applicationId;
	private String employeeName;
	private String employeeDept;
	private String employeeId;
	private LocalDate applicationDate;
	private String designation;
	private String typeOfLeave;
	private boolean isUrgent;
	private LocalDate startDate;
	private LocalDate endDate;
	private long totalDays;
	private String appliedTo;
	private String leaveReason;
	private int hodStatus;
	private int registrarStatus;
	private int dofaStatus;
	private int directorStatus;
	private int applicationStatus;
	
	public LeaveApplicationModel(String employeeId) {
		this.employeeId = employeeId;
	}

	public LeaveApplicationModel(String employeeName, String employeeDept, String employeeId, LocalDate applicationDate,
			String designation, String typeOfLeave, boolean isUrgent, LocalDate startDate, LocalDate endDate, long totalDays,
			String appliedTo, String leaveReason) {
		super();
		this.employeeName = employeeName;
		this.employeeDept = employeeDept;
		this.employeeId = employeeId;
		this.applicationDate = applicationDate;
		this.designation = designation;
		this.typeOfLeave = typeOfLeave;
		this.isUrgent = isUrgent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalDays = totalDays;
		this.appliedTo = appliedTo;
		this.leaveReason = leaveReason;
	}

	public LeaveApplicationModel(String employeeName, String employeeDept, String employeeId, LocalDate applicationDate,
			String designation, String typeOfLeave, boolean isUrgent, LocalDate startDate, LocalDate endDate, long totalDays,
			String appliedTo, String leaveReason, int applicationStatus) {
		super();
		this.employeeName = employeeName;
		this.employeeDept = employeeDept;
		this.employeeId = employeeId;
		this.applicationDate = applicationDate;
		this.designation = designation;
		this.typeOfLeave = typeOfLeave;
		this.isUrgent = isUrgent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalDays = totalDays;
		this.appliedTo = appliedTo;
		this.leaveReason = leaveReason;
		this.applicationStatus = applicationStatus;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void getEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTypeOfLeave() {
		return typeOfLeave;
	}

	public void setTypeOfLeave(String typeOfLeave) {
		this.typeOfLeave = typeOfLeave;
	}

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgent(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public long getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(long totalDays) {
		this.totalDays = totalDays;
	}

	public String getAppliedTo() {
		return appliedTo;
	}

	public void setAppliedTo(String appliedTo) {
		this.appliedTo = appliedTo;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public int getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public int getHodStatus() {
		return hodStatus;
	}

	public void setHodStatus(int hodStatus) {
		this.hodStatus = hodStatus;
	}

	public int getRegistrarStatus() {
		return registrarStatus;
	}

	public void setRegistrarStatus(int registrarStatus) {
		this.registrarStatus = registrarStatus;
	}

	public int getDofaStatus() {
		return dofaStatus;
	}

	public void setDofaStatus(int dofaStatus) {
		this.dofaStatus = dofaStatus;
	}

	public int getDirectorStatus() {
		return directorStatus;
	}

	public void setDirectorStatus(int directorStatus) {
		this.directorStatus = directorStatus;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "LeaveApplicationObject [employeeName=" + employeeName + ", employeeDept=" + employeeDept
				+ ", employeeNo=" + employeeId + ", applicationDate=" + applicationDate + ", designation=" + designation
				+ ", typeOfLeave=" + typeOfLeave + ", isUrgent=" + isUrgent + ", startDate=" + startDate + ", endDate="
				+ endDate + ", totalDays=" + totalDays + ", appliedTo=" + appliedTo + ", leaveReason=" + leaveReason
				+ ", applicationStatus=" + applicationStatus + "]";
	}
	
}

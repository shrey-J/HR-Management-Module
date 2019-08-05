package model;

public class LeaveRecordModel {
	
	private String employeeId;
	private String cl;
	private String pl;
	private String ccl;
	private String odl;
	private String ol;
	
	public LeaveRecordModel(String employeeId, String cl, String pl, String ccl, String odl, String ol) {
		super();
		this.employeeId = employeeId;
		this.cl = cl;
		this.pl = pl;
		this.ccl = ccl;
		this.odl = odl;
		this.ol = ol;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getCl() {
		return cl;
	}
	public void setCl(String cl) {
		this.cl = cl;
	}
	public String getPl() {
		return pl;
	}
	public void setPl(String pl) {
		this.pl = pl;
	}
	public String getCcl() {
		return ccl;
	}
	public void setCcl(String ccl) {
		this.ccl = ccl;
	}
	public String getOdl() {
		return odl;
	}
	public void setOdl(String odl) {
		this.odl = odl;
	}
	public String getOl() {
		return ol;
	}
	public void setOl(String ol) {
		this.ol = ol;
	}
	@Override
	public String toString() {
		return "LeaveRecordModel [employeeId=" + employeeId + ", cl=" + cl + ", pl=" + pl + ", ccl=" + ccl + ", odl="
				+ odl + ", ol=" + ol + "]";
	}
	
		
	
}

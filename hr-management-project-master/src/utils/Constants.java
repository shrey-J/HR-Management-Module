package utils;

public class Constants {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	public static final String DB_URL = "jdbc:mysql://localhost:3306/HR_MANAGEMENT?useSSL=false";

	public static final String USER = "kartik";
	public static final String PASS = "password";
	
	public static final int PENDING = 2;
	public static final int APPROVED = 1;
	public static final int REJECTED = 0;
	public static final int NOT_APPLICABLE = 3;
	
	public static final int HOD_CSE = 0;
	public static final int HOD_ECE = 1;
	public static final int HOD_ME = 2;
	public static final int REGISTRAR = 3;
	public static final int DOFA = 4;
	public static final int DIRECTOR = 5;

}

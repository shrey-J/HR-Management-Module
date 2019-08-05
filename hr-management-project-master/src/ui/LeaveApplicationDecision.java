package ui;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.LeaveApplicationDecisionController;
import model.LeaveApplicationModel;
import model.LeaveRecordModel;
import utils.Constants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;

public class LeaveApplicationDecision extends JFrame {

	private JPanel contentPane;
	private int userType;
	private ArrayList<LeaveApplicationModel> applications;
	private LeaveApplicationDecisionController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeaveApplicationDecision frame = new LeaveApplicationDecision(Constants.DIRECTOR);
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LeaveApplicationDecision(int userType) {
		
		this.userType = userType;
		applications = new ArrayList<>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("Pending Leave Application List");
				
		controller = new LeaveApplicationDecisionController(userType, this);
		
		recreate();
//		setApplications(controller.getApplicationsData());
	}
	
	private void addListItem(int index, LeaveApplicationModel application, boolean noApplications) {
		if(index == -1) {
			JPanel panel = new JPanel(null);
			int height = 30;
			panel.setBounds(20, 60, 775, height);
			JLabel lblIndex = new JLabel("No.");
			lblIndex.setBounds(0, 0, 40, height);
			JLabel lblAppDate = new JLabel("Application Date");
			lblAppDate.setBounds(45, 0, 120, height);
			JLabel lblEmployee = new JLabel("Name");
			lblEmployee.setBounds(170, 0, 150, height);
			JLabel lblUrgent = new JLabel("Urgent");
			lblUrgent.setBounds(325, 0, 50, height);
			JLabel lblTypeOfLeave = new JLabel("Type of leave");
			lblTypeOfLeave.setBounds(380, 0, 90, height);
			JLabel lblFrom = new JLabel("From");
			lblFrom.setBounds(475, 0, 90, height);
			JLabel lblTo = new JLabel("To");
			lblTo.setBounds(570, 0, 90, height);
			panel.add(lblIndex);
			panel.add(lblAppDate);
			panel.add(lblEmployee);
			panel.add(lblUrgent);
			panel.add(lblTypeOfLeave);
			panel.add(lblFrom);
			panel.add(lblTo);
			contentPane.add(panel);
		} else if(noApplications) {
			JPanel panel = new JPanel(null);
			panel.setBounds(20, 90, 570, 30);
			JLabel lblNoApp = new JLabel("No pending applications found.");
			lblNoApp.setBounds(150, 30, 300, 30);
			panel.add(lblNoApp);
			contentPane.add(panel);
		} else{
			Font font;
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			JPanel panel = new JPanel(null);
			int height = 30;
			panel.setBounds(20, 80 + index*height, 775, height);
			JLabel lblIndex = new JLabel(index + 1 + ". ");
			lblIndex.setBounds(0, 0, 40, height);
			font = lblIndex.getFont();
			font = font.deriveFont(
			    Collections.singletonMap(
			        TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
			lblIndex.setFont(font);
			JLabel lblAppDate = new JLabel(formatter.format(application.getApplicationDate()));
			lblAppDate.setBounds(45, 0, 120, height);
			font = lblAppDate.getFont();
			font = font.deriveFont(
			    Collections.singletonMap(
			        TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
			lblAppDate.setFont(font);
			JLabel lblEmployee = new JLabel(application.getEmployeeName());
			lblEmployee.setBounds(170, 0, 150, height);
			font = lblEmployee.getFont();
			font = font.deriveFont(
			    Collections.singletonMap(
			        TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
			lblEmployee.setFont(font);
			JLabel lblUrgent = new JLabel(application.isUrgent()?"Yes":"No");
			lblUrgent.setBounds(325, 0, 50, height);
			font = lblUrgent.getFont();
			font = font.deriveFont(
			    Collections.singletonMap(
			        TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
			lblUrgent.setFont(font);
			JLabel lblTypeOfLeave = new JLabel(application.getTypeOfLeave());
			lblTypeOfLeave.setBounds(380, 0, 90, height);
			font = lblTypeOfLeave.getFont();
			font = font.deriveFont(
			    Collections.singletonMap(
			        TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
			lblTypeOfLeave.setFont(font);
			JLabel lblFrom = new JLabel(formatter.format(application.getStartDate()));
			lblFrom.setBounds(475, 0, 90, height);
			font = lblFrom.getFont();
			font = font.deriveFont(
			    Collections.singletonMap(
			        TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
			lblFrom.setFont(font);
			JLabel lblTo = new JLabel(formatter.format(application.getEndDate()));
			lblTo.setBounds(570, 0, 90, height);
			font = lblTo.getFont();
			font = font.deriveFont(
			    Collections.singletonMap(
			        TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD));
			lblTo.setFont(font);
			JButton reviewButton = new JButton("Review");
			reviewButton.setBounds(665, 3, 90, height-5);
			reviewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(application.getHodStatus() == Constants.NOT_APPLICABLE) {
						LeaveApplicationNTSReview ntsReview = new LeaveApplicationNTSReview(application, userType, LeaveApplicationDecision.this);
						contentPane.removeAll();
					} else if(application.getRegistrarStatus() == Constants.NOT_APPLICABLE){
						LeaveApplicationTSReview tsReview = new LeaveApplicationTSReview(application, userType, LeaveApplicationDecision.this);
						contentPane.removeAll();
					}
				}
			});
			panel.add(lblIndex);
			panel.add(lblAppDate);
			panel.add(lblEmployee);
			panel.add(lblUrgent);
			panel.add(lblTypeOfLeave);
			panel.add(lblFrom);
			panel.add(lblTo);
			panel.add(reviewButton);
			contentPane.add(panel);
		}
	}
	
	public void setApplications() {
		recreate();
	}
	
/*	private void getApplications() {
		Connection conn = null;
		PreparedStatement stmt = null;
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
			setVisible(true);
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Failed to fetch leave applications.", "Operation failed", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Failed to fetch leave applications.", "Operation failed", JOptionPane.ERROR_MESSAGE);
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
	}*/
	
	public void recreate() {
		
		controller.getApplications();
		ArrayList<LeaveApplicationModel> applications = controller.getApplicationsData();
		
		JLabel lblNewLabel = new JLabel("Leave Applications");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel.setBounds(306, 30, 200, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBounds(31, 26, 114, 25);
		contentPane.add(btnBack);
		
		JLabel lblLoggedIn = new JLabel("");
		lblLoggedIn.setBounds(647, 0, 136, 15);
		switch(userType) {
			case Constants.DIRECTOR: 
				lblLoggedIn.setText("Hello Director");
				break;
			case Constants.HOD_CSE: 
				lblLoggedIn.setText("Hello CSE HOD");
				break;
			case Constants.HOD_ECE: 
				lblLoggedIn.setText("Hello ECE HOD");
				break;
			case Constants.HOD_ME: 
				lblLoggedIn.setText("Hello ME HOD");
				break;
			case Constants.REGISTRAR: 
				lblLoggedIn.setText("Hello Registrar");
				break;
			case Constants.DOFA: 
				lblLoggedIn.setText("Hello DOFA");
				break;
		}
		contentPane.add(lblLoggedIn);
		
		addListItem(-1, null, false);
		
		if(applications == null || applications.size() == 0 ) {
			addListItem(0, null, true);
		} else {
			for(int i = 0; i < applications.size(); i++) {
				addListItem(i, applications.get(i), false);
			}
		}	
		validate();
		repaint();
		
		setVisible(true);
	}
	
	public void showConnectionError() {
		JOptionPane.showMessageDialog(this, "Failed to fetch leave applications.", "Operation failed", JOptionPane.ERROR_MESSAGE);
		setVisible(false);
	}
}

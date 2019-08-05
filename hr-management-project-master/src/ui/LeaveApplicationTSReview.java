package ui;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import controller.LeaveApplicationTSReviewController;
import model.LeaveApplicationModel;
import utils.Constants;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class LeaveApplicationTSReview extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LeaveApplicationModel application;
	private LeaveApplicationDecision applicationDecisionList;
	private int userType;
	private LeaveApplicationTSReviewController controller;
	
	private JLabel lblEDept;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					LeaveApplicationReview frame = new LeaveApplicationReview();
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
	public LeaveApplicationTSReview(LeaveApplicationModel application, int userType, LeaveApplicationDecision applicationDecisionList) {
		
		this.application = application;
		this.userType = userType;
		this.applicationDecisionList = applicationDecisionList;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setTitle("Leave Application Review");
		
		JLabel lblNewLabel = new JLabel("Leave Application Review");
		lblNewLabel.setBounds(149, 22, 303, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(26, 53, 113, 14);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(312, 139, 85, 14);
		
		JLabel lblEmployeeId = new JLabel("Employee No");
		lblEmployeeId.setBounds(26, 97, 113, 14);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(312, 97, 85, 14);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(26, 139, 113, 14);
		
		JLabel lblLeaveDetails = new JLabel("Leave Details");
		lblLeaveDetails.setBounds(208, 182, 113, 14);
		lblLeaveDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_1 = new JLabel("Type of Leave");
		lblNewLabel_1.setBounds(26, 254, 113, 14);
		
		String[] leaves = new String[] {"PL", "CL", "ODL", "OL"};
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(26, 297, 46, 24);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(288, 297, 46, 24);
		
		JLabel lblTotalNoOf = new JLabel("Total no. of days");
		lblTotalNoOf.setBounds(26, 339, 113, 14);
		
		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(29, 381, 54, 24);
		
		String[] applyToRoles = new String[] {"HOD", "DOFA", "DORC", "Director"};
		
		JLabel numberOfLeaveDays = new JLabel("");
		numberOfLeaveDays.setBounds(149, 339, 46, 14);
		
		JLabel lblLoggedIn = new JLabel("");
		lblLoggedIn.setBounds(372, 0, 136, 15);
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
		
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblEmployeeName);
		contentPane.add(lblDepartment);
		contentPane.add(lblEmployeeId);
		contentPane.add(lblDate);
		contentPane.add(lblDesignation);
		contentPane.add(lblLeaveDetails);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblFrom);
		contentPane.add(lblTo);
		contentPane.add(lblTotalNoOf);
		contentPane.add(lblReason);
		contentPane.add(numberOfLeaveDays);
		
		JLabel lblEName = new JLabel("");
		lblEName.setBounds(149, 53, 185, 14);
		contentPane.add(lblEName);
		
		JLabel lblEId = new JLabel("");
		lblEId.setBounds(149, 97, 151, 14);
		contentPane.add(lblEId);
		
		JLabel lblEDesignation = new JLabel("");
		lblEDesignation.setBounds(149, 139, 164, 14);
		contentPane.add(lblEDesignation);
		
		lblEDept = new JLabel("");
		lblEDept.setBounds(410, 139, 86, 14);
		contentPane.add(lblEDept);
		
		JLabel lblEAppDate = new JLabel("");
		lblEAppDate.setBounds(410, 97, 85, 14);
		contentPane.add(lblEAppDate);
		
		JLabel lblETypeOfLeave = new JLabel("");
		lblETypeOfLeave.setBounds(149, 254, 94, 14);
		contentPane.add(lblETypeOfLeave);
		
		JLabel lblUrgent = new JLabel("Urgent ");
		lblUrgent.setBounds(284, 254, 85, 14);
		contentPane.add(lblUrgent);
		
		JLabel labelEUrgent = new JLabel("");
		labelEUrgent.setBounds(402, 254, 59, 14);
		contentPane.add(labelEUrgent);
		
		JLabel lblEEndDate = new JLabel("");
		lblEEndDate.setBounds(372, 302, 111, 14);
		contentPane.add(lblEEndDate);
		
		JLabel lblEStartDate = new JLabel("");
		lblEStartDate.setBounds(149, 302, 100, 14);
		contentPane.add(lblEStartDate);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(332, 339, 95, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(93, 381, 46, 14);
		contentPane.add(label_1);
		
		JLabel lblEReason = new JLabel("");
		lblEReason.setVerticalAlignment(SwingConstants.TOP);
		lblEReason.setBounds(95, 386, 388, 70);
		contentPane.add(lblEReason);
		
		JLabel lblHodsApproval = new JLabel("HOD's Approval");
		lblHodsApproval.setBounds(26, 476, 113, 14);
		contentPane.add(lblHodsApproval);
		
		JLabel lblDofa = new JLabel("DoFA's approval");
		lblDofa.setBounds(26, 526, 113, 14);
		contentPane.add(lblDofa);
		
		JLabel lblDirector = new JLabel("Director's Approval");
		lblDirector.setBounds(239, 476, 141, 14);
		contentPane.add(lblDirector);
		
		JLabel lblHodApproval = new JLabel("Pending");
		lblHodApproval.setBounds(149, 476, 85, 14);
		contentPane.add(lblHodApproval);
		
		JLabel lblDofaApproval = new JLabel("Pending");
		lblDofaApproval.setBounds(149, 526, 94, 14);
		contentPane.add(lblDofaApproval);
		
		JLabel lblDirectorApproval = new JLabel("Pending");
		lblDirectorApproval.setBounds(392, 476, 69, 14);
		contentPane.add(lblDirectorApproval);
		
		JButton button = new JButton("Approve ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.approveApplication();
			}
		});
		button.setBounds(71, 576, 100, 23);
		contentPane.add(button);
		
		JButton btnReject = new JButton("Reject ");
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.rejectApplication();
			}
		});
		btnReject.setBounds(208, 576, 89, 23);
		contentPane.add(btnReject);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		lblEName.setText(application.getEmployeeName());
		lblEId.setText(application.getEmployeeId());
		lblEDesignation.setText(application.getDesignation());
		lblEAppDate.setText(formatter.format(application.getApplicationDate()));
		lblEName.setText(application.getEmployeeName());
		lblEName.setText(application.getEmployeeName());
		lblETypeOfLeave.setText(application.getTypeOfLeave());
		labelEUrgent.setText(application.isUrgent()? "Yes" : "No");
		lblEStartDate.setText(formatter.format(application.getStartDate()));
		lblEEndDate.setText(formatter.format(application.getEndDate()));
		numberOfLeaveDays.setText(application.getTotalDays() + "");
		lblEReason.setText(application.getLeaveReason());
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				applicationDecisionList.recreate();
				setVisible(false);
			}
		});
		btnBack.setBounds(347, 575, 114, 25);
		contentPane.add(btnBack);
		switch(application.getHodStatus()) {
			case Constants.PENDING:
				lblHodApproval.setText("Pending");
				break;
			case Constants.APPROVED:
				lblHodApproval.setText("Approved");
				break;
			case Constants.REJECTED:
				lblHodApproval.setText("Rejected");
				break;
		}
		switch(application.getDofaStatus()) {
			case Constants.PENDING:
				lblDofaApproval.setText("Pending");
				break;
			case Constants.APPROVED:
				lblDofaApproval.setText("Approved");
				break;
			case Constants.REJECTED:
				lblDofaApproval.setText("Rejected");
				break;
		}
		switch(application.getDirectorStatus()) {
			case Constants.PENDING:
				lblDirectorApproval.setText("Pending");
				break;
			case Constants.APPROVED:
				lblDirectorApproval.setText("Approved");
				break;
			case Constants.REJECTED:
				lblDirectorApproval.setText("Rejected");
				break;
		}
		
		controller = new LeaveApplicationTSReviewController(application, this, userType);
		
	}
	
	public void setDepartment(String department) {
		lblEDept.setText(department);
		setVisible(true);
	}
	
	public void showConnectionError() {
		JOptionPane.showMessageDialog(this, "Failed to fetch data.", "Operation failed", JOptionPane.ERROR_MESSAGE);
		applicationDecisionList.recreate();
	}
	
	public void showSuccess(int action) {
		if(action == 1) {
			JOptionPane.showMessageDialog(this, "Application successfully approved.", "Process successful", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Application successfully rejected.", "Process successful", JOptionPane.INFORMATION_MESSAGE);
		}
		setVisible(false);
		applicationDecisionList.recreate();
	}
	
	public void showFailure(int action) {
		if(action == 1)
			JOptionPane.showMessageDialog(this, "Could not approve application.", "Operation failed", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this, "Could not reject application.", "Operation failed", JOptionPane.ERROR_MESSAGE);
	}
}

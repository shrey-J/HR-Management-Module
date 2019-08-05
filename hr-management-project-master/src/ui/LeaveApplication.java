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

import controller.LeaveApplicationController;
import model.LeaveApplicationModel;
import model.LeaveRecordModel;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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

public class LeaveApplication extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtDepartment;
	private JTextField txtEmployeeId;
	private JTextField txtDate;
	private JTextField txtDesignation;
	private JComboBox<String> comboBoxLeaveType;
	private JTextArea txtAreaReason;
	private JLabel numberOfLeaveDays;
	private DatePicker startDate;
	private DatePicker endDate;
	private JCheckBox chckbxUrgent;
	private String employeeId;
	private LeaveApplicationController controller;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeaveApplication frame = new LeaveApplication("13NTS003");
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
	public LeaveApplication(String employeeId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setTitle("Leave Application Form");

		this.employeeId = employeeId;
		
		JLabel lblNewLabel = new JLabel("Leave Application Form");
		lblNewLabel.setBounds(181, 11, 303, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(26, 53, 113, 14);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(304, 97, 85, 14);
		
		JLabel lblEmployeeNo = new JLabel("Employee Id");
		lblEmployeeNo.setBounds(26, 97, 113, 14);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(334, 139, 85, 14);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(26, 139, 113, 14);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(149, 50, 202, 20);
		txtName.setColumns(10);
		
		txtDepartment = new JTextField();
		txtDepartment.setEditable(false);
		txtDepartment.setBounds(407, 95, 131, 20);
		txtDepartment.setColumns(10);
		
		txtEmployeeId = new JTextField();
		txtEmployeeId.setEditable(false);
		txtEmployeeId.setBounds(149, 94, 137, 20);
		txtEmployeeId.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(417, 137, 86, 20);
		txtDate.setColumns(10);
		
		txtDesignation = new JTextField();
		txtDesignation.setEditable(false);
		txtDesignation.setBounds(149, 136, 167, 20);
		txtDesignation.setColumns(10);
		
		JLabel lblLeaveDetails = new JLabel("Leave Details");
		lblLeaveDetails.setBounds(229, 208, 122, 14);
		lblLeaveDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_1 = new JLabel("Type of Leave");
		lblNewLabel_1.setBounds(26, 254, 113, 14);
		
		String[] leaves = new String[] {"PL", "CL", "CCL", "ODL", "OL"};
		
		comboBoxLeaveType = new JComboBox(leaves);
		comboBoxLeaveType.setBounds(149, 251, 86, 20);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(26, 297, 46, 24);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(239, 297, 46, 24);
		
		JLabel lblTotalNoOf = new JLabel("Total no. of days");
		lblTotalNoOf.setBounds(26, 339, 113, 14);
		
		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(26, 386, 54, 24);
		
		txtAreaReason = new JTextArea();
		txtAreaReason.setRows(3);
		txtAreaReason.setBounds(95, 381, 351, 61);
		txtAreaReason.setLineWrap(true);
		
		JButton btnGoToAppraisal = new JButton("Submit Leave Application");
		btnGoToAppraisal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submitApplication();
			}
		});
		btnGoToAppraisal.setBounds(95, 467, 216, 23);
		
		numberOfLeaveDays = new JLabel("1");
		numberOfLeaveDays.setFont(new Font("Dialog", Font.PLAIN, 12));
		numberOfLeaveDays.setBounds(149, 339, 46, 14);
		
		URL dateImageURL = LeaveApplication.class.getResource("/resources/datepickerbutton1.png");
        Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
        ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);

		DatePickerSettings startDateSettings = new DatePickerSettings();
        startDateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
        startDateSettings.setAllowEmptyDates(false);
        startDateSettings.setAllowKeyboardEditing(false);
        
        
		startDate = new DatePicker(startDateSettings);
		startDate.setBounds(72, 297, 160, 24);
		startDate.getComponentToggleCalendarButton().setBounds(136, 0, 24, 24);
		startDate.getComponentDateTextField().setBounds(0, 0, 136, 24);
        startDate.setDate(LocalDate.now().plusDays(7));
        JButton startDatePickerButton = startDate.getComponentToggleCalendarButton();
        startDatePickerButton.setText("");
        startDatePickerButton.setIcon(dateExampleIcon);
		startDate.setLayout(null);
		startDateSettings.setDateRangeLimits(LocalDate.now().plusDays(7), null);
		
		DatePickerSettings endDateSettings = startDateSettings.copySettings();
		endDate = new DatePicker(endDateSettings);
		endDate.setBounds(285, 297, 160, 24);
		endDate.getComponentToggleCalendarButton().setBounds(136, 0, 24, 24);
		endDate.getComponentDateTextField().setBounds(0, 0, 136, 24);
        endDate.setDate(LocalDate.now().plusDays(7));
        JButton endDatePickerButton = endDate.getComponentToggleCalendarButton();
        endDatePickerButton.setText("");
        endDatePickerButton.setIcon(dateExampleIcon);
		endDate.setLayout(null);
		endDateSettings.setDateRangeLimits(LocalDate.now().plusDays(7), null);
		
		startDate.addDateChangeListener(new DateChangeListener() {
			
			@Override
			public void dateChanged(DateChangeEvent arg0) {
				// TODO Auto-generated method stub
				if(startDate.getDate().isAfter(endDate.getDate())) {
					endDate.setDate(startDate.getDate());
				}
				numberOfLeaveDays.setText(startDate.getDate().until(endDate.getDate(), ChronoUnit.DAYS) + 1 + "");				
			}
		});
		
		endDate.addDateChangeListener(new DateChangeListener() {
			
			@Override
			public void dateChanged(DateChangeEvent arg0) {
				// TODO Auto-generated method stub
				if(startDate.getDate().isAfter(endDate.getDate())) {
					startDate.setDate(endDate.getDate().minusDays(1));
				}
				numberOfLeaveDays.setText(startDate.getDate().until(endDate.getDate(), ChronoUnit.DAYS) + 1 + "");
			}
		});
		
		chckbxUrgent = new JCheckBox("Urgent");
		chckbxUrgent.setBounds(285, 250, 97, 23);
		chckbxUrgent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int minDays = 7;
				if(chckbxUrgent.isSelected()) {
					minDays = 0;
				}
				if(!comboBoxLeaveType.getSelectedItem().equals("PL")) {
					minDays = 0;
				}
				startDateSettings.setDateRangeLimits(LocalDate.now().plusDays(minDays), null);
				endDateSettings.setDateRangeLimits(LocalDate.now().plusDays(minDays), null);
				startDate.setDate(LocalDate.now().plusDays(minDays));
				endDate.setDate(LocalDate.now().plusDays(minDays));
			}
		});
		
		comboBoxLeaveType.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int minDays;
				if(comboBoxLeaveType.getSelectedItem().equals("PL")) {
					minDays = 7;
				} else {
					minDays = 0;
				}
				startDateSettings.setDateRangeLimits(LocalDate.now().plusDays(minDays), null);
				endDateSettings.setDateRangeLimits(LocalDate.now().plusDays(minDays), null);
				startDate.setDate(LocalDate.now().plusDays(minDays));
				endDate.setDate(LocalDate.now().plusDays(minDays));
			}
		});
		
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblEmployeeName);
		contentPane.add(txtName);
		contentPane.add(lblDepartment);
		contentPane.add(txtDepartment);
		contentPane.add(lblEmployeeNo);
		contentPane.add(txtEmployeeId);
		contentPane.add(lblDate);
		contentPane.add(txtDate);
		contentPane.add(lblDesignation);
		contentPane.add(txtDesignation);
		contentPane.add(lblLeaveDetails);
		contentPane.add(lblNewLabel_1);
		contentPane.add(comboBoxLeaveType);
		contentPane.add(startDate);
		contentPane.add(endDate);
		contentPane.add(lblFrom);
		contentPane.add(lblTo);
		contentPane.add(lblTotalNoOf);
		contentPane.add(lblReason);
		contentPane.add(txtAreaReason);
		contentPane.add(btnGoToAppraisal);
		contentPane.add(numberOfLeaveDays);
		contentPane.add(chckbxUrgent);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBounds(362, 466, 114, 25);
		contentPane.add(btnBack);
		
		controller = new LeaveApplicationController(employeeId, this);
	}
	
	public void setEmployeeData(LeaveApplicationModel model) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if( model == null) {
			JOptionPane.showMessageDialog(this,
			        "No data found for employee ID: " + employeeId,
			        "Data not found",
			        JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		txtName.setText(model.getEmployeeName());
		txtEmployeeId.setText(model.getEmployeeId());
		txtDesignation.setText(model.getDesignation());
		txtDepartment.setText(model.getEmployeeDept());
		txtDate.setText(formatter.format(model.getApplicationDate()));
		setVisible(true);
	}
	
	public void showMysqlError() {
		JOptionPane.showMessageDialog(this,
		        "Connection to MySQL database failed",
		        "Connection failed",
		        JOptionPane.ERROR_MESSAGE);
	}
	
	public LeaveRecordModel getLeaveRecord() {
		Connection conn = null;
		PreparedStatement stmt = null;
		LeaveRecordModel recordModel = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String query = "SELECT * FROM LeaveRecord WHERE EID = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				recordModel = new LeaveRecordModel(employeeId, rs.getString("CL"), rs.getString("PL"), rs.getString("CCL"), rs.getString("ODL"), rs.getString("OL"));
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
		return recordModel;
	}
	
	private void submitApplication() {
		LeaveRecordModel leaveRecord = getLeaveRecord();
		LeaveApplicationModel model = controller.getModel();
		if(leaveRecord == null) {
			JOptionPane.showMessageDialog(this,
			        "Could not fetch leave record.",
			        "Failed",
			        JOptionPane.ERROR_MESSAGE);
		} else {
			if(comboBoxLeaveType.getSelectedItem().equals("PL")) {
				String pl = leaveRecord.getPl();
				String[] plArr = pl.split("/");
				int leavesTaken = Integer.parseInt(plArr[0]);
				int totalLeaves = Integer.parseInt(plArr[1]);
				int leavesLeft = totalLeaves - leavesTaken;
				int leavesApplied = Integer.parseInt(numberOfLeaveDays.getText());
				if(leavesLeft < leavesApplied) {
					JOptionPane.showMessageDialog(this,
					        "Maximum number of PL exceeded. Applied: " + leavesApplied + ", Available: " + leavesLeft,
					        "Leaves not available",
					        JOptionPane.ERROR_MESSAGE);
							return;
				}
			} else if(comboBoxLeaveType.getSelectedItem().equals("CL")) {
				String cl = leaveRecord.getCl();
				String[] clArr = cl.split("/");
				int leavesTaken = Integer.parseInt(clArr[0]);
				int totalLeaves = Integer.parseInt(clArr[1]);
				int leavesLeft = totalLeaves - leavesTaken;
				int leavesApplied = Integer.parseInt(numberOfLeaveDays.getText());
				if(leavesLeft < leavesApplied) {
					JOptionPane.showMessageDialog(this,
					        "Maximum number of CL exceeded. Applied: " + leavesApplied + ", Available: " + leavesLeft,
					        "Leaves not available",
					        JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			model.setTypeOfLeave((String)comboBoxLeaveType.getSelectedItem());
			model.setUrgent(chckbxUrgent.isSelected());
			model.setStartDate(startDate.getDate());
			model.setEndDate(endDate.getDate());
			model.setTotalDays(startDate.getDate().until(endDate.getDate(), ChronoUnit.DAYS) + 1);
			String reason = txtAreaReason.getText();
			if(reason.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this,
				        "Please fill in the reason for which you wish to take a leave.",
				        "Add reason for leave",
				        JOptionPane.ERROR_MESSAGE);
				return;
			}
			model.setLeaveReason(reason);
			int result = controller.sendApplicationToDatabase(employeeId, model);
			if( result == 0 ) {
				JOptionPane.showMessageDialog(this,
		        "Your application was successfully submitted.",
		        "Submission successful",
		        JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
			} else if ( result == -1 ) {
				JOptionPane.showMessageDialog(this,
		        "Could not submit your application due to an error. Please try again.",
		        "Submission failed",
		        JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.LeaveApplicationModel;
import utils.Constants;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class StaffDashboard extends JFrame {

	private JPanel contentPane;
	private String employeeId;
	private JButton btnFillAppraisalForm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					StaffDashboard frame = new StaffDashboard();
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
	public StaffDashboard(String employeeId) {
		
		this.employeeId = employeeId;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("Staff Dashboard");
		
		JButton btnApplyForLeave = new JButton("Apply for Leave");
		btnApplyForLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LeaveApplication obj = new LeaveApplication(employeeId);
				obj.setVisible(true);
				//setVisible(false);
			}
		});
		btnApplyForLeave.setBounds(122, 29, 196, 23);
		contentPane.add(btnApplyForLeave);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage p = new LoginPage();
				p.setVisible(true);
				setVisible(false);
			}
		});
		btnLogOut.setBounds(169, 226, 114, 25);
		contentPane.add(btnLogOut);
		
		JButton btnViewApplicationStatus = new JButton("View Application\n Status");
		btnViewApplicationStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeaveApplicationStatus obj = new LeaveApplicationStatus(employeeId);
				obj.setVisible(true);
				//setVisible(false);
			}
		});
		btnViewApplicationStatus.setBounds(122, 80, 196, 23);
		contentPane.add(btnViewApplicationStatus);
		
		JButton btnViewRemainingLeaves = new JButton("View Leaves Taken");
		btnViewRemainingLeaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LeavesTaken obj = new LeavesTaken(employeeId);
//				obj.setVisible(true);
				//setVisible(false);
			}
		});
		btnViewRemainingLeaves.setBounds(122, 178, 196, 23);
		contentPane.add(btnViewRemainingLeaves);
		
		btnFillAppraisalForm = new JButton("Fill Appraisal Form");
		btnFillAppraisalForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Appraisal obj = new Appraisal(employeeId);
				//obj.setVisible(true);
				//setVisible(false);
			}
		});
		btnFillAppraisalForm.setBounds(122, 131, 196, 23);
		contentPane.add(btnFillAppraisalForm);
		
		JLabel lblNewLabel = new JLabel("Staff Dashboard");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(152, 0, 166, 15);
		contentPane.add(lblNewLabel);
		
		checkIfAppraisalFilled();
	}
	
	private void checkIfAppraisalFilled() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String query = "Select * FROM Appraisal WHERE EID = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				btnFillAppraisalForm.setEnabled(false);
			}
			setVisible(true);
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
			btnFillAppraisalForm.setEnabled(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			btnFillAppraisalForm.setEnabled(false);
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

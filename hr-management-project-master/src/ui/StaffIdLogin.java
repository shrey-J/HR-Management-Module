package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.LeaveApplicationController;
import controller.StaffIdLoginController;
import model.LeaveApplicationModel;
import utils.Constants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffIdLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtStaffId;
	private StaffIdLoginController controller;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffIdLogin frame = new StaffIdLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StaffIdLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("Staff Login");
		
		JLabel lblNewLabel = new JLabel("Staff ID Login");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(154, 12, 172, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterStaffId = new JLabel("Enter Staff ID");
		lblEnterStaffId.setBounds(22, 95, 130, 15);
		contentPane.add(lblEnterStaffId);
		
		txtStaffId = new JTextField();
		txtStaffId.setBounds(133, 93, 193, 19);
		contentPane.add(txtStaffId);
		txtStaffId.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.checkId(txtStaffId.getText());
			}
		});
		btnLogin.setBounds(143, 134, 114, 25);
		contentPane.add(btnLogin);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPage p = new LoginPage();
				p.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setBounds(272, 134, 114, 25);
		contentPane.add(btnBack);
		
		controller = new StaffIdLoginController(this);
	}
	
	public void createNewStaffDashboard(String id) {
		setVisible(false);
		StaffDashboard s = new StaffDashboard(id);
		s.setVisible(true);
	}
	
	public void handleErrors(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}
}

package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Constants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class AuthorityDashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					AuthorityDashboard frame = new AuthorityDashboard();
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
	public AuthorityDashboard(int userType) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("Dashboard");
		
		JButton btnViewPendingApplications = new JButton("View Pending Applications");
		btnViewPendingApplications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LeaveApplicationDecision a = new LeaveApplicationDecision(userType);
			}
		});
		btnViewPendingApplications.setBounds(107, 126, 251, 36);
		contentPane.add(btnViewPendingApplications);
		
		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(176, 43, 103, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblLoggedIn = new JLabel("");
		lblLoggedIn.setBounds(314, 0, 136, 15);
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
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage p = new LoginPage();
				p.setVisible(true);
				setVisible(false);
			}
		});
		btnLogOut.setBounds(176, 215, 114, 25);
		contentPane.add(btnLogOut);
	}
}

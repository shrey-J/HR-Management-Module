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

public class HeadofDepartment extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HeadofDepartment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setTitle("HOD Department Selection");
		
		JButton btnCSE = new JButton("CSE HOD");
		btnCSE.setBounds(25, 99, 124, 61);
		btnCSE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AuthorityDashboard d = new AuthorityDashboard(Constants.HOD_CSE);
				d.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnCSE);
		
		JButton btnECE = new JButton("ECE HOD");
		btnECE.setBounds(168, 102, 124, 61);
		btnECE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuthorityDashboard d = new AuthorityDashboard(Constants.HOD_ECE);
				d.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnECE);
		
		JButton btnME = new JButton("ME HOD");
		btnME.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AuthorityDashboard d = new AuthorityDashboard(Constants.HOD_ME);
				d.setVisible(true);
				setVisible(false);
			}
		});
		btnME.setBounds(304, 99, 124, 61);
		contentPane.add(btnME);
		
		JLabel lblSelectA = new JLabel("Select a Department");
		lblSelectA.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSelectA.setBounds(125, 12, 259, 15);
		contentPane.add(lblSelectA);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBounds(178, 223, 114, 25);
		contentPane.add(btnBack);
	}
}




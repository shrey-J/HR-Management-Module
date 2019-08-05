package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.LeaveRecordController;
import model.LeaveRecordModel;

import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LeavesTaken extends JFrame {

	private JPanel contentPane;
	private JTable leaveRecordTable;
	private JButton btnBack;
	private String employeeId;
	private JScrollPane sp;
	private LeaveRecordController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeavesTaken frame = new LeavesTaken("16TS112");
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
	public LeavesTaken(String employeeId) {
		this.employeeId = employeeId;
		System.out.print("Hello " + employeeId);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("Leave Record");
		
		sp = new JScrollPane();
		sp.setBounds(10, 52, 414, 209);
		contentPane.add(sp);
		
		JLabel lblNewLabel = new JLabel("Leave Record");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(160, 12, 264, 15);
		contentPane.add(lblNewLabel);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnBack.setBounds(178, 272, 89, 23);
		contentPane.add(btnBack);
		
		controller = new LeaveRecordController(employeeId, this);
	}
	
	public void showLeaveRecord(boolean dataPresent, LeaveRecordModel record) {
		if(dataPresent) {
			contentPane.remove(sp);
			String[][] data = new String[5][2];
			data[0][0] = "PL taken";
			data[0][1] = record.getPl();
			data[1][0] = "CL taken";
			data[1][1] = record.getCl();
			data[2][0] = "CCL taken";
			data[2][1] = record.getCcl();
			data[3][0] = "ODL taken";
			data[3][1] = record.getOdl();
			data[4][0] = "OL taken";
			data[4][1] = record.getOl();
			String columns[] = {"Type of leave", "Number of leaves taken"};
			leaveRecordTable = new JTable(data, columns);
			leaveRecordTable.setBounds(10, 52, 414, 209);
			sp = new JScrollPane(leaveRecordTable);
			sp.setBounds(10, 52, 414, 209);
			contentPane.add(sp);
			DefaultTableModel model = new DefaultTableModel(data, columns) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			leaveRecordTable.setModel(model);
			validate();
			repaint();
			setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "No leave record found for Employee ID: " + employeeId, "No Record found", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void showSQLConnectionError() {
		JOptionPane.showMessageDialog(this,
		        "Connection to MySQL database failed",
		        "Connection failed",
		        JOptionPane.ERROR_MESSAGE);
	}

}

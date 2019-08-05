package ui;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.LeaveApplicationStatusController;

import model.LeaveApplicationModel;
import utils.Constants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class LeaveApplicationStatus extends JFrame {

	private JPanel contentPane;
	String employeeId;
	ArrayList<LeaveApplicationModel> applications;
	private JScrollPane sp;
	JTable applicationStatusTable;
	private LeaveApplicationStatusController controller;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeaveApplicationStatus frame = new LeaveApplicationStatus("16TS112");
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
	public LeaveApplicationStatus(String employeeId) {
		
		this.employeeId = employeeId;
		applications = new ArrayList<>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setTitle("Leave Applications");
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(143, 277, 135, 23);
		contentPane.add(btnNewButton);
		
		controller = new LeaveApplicationStatusController(this, employeeId);
	}
	
	public void showTable(ArrayList<LeaveApplicationModel> applications) {
		String columns[] = {"No.", "Application date", "Type of leave", "Urgent", "From", "To", "Status"};
		String[][] data = new String[applications.size()][7];
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for(int i = 0; i < applications.size(); i++) {
			LeaveApplicationModel application = applications.get(i);
			data[i][0] = i + 1 + "";
			data[i][1] = formatter.format(application.getApplicationDate());
			data[i][2] = application.getTypeOfLeave();
			data[i][3] = application.isUrgent()? "Yes" : "No";
			data[i][4] = formatter.format(application.getStartDate());
			data[i][5] = formatter.format(application.getEndDate());
			switch(application.getApplicationStatus()) {
				case Constants.APPROVED:
					data[i][6] = "Approved";
					break;
				case Constants.REJECTED:
					data[i][6] = "Rejected";
					break;
				case Constants.PENDING:
					data[i][6] = "Pending";
					break;
				default:
					data[i][6] = "Not available";
			}
		}
		DefaultTableModel model = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		applicationStatusTable = new JTable(data, columns);
		applicationStatusTable.setModel(model);
		applicationStatusTable.setBounds(5, 5, 430, 261);
		applicationStatusTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		applicationStatusTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		applicationStatusTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		applicationStatusTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		applicationStatusTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		applicationStatusTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		applicationStatusTable.getColumnModel().getColumn(5).setPreferredWidth(80);
		applicationStatusTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	LeaveApplicationModel selectedApplication = applications.get(table.getSelectedRow());
		        	if(selectedApplication.getHodStatus() == Constants.NOT_APPLICABLE) {
		        		ApplicationStatusNTS status = new ApplicationStatusNTS(selectedApplication);
		        	} else {
		        		ApplicationStatusTS status = new ApplicationStatusTS(selectedApplication);
		        	}
		        }
		    }
		});
		contentPane.setLayout(null);
		JScrollPane sp = new JScrollPane(applicationStatusTable);
		sp.setBounds(5, 39, 430, 227);
		contentPane.add(sp);
		
		JLabel lblNewLabel = new JLabel("Leave Application Status");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(107, 12, 264, 15);
		contentPane.add(lblNewLabel);
		validate();
		repaint();
		setVisible(true);
	}
	
	public void handleErrors(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}
}

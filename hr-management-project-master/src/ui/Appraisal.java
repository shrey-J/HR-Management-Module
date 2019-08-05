package ui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import controller.Appraisal_Controller;
import model.AppraisalModel;
import model.Conference;
import model.ResearchPaper;
import utils.DocumentSizeFilter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Appraisal extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmployeeName;
	private JTextField txtEmployeeDept;
	private JTextField txtEmployeeId;
	private JTextField txtEmployeeDesignation;
	private JTextField txtTitle;
	private JTextField txtConfTitle;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable papersTable;
	private JTable conferencesTable;
	private JPanel panel;
	private ImageIcon dateButtonIcon;
	private JLabel lblHOD;
	private Appraisal_Controller controller;
	private String employeeId;
	private DatePicker publicationDatePicker;
	private JComboBox comboBoxAC;
	private	JTextArea txtAreaConfDesc;
	private JComboBox comboBoxSpeaker;
	private DatePicker conferenceStartDate;
	private DatePicker conferenceEndDate;
	private JTextArea txtAreaDesc;
	private JTextArea txtAreaFM;
	private JTextArea txtAreaHOD;
	private JComboBox comboBoxFirstAuthor;
	private JTextArea txtAreaJournals;
	private boolean editingMode = false;
	private int editIndex = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Appraisal frame = new Appraisal("16TS112");
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Appraisal(String employeeId) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setTitle("Appraisal Form");
		
		panel = new JPanel();
		panel.setBounds(471, 127, 438, 423);
		contentPane.add(panel);
		
		JLabel lblAppraisal = new JLabel("Appraisal Form");
		lblAppraisal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAppraisal.setBounds(161, 23, 132, 19);
		contentPane.add(lblAppraisal);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(32, 78, 138, 14);
		contentPane.add(lblEmployeeName);
		
		JLabel lblEmployeeNo = new JLabel("Employee No");
		lblEmployeeNo.setBounds(32, 128, 121, 14);
		contentPane.add(lblEmployeeNo);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(32, 171, 121, 14);
		contentPane.add(lblDepartment);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(32, 218, 138, 14);
		contentPane.add(lblDesignation);
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.setEditable(false);
		txtEmployeeName.setBounds(188, 76, 173, 20);
		contentPane.add(txtEmployeeName);
		txtEmployeeName.setColumns(10);
		
		txtEmployeeDept = new JTextField();
		txtEmployeeDept.setEditable(false);
		txtEmployeeDept.setBounds(188, 169, 173, 20);
		contentPane.add(txtEmployeeDept);
		txtEmployeeDept.setColumns(10);
		
		txtEmployeeId = new JTextField();
		txtEmployeeId.setEditable(false);
		txtEmployeeId.setBounds(188, 126, 173, 20);
		contentPane.add(txtEmployeeId);
		txtEmployeeId.setColumns(10);
		
		txtEmployeeDesignation = new JTextField();
		txtEmployeeDesignation.setEditable(false);
		txtEmployeeDesignation.setBounds(188, 216, 173, 20);
		contentPane.add(txtEmployeeDesignation);
		txtEmployeeDesignation.setColumns(10);
		
		lblHOD = new JLabel("HOD");
		lblHOD.setBounds(32, 277, 121, 14);
		contentPane.add(lblHOD);
		
		txtAreaHOD = new JTextArea();
        txtAreaHOD.setBounds(161, 277, 275, 47);
        txtAreaHOD.setLineWrap(true);
        JScrollPane spHOD = new JScrollPane(txtAreaHOD);
		spHOD.setBounds(161, 277, 275, 47);
		contentPane.add(spHOD);
		
		JLabel lblWhatToAdd = new JLabel("What to add?");
		lblWhatToAdd.setBounds(481, 78, 95, 14);
		contentPane.add(lblWhatToAdd);
		panel.setLayout(null);
		
		JRadioButton rdbtnPaper = new JRadioButton("Research Paper");
		buttonGroup.add(rdbtnPaper);
		rdbtnPaper.setBounds(590, 74, 177, 23);
		contentPane.add(rdbtnPaper);
		
		URL dateImageURL = LeaveApplication.class.getResource("/resources/datepickerbutton1.png");
        Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
        dateButtonIcon = new ImageIcon(dateExampleImage);
        
		rdbtnPaper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				panel.removeAll();
				showPaperTable();
				validate();
				repaint();
				
			}
		});
		
		JRadioButton rdbtnConference = new JRadioButton("Conference");
		buttonGroup.add(rdbtnConference);
		rdbtnConference.setBounds(771, 74, 138, 23);
		contentPane.add(rdbtnConference);
		
		JLabel lblFacultyMentor = new JLabel("Faculty Mentor");
		lblFacultyMentor.setBounds(32, 347, 121, 15);
		contentPane.add(lblFacultyMentor);
		
		txtAreaFM = new JTextArea();
        txtAreaFM.setBounds(161, 347, 275, 58);
        contentPane.add(txtAreaFM);
        txtAreaFM.setLineWrap(true);
		JScrollPane spFM = new JScrollPane(txtAreaFM);
		spFM.setBounds(161, 347, 275, 58);
		contentPane.add(spFM);
		
		JButton btnSubmitAppraisalForm = new JButton("Submit Appraisal Form");
		btnSubmitAppraisalForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.setHOD(txtAreaHOD.getText());
				controller.setFacultyMentor(txtAreaFM.getText());
				controller.submitAppraisalForm();
			}
		});
		btnSubmitAppraisalForm.setBounds(80, 499, 210, 25);
		contentPane.add(btnSubmitAppraisalForm);
		
		rdbtnConference.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Conference choice
				
				panel.removeAll();
				showConferenceTable();				
				validate();
				repaint();
			}
		});
		this.employeeId = employeeId;
		controller = new Appraisal_Controller(employeeId, this);
		showPaperTable();
        rdbtnPaper.setSelected(true);
        
        JButton btnNewButto = new JButton("Back");
        btnNewButto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		setVisible(false);
        	}
        });
        btnNewButto.setBounds(333, 499, 114, 25);
        contentPane.add(btnNewButto);
        
	}
	
	private void showConferenceForm() {
		JLabel lblConferenceTopic = new JLabel("Conference Topic");
		lblConferenceTopic.setBounds(10, 0, 139, 20);
		panel.add(lblConferenceTopic);
		
		txtConfTitle = new JTextField();
		txtConfTitle.setBounds(159, 0, 269, 20);
		panel.add(txtConfTitle);
		txtConfTitle.setColumns(10);
		
		JLabel lblAttendedconducted = new JLabel("Attended/Conducted");
		lblAttendedconducted.setBounds(18, 212, 160, 14);
		panel.add(lblAttendedconducted);
		
		String[] confChoice = new String[] {"Attended", "Conducted"};
		
		comboBoxAC = new JComboBox(confChoice);
		comboBoxAC.setBounds(198, 209, 97, 20);
		panel.add(comboBoxAC);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 60, 105, 14);
		panel.add(lblDescription);
		
		txtAreaConfDesc = new JTextArea();
		JScrollPane spConfDesc = new JScrollPane(txtAreaConfDesc);
		spConfDesc.setBounds(159, 55, 269, 89);
		panel.add(spConfDesc);
		txtAreaConfDesc.setLineWrap(true);
		((AbstractDocument)txtAreaConfDesc.getDocument()).setDocumentFilter(new DocumentSizeFilter(255));
		
		JLabel lblWasSpeaker = new JLabel("Was Speaker?");
		lblWasSpeaker.setBounds(18, 181, 97, 14);
		panel.add(lblWasSpeaker);
		
		comboBoxSpeaker = new JComboBox(new String[] {"Yes", "No"});
		comboBoxSpeaker.setBounds(198, 178, 77, 20);
		panel.add(comboBoxSpeaker);
		
		JLabel lblDateOfConference = new JLabel("Date of conference");
		lblDateOfConference.setBounds(18, 250, 131, 24);
		panel.add(lblDateOfConference);
		
		DatePickerSettings conferenceStartDateSettings = new DatePickerSettings();
		conferenceStartDateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
		conferenceStartDateSettings.setAllowEmptyDates(false);
		conferenceStartDateSettings.setAllowKeyboardEditing(false);
		
		conferenceStartDate = new DatePicker(conferenceStartDateSettings);
		conferenceStartDate.setBounds(159, 251, 160, 24);
		conferenceStartDate.getComponentToggleCalendarButton().setBounds(136, 0, 24, 24);
		conferenceStartDate.getComponentDateTextField().setBounds(0, 0, 136, 24);
        conferenceStartDate.setDate(LocalDate.now());
        JButton conferenceStartDatePickerButton = conferenceStartDate.getComponentToggleCalendarButton();
        conferenceStartDatePickerButton.setText("");
        conferenceStartDatePickerButton.setIcon(dateButtonIcon);
		conferenceStartDate.setLayout(null);
		conferenceStartDateSettings.setDateRangeLimits(null, LocalDate.now());
		panel.add(conferenceStartDate);
		
		DatePickerSettings conferenceEndDateSettings = new DatePickerSettings();
		conferenceEndDateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
		conferenceEndDateSettings.setAllowEmptyDates(false);
		conferenceEndDateSettings.setAllowKeyboardEditing(false);
		
		conferenceEndDate = new DatePicker(conferenceEndDateSettings);
		conferenceEndDate.setBounds(159, 311, 160, 24);
		conferenceEndDate.getComponentToggleCalendarButton().setBounds(136, 0, 24, 24);
		conferenceEndDate.getComponentDateTextField().setBounds(0, 0, 136, 24);
		conferenceEndDate.setDate(LocalDate.now());
        JButton conferenceEndDatePickerButton = conferenceEndDate.getComponentToggleCalendarButton();
        conferenceEndDatePickerButton.setText("");
        conferenceEndDatePickerButton.setIcon(dateButtonIcon);
        conferenceEndDate.setLayout(null);
		conferenceEndDateSettings.setDateRangeLimits(null, LocalDate.now());
		panel.add(conferenceEndDate);
		
		conferenceStartDate.addDateChangeListener(new DateChangeListener() {
			
			@Override
			public void dateChanged(DateChangeEvent arg0) {
				// TODO Auto-generated method stub
				if(conferenceStartDate.getDate().isAfter(conferenceEndDate.getDate())) {
					conferenceEndDate.setDate(conferenceStartDate.getDate());
				}			
			}
		});
		
		conferenceEndDate.addDateChangeListener(new DateChangeListener() {
			
			@Override
			public void dateChanged(DateChangeEvent arg0) {
				// TODO Auto-generated method stub
				if(conferenceStartDate.getDate().isAfter(conferenceEndDate.getDate())) {
					conferenceStartDate.setDate(conferenceEndDate.getDate());
				}			
			}
		});
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(216, 286, 46, 14);
		panel.add(lblTo);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String confTitle = txtConfTitle.getText();
				String confDesc = txtAreaConfDesc.getText();
				boolean isSpeaker = comboBoxSpeaker.getSelectedItem().equals("Yes");
				String ac = (String)comboBoxAC.getSelectedItem();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				String startDate = formatter.format(conferenceStartDate.getDate());
				String endDate = formatter.format(conferenceEndDate.getDate());
				
				if(confTitle.trim().isEmpty() || confDesc.trim().isEmpty()) {
					JOptionPane.showMessageDialog(Appraisal.this, "Fill all the required fields", "Fill empty fields", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Conference conf = new Conference(confTitle, confDesc, isSpeaker, ac, startDate, endDate);
				
				if(editingMode) {
					controller.getConferences().remove(editIndex);
					controller.onAddConference(conf, editIndex);
					editingMode = false;
					editIndex = -1;
				} else {
					controller.onAddConference(conf);
				}
								
				panel.removeAll();
				showConferenceTable();
				validate();
				repaint();
			}
		});
		btnSubmit.setBounds(83, 381, 89, 23);
		panel.add(btnSubmit);
				
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				showConferenceTable();
				validate();
				repaint();
			}
		});
		btnCancel.setBounds(283, 381, 89, 23);
		panel.add(btnCancel);
	}
	
	private void showConferenceTable() {
		String[] columnNames = {"No.", "Title", "Description", "Date of conference", "Was speaker", "Attended/Conducted" };
		ArrayList<Conference> conferences = controller.getConferences();
		
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String[][] data = new String[conferences.size()][6];
		for(int i = 0; i < conferences.size(); i++) {
			Conference conference = conferences.get(i);
			data[i][0] = Integer.toString(i+1);
			data[i][1] = conference.getTopic();
			data[i][2] = conference.getDescription();
			String startDate = formatter.format(LocalDate.parse(conference.getStartDate(), parser));
			String endDate = formatter.format(LocalDate.parse(conference.getEndDate(), parser));
			data[i][3] = startDate + " - " + endDate;
			data[i][4] = conference.isSpeaker()?"Yes":"No";
			data[i][5] = conference.getAttendedOrConducted();
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		conferencesTable = new JTable(data, columnNames);
		conferencesTable.setModel(model);
		conferencesTable.setBounds(10, 11, 418, 300);
		conferencesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		conferencesTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		conferencesTable.getColumnModel().getColumn(1).setPreferredWidth(75);
		conferencesTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		conferencesTable.getColumnModel().getColumn(3).setPreferredWidth(180);
		conferencesTable.getColumnModel().getColumn(4).setPreferredWidth(90);
		conferencesTable.getColumnModel().getColumn(5).setPreferredWidth(110);
		JScrollPane sp=new JScrollPane(conferencesTable);
		sp.setBounds(10, 11, 418, 300);
		
		conferencesTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		            Conference conference = controller.getConferences().get(table.getSelectedRow());
		            panel.removeAll();
		            showConferenceForm();
		            validate();
		            repaint();
		            editingMode = true;
		            editIndex = table.getSelectedRow();
		            txtConfTitle.setText(conference.getTopic());
		            txtAreaConfDesc.setText(conference.getDescription());
		            comboBoxSpeaker.setSelectedItem(conference.isSpeaker()?"Yes":"No");
		            comboBoxAC.setSelectedItem(conference.getAttendedOrConducted());
		            conferenceStartDate.setDate(LocalDate.parse(conference.getStartDate(), parser));
		            conferenceEndDate.setDate(LocalDate.parse(conference.getEndDate(), parser));
		        }
		    }
		});
		
		panel.add(sp);
		
		JButton btnAddConferenceEntry = new JButton("Add Conference Entry");
		
		btnAddConferenceEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				showConferenceForm();
				validate();
				repaint();
			}
		});
		
		btnAddConferenceEntry.setBounds(129, 339, 183, 23);
		panel.add(btnAddConferenceEntry);
	}
	
	private void showPaperForm() {
		JLabel lblName = new JLabel("Title*");
		lblName.setBounds(10, 0, 46, 20);
		panel.add(lblName);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(107, 0, 308, 20);
		panel.add(txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description*");
		lblDescription.setBounds(10, 71, 77, 14);
		panel.add(lblDescription);
		
		txtAreaDesc = new JTextArea();
		JScrollPane spDesc = new JScrollPane(txtAreaDesc);
		spDesc.setBounds(107, 66, 308, 72);
		panel.add(spDesc);
		txtAreaDesc.setLineWrap(true);
		((AbstractDocument)txtAreaDesc.getDocument()).setDocumentFilter(new DocumentSizeFilter(255));

		
		JLabel lblDateOfPublication = new JLabel("Date of publication*");
		lblDateOfPublication.setBounds(10, 173, 116, 14);
		panel.add(lblDateOfPublication);
		
		DatePickerSettings publicationDateSettings = new DatePickerSettings();
        publicationDateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
        publicationDateSettings.setAllowEmptyDates(false);
        publicationDateSettings.setAllowKeyboardEditing(false);
        
		publicationDatePicker = new DatePicker(publicationDateSettings);
		publicationDatePicker.setBounds(186, 169, 160, 24);
		panel.add(publicationDatePicker);
		publicationDatePicker.getComponentToggleCalendarButton().setBounds(136, 0, 24, 24);
		publicationDatePicker.getComponentDateTextField().setBounds(0, 0, 136, 24);
		publicationDatePicker.setDate(LocalDate.now());
		JButton publicationDatePickerButton = publicationDatePicker.getComponentToggleCalendarButton();
		publicationDatePickerButton.setText("");
		publicationDatePickerButton.setIcon(dateButtonIcon);
		publicationDatePicker.setLayout(null);
		publicationDateSettings.setDateRangeLimits(null, LocalDate.now());
		
		JLabel lblAreYouThe = new JLabel("Are you the first author?");
		lblAreYouThe.setBounds(10, 204, 163, 14);
		panel.add(lblAreYouThe);
		
		String choices[] = new String[] {"Yes", "No"};
		
		comboBoxFirstAuthor = new JComboBox(choices);
		comboBoxFirstAuthor.setBounds(186, 201, 77, 20);
		panel.add(comboBoxFirstAuthor);
		
		JLabel lblPublishedInWhich = new JLabel("Published in which journals?");
		lblPublishedInWhich.setBounds(10, 246, 163, 14);
		panel.add(lblPublishedInWhich);
		
		txtAreaJournals = new JTextArea();
		JScrollPane spJournals = new JScrollPane(txtAreaJournals);
		spJournals.setBounds(183, 241, 199, 42);
		panel.add(spJournals);
		txtAreaJournals.setLineWrap(true);
		((AbstractDocument)txtAreaJournals.getDocument()).setDocumentFilter(new DocumentSizeFilter(255));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String paperTitle = txtTitle.getText();
				String paperDesc = txtAreaDesc.getText();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				String date = formatter.format(publicationDatePicker.getDate());
				
				boolean firstAuthor = comboBoxFirstAuthor.getSelectedItem().equals("Yes");
				String journals = txtAreaJournals.getText();
				
				if(paperTitle.trim().isEmpty() || paperDesc.trim().isEmpty()) {
					JOptionPane.showMessageDialog(Appraisal.this, "Fill all the required fields", "Fill empty fields", JOptionPane.WARNING_MESSAGE);
					return;
				}
				ResearchPaper paper = new ResearchPaper(paperTitle, paperDesc, date, firstAuthor, journals);
				
				if(editingMode) {
					controller.getPapers().remove(editIndex);
					controller.onAddPaper(paper, editIndex);
					editIndex = -1;
					editingMode = false;
				} else {
					controller.onAddPaper(paper);
				}
								
				panel.removeAll();
				showPaperTable();
				validate();
				repaint();
			}
		});
		btnSubmit.setBounds(83, 330, 89, 23);
		panel.add(btnSubmit);
				
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				showPaperTable();
				validate();
				repaint();
			}
		});
		btnCancel.setBounds(283, 330, 89, 23);
		panel.add(btnCancel);
	}
	
	private void showPaperTable() {
		String[] columnNames = {"No.", "Title", "Description", "Date of publication", "First Author", "Journals" };   
		
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		ArrayList<ResearchPaper> papers = controller.getPapers();
		String[][] data = new String[papers.size()][6];
		for(int i = 0; i < papers.size(); i++) {
			ResearchPaper paper = papers.get(i);
			data[i][0] = Integer.toString(i+1);
			data[i][1] = paper.getTitle();
			data[i][2] = paper.getDescription();
			data[i][3] = formatter.format(LocalDate.parse(paper.getDate(), parser));
			data[i][4] = paper.isFirstAuthor()?"Yes":"No";
			data[i][5] = paper.getJournals();
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		papersTable = new JTable(data, columnNames);
		papersTable.setBounds(10, 11, 418, 300);
		papersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		papersTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		papersTable.getColumnModel().getColumn(1).setPreferredWidth(75);
		papersTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		papersTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		papersTable.getColumnModel().getColumn(5).setPreferredWidth(200);
		JScrollPane sp=new JScrollPane(papersTable);
		sp.setBounds(10, 11, 418, 300);
		papersTable.setModel(model);
		
		papersTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	ResearchPaper paper = controller.getPapers().get(table.getSelectedRow());
		            panel.removeAll();
		            showPaperForm();
		            validate();
		            repaint();
		            editingMode = true;
		            editIndex = table.getSelectedRow();
		            txtTitle.setText(paper.getTitle());
		            txtAreaDesc.setText(paper.getDescription());
		            comboBoxFirstAuthor.setSelectedItem(paper.isFirstAuthor()?"Yes":"No");
		            publicationDatePicker.setDate(LocalDate.parse(paper.getDate(), parser));
		            txtAreaJournals.setText(paper.getJournals());
		        }
		    }
		});
		
		panel.add(sp);
		
		JButton btnAddPaper = new JButton("Add Research Paper");
		btnAddPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				showPaperForm();
				validate();
				repaint();
			}
		});
		btnAddPaper.setBounds(133, 339, 179, 23);
		panel.add(btnAddPaper);
	}
	
	public void setAppraisalData(boolean dataPresent, String name, String dept, String designation) {
		if(dataPresent) {
			txtEmployeeName.setText(name);
			txtEmployeeDept.setText(dept);
			txtEmployeeDesignation.setText(designation);
			txtEmployeeId.setText(employeeId);
			setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this,
			        "No data found for employee ID: " + employeeId,
			        "No data found",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void showSQLConnectionError() {
		JOptionPane.showMessageDialog(this,
		        "Connection to MySQL database failed",
		        "Connection failed",
		        JOptionPane.ERROR_MESSAGE);
	}
	
	public void showSuccessMessage() {
		JOptionPane.showMessageDialog(this,
		        "Appraisal Form Submitted Successfully.",
		        "Success",
		        JOptionPane.INFORMATION_MESSAGE);
		setVisible(false);
	}
}
package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;

import BackEnd.BulletListItem;
import BackEnd.CVEditor;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.awt.event.ActionEvent;

public class experienceFrame extends JFrame {

	private JPanel contentPane;
	private JTextField compNameField;
	private JTextField jobField;
	private JTextField dateField;
	private JTextField PoRField;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final Date invalidDate = new Date(0);
	private Date date;
	BulletListItem crs = new BulletListItem();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					experienceFrame frame = new experienceFrame();
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
	private static final Date fromString( String spec ) {
	    try {
	        return dateFormat.parse( spec );
	    } catch( ParseException dfe ) {
	        return invalidDate;
	    }
	}

	public experienceFrame() {
		CVEditor.setItem(crs);
		setTitle("Professional Experience");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 717, 649);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(12, 12, 683, 637);
		contentPane.add(panel);
		
		compNameField = new JTextField();
		compNameField.setColumns(10);
		compNameField.setBounds(356, 66, 276, 20);
		panel.add(compNameField);
		
		jobField = new JTextField();
		jobField.setColumns(10);
		jobField.setBounds(356, 155, 276, 20);
		panel.add(jobField);
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(356, 234, 276, 20);
		panel.add(dateField);
		
		JLabel compNamePanel = new JLabel("Company Name:");
		compNamePanel.setForeground(new Color(0, 128, 128));
		compNamePanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		compNamePanel.setBounds(52, 63, 157, 24);
		panel.add(compNamePanel);
		
		JLabel jobPanel = new JLabel("Job Title:");
		jobPanel.setForeground(new Color(0, 128, 128));
		jobPanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		jobPanel.setBounds(52, 155, 71, 19);
		panel.add(jobPanel);
		
		JLabel datePanel = new JLabel("Date:");
		datePanel.setForeground(new Color(0, 128, 128));
		datePanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		datePanel.setBounds(52, 236, 46, 14);
		panel.add(datePanel);
		
		JLabel addCompPanel = new JLabel("Do you want to add a new company?");
		addCompPanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addCompPanel.setBounds(52, 465, 291, 39);
		panel.add(addCompPanel);
		
		JButton yesButton = new JButton("YES");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVEditor.getItem().setContent1(compNameField.getText());
				CVEditor.getItem().setContent2(jobField.getText());
				CVEditor.getItem().setDate(fromString(dateField.getText()));
				CVEditor.getItem().setContent3(PoRField.getText());
				date = fromString(dateField.getText());
				
				if(CVEditor.getCV().getSections().get(2).getBulletList().isEmpty()){ //get(2) -> 3rd Section -> Professional Experience
					CVEditor.getCV().getSections().get(2).addBulletListItem(CVEditor.getItem());
				}
				else{
					if(CVEditor.getCV().getSections().get(2).getBulletList().get(CVEditor.getCV().getSections().get(2).getBulletList().size()-1).getDate().after(date)){
						CVEditor.getCV().getSections().get(2).addBulletListItem(CVEditor.getItem());
					}else{
						JOptionPane.showMessageDialog(null, "Date of current Company is more recent from the last one's. The Companies list will be sorted!");
						CVEditor.getCV().getSections().get(2).addBulletListItem(CVEditor.getItem());
						ArrayList<BulletListItem> daterow = new ArrayList();
			    		int xr=CVEditor.getCV().getSections().get(2).getBulletList().size();
		    			Date [] t =new Date[xr];
		    			
		    			for(int i=0;i<xr;i++){
		    				t[i]=CVEditor.getCV().getSections().get(2).getBulletList().get(i).getDate();
		    			}
		    			Arrays.sort(t);
		    			Collections.reverse(Arrays.asList(t));
		    			
		    			for(int j=0;j<CVEditor.getCV().getSections().get(2).getBulletList().size();j++){
			    			Date reportDate = t[j];
			    			for(int i=0;i<CVEditor.getCV().getSections().get(2).getBulletList().size();i++){
			    				if(CVEditor.getCV().getSections().get(2).getBulletList().get(i).getDate().equals(reportDate)){
								   	daterow.add(CVEditor.getCV().getSections().get(2).getBulletList().get(i));
			    				}
			    			}
		    			}
		    			CVEditor.getCV().getSections().get(2).setBulletList(daterow);
					}
				}
				dispose();
				experienceFrame frame = new experienceFrame();
				frame.setVisible(true);
			}
		});
		yesButton.setForeground(new Color(240, 248, 255));
		yesButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		yesButton.setBackground(new Color(0, 128, 0));
		yesButton.setBounds(356, 468, 103, 35);
		panel.add(yesButton);
		
		JButton noButton = new JButton("NO");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVEditor.getItem().setContent1(compNameField.getText());
				CVEditor.getItem().setContent2(jobField.getText());
				CVEditor.getItem().setDate(fromString(dateField.getText()));
				CVEditor.getItem().setContent3(PoRField.getText());
				date = fromString(dateField.getText());
				
				if(CVEditor.getCV().getSections().get(2).getBulletList().isEmpty()){ //get(2) -> 3rd Section -> Professional Experience
					CVEditor.getCV().getSections().get(2).addBulletListItem(CVEditor.getItem());
				}
				else{
					if(CVEditor.getCV().getSections().get(2).getBulletList().get(CVEditor.getCV().getSections().get(2).getBulletList().size()-1).getDate().after(date)){
						CVEditor.getCV().getSections().get(2).addBulletListItem(CVEditor.getItem());
					}else{
						JOptionPane.showMessageDialog(null, "Date of current Company is more recent from the last one's. The Companies list will be sorted!");
						CVEditor.getCV().getSections().get(2).addBulletListItem(CVEditor.getItem());
						ArrayList<BulletListItem> daterow = new ArrayList();
			    		int xr=CVEditor.getCV().getSections().get(2).getBulletList().size();
		    			Date [] t =new Date[xr];
		    			
		    			for(int i=0;i<xr;i++){
		    				t[i]=CVEditor.getCV().getSections().get(2).getBulletList().get(i).getDate();
		    			}
		    			Arrays.sort(t);
		    			Collections.reverse(Arrays.asList(t));
		    			
		    			for(int j=0;j<CVEditor.getCV().getSections().get(2).getBulletList().size();j++){
			    			Date reportDate = t[j];
			    			for(int i=0;i<CVEditor.getCV().getSections().get(2).getBulletList().size();i++){
			    				if(CVEditor.getCV().getSections().get(2).getBulletList().get(i).getDate().equals(reportDate)){
								   	daterow.add(CVEditor.getCV().getSections().get(2).getBulletList().get(i));
			    				}
			    			}
		    			}
		    			CVEditor.getCV().getSections().get(2).setBulletList(daterow);
					}
				}
				dispose();
			}
		});
		noButton.setForeground(new Color(240, 248, 255));
		noButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		noButton.setBackground(Color.RED);
		noButton.setBounds(495, 467, 103, 35);
		panel.add(noButton);
		
		JLabel PoRPanel = new JLabel("Paragraph of Responsibilities:");
		PoRPanel.setForeground(new Color(0, 128, 128));
		PoRPanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		PoRPanel.setBounds(52, 313, 251, 19);
		panel.add(PoRPanel);
		
		PoRField = new JTextField();
		PoRField.setColumns(10);
		PoRField.setBounds(356, 313, 276, 20);
		panel.add(PoRField);
		
		JLabel achievPanel = new JLabel("Add Achievements:");
		achievPanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		achievPanel.setBounds(52, 427, 175, 19);
		panel.add(achievPanel);
		
		JButton ButtonAdd = new JButton("ADD");
		ButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAchievementsFrame frame = new addAchievementsFrame();
				frame.setVisible(true);				
			}
		});
		ButtonAdd.setForeground(new Color(240, 248, 255));
		ButtonAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		ButtonAdd.setBackground(new Color(128, 0, 0));
		ButtonAdd.setBounds(354, 420, 103, 35);
		panel.add(ButtonAdd);
		
		JButton button = new JButton("Select");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CVEditor.getCvForUpdate()==1){
					if(CVEditor.getCV().getSections().get(2).getBulletList().isEmpty()){
						JOptionPane.showMessageDialog(null, "There are no companies to update!");
					}
					else{
						String s;
						for(int i=1; i<=CVEditor.getCV().getSections().get(2).getBulletList().size(); i++){
					    	s=CVEditor.getCV().getSections().get(2).getBulletList().get(i-1).getContent1();
					    	System.out.println(s);
					    } 
						updateCompanies frame = new updateCompanies();
						frame.setVisible(true);
					}
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "You are currently creating a new CV. You can't update existing CVs now!");
				}
			}
		});
		button.setBounds(356, 517, 105, 25);
		panel.add(button);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(Color.BLUE);
		
		JLabel lblClickHereTo = new JLabel("Click here to update existing Companies:");
		lblClickHereTo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClickHereTo.setBounds(52, 508, 291, 39);
		panel.add(lblClickHereTo);
		
		JButton button_1 = new JButton("BACK");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Dialog", Font.BOLD, 11));
		button_1.setBackground(new Color(139, 0, 0));
		button_1.setBounds(495, 543, 103, 33);
		panel.add(button_1);
		
		JLabel label = new JLabel("(*)These fields must be filled");
		label.setForeground(Color.RED);
		label.setBounds(12, 580, 235, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("(*)");
		label_1.setForeground(Color.RED);
		label_1.setBounds(637, 233, 46, 20);
		panel.add(label_1);
	}
}

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
import java.awt.SystemColor;

public class careerFrame extends JFrame {

	private JPanel contentPaneCareer;
	private JTextField compField;
	private JTextField jobField;
	private JTextField dateField;
	private String content1, content2;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final Date invalidDate = new Date(0);
	private Date date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					careerFrame frame = new careerFrame();
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
	public careerFrame() {
		setTitle("Career Summary");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 540);
		contentPaneCareer = new JPanel();
		contentPaneCareer.setBackground(new Color(230, 230, 250));
		contentPaneCareer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneCareer);
		contentPaneCareer.setLayout(null);
		
		JPanel carrerPanel = new JPanel();
		carrerPanel.setLayout(null);
		carrerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		carrerPanel.setBackground(new Color(230, 230, 250));
		carrerPanel.setBounds(12, 0, 671, 510);
		contentPaneCareer.add(carrerPanel);
		
		compField = new JTextField();
		compField.setColumns(10);
		compField.setBounds(343, 66, 276, 20);
		carrerPanel.add(compField);
		
		jobField = new JTextField();
		jobField.setColumns(10);
		jobField.setBounds(343, 155, 276, 20);
		carrerPanel.add(jobField);
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(343, 234, 276, 20);
		carrerPanel.add(dateField);
		
		JLabel lblCompanyName = new JLabel("Company Name:");
		lblCompanyName.setForeground(SystemColor.desktop);
		lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCompanyName.setBounds(52, 63, 200, 24);
		carrerPanel.add(lblCompanyName);
		
		JLabel lblJobTitle = new JLabel("Job Title:");
		lblJobTitle.setForeground(SystemColor.desktop);
		lblJobTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblJobTitle.setBounds(52, 155, 71, 19);
		carrerPanel.add(lblJobTitle);
		
		JLabel lbDate = new JLabel("Date:");
		lbDate.setForeground(SystemColor.desktop);
		lbDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbDate.setBounds(52, 236, 46, 14);
		carrerPanel.add(lbDate);
		
		JLabel lblDoYouWant = new JLabel("Do you want to add a new company?");
		lblDoYouWant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDoYouWant.setBounds(52, 352, 325, 39);
		carrerPanel.add(lblDoYouWant);
		
		JButton button = new JButton("YES");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				content1 = compField.getText();
				content2 = jobField.getText();
				date = fromString(dateField.getText());
				if(CVEditor.getCV().getSections().get(2).getBulletList().isEmpty()){ //get(2) -> 3rd Section -> Career Summary
					BulletListItem crs = new BulletListItem(content1,content2,date);
					CVEditor.getCV().getSections().get(2).addBulletListItem(crs);
				}
				else{
					if(CVEditor.getCV().getSections().get(2).getBulletList().get(CVEditor.getCV().getSections().get(2).getBulletList().size()-1).getDate().after(date)){
						BulletListItem crs = new BulletListItem(content1,content2,date);
						CVEditor.getCV().getSections().get(2).addBulletListItem(crs);
					}else{
						JOptionPane.showMessageDialog(null, "Date of current Company is more recent from the last one's. The Companies list will be sorted!");
						BulletListItem crs = new BulletListItem(content1,content2,date);
						CVEditor.getCV().getSections().get(2).addBulletListItem(crs);
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
				careerFrame frame = new careerFrame();
				frame.setVisible(true);
			}
		});
		button.setForeground(new Color(240, 248, 255));
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(new Color(0, 128, 0));
		button.setBounds(401, 354, 103, 35);
		carrerPanel.add(button);
		
		JButton button_1 = new JButton("NO");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content1 = compField.getText();
				content2 = jobField.getText();
				date = fromString(dateField.getText());
				if(CVEditor.getCV().getSections().get(2).getBulletList().isEmpty()){ //get(2) -> 3rd Section -> Career Summary
					BulletListItem crs = new BulletListItem(content1,content2,date);
					CVEditor.getCV().getSections().get(2).addBulletListItem(crs);
				}
				else{
					if(CVEditor.getCV().getSections().get(2).getBulletList().get(CVEditor.getCV().getSections().get(2).getBulletList().size()-1).getDate().after(date)){
						BulletListItem crs = new BulletListItem(content1,content2,date);
						CVEditor.getCV().getSections().get(2).addBulletListItem(crs);
					}else{
						JOptionPane.showMessageDialog(null, "Date of current Company is more recent from the last one's. The Companies list will be sorted!");
						BulletListItem crs = new BulletListItem(content1,content2,date);
						CVEditor.getCV().getSections().get(2).addBulletListItem(crs);
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
		button_1.setForeground(new Color(240, 248, 255));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBackground(Color.RED);
		button_1.setBounds(516, 355, 103, 35);
		carrerPanel.add(button_1);
		
		JButton button_2 = new JButton("Select");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_2.setBackground(Color.BLUE);
		button_2.setBounds(401, 410, 105, 25);
		carrerPanel.add(button_2);
		
		JLabel lblClickHereTo = new JLabel("Click here to update existing Companies:");
		lblClickHereTo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClickHereTo.setBounds(52, 402, 325, 39);
		carrerPanel.add(lblClickHereTo);
		
		JLabel label = new JLabel("(*)These fields must be filled");
		label.setForeground(Color.RED);
		label.setBounds(12, 483, 235, 15);
		carrerPanel.add(label);
		
		JLabel label_1 = new JLabel("(*)");
		label_1.setForeground(Color.RED);
		label_1.setBounds(629, 236, 30, 15);
		carrerPanel.add(label_1);
		
		JButton button_3 = new JButton("BACK");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Dialog", Font.BOLD, 11));
		button_3.setBackground(new Color(139, 0, 0));
		button_3.setBounds(516, 444, 103, 33);
		carrerPanel.add(button_3);
	}
}

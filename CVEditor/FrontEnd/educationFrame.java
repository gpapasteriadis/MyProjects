package FrontEnd;

import java.awt.BorderLayout; 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.JDesktopPane;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import BackEnd.BulletListItem;
import BackEnd.CVEditor;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class educationFrame extends JFrame {

	private JPanel contentPane;
	private JTextField qualField;
	private JTextField establField;
	private JTextField locationField;
	private JTextField dateField;
	private String content1, content2, content3;
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
					educationFrame frame = new educationFrame();
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
	public static final Date fromString( String spec ) {
        try {
            return dateFormat.parse( spec );
        } catch( ParseException dfe ) {
            return invalidDate;
        }
    }
	public educationFrame() {
		setBackground(new Color(255, 255, 255));
		setTitle("Education & Training");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 711, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		qualField = new JTextField();
		qualField.setBounds(336, 31, 276, 20);
		contentPane.add(qualField);
		qualField.setColumns(10);
		
		establField = new JTextField();
		establField.setColumns(10);
		establField.setBounds(336, 112, 276, 20);
		contentPane.add(establField);
		
		
		locationField = new JTextField();
		locationField.setColumns(10);
		locationField.setBounds(336, 193, 276, 20);
		contentPane.add(locationField);
		
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(336, 264, 276, 20);
		contentPane.add(dateField);
		
		
		JLabel lblNewLabel = new JLabel("Qualification");
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(39, 21, 107, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblEstablishment = new JLabel("Establishment:");
		lblEstablishment.setForeground(new Color(0, 128, 128));
		lblEstablishment.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstablishment.setBounds(39, 109, 123, 24);
		contentPane.add(lblEstablishment);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setForeground(new Color(0, 128, 128));
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLocation.setBounds(39, 193, 107, 19);
		contentPane.add(lblLocation);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(new Color(0, 128, 128));
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setBounds(39, 265, 46, 17);
		contentPane.add(lblDate);
		
		JLabel lblNewLabel_1 = new JLabel("Do you want to add a new qualificaton?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(39, 353, 311, 39);
		contentPane.add(lblNewLabel_1);
		
		JButton btnYes = new JButton("YES");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				content1 = qualField.getText();
				content2 = establField.getText();
				content3 = locationField.getText();
				date = fromString(dateField.getText());
				if(CVEditor.getCV().getSections().get(3).getBulletList().isEmpty()){ //get(3) -> 4th Section -> Education and Training
					BulletListItem crs = new BulletListItem(content1,content2,content3,date);
					CVEditor.getCV().getSections().get(3).addBulletListItem(crs);
				}
				else{
					if(CVEditor.getCV().getSections().get(3).getBulletList().get(CVEditor.getCV().getSections().get(3).getBulletList().size()-1).getDate().after(date)){
						BulletListItem crs = new BulletListItem(content1,content2,content3,date);
						CVEditor.getCV().getSections().get(3).addBulletListItem(crs);
					}else{
						JOptionPane.showMessageDialog(null, "Date of current Qualification is more recent from the last one's. The Qualifications list will be sorted!");
						BulletListItem crs = new BulletListItem(content1,content2,content3,date);
						CVEditor.getCV().getSections().get(3).addBulletListItem(crs);
						ArrayList<BulletListItem> daterow = new ArrayList();
			    		int xr=CVEditor.getCV().getSections().get(3).getBulletList().size();
		    			Date [] t =new Date[xr];
		    			
		    			for(int i=0;i<xr;i++){
		    				t[i]=CVEditor.getCV().getSections().get(3).getBulletList().get(i).getDate();
		    			}
		    			Arrays.sort(t);
		    			Collections.reverse(Arrays.asList(t));
		    			
		    			for(int j=0;j<CVEditor.getCV().getSections().get(3).getBulletList().size();j++){
			    			Date reportDate = t[j];
			    			for(int i=0;i<CVEditor.getCV().getSections().get(3).getBulletList().size();i++){
			    				if(CVEditor.getCV().getSections().get(3).getBulletList().get(i).getDate().equals(reportDate)){
								   	daterow.add(CVEditor.getCV().getSections().get(3).getBulletList().get(i));
			    				}
			    			}
		    			}
		    			CVEditor.getCV().getSections().get(3).setBulletList(daterow);
					}
				}
				dispose();
				educationFrame frame = new educationFrame();
				frame.setVisible(true);
			}
		});
		btnYes.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnYes.setForeground(new Color(240, 248, 255));
		btnYes.setBackground(new Color(0, 128, 0));
		btnYes.setBounds(394, 355, 103, 35);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				content1 = qualField.getText();
				content2 = establField.getText();
				content3 = locationField.getText();
				date = fromString(dateField.getText());
				if(CVEditor.getCV().getSections().get(3).getBulletList().isEmpty()){ //get(3) -> 4th Section -> Education and Training
					BulletListItem crs = new BulletListItem(content1,content2,content3,date);
					CVEditor.getCV().getSections().get(3).addBulletListItem(crs);
				}
				else{
					if(CVEditor.getCV().getSections().get(3).getBulletList().get(CVEditor.getCV().getSections().get(3).getBulletList().size()-1).getDate().after(date)){
						BulletListItem crs = new BulletListItem(content1,content2,content3,date);
						CVEditor.getCV().getSections().get(3).addBulletListItem(crs);
					}else{
						JOptionPane.showMessageDialog(null, "Date of current Qualification is more recent from the last one's. The Qualifications list will be sorted!");
						BulletListItem crs = new BulletListItem(content1,content2,content3,date);
						CVEditor.getCV().getSections().get(3).addBulletListItem(crs);
						ArrayList<BulletListItem> daterow = new ArrayList();
			    		int xr=CVEditor.getCV().getSections().get(3).getBulletList().size();
		    			Date [] t =new Date[xr];
		    			
		    			for(int i=0;i<xr;i++){
		    				t[i]=CVEditor.getCV().getSections().get(3).getBulletList().get(i).getDate();
		    			}
		    			Arrays.sort(t);
		    			Collections.reverse(Arrays.asList(t));
		    			
		    			for(int j=0;j<CVEditor.getCV().getSections().get(3).getBulletList().size();j++){
			    			Date reportDate = t[j];
			    			for(int i=0;i<CVEditor.getCV().getSections().get(3).getBulletList().size();i++){
			    				if(CVEditor.getCV().getSections().get(3).getBulletList().get(i).getDate().equals(reportDate)){
								   	daterow.add(CVEditor.getCV().getSections().get(3).getBulletList().get(i));
			    				}
			    			}
		    			}
		    			CVEditor.getCV().getSections().get(3).setBulletList(daterow);
					}
				}
				dispose();
			}
		});
		btnNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNo.setForeground(new Color(240, 248, 255));
		btnNo.setBackground(Color.RED);
		btnNo.setBounds(509, 355, 103, 35);
		contentPane.add(btnNo);
		
		JButton button = new JButton("Select");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==1){
					if(CVEditor.getCV().getSections().get(3).getBulletList().isEmpty()){
						JOptionPane.showMessageDialog(null, "There are no qualifications to update!");
					}
					else{
						String s;
						for(int i=1; i<=CVEditor.getCV().getSections().get(3).getBulletList().size(); i++){
					    	s=CVEditor.getCV().getSections().get(3).getBulletList().get(i-1).getContent1();
					    	System.out.println(s);
					    } 
						updateQual frame = new updateQual();
						frame.setVisible(true);
					}
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "You are currently creating a new CV. You can't update existing CVs now!");
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(Color.BLUE);
		button.setBounds(392, 402, 105, 25);
		contentPane.add(button);
		
		JLabel lblToUpdate = new JLabel("Click here to update existing qualifications:");
		lblToUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblToUpdate.setBounds(39, 394, 352, 39);
		contentPane.add(lblToUpdate);
		
		JButton button_1 = new JButton("BACK");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Dialog", Font.BOLD, 11));
		button_1.setBackground(new Color(139, 0, 0));
		button_1.setBounds(509, 431, 103, 33);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("(*)These fields must be filled");
		label.setForeground(Color.RED);
		label.setBounds(12, 466, 235, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("(*)");
		label_1.setForeground(Color.RED);
		label_1.setBounds(617, 266, 39, 15);
		contentPane.add(label_1);
	}
}

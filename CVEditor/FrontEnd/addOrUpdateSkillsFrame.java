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

public class addOrUpdateSkillsFrame extends JFrame {

	private JPanel contentPane;
	private JTextField compNameField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addOrUpdateSkillsFrame frame = new addOrUpdateSkillsFrame();
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
	

	public addOrUpdateSkillsFrame() {
		BulletListItem skill = new BulletListItem("");
		CVEditor.setItem(skill);
		setTitle("SKILLS AND EXPERIENCE");
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
		panel.setBounds(12, 12, 683, 559);
		contentPane.add(panel);
		
		compNameField = new JTextField();
		compNameField.setColumns(10);
		compNameField.setBounds(356, 66, 276, 20);
		panel.add(compNameField);
		
		JLabel skillNamePanel = new JLabel("Skill Name:");
		skillNamePanel.setForeground(new Color(0, 128, 128));
		skillNamePanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		skillNamePanel.setBounds(52, 63, 157, 24);
		panel.add(skillNamePanel);
		
		JLabel addSkillPanel = new JLabel("Do you want to add a new Skill?");
		addSkillPanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addSkillPanel.setBounds(47, 241, 291, 39);
		panel.add(addSkillPanel);
		
		JButton yesButton = new JButton("YES");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVEditor.getItem().setContent1(compNameField.getText());
				CVEditor.getCV().getSections().get(1).getBulletList().add(CVEditor.getItem());
				dispose();
				addOrUpdateSkillsFrame frame = new addOrUpdateSkillsFrame();
				frame.setVisible(true);
			}
		});
		yesButton.setForeground(new Color(240, 248, 255));
		yesButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		yesButton.setBackground(new Color(0, 128, 0));
		yesButton.setBounds(401, 243, 103, 35);
		panel.add(yesButton);
		
		JButton noButton = new JButton("NO");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVEditor.getItem().setContent1(compNameField.getText());
				CVEditor.getCV().getSections().get(1).getBulletList().add(CVEditor.getItem());
				dispose();
			}
		});
		noButton.setForeground(new Color(240, 248, 255));
		noButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		noButton.setBackground(Color.RED);
		noButton.setBounds(516, 243, 103, 35);
		panel.add(noButton);
		
		JLabel descriptPanel = new JLabel("Add Descriptions:");
		descriptPanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		descriptPanel.setBounds(52, 167, 175, 19);
		panel.add(descriptPanel);
		
		JButton ButtonAdd = new JButton("ADD");
		ButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addDescriptionsFrame frame = new addDescriptionsFrame();
				frame.setVisible(true);				
			}
		});
		ButtonAdd.setForeground(new Color(240, 248, 255));
		ButtonAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		ButtonAdd.setBackground(new Color(128, 0, 0));
		ButtonAdd.setBounds(401, 151, 103, 35);
		panel.add(ButtonAdd);
		
		JButton button = new JButton("Select");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CVEditor.getCvForUpdate()==1){
					if(CVEditor.getCV().getSections().get(1).getBulletList().isEmpty()){
						JOptionPane.showMessageDialog(null, "There are no skills to update!");
					}
					else{
						String s;
						for(int i=1; i<=CVEditor.getCV().getSections().get(1).getBulletList().size(); i++){
					    	s=CVEditor.getCV().getSections().get(1).getBulletList().get(i-1).getContent1();
					    	System.out.println(s);
					    } 
						updateSkills frame = new updateSkills();
						frame.setVisible(true);
						dispose();
					}
				}else{
					JOptionPane.showMessageDialog(null, "You are currently creating a new CV. You can't update existing CVs now!");
				}
			}
		});
		button.setBounds(399, 310, 105, 25);
		panel.add(button);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(Color.BLUE);
		
		JLabel lblClickHereTo = new JLabel("Click here to update existing Skills:");
		lblClickHereTo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClickHereTo.setBounds(47, 302, 334, 39);
		panel.add(lblClickHereTo);
	}
}
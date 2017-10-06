package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;

import BackEnd.CVEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class updateChosenSkillFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateChosenSkillFrame frame = new updateChosenSkillFrame();
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
	public updateChosenSkillFrame() {
		setTitle("Update Skill Name");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(12, 36, 659, 418);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(309, 31, 276, 20);
		panel.add(textField);
		
		JLabel lblSkillName = new JLabel("Skill Name:");
		lblSkillName.setForeground(new Color(0, 128, 128));
		lblSkillName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSkillName.setBounds(52, 21, 144, 39);
		panel.add(lblSkillName);
		
		JLabel label_3 = new JLabel("Update:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(224, 171, 63, 39);
		panel.add(label_3);
		
		
		textField.setText(CVEditor.getItem().getContent1());
		
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVEditor.getItem().setContent1(textField.getText());
				CVEditor.getCV().getSections().get(1).getBulletList().set(CVEditor.getIndexItem()-1, CVEditor.getItem());
				dispose();
				updateSkills frame = new updateSkills();
				frame.setVisible(true);
			}
		});
		button.setForeground(new Color(240, 248, 255));
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(new Color(0, 128, 0));
		button.setBounds(347, 173, 103, 35);
		panel.add(button);
		
		JLabel lblUpdateAchivements = new JLabel("Update Descriptions:");
		lblUpdateAchivements.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUpdateAchivements.setBounds(130, 100, 172, 39);
		panel.add(lblUpdateAchivements);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==1){
					chooseDescriptionFrame frame = new chooseDescriptionFrame();
					frame.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "You are currently creating a new CV. You can't update existing CVs now!");
				}
			}
		});
		btnSelect.setForeground(new Color(240, 248, 255));
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSelect.setBackground(Color.BLUE);
		btnSelect.setBounds(347, 102, 103, 35);
		panel.add(btnSelect);
	}
}
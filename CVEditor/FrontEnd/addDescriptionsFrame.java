package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import BackEnd.CVEditor;
import BackEnd.Paragraph;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class addDescriptionsFrame extends JFrame {

	private JPanel contentPane;
	private JTextField achievementField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addDescriptionsFrame frame = new addDescriptionsFrame();
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
	public addDescriptionsFrame() {
		setTitle("Descriptions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(230, 230, 250));
		contentPane.add(panel, BorderLayout.CENTER);
		
		achievementField = new JTextField();
		achievementField.setColumns(10);
		achievementField.setBounds(209, 67, 276, 20);
		panel.add(achievementField);
		
		JLabel achievementPanel = new JLabel("Description:");
		achievementPanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		achievementPanel.setBounds(52, 63, 122, 24);
		panel.add(achievementPanel);
		
		JLabel addAchievementPanel = new JLabel("Do you want to add a new Description?");
		addAchievementPanel.setFont(new Font("Tahoma", Font.BOLD, 14));
		addAchievementPanel.setBounds(52, 168, 339, 39);
		panel.add(addAchievementPanel);
		
		JButton yesButton = new JButton("YES");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Paragraph p = new Paragraph(achievementField.getText());
				CVEditor.getItem().getBulletList().add(p);
				dispose();
				addDescriptionsFrame frame = new addDescriptionsFrame();
				frame.setVisible(true);				
			}
		});
		yesButton.setForeground(new Color(240, 248, 255));
		yesButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		yesButton.setBackground(new Color(0, 128, 0));
		yesButton.setBounds(409, 170, 103, 35);
		panel.add(yesButton);
		
		JButton noButton = new JButton("NO");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Paragraph p = new Paragraph(achievementField.getText());
				CVEditor.getItem().getBulletList().add(p);
				dispose();
			}
		});
		noButton.setForeground(new Color(240, 248, 255));
		noButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		noButton.setBackground(Color.RED);
		noButton.setBounds(555, 170, 103, 35);
		panel.add(noButton);
	}

}

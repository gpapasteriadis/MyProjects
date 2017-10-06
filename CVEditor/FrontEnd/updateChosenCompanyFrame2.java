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

public class updateChosenCompanyFrame2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private static final SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final Date invalidDate = new Date(0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateChosenCompanyFrame2 frame = new updateChosenCompanyFrame2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static final Date fromString( String spec ) {
        try {
            return dateFormat.parse( spec );
        } catch( ParseException dfe ) {
            return invalidDate;
        }
    }
	/**
	 * Create the frame.
	 */
	public updateChosenCompanyFrame2() {
		setTitle("Update Company");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(12, 0, 671, 457);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(309, 31, 276, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(309, 112, 276, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(309, 194, 276, 20);
		panel.add(textField_2);
		
		JLabel label = new JLabel("Company Name:");
		label.setForeground(new Color(0, 128, 128));
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(52, 21, 144, 39);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Job Title:");
		label_1.setForeground(new Color(0, 128, 128));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(52, 109, 91, 24);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Date:");
		label_2.setForeground(new Color(0, 128, 128));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(52, 195, 46, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Update:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(343, 368, 63, 39);
		panel.add(label_3);
		

			textField.setText(CVEditor.getItem().getContent1());
			textField_1.setText(CVEditor.getItem().getContent2());
			String x = dateFormat3.format(CVEditor.getItem().getDate());
			textField_2.setText(x);
			textField_3.setText(CVEditor.getItem().getContent3());
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVEditor.getItem().setContent1(textField.getText());
				CVEditor.getItem().setContent2(textField_1.getText());
				CVEditor.getItem().setDate(fromString(textField_2.getText()));
				CVEditor.getItem().setContent3(textField_3.getText());
				System.out.println(CVEditor.getCV().getSections().get(2).getBulletList().set(CVEditor.getIndexItem()-1,CVEditor.getItem()).getContent1()+"aaaa");
				CVEditor.getCV().getSections().get(2).getBulletList().set(CVEditor.getIndexItem()-1,CVEditor.getItem());
				dispose();
				updateCompanies frame = new updateCompanies();
				frame.setVisible(true);
			}
		});
		button.setForeground(new Color(240, 248, 255));
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(new Color(0, 128, 0));
		button.setBounds(433, 371, 103, 35);
		panel.add(button);
		
		JLabel label_4 = new JLabel("Paragraph of Responsibilities:");
		label_4.setForeground(new Color(0, 128, 128));
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(52, 259, 227, 19);
		panel.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(309, 259, 276, 34);
		panel.add(textField_3);
		
		JLabel lblUpdateAchivements = new JLabel("Update Achivements:");
		lblUpdateAchivements.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUpdateAchivements.setBounds(234, 304, 172, 39);
		panel.add(lblUpdateAchivements);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==1){
					chooseAchievementFrame frame = new chooseAchievementFrame();
					frame.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "You are currently creating a new CV. You can't update existing CVs now!");
				}
			}
		});
		btnSelect.setForeground(new Color(240, 248, 255));
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSelect.setBackground(Color.BLUE);
		btnSelect.setBounds(433, 304, 103, 35);
		panel.add(btnSelect);
		
		JLabel label_5 = new JLabel("(*)These fields must be filled");
		label_5.setForeground(Color.RED);
		label_5.setBounds(12, 420, 235, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("(*)");
		label_6.setForeground(Color.RED);
		label_6.setBounds(589, 196, 40, 15);
		panel.add(label_6);
	}
}

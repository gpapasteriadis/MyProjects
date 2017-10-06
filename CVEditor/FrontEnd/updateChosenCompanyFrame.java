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

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class updateChosenCompanyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
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
					updateChosenCompanyFrame frame = new updateChosenCompanyFrame();
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
	public updateChosenCompanyFrame() {
		setTitle("Update Company");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(12, 0, 659, 431);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(309, 31, 276, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(309, 112, 276, 20);
		panel.add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(309, 193, 276, 20);
		panel.add(textField_3);
		
		JLabel lblCompanyname = new JLabel("Company Name:");
		lblCompanyname.setForeground(new Color(0, 128, 128));
		lblCompanyname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCompanyname.setBounds(52, 21, 144, 39);
		panel.add(lblCompanyname);
		
		JLabel lblJobTitle = new JLabel("Job Title:");
		lblJobTitle.setForeground(new Color(0, 128, 128));
		lblJobTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblJobTitle.setBounds(52, 109, 91, 24);
		panel.add(lblJobTitle);
		
		JLabel label_3 = new JLabel("Date:");
		label_3.setForeground(new Color(0, 128, 128));
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(52, 195, 46, 14);
		panel.add(label_3);
		
		JLabel lblUpdate = new JLabel("Update:");
		lblUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUpdate.setBounds(349, 313, 63, 39);
		panel.add(lblUpdate);
		
		textField.setText(CVEditor.getItem().getContent1());
		textField_1.setText(CVEditor.getItem().getContent2());
		String x = dateFormat3.format(CVEditor.getItem().getDate());
		textField_3.setText(x);
		
		JButton btnSelect = new JButton("OK");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVEditor.getItem().setContent1(textField.getText());
				CVEditor.getItem().setContent2(textField_1.getText());
				CVEditor.getItem().setDate(fromString(textField_3.getText()));
				CVEditor.getCV().getSections().get(2).getBulletList().set(CVEditor.getIndexItem()-1,CVEditor.getItem());
				dispose();
				updateCompanies frame = new updateCompanies();
				frame.setVisible(true);
			}
		});
		btnSelect.setForeground(new Color(240, 248, 255));
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSelect.setBackground(new Color(0, 128, 0));
		btnSelect.setBounds(430, 315, 103, 35);
		panel.add(btnSelect);
		
		JLabel label = new JLabel("(*)These fields must be filled");
		label.setForeground(Color.RED);
		label.setBounds(27, 404, 235, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("(*)");
		label_1.setForeground(Color.RED);
		label_1.setBounds(592, 195, 27, 15);
		panel.add(label_1);
	}

}

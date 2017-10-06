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

public class updateChosenQualFrame extends JFrame {

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
					updateChosenQualFrame frame = new updateChosenQualFrame();
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
	public updateChosenQualFrame() {
		setTitle("Update Qualification");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(12, 0, 630, 442);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(293, 31, 276, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(293, 112, 276, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(293, 193, 276, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(293, 264, 276, 20);
		panel.add(textField_3);
		
		JLabel label = new JLabel("Qualification");
		label.setForeground(new Color(0, 128, 128));
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(52, 21, 130, 39);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Establishment:");
		label_1.setForeground(new Color(0, 128, 128));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(52, 109, 130, 24);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Location:");
		label_2.setForeground(new Color(0, 128, 128));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(52, 193, 92, 19);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Date:");
		label_3.setForeground(new Color(0, 128, 128));
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(52, 266, 46, 14);
		panel.add(label_3);
		
		JLabel lblUpdate = new JLabel("Update:");
		lblUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUpdate.setBounds(385, 355, 63, 39);
		panel.add(lblUpdate);
		
		textField.setText(CVEditor.getItem().getContent1());
		textField_1.setText(CVEditor.getItem().getContent2());
		textField_2.setText(CVEditor.getItem().getContent3());
		String x = dateFormat3.format(CVEditor.getItem().getDate());
		textField_3.setText(x);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVEditor.getItem().setContent1(textField.getText());
				CVEditor.getItem().setContent2(textField_1.getText());
				CVEditor.getItem().setContent3(textField_2.getText());
				CVEditor.getItem().setDate(fromString(textField_3.getText()));
				CVEditor.getCV().getSections().get(3).getBulletList().set(CVEditor.getIndexItem()-1,CVEditor.getItem()); //replace with updated item
				dispose();
				updateQual frame = new updateQual();
				frame.setVisible(true);
			}
		});
		btnOk.setForeground(new Color(240, 248, 255));
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOk.setBackground(new Color(0, 128, 0));
		btnOk.setBounds(466, 357, 103, 35);
		panel.add(btnOk);
		
		JLabel label_4 = new JLabel("(*)These fields must be filled");
		label_4.setForeground(Color.RED);
		label_4.setBounds(12, 415, 235, 15);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("(*)");
		label_5.setForeground(Color.RED);
		label_5.setBounds(572, 266, 46, 15);
		panel.add(label_5);
	}
}

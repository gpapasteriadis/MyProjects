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
import java.awt.event.ActionEvent;

public class updateChosenDescriptionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateChosenDescriptionFrame frame = new updateChosenDescriptionFrame();
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
	public updateChosenDescriptionFrame() {
		setTitle("Update Descriptions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(10, 11, 532, 248);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(209, 67, 276, 20);
		panel.add(textField);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescription.setBounds(52, 63, 122, 24);
		panel.add(lblDescription);
		
		JLabel label_2 = new JLabel("Update:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(209, 153, 63, 39);
		panel.add(label_2);
		
		textField.setText(CVEditor.getParagraph().getContents());
		
		JButton button_2 = new JButton("OK");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CVEditor.getParagraph().setContents(textField.getText());
				CVEditor.getItem().getBulletList().set(CVEditor.getIndexItem()-1, CVEditor.getParagraph());
				dispose();
				chooseDescriptionFrame frame = new chooseDescriptionFrame();
				frame.setVisible(true);
			}
		});
		button_2.setForeground(new Color(240, 248, 255));
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_2.setBackground(new Color(0, 128, 0));
		button_2.setBounds(306, 156, 103, 35);
		panel.add(button_2);
	}

}

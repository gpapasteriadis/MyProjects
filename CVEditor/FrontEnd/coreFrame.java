package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;

import BackEnd.CVEditor;
import BackEnd.Paragraph;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class coreFrame extends JFrame {

	private JPanel contentPane;
	private JEditorPane corePanel;
	private JButton button;
	private String coreStrengths;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					coreFrame frame = new coreFrame();
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
	public String getCoreStrengths(){
		 return coreStrengths;
	}
	public coreFrame() {
		setTitle("Core Strengths");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 511);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fill in the box with your core strengths information:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(121, 10, 421, 17);
		contentPane.add(lblNewLabel);
		
		corePanel = new JEditorPane();
		corePanel.setBounds(34, 39, 595, 341);
		contentPane.add(corePanel);
		coreStrengths = corePanel.getText();
		if(CVEditor.getCvForUpdate() == 1){
			corePanel.setText(CVEditor.getCV().getSections().get(2).getParagraph().getContents());//get(2) -> 3rd Section -> Core Strengths
		}
		button = new JButton("DONE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==0){	
					Paragraph p = new Paragraph(corePanel.getText());
					CVEditor.getCV().getSections().get(2).addParagraph(p);
					dispose();
				}else{
					Paragraph p = new Paragraph(corePanel.getText());
					CVEditor.getCV().getSections().get(2).setParagraph(p);
					dispose();
				}
			}
		});
		button.setForeground(new Color(240, 248, 255));
		button.setBackground(new Color(0, 128, 0));
		button.setBounds(276, 418, 103, 35);
		contentPane.add(button);
	}

}

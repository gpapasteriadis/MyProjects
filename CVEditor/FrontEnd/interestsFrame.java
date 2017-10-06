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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

import BackEnd.CVEditor;
import BackEnd.Paragraph;

public class interestsFrame extends JFrame {

	private JPanel contentPane;
	private JEditorPane interestsPanel;
	private JButton doneButton;
	private String interests;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interestsFrame frame = new interestsFrame();
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
	public String getInterests(){
		return interests;
	}
	public interestsFrame() {
		setTitle("Interests");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fill in the box with your Interests:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(184, 11, 272, 17);
		contentPane.add(lblNewLabel);
		
		interestsPanel = new JEditorPane();
		interestsPanel.setBounds(39, 50, 543, 257);
		contentPane.add(interestsPanel);
		if(CVEditor.getCvForUpdate() == 1){
			interestsPanel.setText(CVEditor.getCV().getSections().get(6).getParagraph().getContents()); //get(6) -> 7th Section -> Interests
		}
		
		doneButton = new JButton("DONE");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==0){
					Paragraph p = new Paragraph( interestsPanel.getText());
					CVEditor.getCV().getSections().get(6).addParagraph(p);
					dispose();
				}else{
					Paragraph p = new Paragraph( interestsPanel.getText());
					CVEditor.getCV().getSections().get(6).setParagraph(p);
					dispose();
				}
			}
		});
		doneButton.setForeground(new Color(240, 248, 255));
		doneButton.setBackground(new Color(0, 128, 0));
		doneButton.setBounds(270, 335, 103, 35);
		contentPane.add(doneButton);
	}

}

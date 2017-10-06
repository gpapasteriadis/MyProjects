package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JEditorPane;

import BackEnd.CVEditor;
import BackEnd.Paragraph;

public class profFrame extends JFrame {

	private JPanel contentPaneProf;
	private String profProfile;

	/**
	 * Launch the application.
	 */
    public String getProfProfile(){
    	return profProfile;
    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					profFrame frame = new profFrame();
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
	public profFrame() {
		setTitle("Professional Profile");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 398);
		contentPaneProf = new JPanel();
		contentPaneProf.setBackground(new Color(230, 230, 250));
		contentPaneProf.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneProf);
		contentPaneProf.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fill in the box with your Professional Profile information:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(77, 12, 417, 25);
		contentPaneProf.add(lblNewLabel);
		
		JEditorPane profProfilePanel = new JEditorPane();
		profProfilePanel.setBounds(44, 47, 489, 241);
		contentPaneProf.add(profProfilePanel);
		if(CVEditor.getCvForUpdate() == 1){
			profProfilePanel.setText(CVEditor.getCV().getSections().get(0).getParagraph().getContents());//get(0) -> 1st Section -> Professional Profile
		}
		JButton button = new JButton("DONE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==0){
					Paragraph p = new Paragraph(profProfilePanel.getText());
					CVEditor.getCV().getSections().get(0).addParagraph(p);
					dispose();
				}else{
					Paragraph p = new Paragraph(profProfilePanel.getText());
					CVEditor.getCV().getSections().get(0).setParagraph(p);
					dispose();
				}
			}
		});
		button.setForeground(new Color(240, 248, 255));
		button.setBackground(new Color(0, 128, 0));
		button.setBounds(227, 314, 103, 35);
		contentPaneProf.add(button);
		profProfile = profProfilePanel.getText();
		
	}
}

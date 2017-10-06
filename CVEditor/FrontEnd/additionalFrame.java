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
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JButton;

import BackEnd.CVEditor;
import BackEnd.Paragraph;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class additionalFrame extends JFrame {

	private JPanel contentPane;
	private JEditorPane addInfoPanel;
	private String addInfo;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					additionalFrame frame = new additionalFrame();
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
	public String getAddInfo(){
		return addInfo;
	}
	public additionalFrame() {
		setTitle("Additional Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 671, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fill in the box with your Additional Information:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(152, 11, 369, 17);
		contentPane.add(lblNewLabel);
		
		addInfoPanel = new JEditorPane();
		addInfoPanel.setBounds(47, 63, 555, 252);
		contentPane.add(addInfoPanel);
		if(CVEditor.getCvForUpdate() == 1){
			addInfoPanel.setText(CVEditor.getCV().getSections().get(5).getParagraph().getContents()); //get(5) -> 6th Section -> Additional Information
		}
		
		button = new JButton("DONE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==0){
					Paragraph p = new Paragraph( addInfoPanel.getText());
					CVEditor.getCV().getSections().get(5).addParagraph(p);
					dispose();
				}else{
					Paragraph p = new Paragraph( addInfoPanel.getText());
					CVEditor.getCV().getSections().get(5).setParagraph(p);
					dispose();
				}
			}
		});
		button.setForeground(new Color(240, 248, 255));
		button.setBackground(new Color(0, 128, 0));
		button.setBounds(261, 330, 103, 35);
		contentPane.add(button);
		addInfo = addInfoPanel.getText();
		
	}
}

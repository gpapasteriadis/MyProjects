package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import BackEnd.CV;
import BackEnd.CVEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class updateCVFrame extends JFrame {

	private JPanel contentPane;
	private JTextField numberField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateCVFrame frame = new updateCVFrame();
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
	public updateCVFrame() {
		setTitle("Update CVs");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 669, 385);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JList itemList = new JList(CVEditor.getCVs().toArray());
		itemList.setBounds(0, 0, 0, 0);
		contentPane.add(itemList);
		
		JEditorPane CVsField = new JEditorPane();
		CVsField.setBackground(new Color(255, 255, 255));
		CVsField.setBounds(31, 70, 182, 179);
		contentPane.add(CVsField);
		
		JLabel lblNewLabel = new JLabel("These are the existing CVs, choose one to update:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(31, 11, 419, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the number of CV you've chosen:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(40, 281, 349, 25);
		contentPane.add(lblNewLabel_1);
		
		numberField = new JTextField();
		numberField.setBackground(new Color(255, 255, 255));
		numberField.setBounds(435, 279, 80, 29);
		contentPane.add(numberField);
		numberField.setColumns(10);
		String text="";
	    for(int i=1; i<=CVEditor.getCVs().size(); i++){
	    	String s=CVEditor.getCVs().get(i-1).getName();
	        text += i+")"+" " +s+ "\r\n";
	    } 
	    CVsField.setText(text);
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					CVEditor.setIndex(Integer.parseInt(numberField.getText()));
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please Enter number");
					return;
				}
				
				if(CVEditor.getIndex() > CVEditor.getCVs().size() || CVEditor.getIndex()-1<0){
					JOptionPane.showMessageDialog(null, "Please Enter a valid number");
					return;
				}
				else{
					CV x = CVEditor.getCVs().get(CVEditor.getIndex()-1);
					CVEditor.setCV(x);
					if(CVEditor.getCV().getSections().get(1).getTitle()=="3. SKILLS AND EXPERIENCE")
					{
						if(CVEditor.getCV().getSections().get(2).getTitle()=="4. CAREER SUMMARY") //functional
						{
							dispose();
							functFrame func = new functFrame();
							func.setVisible(true);
						}else{                                                                    //combined
							dispose();
							combFrame func = new combFrame();
							func.setVisible(true);
						}
					}else{                                                                       //chronological
						dispose();
						chronFrame func = new chronFrame();
						func.setVisible(true);
					}
				}
				
			}
		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOk.setBackground(new Color(0, 128, 0));
		btnOk.setBounds(539, 278, 86, 33);
		contentPane.add(btnOk);
		
		JButton button = new JButton("BACK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.BOLD, 11));
		button.setBackground(new Color(139, 0, 0));
		button.setBounds(539, 323, 86, 33);
		contentPane.add(button);

	}
}

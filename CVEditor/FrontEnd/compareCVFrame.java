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

public class compareCVFrame extends JFrame {

	private JPanel contentPane;
	private JTextField numberField1;
	private JTextField numberField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					compareCVFrame frame = new compareCVFrame();
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
	public compareCVFrame() {
		setTitle("Update CVs");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 747, 580);
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
		CVsField.setBounds(31, 70, 182, 242);
		contentPane.add(CVsField);
		
		JLabel lblNewLabel = new JLabel("These are the existing CVs, choose one to update:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(31, 11, 419, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the number of CVs you want to compare: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(31, 355, 380, 25);
		contentPane.add(lblNewLabel_1);
		
		numberField1 = new JTextField();
		numberField1.setBackground(new Color(255, 255, 255));
		numberField1.setBounds(149, 390, 80, 29);
		contentPane.add(numberField1);
		numberField1.setColumns(10);
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
					CVEditor.setIndex(Integer.parseInt(numberField1.getText()));
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please Enter number");
					return;
				}
				try{
					CVEditor.setIndex2(Integer.parseInt(numberField2.getText()));
				}catch(Exception e2){
					JOptionPane.showMessageDialog(null, "Please Enter number");
					return;
				}
				
				
				if(CVEditor.getIndex() > CVEditor.getCVs().size() || CVEditor.getIndex()-1<0 || CVEditor.getIndex2() > CVEditor.getCVs().size() || CVEditor.getIndex2()-1<0){
					JOptionPane.showMessageDialog(null, "Please Enter a valid number");
					return;
				}
				else{
					CV x = CVEditor.getCVs().get(CVEditor.getIndex()-1);
					
					CV y = CVEditor.getCVs().get(CVEditor.getIndex2()-1);
					
					if(!x.getName().equals(y.getName()) )
					{
						JOptionPane.showMessageDialog(null, "The two CVs you selected are not characterized by the same name.");
					}
					else if((Integer.parseInt(numberField1.getText())) == (Integer.parseInt(numberField2.getText())))
					{ 
						JOptionPane.showMessageDialog(null, "You have selected the same CV twice.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "The two CVs you selected are characterized by the same name.");
						
					}
				}
				
			}
		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOk.setBackground(new Color(0, 128, 0));
		btnOk.setBounds(303, 404, 86, 33);
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
		button.setBounds(590, 487, 86, 33);
		contentPane.add(button);
		
		numberField2 = new JTextField();
		numberField2.setColumns(10);
		numberField2.setBackground(Color.WHITE);
		numberField2.setBounds(149, 431, 80, 29);
		contentPane.add(numberField2);
		
		JLabel lblFirstCv = new JLabel("First CV:");
		lblFirstCv.setBounds(31, 397, 70, 15);
		contentPane.add(lblFirstCv);
		
		JLabel lblSecondCv = new JLabel("Second CV:");
		lblSecondCv.setBounds(31, 438, 80, 15);
		contentPane.add(lblSecondCv);

	}
}

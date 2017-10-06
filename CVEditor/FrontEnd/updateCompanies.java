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

import BackEnd.BulletListItem;
import BackEnd.CVEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class updateCompanies extends JFrame {

	private JPanel contentPane;
	private JTextField numberField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateCompanies frame = new updateCompanies();
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
	public updateCompanies() {
		setTitle("Update Companies");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 669, 416);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JList itemList = new JList(CVEditor.getCVs().toArray());
		itemList.setBounds(0, 0, 0, 0);
		contentPane.add(itemList);
		
		JEditorPane CVsField = new JEditorPane();
		CVsField.setBounds(31, 70, 182, 179);
		CVsField.setBackground(new Color(255, 255, 255));
		contentPane.add(CVsField);
		
		JLabel lblNewLabel = new JLabel("These are the existing Companies, choose one to update:");
		lblNewLabel.setBounds(31, 11, 480, 33);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the number of Company you've chosen:");
		lblNewLabel_1.setBounds(31, 277, 373, 25);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		numberField = new JTextField();
		numberField.setBounds(432, 273, 69, 33);
		numberField.setBackground(new Color(255, 255, 255));
		contentPane.add(numberField);
		numberField.setColumns(10);
		
		String text="";
		
		for(int i=1; i<=CVEditor.getCV().getSections().get(2).getBulletList().size(); i++){
	    	String s=CVEditor.getCV().getSections().get(2).getBulletList().get(i-1).getContent1();
	        text += i+")"+" " +s+ "\r\n";
	    } 
	    CVsField.setText(text);
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(533, 273, 86, 33);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					CVEditor.setIndexItem(Integer.parseInt(numberField.getText()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please Enter a valid number");
					return;
					
				}
					if(CVEditor.getIndexItem() > CVEditor.getCV().getSections().get(2).getBulletList().size() || CVEditor.getIndexItem()-1<0){
						JOptionPane.showMessageDialog(null, "Please Enter a valid number");
						return;
					}
					else{
						BulletListItem z = CVEditor.getCV().getSections().get(2).getBulletList().get(CVEditor.getIndexItem()-1);
						CVEditor.setItem(z);
						if(CVEditor.getCV().getSections().get(1).getTitle()=="3. SKILLS AND EXPERIENCE")
						{
							if(CVEditor.getCV().getSections().get(2).getTitle()=="4. CAREER SUMMARY") //functional
							{
								dispose();
								updateChosenCompanyFrame frame = new updateChosenCompanyFrame();
								frame.setVisible(true);
							}else{                                                                    //combined
								dispose();
								updateChosenCompanyFrame2 frame = new updateChosenCompanyFrame2();
								frame.setVisible(true);
							}
						}else{                                                                       //chronological
							dispose();
							updateChosenCompanyFrame2 frame = new updateChosenCompanyFrame2();
							frame.setVisible(true);
						}
					}
				
			}
		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOk.setBackground(new Color(0, 128, 0));
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
		button.setBounds(533, 318, 86, 33);
		contentPane.add(button);

	}
}
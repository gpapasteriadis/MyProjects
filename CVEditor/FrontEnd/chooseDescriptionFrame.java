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

import BackEnd.CVEditor;
import BackEnd.Paragraph;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class chooseDescriptionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField numberField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chooseDescriptionFrame frame = new chooseDescriptionFrame();
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
	public chooseDescriptionFrame() {
		setTitle("Update Descriptions");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 669, 391);
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
		
		JLabel lblNewLabel = new JLabel("These are the existing Descriptions, choose one to update:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(31, 11, 480, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the number of Description you've chosen:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(31, 277, 373, 25);
		contentPane.add(lblNewLabel_1);
		
		numberField = new JTextField();
		numberField.setBackground(new Color(255, 255, 255));
		numberField.setBounds(432, 273, 69, 33);
		contentPane.add(numberField);
		numberField.setColumns(10);
		
		String text="";
		System.out.println(CVEditor.getItem().getBulletList().size());
		for(int i=1; i<=CVEditor.getItem().getBulletList().size(); i++){
	    	String s=CVEditor.getItem().getBulletList().get(i-1).getContents();
	        text += i+")"+" " +s+ "\r\n";
	        System.out.println(s);
	    } 
	    CVsField.setText(text);
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					CVEditor.setIndexItem(Integer.parseInt(numberField.getText()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please Enter a valid number");
					return;
					
				}
				
					if(CVEditor.getIndexItem() > CVEditor.getItem().getBulletList().size() || CVEditor.getIndexItem()-1<0){
						JOptionPane.showMessageDialog(null, "Please Enter a valid number");
						return;
					}
					else{
						Paragraph q = CVEditor.getItem().getBulletList().get(CVEditor.getIndexItem()-1);
						CVEditor.setParagraph(q);
						dispose();
						updateChosenDescriptionFrame frame = new updateChosenDescriptionFrame();
						frame.setVisible(true);
					}
				
			}
		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOk.setBackground(new Color(0, 128, 0));
		btnOk.setBounds(533, 273, 86, 33);
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
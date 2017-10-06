package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollBar;

import BackEnd.CVEditor;

import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

public class generalFrame extends JFrame {

	private JPanel contentPaneGen;
	private JTextField namefield;
	private JTextField addressfield;
	private JTextField telehomefield;
	private JTextField telemobilefield;
	private JTextField emailfield;
	private JButton button;
	private JLabel label;
	private JLabel lbltheseFieldsMust;
	

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					generalFrame frame = new generalFrame();
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
	
	public generalFrame() {
		
		setTitle("General Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 414);
		contentPaneGen = new JPanel();
		contentPaneGen.setBackground(new Color(230, 230, 250));
		contentPaneGen.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneGen);
		contentPaneGen.setLayout(null);
		
		namefield = new JTextField();
		namefield.setBounds(297, 11, 183, 20);
		contentPaneGen.add(namefield);
		namefield.setColumns(10);
		
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setForeground(new Color(0, 128, 128));
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(52, 11, 53, 17);
		contentPaneGen.add(nameLabel);
		
		JLabel adressLabel = new JLabel("Address:");
		adressLabel.setForeground(new Color(0, 128, 128));
		adressLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		adressLabel.setBounds(52, 70, 93, 14);
		contentPaneGen.add(adressLabel);
		
		addressfield = new JTextField();
		addressfield.setBounds(298, 69, 182, 20);
		contentPaneGen.add(addressfield);
		addressfield.setColumns(10);
		
		JLabel teleLabel = new JLabel("Telephone:   (Home)");
		teleLabel.setForeground(new Color(0, 128, 128));
		teleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		teleLabel.setBounds(52, 126, 173, 19);
		contentPaneGen.add(teleLabel);
		
		telehomefield = new JTextField();
		telehomefield.setColumns(10);
		telehomefield.setBounds(297, 125, 183, 20);
		contentPaneGen.add(telehomefield);
		
		
		JLabel tele2Label = new JLabel("(Mobile)");
		tele2Label.setForeground(new Color(0, 128, 128));
		tele2Label.setFont(new Font("Tahoma", Font.BOLD, 14));
		tele2Label.setBounds(158, 157, 67, 14);
		contentPaneGen.add(tele2Label);
		
		telemobilefield = new JTextField();
		telemobilefield.setColumns(10);
		telemobilefield.setBounds(297, 156, 183, 20);
		contentPaneGen.add(telemobilefield);
		
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setForeground(new Color(0, 128, 128));
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		emailLabel.setBounds(52, 205, 46, 14);
		contentPaneGen.add(emailLabel);
		
		emailfield = new JTextField();
		emailfield.setColumns(10);
		emailfield.setBounds(297, 204, 183, 20);
		contentPaneGen.add(emailfield);
		
		if(CVEditor.getCvForUpdate() == 1){
			namefield.setText(CVEditor.getCV().getName());
			addressfield.setText(CVEditor.getCV().getAddress());
			telehomefield.setText(CVEditor.getCV().getTeleHome());
			telemobilefield.setText(CVEditor.getCV().getTeleMobile());
			emailfield.setText(CVEditor.getCV().getEmail());
		}
		
		button = new JButton("DONE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==0){
					CVEditor.getCV().setName(namefield.getText());
					CVEditor.getCV().setAddress(addressfield.getText());
					CVEditor.getCV().setTeleHome(telehomefield.getText());
					CVEditor.getCV().setTeleMobile(telemobilefield.getText());
					CVEditor.getCV().setEmail(emailfield.getText());
					dispose();
				}else{
					CVEditor.getCVs().get(CVEditor.getIndex()-1).setName(namefield.getText());
					CVEditor.getCVs().get(CVEditor.getIndex()-1).setAddress(addressfield.getText());
					CVEditor.getCVs().get(CVEditor.getIndex()-1).setTeleHome(telehomefield.getText());
					CVEditor.getCVs().get(CVEditor.getIndex()-1).setTeleMobile(telemobilefield.getText());
					CVEditor.getCVs().get(CVEditor.getIndex()-1).setEmail(emailfield.getText());
					dispose();
				}
			}
		});
		button.setForeground(new Color(240, 248, 255));
		button.setBackground(new Color(0, 128, 0));
		button.setBounds(214, 275, 103, 35);
		contentPaneGen.add(button);
		
		label = new JLabel("(*)");
		label.setForeground(Color.RED);
		label.setBounds(487, 13, 103, 15);
		contentPaneGen.add(label);
		
		lbltheseFieldsMust = new JLabel("(*)These fields must be filled");
		lbltheseFieldsMust.setForeground(Color.RED);
		lbltheseFieldsMust.setBounds(12, 365, 235, 15);
		contentPaneGen.add(lbltheseFieldsMust);
	}
}

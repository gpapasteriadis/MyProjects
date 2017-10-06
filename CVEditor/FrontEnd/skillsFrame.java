package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JTree;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Choice;
import java.awt.Button;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

import BackEnd.BulletListItem;
import BackEnd.CVEditor;
import BackEnd.Paragraph;

import java.awt.Font;

public class skillsFrame extends JFrame {

	private JPanel contentPaneSkills;
	private JTextField skillField;
	private JTextField descriptField;
	private ArrayList<BulletListItem> skills = new ArrayList();
	//private ArrayList<Paragraph> descriptions = new ArrayList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					skillsFrame frame = new skillsFrame();
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
	public skillsFrame() {
		setTitle("Skills and Experience");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 903, 555);
		contentPaneSkills = new JPanel();
		contentPaneSkills.setBackground(new Color(230, 230, 250));
		contentPaneSkills.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneSkills);
		contentPaneSkills.setLayout(null);
		
		JTree treeSkills = new JTree();
		treeSkills.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Skills ") {
				{
				}
			}
		));
		treeSkills.setBounds(374, 21, 477, 363);
		contentPaneSkills.add(treeSkills);
		
		JLabel lblNewLabel = new JLabel("Enter Skill:");
		lblNewLabel.setBounds(10, 23, 103, 14);
		contentPaneSkills.add(lblNewLabel);
		
		skillField = new JTextField();
		skillField.setBounds(175, 21, 86, 20);
		contentPaneSkills.add(skillField);
		skillField.setColumns(10);
		
		JLabel msgField = new JLabel("");
		msgField.setForeground(Color.RED);
		msgField.setBounds(23, 172, 282, 60);
		contentPaneSkills.add(msgField);
		
		JButton skillButton = new JButton(">>");
		skillButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String x;
				msgField.setText("");
				DefaultTreeModel model = (DefaultTreeModel) treeSkills.getModel();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
				if(!skillField.getText().trim().equals("")){
					root.add(new DefaultMutableTreeNode(skillField.getText()));
					model.reload();
					x = skillField.getText();
					BulletListItem skill = new BulletListItem(x);
					skills.add(skill);
				}
				else{
					msgField.setText("You must enter Skill");
				}
			}
		});
		skillButton.setBounds(273, 19, 89, 23);
		contentPaneSkills.add(skillButton);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Skill Description:");
		lblNewLabel_1.setBounds(10, 60, 161, 14);
		contentPaneSkills.add(lblNewLabel_1);
		
		descriptField = new JTextField();
		descriptField.setBounds(175, 58, 86, 20);
		contentPaneSkills.add(descriptField);
		descriptField.setColumns(10);
		
		JButton descButton = new JButton(">>");
		descButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msgField.setText("");
				DefaultTreeModel model = (DefaultTreeModel) treeSkills.getModel();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSkills.getLastSelectedPathComponent();
				DefaultMutableTreeNode newDescription = new DefaultMutableTreeNode(descriptField.getText());
				if(selectedNode==null){
					msgField.setText("You must choose a parent node to insert");
					return;
				}
				if(selectedNode.isRoot()){
					msgField.setText("You must select Skill");
					return;
				}
				else{
					if(selectedNode!=null){
						if(!descriptField.getText().trim().equals("")){
							model.insertNodeInto(newDescription, selectedNode, selectedNode.getChildCount());
							for (BulletListItem skill : skills){
								if(skill.getContent1().equals(selectedNode.toString())){
									Paragraph p = new Paragraph(descriptField.getText());
									skill.getBulletList().add(p);
								}
							}
						}
						else{
							msgField.setText("You must enter Description");
						}
					}
				}
				
			}
		});
		descButton.setBounds(273, 56, 89, 23);
		contentPaneSkills.add(descButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setForeground(new Color(255, 255, 255));
		removeButton.setBackground(new Color(255, 0, 0));
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msgField.setText("");
				DefaultTreeModel model = (DefaultTreeModel) treeSkills.getModel();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSkills.getLastSelectedPathComponent();
				if(selectedNode==null){
					msgField.setText("You must choose a description to delete");
					return;
				}
				if(selectedNode.isRoot()){
					msgField.setText("You can't delete root");
					return;
				}
				else{
					if(selectedNode!=null){
						int i;
						model.removeNodeFromParent(selectedNode);
						for(i=0; i<skills.size(); i++){
							if(skills.get(i).getContent1().equals(selectedNode.toString())){
								skills.remove(skills.get(i));
							}
							else{
								int j;
								for (j=0; j<skills.get(i).getBulletList().size(); j++){
									if(skills.get(i).getBulletList().get(j).getContents().equals(selectedNode.toString())){
										skills.get(i).getBulletList().remove(skills.get(i).getBulletList().get(j));
									}
								}
							}
						}
						
					}
				}
			}
		});
		removeButton.setBounds(273, 103, 89, 23);
		contentPaneSkills.add(removeButton);
		
		JButton button = new JButton("DONE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CVEditor.getCV().getSections().get(1).setBulletList(skills);
				dispose();
			}
		});
		button.setForeground(new Color(240, 248, 255));
		button.setBackground(new Color(0, 128, 0));
		button.setBounds(259, 349, 103, 35);
		contentPaneSkills.add(button);
		
		JButton button_1 = new JButton("Select");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==1){
					if(CVEditor.getCV().getSections().get(1).getBulletList().isEmpty()){
						JOptionPane.showMessageDialog(null, "There are no skills to update!");
					}
					else{
						String s;
						for(int i=1; i<=CVEditor.getCV().getSections().get(1).getBulletList().size(); i++){
					    	s=CVEditor.getCV().getSections().get(1).getBulletList().get(i-1).getContent1();
					    	System.out.println(s);
					    } 
						updateSkills frame = new updateSkills();
						frame.setVisible(true);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "You are currently creating a new CV. You can't update existing CVs now!");
				}
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Dialog", Font.BOLD, 11));
		button_1.setBackground(Color.BLUE);
		button_1.setBounds(366, 408, 105, 25);
		contentPaneSkills.add(button_1);
		
		JLabel lblClickHereTo = new JLabel("Click here to update existing Skills:");
		lblClickHereTo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblClickHereTo.setBounds(49, 400, 313, 39);
		contentPaneSkills.add(lblClickHereTo);
		
		JButton button_2 = new JButton("BACK");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Dialog", Font.BOLD, 11));
		button_2.setBackground(new Color(139, 0, 0));
		button_2.setBounds(374, 490, 97, 33);
		contentPaneSkills.add(button_2);
		

	}
}

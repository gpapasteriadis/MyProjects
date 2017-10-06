package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;

import BackEnd.CVEditor;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class functFrame extends JFrame {

	private JPanel contentPaneFunc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					functFrame frame = new functFrame();
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
	public functFrame() {
		setTitle("Fuctional Template");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 708, 676);
		contentPaneFunc = new JPanel();
		contentPaneFunc.setBackground(new Color(230, 230, 250));
		contentPaneFunc.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneFunc);
		contentPaneFunc.setLayout(null);
		
		JLabel generalField = new JLabel("1. GENERAL INFORMATION");
		generalField.setForeground(new Color(0, 128, 128));
		generalField.setFont(new Font("Tahoma", Font.BOLD, 12));
		generalField.setBounds(10, 86, 200, 50);
		contentPaneFunc.add(generalField);
		
		JLabel professField = new JLabel("2. PROFESSIONAL PROFILE");
		professField.setForeground(new Color(0, 128, 128));
		professField.setFont(new Font("Tahoma", Font.BOLD, 12));
		professField.setBounds(10, 148, 200, 50);
		contentPaneFunc.add(professField);
		
		JLabel skillsField = new JLabel("3. SKILLS AND EXPERIENCE");
		skillsField.setForeground(new Color(0, 128, 128));
		skillsField.setFont(new Font("Tahoma", Font.BOLD, 12));
		skillsField.setBounds(10, 210, 200, 50);
		contentPaneFunc.add(skillsField);
		
		JLabel careerField = new JLabel("4. CAREER SUMMARY");
		careerField.setForeground(new Color(0, 128, 128));
		careerField.setFont(new Font("Tahoma", Font.BOLD, 12));
		careerField.setBounds(10, 272, 200, 50);
		contentPaneFunc.add(careerField);
		
		JLabel educationField = new JLabel("5. EDUCATION AND TRAINING");
		educationField.setForeground(new Color(0, 128, 128));
		educationField.setFont(new Font("Tahoma", Font.BOLD, 12));
		educationField.setBounds(10, 334, 200, 50);
		contentPaneFunc.add(educationField);
		
		JLabel furtherField = new JLabel("6. FURTHER COURSES");
		furtherField.setForeground(new Color(0, 128, 128));
		furtherField.setFont(new Font("Tahoma", Font.BOLD, 12));
		furtherField.setBounds(10, 396, 200, 50);
		contentPaneFunc.add(furtherField);
		
		JLabel addinfoField = new JLabel("7. ADDITIONAL INFORMATION");
		addinfoField.setForeground(new Color(0, 128, 128));
		addinfoField.setFont(new Font("Tahoma", Font.BOLD, 12));
		addinfoField.setBounds(10, 458, 200, 50);
		contentPaneFunc.add(addinfoField);
		
		JLabel interestsField = new JLabel("8. INTERESTS");
		interestsField.setForeground(new Color(0, 128, 128));
		interestsField.setFont(new Font("Tahoma", Font.BOLD, 12));
		interestsField.setBounds(10, 520, 200, 50);
		contentPaneFunc.add(interestsField);
		
		JLabel topFieldsLabel = new JLabel(" FIELDS OF FUNCTIONAL CV TEMPLATE");
		topFieldsLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		topFieldsLabel.setBounds(126, 11, 420, 50);
		contentPaneFunc.add(topFieldsLabel);
		
		JButton editGeneral = new JButton("EDIT");
		editGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalFrame genFrame = new generalFrame();
				genFrame.setVisible(true);
			}
		});
		editGeneral.setFont(new Font("Tahoma", Font.BOLD, 11));
		editGeneral.setForeground(Color.WHITE);
		editGeneral.setBackground(new Color(60, 179, 113));
		editGeneral.setBounds(457, 101, 89, 23);
		contentPaneFunc.add(editGeneral);
		
		JButton editProfile = new JButton("EDIT");
		editProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profFrame professFrame = new profFrame();
				professFrame.setVisible(true);
			}
		});
		editProfile.setFont(new Font("Tahoma", Font.BOLD, 11));
		editProfile.setForeground(Color.WHITE);
		editProfile.setBackground(new Color(60, 179, 113));
		editProfile.setBounds(457, 163, 89, 23);
		contentPaneFunc.add(editProfile);
		
		JButton editSkills = new JButton("EDIT");
		editSkills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==0){
					skillsFrame skFrame = new skillsFrame();
					skFrame.setVisible(true);
				}else{
					addOrUpdateSkillsFrame frame = new addOrUpdateSkillsFrame();
					frame.setVisible(true);
				}
			}
		});
		editSkills.setFont(new Font("Tahoma", Font.BOLD, 11));
		editSkills.setForeground(Color.WHITE);
		editSkills.setBackground(new Color(60, 179, 113));
		editSkills.setBounds(457, 225, 89, 23);
		contentPaneFunc.add(editSkills);
		
		JButton editCareer = new JButton("EDIT");
		editCareer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				careerFrame carFrame = new careerFrame();
				carFrame.setVisible(true);
			}
		});
		editCareer.setFont(new Font("Tahoma", Font.BOLD, 11));
		editCareer.setForeground(Color.WHITE);
		editCareer.setBackground(new Color(60, 179, 113));
		editCareer.setBounds(457, 287, 89, 23);
		contentPaneFunc.add(editCareer);
		
		JButton editEducation = new JButton("EDIT");
		editEducation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				educationFrame eduFrame = new educationFrame();
				eduFrame.setVisible(true);
			}
		});
		editEducation.setFont(new Font("Tahoma", Font.BOLD, 11));
		editEducation.setForeground(Color.WHITE);
		editEducation.setBackground(new Color(60, 179, 113));
		editEducation.setBounds(457, 349, 89, 23);
		contentPaneFunc.add(editEducation);
		
		JButton editFurther = new JButton("EDIT");
		editFurther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				furtherFrame furFrame = new furtherFrame();
				furFrame.setVisible(true);
			}
			
		});
		editFurther.setFont(new Font("Tahoma", Font.BOLD, 11));
		editFurther.setForeground(Color.WHITE);
		editFurther.setBackground(new Color(60, 179, 113));
		editFurther.setBounds(457, 411, 89, 23);
		contentPaneFunc.add(editFurther);
		
		JButton editAdditional = new JButton("EDIT");
		editAdditional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				additionalFrame addFrame = new additionalFrame();
				addFrame.setVisible(true);
			}
			
		});
		editAdditional.setFont(new Font("Tahoma", Font.BOLD, 11));
		editAdditional.setForeground(Color.WHITE);
		editAdditional.setBackground(new Color(60, 179, 113));
		editAdditional.setBounds(457, 473, 89, 23);
		contentPaneFunc.add(editAdditional);
		
		JButton editInterests = new JButton("EDIT");
		editInterests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interestsFrame intFrame = new interestsFrame();
				intFrame.setVisible(true);
			}
		});
		editInterests.setFont(new Font("Tahoma", Font.BOLD, 11));
		editInterests.setForeground(Color.WHITE);
		editInterests.setBackground(new Color(60, 179, 113));
		editInterests.setBounds(457, 535, 89, 23);
		contentPaneFunc.add(editInterests);
		
		JButton btnNewButton = new JButton("DONE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CVEditor.getCvForUpdate()==0)
				{
					CVEditor.getCVs().add(CVEditor.getCV()); //prostheto to temporary CV, meta tis tropopoiiseis tou hristi, stin lista me ta CVs
					dispose();
				}else{
					CVEditor.getCVs().set(CVEditor.getIndex()-1, CVEditor.getCV());
					dispose();
				}
			}
		});
		btnNewButton.setForeground(new Color(240, 248, 255));
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setBounds(245, 583, 103, 35);
		contentPaneFunc.add(btnNewButton);
		
		JLabel label = new JLabel("(*)These fields must be filled");
		label.setForeground(Color.RED);
		label.setBounds(12, 624, 235, 15);
		contentPaneFunc.add(label);
		
		JLabel label_1 = new JLabel("(*)");
		label_1.setForeground(Color.RED);
		label_1.setBounds(554, 104, 38, 15);
		contentPaneFunc.add(label_1);
		
	}
}

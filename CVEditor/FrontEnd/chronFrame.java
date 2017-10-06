package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import BackEnd.CVEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class chronFrame extends JFrame {

	private JPanel contentPaneChron;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chronFrame frame = new chronFrame();
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
	public chronFrame() {
		setTitle("Chronological Template");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 645, 707);
		contentPaneChron = new JPanel();
		contentPaneChron.setBackground(new Color(230, 230, 250));
		contentPaneChron.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneChron);
		contentPaneChron.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FIELDS OF CHRONOLOGICAL TEMPLATE");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(115, 11, 397, 41);
		contentPaneChron.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1. GENERAL INFORMATION");
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 98, 272, 27);
		contentPaneChron.add(lblNewLabel_1);
		
		JLabel lblProfessionalProfile = new JLabel("2. PROFESSIONAL PROFILE");
		lblProfessionalProfile.setForeground(SystemColor.desktop);
		lblProfessionalProfile.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProfessionalProfile.setBounds(10, 170, 282, 14);
		contentPaneChron.add(lblProfessionalProfile);
		
		JLabel lblFurtherCourses = new JLabel("6. FURTHER COURSES");
		lblFurtherCourses.setForeground(SystemColor.desktop);
		lblFurtherCourses.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFurtherCourses.setBounds(10, 432, 176, 14);
		contentPaneChron.add(lblFurtherCourses);
		
		JLabel lblProfessionalProfile_1 = new JLabel("4. PROFESSIONAL EXPERIENCE");
		lblProfessionalProfile_1.setForeground(SystemColor.desktop);
		lblProfessionalProfile_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProfessionalProfile_1.setBounds(10, 299, 237, 14);
		contentPaneChron.add(lblProfessionalProfile_1);
		
		JLabel lblEducationAnd = new JLabel("5. EDUCATION AND TRAINING");
		lblEducationAnd.setForeground(SystemColor.desktop);
		lblEducationAnd.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEducationAnd.setBounds(10, 367, 215, 14);
		contentPaneChron.add(lblEducationAnd);
		
		JLabel lblAdditionalInformation = new JLabel("7. ADDITIONAL INFORMATION");
		lblAdditionalInformation.setForeground(SystemColor.desktop);
		lblAdditionalInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdditionalInformation.setBounds(10, 496, 215, 14);
		contentPaneChron.add(lblAdditionalInformation);
		
		JLabel lblInterests = new JLabel("8. INTERESTS");
		lblInterests.setForeground(SystemColor.desktop);
		lblInterests.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInterests.setBounds(10, 559, 120, 14);
		contentPaneChron.add(lblInterests);
		
		JLabel lblCoreStrengths = new JLabel("3. CORE STRENGTHS");
		lblCoreStrengths.setForeground(SystemColor.desktop);
		lblCoreStrengths.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCoreStrengths.setBounds(10, 235, 237, 20);
		contentPaneChron.add(lblCoreStrengths);
		
		JButton editGeneral = new JButton("EDIT");
		editGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalFrame genFrame = new generalFrame();
				genFrame.setVisible(true);
			}
		});
		editGeneral.setBackground(new Color(60, 179, 113));
		editGeneral.setForeground(new Color(255, 255, 255));
		editGeneral.setFont(new Font("Tahoma", Font.BOLD, 11));
		editGeneral.setBounds(446, 101, 89, 23);
		contentPaneChron.add(editGeneral);
		
		JButton editProfile = new JButton("EDIT");
		editProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profFrame professFrame = new profFrame();
				professFrame.setVisible(true);
			}
		});
		editProfile.setForeground(new Color(255, 255, 255));
		editProfile.setBackground(new Color(60, 179, 113));
		editProfile.setFont(new Font("Tahoma", Font.BOLD, 11));
		editProfile.setBounds(446, 167, 89, 23);
		contentPaneChron.add(editProfile);
		
		JButton editCore = new JButton("EDIT");
		editCore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coreFrame corFrame = new coreFrame();
				corFrame.setVisible(true);
			}
		});
		editCore.setForeground(new Color(255, 255, 255));
		editCore.setBackground(new Color(60, 179, 113));
		editCore.setFont(new Font("Tahoma", Font.BOLD, 11));
		editCore.setBounds(446, 232, 89, 23);
		contentPaneChron.add(editCore);
		
		JButton editExperience = new JButton("EDIT");
		editExperience.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				experienceFrame expFrame = new experienceFrame();
				expFrame.setVisible(true);
			}
		});
		editExperience.setForeground(new Color(255, 255, 255));
		editExperience.setBackground(new Color(60, 179, 113));
		editExperience.setFont(new Font("Tahoma", Font.BOLD, 11));
		editExperience.setBounds(446, 296, 89, 23);
		contentPaneChron.add(editExperience);
		
		JButton editEducation = new JButton("EDIT");
		editEducation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				educationFrame eduFrame = new educationFrame();
				eduFrame.setVisible(true);
			}
		});
		editEducation.setFont(new Font("Tahoma", Font.BOLD, 11));
		editEducation.setForeground(new Color(255, 255, 255));
		editEducation.setBackground(new Color(60, 179, 113));
		editEducation.setBounds(446, 364, 89, 23);
		contentPaneChron.add(editEducation);
		
		JButton editFurther = new JButton("EDIT");
		editFurther.setForeground(new Color(255, 255, 255));
		editFurther.setFont(new Font("Tahoma", Font.BOLD, 11));
		editFurther.setBackground(new Color(60, 179, 113));
		editFurther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				furtherFrame furFrame = new furtherFrame();
				furFrame.setVisible(true);
			}
		});
		editFurther.setBounds(446, 429, 89, 23);
		contentPaneChron.add(editFurther);
		
		JButton editAdditional = new JButton("EDIT");
		editAdditional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				additionalFrame addFrame = new additionalFrame();
				addFrame.setVisible(true);
			}
		});
		editAdditional.setForeground(new Color(255, 255, 255));
		editAdditional.setFont(new Font("Tahoma", Font.BOLD, 11));
		editAdditional.setBackground(new Color(60, 179, 113));
		editAdditional.setBounds(446, 493, 89, 23);
		contentPaneChron.add(editAdditional);
		
		JButton editInterests = new JButton("EDIT");
		editInterests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interestsFrame intFrame = new interestsFrame();
				intFrame.setVisible(true);
			}
		});
		editInterests.setForeground(new Color(255, 255, 255));
		editInterests.setFont(new Font("Tahoma", Font.BOLD, 11));
		editInterests.setBackground(new Color(60, 179, 113));
		editInterests.setBounds(446, 556, 89, 23);
		contentPaneChron.add(editInterests);
		
		JButton btnNewButton = new JButton("DONE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CVEditor.getCvForUpdate()==0)
				{
					CVEditor.getCVs().add(CVEditor.getCV()); //prostheto to temporary CV, meta tis tropopoiiseis tou hristi, stin lista me ta CVs
					dispose();
				}else{
					CVEditor.getCVs().set(CVEditor.getIndex()-1, CVEditor.getCV());
					dispose();
				};
			}
		});
		btnNewButton.setForeground(new Color(240, 248, 255));
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setBounds(241, 599, 102, 39);
		contentPaneChron.add(btnNewButton);
		
		JLabel label = new JLabel("(*)These fields must be filled");
		label.setForeground(Color.RED);
		label.setBounds(10, 655, 235, 15);
		contentPaneChron.add(label);
		
		JLabel label_1 = new JLabel("(*)");
		label_1.setForeground(Color.RED);
		label_1.setBounds(543, 98, 33, 27);
		contentPaneChron.add(label_1);
	}
}

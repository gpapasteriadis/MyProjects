package FrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;

import BackEnd.CVEditor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class combFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					combFrame frame = new combFrame();
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
	public combFrame() {
		setTitle("Combined Template");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FIELDS OF THE COMBINDED CV TEMPLATE");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(85, 11, 401, 28);
		contentPane.add(lblNewLabel);
		
		JLabel genLabel = new JLabel("1. GENERAL INFORMATION");
		genLabel.setForeground(SystemColor.desktop);
		genLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		genLabel.setBackground(new Color(255, 255, 255));
		genLabel.setBounds(10, 98, 236, 28);
		contentPane.add(genLabel);
		
		JLabel profLabel = new JLabel("2. PROFESSIONAL PROFILE");
		profLabel.setForeground(SystemColor.desktop);
		profLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		profLabel.setBounds(10, 150, 236, 28);
		contentPane.add(profLabel);
		
		JLabel skillsLabel = new JLabel("3. SKILLS AND EXPERIENCE");
		skillsLabel.setForeground(SystemColor.desktop);
		skillsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		skillsLabel.setBounds(10, 203, 236, 28);
		contentPane.add(skillsLabel);
		
		JLabel experLabel = new JLabel("4. PROFESSIONAL EXPERIENCE");
		experLabel.setForeground(SystemColor.desktop);
		experLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		experLabel.setBounds(10, 258, 255, 28);
		contentPane.add(experLabel);
		
		JLabel eduLabel = new JLabel("5. EDUCATION AND TRAINING");
		eduLabel.setForeground(SystemColor.desktop);
		eduLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		eduLabel.setBounds(10, 313, 219, 28);
		contentPane.add(eduLabel);
		
		JLabel furthLabel = new JLabel("6. FURTHER COURSES");
		furthLabel.setForeground(SystemColor.desktop);
		furthLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		furthLabel.setBounds(10, 370, 175, 28);
		contentPane.add(furthLabel);
		
		JLabel addLabel = new JLabel("7. ADDITIONAL INFORMATION");
		addLabel.setForeground(SystemColor.desktop);
		addLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		addLabel.setBounds(10, 424, 207, 28);
		contentPane.add(addLabel);
		
		JLabel intLabel = new JLabel("8. INTERESTS");
		intLabel.setForeground(SystemColor.desktop);
		intLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		intLabel.setBounds(10, 478, 159, 31);
		contentPane.add(intLabel);
		
		JButton editGeneral = new JButton("EDIT");
		editGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generalFrame genFrame = new generalFrame();
				genFrame.setVisible(true);
			}
		});
		editGeneral.setForeground(new Color(255, 255, 255));
		editGeneral.setBackground(new Color(60, 179, 113));
		editGeneral.setFont(new Font("Tahoma", Font.BOLD, 12));
		editGeneral.setBounds(472, 101, 89, 23);
		contentPane.add(editGeneral);
		
		JButton editProfile = new JButton("EDIT");
		editProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				profFrame professFrame = new profFrame();
				professFrame.setVisible(true);
			}
		});
		editProfile.setForeground(Color.WHITE);
		editProfile.setFont(new Font("Tahoma", Font.BOLD, 12));
		editProfile.setBackground(new Color(60, 179, 113));
		editProfile.setBounds(472, 153, 89, 23);
		contentPane.add(editProfile);
		
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
		editSkills.setForeground(Color.WHITE);
		editSkills.setFont(new Font("Tahoma", Font.BOLD, 12));
		editSkills.setBackground(new Color(60, 179, 113));
		editSkills.setBounds(472, 206, 89, 23);
		contentPane.add(editSkills);
		
		JButton editExperience = new JButton("EDIT");
		editExperience.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				experienceFrame expFrame = new experienceFrame();
				expFrame.setVisible(true);
			}
		});
		editExperience.setForeground(Color.WHITE);
		editExperience.setFont(new Font("Tahoma", Font.BOLD, 12));
		editExperience.setBackground(new Color(60, 179, 113));
		editExperience.setBounds(472, 261, 89, 23);
		contentPane.add(editExperience);
		
		JButton editEducation = new JButton("EDIT");
		editEducation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				educationFrame eduFrame = new educationFrame();
				eduFrame.setVisible(true);
			}
		});
		editEducation.setForeground(Color.WHITE);
		editEducation.setFont(new Font("Tahoma", Font.BOLD, 12));
		editEducation.setBackground(new Color(60, 179, 113));
		editEducation.setBounds(472, 316, 89, 23);
		contentPane.add(editEducation);
		
		JButton editFurther = new JButton("EDIT");
		editFurther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				furtherFrame furFrame = new furtherFrame();
				furFrame.setVisible(true);
			}
		});
		editFurther.setForeground(Color.WHITE);
		editFurther.setFont(new Font("Tahoma", Font.BOLD, 12));
		editFurther.setBackground(new Color(60, 179, 113));
		editFurther.setBounds(472, 373, 89, 23);
		contentPane.add(editFurther);
		
		JButton editAdditional = new JButton("EDIT");
		editAdditional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				additionalFrame addFrame = new additionalFrame();
				addFrame.setVisible(true);
			}
		});
		editAdditional.setForeground(Color.WHITE);
		editAdditional.setFont(new Font("Tahoma", Font.BOLD, 12));
		editAdditional.setBackground(new Color(60, 179, 113));
		editAdditional.setBounds(472, 427, 89, 23);
		contentPane.add(editAdditional);
		
		JButton editInterests = new JButton("EDIT");
		editInterests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interestsFrame intFrame = new interestsFrame();
				intFrame.setVisible(true);
			}
		});
		editInterests.setForeground(Color.WHITE);
		editInterests.setFont(new Font("Tahoma", Font.BOLD, 12));
		editInterests.setBackground(new Color(60, 179, 113));
		editInterests.setBounds(472, 482, 89, 23);
		contentPane.add(editInterests);
		
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
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setForeground(new Color(240, 248, 255));
		btnNewButton.setBounds(245, 543, 102, 38);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("(*)These fields must be filled");
		label.setForeground(Color.RED);
		label.setBounds(10, 597, 235, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("(*)");
		label_1.setForeground(Color.RED);
		label_1.setBounds(569, 105, 16, 15);
		contentPane.add(label_1);
	}
}

package FrontEnd;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BackEnd.CV;
import BackEnd.CVEditor;
import BackEnd.Section;
import Files.exportCVFrame;

public class Frame {
	public JFrame frmCvEditor;
	private JPanel contentPane;
	ArrayList<String> func = new ArrayList(); //All these arraylists are used for US4
	ArrayList<String> chron = new ArrayList();
	ArrayList<String> comb = new ArrayList();
	ArrayList<String> funct = new ArrayList();
	ArrayList<String> chront = new ArrayList();
	ArrayList<String> combt = new ArrayList();
	
/**
 * Launch the application.
 */
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Frame window = new Frame();
				window.frmCvEditor.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}

/**
 * Create the application.
 */
public Frame() {
	initialize();
}

/**
 * Initialize the contents of the frame.
 */

private void initialize() {
	frmCvEditor = new JFrame();
	frmCvEditor.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	frmCvEditor.setTitle("CV  Editor");
	frmCvEditor.getContentPane().setBackground(new Color(230, 230, 250));
	frmCvEditor.setBounds(100, 100, 1377, 745);
	frmCvEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmCvEditor.getContentPane().setLayout(null);
	
	JButton selectFunc = new JButton("Add New");
	selectFunc.setForeground(new Color(255, 255, 255));
	selectFunc.setFont(new Font("Tahoma", Font.BOLD, 11));
	selectFunc.setBackground(new Color(139, 0, 0));
	selectFunc.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			CV cv = new CV();
			Section s1 = new Section("2. PROFESSIONAL PROFILE");
			Section s2 = new Section("3. SKILLS AND EXPERIENCE");
			Section s3 = new Section("4. CAREER SUMMARY");
			Section s4 = new Section("5. EDUCATION AND TRAINING");
			Section s5 = new Section("6. FURTHER COURSES");
			Section s6 = new Section("7. ADDITIONAL INFORMATION");
			Section s7 = new Section("8. INTERESTS");
			cv.addSection(s1);
			cv.addSection(s2);
			cv.addSection(s3);
			cv.addSection(s4);
			cv.addSection(s5);
			cv.addSection(s6);
			cv.addSection(s7);
			CVEditor.setCV(cv);
			CVEditor.setCvForUpdate(0);
			CVEditor.setIndex(99);
			functFrame func = new functFrame();
			func.setVisible(true);
		}
	});
	selectFunc.setBounds(442, 157, 105, 25);
	frmCvEditor.getContentPane().add(selectFunc);
	
	JLabel funcLabel = new JLabel("Functional CV Template:");
	funcLabel.setForeground(SystemColor.desktop);
	funcLabel.setFont(new Font("Dialog", Font.BOLD, 15));
	funcLabel.setBounds(129, 149, 261, 37);
	frmCvEditor.getContentPane().add(funcLabel);
	
	JButton selectChron = new JButton("Add New");
	selectChron.setFont(new Font("Tahoma", Font.BOLD, 11));
	selectChron.setForeground(new Color(255, 255, 255));
	selectChron.setBackground(new Color(128, 0, 0));
	selectChron.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			CV cv = new CV(); //dimiourgo CV me ta antistoiha sections tou
			Section s1 = new Section("2. PROFESSIONAL PROFILE");
			Section s2 = new Section("3. CORE STRENGTHS");
			Section s3 = new Section("4. PROFESSIONAL EXPERIENCE");
			Section s4 = new Section("5. EDUCATION AND TRAINING");
			Section s5 = new Section("6. FURTHER COURSES");
			Section s6 = new Section("7. ADDITIONAL INFORMATION");
			Section s7 = new Section("8. INTERESTS");
			cv.addSection(s1);
			cv.addSection(s2);
			cv.addSection(s3);
			cv.addSection(s4);
			cv.addSection(s5);
			cv.addSection(s6);
			cv.addSection(s7);
			CVEditor.setCV(cv); //to theto os to temporary CV gia na to symplirosei o hristis 
			CVEditor.setCvForUpdate(0); //Dimiourgo neo CV kai den eimai se Update mode
			CVEditor.setIndex(99);
			chronFrame chron = new chronFrame();
			chron.setVisible(true);	
		}
	});
	selectChron.setBounds(442, 260, 105, 25);
	frmCvEditor.getContentPane().add(selectChron);
	
	JButton selectComb = new JButton("Add New");
	selectComb.setFont(new Font("Tahoma", Font.BOLD, 11));
	selectComb.setBackground(new Color(128, 0, 0));
	selectComb.setForeground(new Color(255, 255, 255));
	selectComb.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			CV cv = new CV();
			Section s1 = new Section("2. PROFESSIONAL PROFILE");
			Section s2 = new Section("3. SKILLS AND EXPERIENCE");
			Section s3 = new Section("4. PROFESSIONAL EXPERIENCE");
			Section s4 = new Section("5. EDUCATION AND TRAINING");
			Section s5 = new Section("6. FURTHER COURSES");
			Section s6 = new Section("7. ADDITIONAL INFORMATION");
			Section s7 = new Section("8. INTERESTS");
			cv.addSection(s1);
			cv.addSection(s2);
			cv.addSection(s3);
			cv.addSection(s4);
			cv.addSection(s5);
			cv.addSection(s6);
			cv.addSection(s7);
			CVEditor.setCV(cv);
			CVEditor.setCvForUpdate(0);
			CVEditor.setIndex(99);
			combFrame comb = new combFrame();
			comb.setVisible(true);
		}
	});
	selectComb.setBounds(442, 355, 105, 25);
	frmCvEditor.getContentPane().add(selectComb);
	
	JLabel topLabel = new JLabel("WELCOME TO CV EDITOR ");
	topLabel.setBackground(new Color(128, 0, 0));
	topLabel.setFont(new Font("Dialog", Font.BOLD, 30));
	topLabel.setForeground(new Color(0, 0, 128));
	topLabel.setBounds(173, 30, 429, 50);
	frmCvEditor.getContentPane().add(topLabel);
	
	JLabel chronLabel = new JLabel("Chronological CV Template:");
	chronLabel.setFont(new Font("Dialog", Font.BOLD, 15));
	chronLabel.setForeground(SystemColor.desktop);
	chronLabel.setBounds(129, 245, 281, 50);
	frmCvEditor.getContentPane().add(chronLabel);
	
	JLabel combLabel = new JLabel("Combined CV Template:");
	combLabel.setFont(new Font("Dialog", Font.BOLD, 15));
	combLabel.setForeground(SystemColor.desktop);
	combLabel.setBounds(129, 340, 200, 50);
	frmCvEditor.getContentPane().add(combLabel);
	
	JButton btnUpdateExisting_2 = new JButton("Select");
	btnUpdateExisting_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(CVEditor.getCVs().isEmpty()){
				JOptionPane.showMessageDialog(null, "There is no CV to update!");
			}
			else{
				String s;
				for(int i=1; i<=CVEditor.getCVs().size(); i++){
			    	s=CVEditor.getCVs().get(i-1).getName();
			    	System.out.println(s);
			    }
				CVEditor.setCvForUpdate(1);
				updateCVFrame frame = new updateCVFrame();
				frame.setVisible(true);
			}
		}
	});
	btnUpdateExisting_2.setForeground(Color.WHITE);
	btnUpdateExisting_2.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnUpdateExisting_2.setBackground(Color.BLUE);
	btnUpdateExisting_2.setBounds(442, 442, 105, 25);
	frmCvEditor.getContentPane().add(btnUpdateExisting_2);
	
	JLabel lblNewLabel = new JLabel("Update Existing CVs:");
	lblNewLabel.setForeground(SystemColor.desktop);
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblNewLabel.setBounds(129, 447, 174, 20);
	frmCvEditor.getContentPane().add(lblNewLabel);
	
	JLabel lblExportExistingCvs = new JLabel("Export Existing CVs:");
	lblExportExistingCvs.setForeground(SystemColor.desktop);
	lblExportExistingCvs.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblExportExistingCvs.setBounds(129, 523, 174, 20);
	frmCvEditor.getContentPane().add(lblExportExistingCvs);
	
	JButton btnEnter = new JButton("Enter");
	btnEnter.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(CVEditor.getCVs().isEmpty()){
				JOptionPane.showMessageDialog(null, "There is no CV to export!");
			}
			else{
				JOptionPane.showMessageDialog(null, "WARNING: You can only export CVs that have all their fields filled in.");
				String s;
				for(int i=1; i<=CVEditor.getCVs().size(); i++){
			    	s=CVEditor.getCVs().get(i-1).getName();
			    } 
				exportCVFrame frame = new exportCVFrame();
				frame.setVisible(true);
			}
			
		}
	});
	btnEnter.setForeground(Color.WHITE);
	btnEnter.setFont(new Font("Tahoma", Font.BOLD, 11));
	btnEnter.setBackground(new Color(46, 139, 87));
	btnEnter.setBounds(442, 522, 105, 25);
	frmCvEditor.getContentPane().add(btnEnter);
	
	
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));
	JFileChooser fc = new JFileChooser(new File("/usr/home/students/stud13/cse32615/workspace/SoftwareEngineering"));
	

    JButton btnNewButton = new JButton("Open");
    btnNewButton.setForeground(new Color(255, 255, 255));
    btnNewButton.setBackground(new Color(95, 158, 160));

    btnNewButton.addActionListener(
    new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int j=0,k=0,l=0;
                if (e.getSource() == btnNewButton) {
            	func.add("1. GENERAL INFORMATION");
        		func.add("2. PROFESSIONAL PROFILE");
        		func.add("3. SKILLS AND EXPERIENCE");
        		func.add("4. CAREER SUMMARY");
        		func.add("5. EDUCATION AND TRAINING");
        		func.add("6. FURTHER COURSES");
        		func.add("7. ADDITIONAL INFORMATION");
        		func.add("8. INTERESTS");
        		
        		chron.add("1. GENERAL INFORMATION");
        		chron.add("2. PROFESSIONAL PROFILE");
        		chron.add("3. CORE STRENGTHS");
        		chron.add("4. PROFESSIONAL EXPERIENCE");
        		chron.add("5. EDUCATION AND TRAINING");
        		chron.add("6. FURTHER COURSES");
        		chron.add("7. ADDITIONAL INFORMATION");
        		chron.add("8. INTERESTS");
        		
        		comb.add("1. GENERAL INFORMATION");
        		comb.add("2. PROFESSIONAL PROFILE");
        		comb.add("3. SKILLS AND EXPERIENCE");
        		comb.add("4. PROFESSIONAL EXPERIENCE");
        		comb.add("5. EDUCATION AND TRAINING");
        		comb.add("6. FURTHER COURSES");
        		comb.add("7. ADDITIONAL INFORMATION");
        		comb.add("8. INTERESTS");
        		
        		funct.add("\\section{1. GENERAL INFORMATION}");
        		funct.add("\\section{2. PROFESSIONAL PROFILE}");
        		funct.add("\\section{3. SKILLS AND EXPERIENCE}");
        		funct.add("\\section{4. CAREER SUMMARY}");
        		funct.add("\\section{5. EDUCATION AND TRAINING}");
        		funct.add("\\section{6. FURTHER COURSES}");
        		funct.add("\\section{7. ADDITIONAL INFORMATION}");
        		funct.add("\\section{8. INTERESTS}");
        		
        		chront.add("\\section{1. GENERAL INFORMATION}");
        		chront.add("\\section{2. PROFESSIONAL PROFILE}");
        		chront.add("\\section{3. CORE STRENGTHS}");
        		chront.add("\\section{4. PROFESSIONAL EXPERIENCE}");
        		chront.add("\\section{5. EDUCATION AND TRAINING}");
        		chront.add("\\section{6. FURTHER COURSES}");
        		chront.add("\\section{7. ADDITIONAL INFORMATION}");
        		chront.add("\\section{8. INTERESTS}");
        		
        		combt.add("\\section{1. GENERAL INFORMATION}");
        		combt.add("\\section{2. PROFESSIONAL PROFILE}");
        		combt.add("\\section{3. SKILLS AND EXPERIENCE}");
        		combt.add("\\section{4. PROFESSIONAL EXPERIENCE}");
        		combt.add("\\section{5. EDUCATION AND TRAINING}");
        		combt.add("\\section{6. FURTHER COURSES}");
        		combt.add("\\section{7. ADDITIONAL INFORMATION}");
        		combt.add("\\section{8. INTERESTS}");
                	
                int returnVal = fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    String path = fc.getSelectedFile().getPath();
					String[] p = path.split("/");
					ArrayList<String> lines = new ArrayList();
					if(p[p.length-1].contains("txt") || p[p.length-1].contains("tex")){
						try {
							for (String line : Files.readAllLines(Paths.get(p[p.length-1]))) {
								lines.add(line);
							}
							
							for(int i=0; i<8; i++){

								if(lines.contains(func.get(i)) ||lines.contains(funct.get(i))){
									j+=1;
								}else if(lines.contains(chron.get(i))||lines.contains(chront.get(i))){
									k+=1;
								}else if(lines.contains(comb.get(i))||lines.contains(combt.get(i))){
									l+=1;
								}			
							}
							try{
								Desktop.getDesktop().open(new File(path));
						      } catch (Exception ex) {
						        ex.printStackTrace();
						      }

							if(j==7){
								JOptionPane.showMessageDialog(null,"You are opening a Functional CV");
							}else if(k==7){
								JOptionPane.showMessageDialog(null,"You are opening a Chronological CV");
							}else if(l==7){
								JOptionPane.showMessageDialog(null,"You are opening a Combined CV");
							}else{
								JOptionPane.showMessageDialog(null,"The Selected File does not follow one of the supported templates");
								
							}
						}
						catch (IOException e1) {
							e1.printStackTrace();
						}
						
					}else{
						JOptionPane.showMessageDialog(null,"The Selected File is not valid");
					}
                    System.out.println(file.getName());
                }
                    }
                    }
                    }
                    );
    //contentPane.add(btnNewButton, BorderLayout.WEST);
	btnNewButton.setBounds(442, 588, 105, 25);
	frmCvEditor.getContentPane().add(btnNewButton);
	
	JLabel lblOpenCvDocument = new JLabel("Open CV Document:");
	lblOpenCvDocument.setForeground(SystemColor.desktop);
	lblOpenCvDocument.setFont(new Font("Dialog", Font.BOLD, 14));
	lblOpenCvDocument.setBounds(129, 593, 174, 20);
	frmCvEditor.getContentPane().add(lblOpenCvDocument);
	
	JLabel lblCompareTwoCvs = new JLabel("Compare two CVs:");
	lblCompareTwoCvs.setForeground(SystemColor.desktop);
	lblCompareTwoCvs.setFont(new Font("Dialog", Font.BOLD, 14));
	lblCompareTwoCvs.setBounds(129, 656, 174, 20);
	frmCvEditor.getContentPane().add(lblCompareTwoCvs);
	
	JButton button = new JButton("Select");
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			compareCVFrame f = new compareCVFrame();
			f.setVisible(true);
		}
	});
	button.setForeground(Color.WHITE);
	button.setFont(new Font("Dialog", Font.BOLD, 11));
	button.setBackground(new Color(46, 139, 87));
	button.setBounds(442, 654, 105, 25);
	frmCvEditor.getContentPane().add(button);
}
}

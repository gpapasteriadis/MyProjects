package Files;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BackEnd.CVEditor;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class exportCVFrame extends JFrame {

	private JPanel contentPane;
	private JTextField numberField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					exportCVFrame frame = new exportCVFrame();
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
	public exportCVFrame() {
		setTitle("Export CVs");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 751, 367);
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
		
		JLabel lblNewLabel = new JLabel("These are the existing CVs, choose one to export:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(31, 11, 419, 33);
		contentPane.add(lblNewLabel);
		
		numberField = new JTextField();
		numberField.setBackground(new Color(255, 255, 255));
		numberField.setBounds(242, 106, 91, 29);
		contentPane.add(numberField);
		numberField.setColumns(10);
		String text="";
	    for(int i=1; i<=CVEditor.getCVs().size(); i++){
	    	String s=CVEditor.getCVs().get(i-1).getName();
	        text += i+")"+" " +s+ "\r\n";
	    } 
	    CVsField.setText(text);
		JButton btnOk = new JButton("Plain Text");
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
					try{
					    PrintWriter writer = new PrintWriter(CVEditor.getCVs().get(CVEditor.getIndex()-1).getName()+".txt", "UTF-8");
					    writer.println("1. GENERAL INFORMATION");
					    writer.println("Name: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getName());
					    writer.println("Address: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getAddress());
					    writer.print("Telephone (Home): "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getTeleHome());
					    writer.println("\t(Mobile): "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getTeleMobile());
					    writer.println("E-Mail: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getEmail()+"\n");
					    writer.println("2. PROFESSIONAL PROFILE");
					    if(CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(0).getParagraph().getContents() != null){
						    String[] words = CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(0).getParagraph().getContents().split(" ");
						    int c=0;
						    for (String word: words){
						   		writer.print(word);
						   		writer.print(" ");
						   		c+=1;
						    	if (c==10){
						    		writer.println();
						    		c=0;
						    	}
						    }
					    }else{
					    }
					    writer.println("\n");
					    if(CVEditor.getCV().getSections().get(1).getTitle()=="3. SKILLS AND EXPERIENCE") //functional or combined
						{
					    	writer.println("3. SKILLS AND EXPERIENCE");
					    	for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getBulletList().size(); i++){
					    		writer.println("\t3. "+(i+1)+". SKILLS AND EXPERIENCE ON "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getBulletList().get(i).getContent1());
					    		for(int j=0; j<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getBulletList().get(i).getBulletList().size(); j++){
					    			writer.println("\t\t� "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getBulletList().get(i).getBulletList().get(j).getContents());
					    		}
					    	}
						}else{                                                                         //chronological
							writer.println("3. CORE STRENGTHS");
					    	if(CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getParagraph().getContents() != null){
						    	String[] words1 = CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getParagraph().getContents().split(" ");
							    int k=0;
							    for (String word: words1){
							   		writer.print(word);
							   		writer.print(" ");
							   		k+=1;
							    	if (k==10){
							    		writer.println();
							    		k=0;
							    	}
							    }
					    	}
						}
					    
					    writer.println("\n");
					    if(CVEditor.getCV().getSections().get(1).getTitle()=="3. SKILLS AND EXPERIENCE")
						{
							if(CVEditor.getCV().getSections().get(2).getTitle()=="4. CAREER SUMMARY") //functional
							{
								writer.println("4. CARRER SUMMARY");
						    	for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().size(); i++){
						    		writer.println("\t� Company Name: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent1()+", Job Title: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent2()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getDate());
						    	}
							}else{                                                                    //combined
								writer.println("4. PROFESSIONAL EXPERIENCE");
						    	for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().size(); i++){
						    		writer.println("\t� Company Name: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent1()+", Job Title: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent2()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getDate());
						    		writer.println("\t \t� "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent3());
						    		writer.println("\t \t�  List of achievements");
						    		for(int j=0; j<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getBulletList().size(); j++){
						    			writer.println("\t \t \t�	Achievement "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getBulletList().get(j).getContents());
						    		}
						    	}
							}
						}else{                                                                       //chronological
							writer.println("4. PROFESSIONAL EXPERIENCE");
					    	for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().size(); i++){
					    		writer.println("\t� Company Name: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent1()+", Job Title: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent2()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getDate());
					    		writer.println("\t \t� "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent3());
					    		writer.println("\t \t�  List of achievements");
					    		for(int j=0; j<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getBulletList().size(); j++){
					    			writer.println("\t \t \t�	Achievement "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getBulletList().get(j).getContents());
					    		}
					    	}
						}
					    
					   
					    writer.println("\n");
					    writer.println("5. EDUCATION AND TRAINING");
					    for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().size(); i++){
					    	writer.println("\t� Qualification: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().get(i).getContent1()+", Establishment: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().get(i).getContent2()+", Location: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().get(i).getContent3()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().get(i).getDate());
					    }
					    writer.println("\n");
					    writer.println("6. FURTHER COURSES");
					    for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().size(); i++){
					    	writer.println("\t� Course: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().get(i).getContent1()+", Establishment: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().get(i).getContent2()+", Location: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().get(i).getContent3()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().get(i).getDate());
					    }
					    writer.println("\n");
					    writer.println("7. ADDITIONAL INFORMATION");
					    if(CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(5).getParagraph().getContents() != null){
					    	String[] words1 = CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(5).getParagraph().getContents().split(" ");
						    int k=0;
						    for (String word: words1){
						   		writer.print(word);
						   		writer.print(" ");
						   		k+=1;
						    	if (k==10){
						    		writer.println();
						    		k=0;
						    	}
						    }
				    	}
					    writer.println("\n");
					    writer.println("8. INTERESTS");
					    if(CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(6).getParagraph().getContents()  != null){
					    	String[] words1 = CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(6).getParagraph().getContents().split(" ");
						    int k=0;
						    for (String word: words1){
						   		writer.print(word);
						   		writer.print(" ");
						   		k+=1;
						    	if (k==10){
						    		writer.println();
						    		k=0;
						    	}
						    }
				    	}
					    writer.close();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "CV couldn't be exported!");
						return;
					}
					JOptionPane.showMessageDialog(null, "CV exported succesfully!");
					return;
				}
				
			}
		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOk.setBackground(new Color(0, 128, 0));
		btnOk.setBounds(380, 104, 110, 33);
		contentPane.add(btnOk);
		
		JLabel lblEnterTheNumber_1 = new JLabel("Enter the number of CV:");
		lblEnterTheNumber_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterTheNumber_1.setBounds(242, 70, 231, 25);
		contentPane.add(lblEnterTheNumber_1);
		
		JButton btnLatex = new JButton("LATEX");
		btnLatex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					try{
					    PrintWriter writer = new PrintWriter(CVEditor.getCVs().get(CVEditor.getIndex()-1).getName()+".tex", "UTF-8");
					    writer.println("\\documentclass{10pt}{article}");
					    writer.println();
					    writer.println("\\begin{document}");
					    writer.println("\\section{1. GENERAL INFORMATION}");
					    writer.println("Name: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getName()+"\n");
					    writer.println("Address: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getAddress()+"\n");
					    writer.print("Telephone (Home): "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getTeleHome());
					    writer.println("(Mobile): "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getTeleMobile()+"\n");
					    writer.println("E-Mail: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getEmail()+"\n");
					    writer.println("\\section{2. PROFESSIONAL PROFILE}");
					    if(CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(0).getParagraph().getContents() != null){
						    String[] words = CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(0).getParagraph().getContents().split(" ");
						    int c=0;
						    for (String word: words){
						   		writer.print(word);
						   		writer.print(" ");
						   		c+=1;
						    	if (c==10){
						    		writer.println();
						    		c=0;
						    	}
						    }
					    }
					    writer.println("\n");
					    if(CVEditor.getCV().getSections().get(1).getTitle()=="3. SKILLS AND EXPERIENCE") //functional or combined
						{
					    	writer.println("\\section{3. SKILLS AND EXPERIENCE}");
					    	for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getBulletList().size(); i++){
					    		writer.println("\\hspace{10mm} \\subsection{3."+(i+1)+". SKILLS AND EXPERIENCE ON "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getBulletList().get(i).getContent1()+"}\n");
					    		for(int j=0; j<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getBulletList().get(i).getBulletList().size(); j++){
					    			writer.println("\\hspace{20mm}\\subsection{"+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getBulletList().get(i).getBulletList().get(j).getContents()+"}\n");
					    		}
					    	}
						}else{                                                                         //chronological
					    	writer.println("\\section{3. CORE STRENGTHS}");
					    	if(CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getParagraph().getContents() != null){
						    	String[] words1 = CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(1).getParagraph().getContents().split(" ");
							    int k=0;
							    for (String word: words1){
							   		writer.print(word);
							   		writer.print(" ");
							   		k+=1;
							    	if (k==10){
							    		writer.println();
							    		k=0;
							    	}
							    }
					    	}
						}
					    
					    writer.println("\n");
					    if(CVEditor.getCV().getSections().get(1).getTitle()=="3. SKILLS AND EXPERIENCE")
						{
							if(CVEditor.getCV().getSections().get(2).getTitle()=="4. CAREER SUMMARY") //functional
							{
						    	writer.println("\\section{4. CARRER SUMMARY}");
						    	for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().size(); i++){
						    		writer.println("\\hspace{10mm} \\subsection{Company Name: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent1()+", Job Title: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent2()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getDate()+"}\n");
						    	}
							}else{                                                                    //combined
						    	writer.println("\\section{4. PROFESSIONAL EXPERIENCE}");
						    	for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().size(); i++){
						    		writer.println("\\hspace{10mm} \\subsection{Company Name: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent1()+", Job Title: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent2()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getDate()+"}\n");
						    		writer.println("\\hspace{10mm}\\hspace{10mm} \\subsection{Paragraph of Responsibilities: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent3()+"}\n");
						    		writer.println("\\hspace{10mm}\\hspace{10mm} \\subsection{List of achievements:}\n");
						    		for(int j=0; j<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getBulletList().size(); j++){
						    			writer.println("\\hspace{20mm}\\hspace{10mm} \\subsection{Achievement "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getBulletList().get(j).getContents()+"}\n");
						    		}
						    	}
							}
						}else{                                                                       //chronological
							writer.println("\\section{4. PROFESSIONAL EXPERIENCE}");
					    	for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().size(); i++){
					    		writer.println("\\hspace{10mm} \\subsection{Company Name: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent1()+", Job Title: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent2()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getDate()+"}\n");
					    		writer.println("\\hspace{10mm}\\hspace{10mm} \\subsection{Paragraph of Responsibilities: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getContent3()+"}\n");
					    		writer.println("\\hspace{10mm}\\hspace{10mm} \\subsection{List of achievements:}\n");
					    		for(int j=0; j<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getBulletList().size(); j++){
					    			writer.println("\\hspace{20mm}\\hspace{10mm} \\subsection{Achievement "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(2).getBulletList().get(i).getBulletList().get(j).getContents()+"}\n");
					    		}
					    	}
						}
					    
					    writer.println("\n");
					    writer.println("\\section{5. EDUCATION AND TRAINING}");
					    for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().size(); i++){
					    	writer.println("\\hspace{10mm} \\subsection{Qualification: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().get(i).getContent1()+", Establishment: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().get(i).getContent2()+", Location: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().get(i).getContent3()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(3).getBulletList().get(i).getDate()+"}\n");
					    }
					    writer.println("\n");
					    writer.println("\\section{6. FURTHER COURSES}");
					    for(int i=0; i<CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().size(); i++){
					    	writer.println("\\hspace{10mm} \\subsection{Course: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().get(i).getContent1()+", Establishment: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().get(i).getContent2()+", Location: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().get(i).getContent3()+", Date: "+CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(4).getBulletList().get(i).getDate()+"}\n");
					    }
					    writer.println("\n");
					    writer.println("\\section{7. ADDITIONAL INFORMATION}");
					    if(CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(5).getParagraph().getContents() != null){
					    	String[] words1 = CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(5).getParagraph().getContents().split(" ");
						    int k=0;
						    for (String word: words1){
						   		writer.print(word);
						   		writer.print(" ");
						   		k+=1;
						    	if (k==10){
						    		writer.println();
						    		k=0;
						    	}
						    }
				    	}
					    writer.println("\n");
					    writer.println("\\section{8. INTERESTS}");
					    if(CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(6).getParagraph().getContents() != null){
					    	String[] words1 = CVEditor.getCVs().get(CVEditor.getIndex()-1).getSections().get(6).getParagraph().getContents().split(" ");
						    int k=0;
						    for (String word: words1){
						   		writer.print(word);
						   		writer.print(" ");
						   		k+=1;
						    	if (k==10){
						    		writer.println();
						    		k=0;
						    	}
						    }
				    	}
					    writer.println("\\end{document}");
					    writer.close();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "CV couldn't be exported!");
						return;
					}
					JOptionPane.showMessageDialog(null, "CV exported succesfully!");
					return;
				}
			
			}
		});
		btnLatex.setForeground(Color.WHITE);
		btnLatex.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLatex.setBackground(new Color(0, 128, 0));
		btnLatex.setBounds(528, 104, 110, 33);
		contentPane.add(btnLatex);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBackground(new Color(0, 128, 0));
		btnExit.setBounds(568, 216, 110, 33);
		contentPane.add(btnExit);

	}
}

package src.frontend;
import java.io.FileOutputStream;  
import java.io.IOException;



import org.apache.poi.xslf.usermodel.XMLSlideShow;

import src.poiExtractor.*;

import java.util.ArrayList; 
import java.util.Scanner;

public abstract class Page{
	protected String date;
	protected ArrayList<String> key = new ArrayList();
	

	abstract String getType();
	abstract String getTitle();
	abstract String getText();
	abstract String getPath();
	abstract String getDate();
	abstract ArrayList<String> getKey();
	abstract void setTitle(String x);
	abstract void setText(String x);
	abstract void setPath(String x);
	abstract void setDate(String x);
	abstract void setKey(ArrayList<String> key);
	
	
	public void EditPage(){

		if(this.getType()=="Textpage"){
			
			Scanner in = new Scanner(System.in);
			System.out.println("You can change: Title,Text And Date.");
			System.out.println("Title: "+getTitle() +"\n Text: "+getText()+"\n Date: "+getDate());
			System.out.println("Do you want to change the Title? YES <Press 1> | NO <Press 0> :");
		    int input20=readInteger();
			while(input20!=1 && input20!=0){
				System.out.println("Invalid Input! YES <Press 1> | NO <Press 0> :");
			    input20=readInteger();
			}
			if(input20==1){
				System.out.println("Type new Title: ");
				String input1=in.next();	
				setTitle(input1);
			}else {
					//do nothing
			}
			
			Scanner in1 = new Scanner(System.in);
			System.out.println("Do you want to change the Text? YES <Press 1> | NO <Press 0> :");
		    int input21=readInteger();
			while(input21!=1 && input21!=0){
				System.out.println("Invalid Input! YES <Press 1> | NO <Press 0> :");
				input21=in1.nextInt();
			}
			if(input21==1){
				System.out.println("Type new Text: ");	
				String input2=in1.next();
				setText(input2);
			}else {
					//do nothing
			}
			
			Scanner in2 = new Scanner(System.in);
			System.out.println("Do you want to change the Date? YES <Press 1> | NO <Press 0> :");
		    int input22=readInteger();
			while(input22!=1 && input22!=0){
				System.out.println("Invalid Input! YES <Press 1> | NO <Press 0> :");
			    input22=readInteger();
			}
			if(input22==1){
				System.out.println("Type new Date: ");
				String input3=in2.next();	
				setDate(input3);
			}else {
					//do nothing
			}
		}
		else if(this.getType()=="Photopage"){
			
			Scanner in = new Scanner(System.in);
			System.out.println("You can change: Title,Path And Date.");
			System.out.println("Title: "+getTitle() +"\n Path: "+getPath()+"\n Date: "+getDate());
			System.out.println("Do you want to change the Title? YES <Press 1> | NO <Press 0> :");
		    int input20=readInteger();
			while(input20!=1 && input20!=0){
				System.out.println("Invalid Input! YES <Press 1> | NO <Press 0> :");
			    input20=readInteger();
			}
			if(input20==1){
				System.out.println("Type new Title: ");
				String input1=in.next();	
				setTitle(input1);
			}else{
					//do nothing
			}
			
			Scanner in1 = new Scanner(System.in);
			System.out.println("Do you want to change the Path? YES <Press 1> | NO <Press 0> :");
		    int input21=readInteger();
			while(input21!=1 && input21!=0){
				System.out.println("Invalid Input! YES <Press 1> | NO <Press 0> :");
			    input21=readInteger();
			}
			if(input21==1){
				System.out.println("Type new Path: ");	
				String input2=in1.next();
				setPath(input2);
			}else{
					//do nothing
			}
			
			Scanner in2 = new Scanner(System.in);
			System.out.println("Do you want to change the Date? YES <Press 1> | NO <Press 0> :");
		    int input22=readInteger();
			while(input22!=1 && input22!=0){
				System.out.println("Invalid Input! YES <Press 1> | NO <Press 0> :");
			    input22=readInteger();
			}
			if(input22==1){
				System.out.println("Type new Date: ");
				String input3=in2.next();	
				setDate(input3);
			}else {
					//do nothing
			}
		}
		else if(this.getType()=="Titlepage"){
			Scanner in = new Scanner(System.in);

			System.out.println("You can change: Title And Date.");
			System.out.println("Title: "+getTitle() +"\n Date: "+getDate());
			System.out.println("Do you want to change the Title? YES <Press 1> | NO <Press 0> :");
		    int input20=readInteger();
			while(input20!=1 && input20!=0){
				System.out.println("Invalid Input! YES <Press 1> | NO <Press 0> :");
			    input20=readInteger();
			}
			if(input20==1){
				System.out.println("Type new Title: ");
				String input1=in.next();	
				setTitle(input1);
			}else {
					//do nothing
			}
			
			Scanner in1 = new Scanner(System.in);
			System.out.println("Do you want to change the Date? YES <Press 1> | NO <Press 0> :");
		    int input21=readInteger();
			while(input21!=1 && input21!=0){
				System.out.println("Invalid Input! YES <Press 1> | NO <Press 0> :");
			    input21=readInteger();
			}
			if(input21==1){
				System.out.println("Type new Date: ");
				String input3=in1.next();	
				setDate(input3);
			}else{
					//do nothing
			}
		}
		else if(this.getType()=="Emptypage"){
			Scanner in = new Scanner(System.in);

			System.out.println("You can change: The Date.");
			System.out.println("Date: "+getDate());
			System.out.println("Do you want to change the Date? YES <Press 1> | NO <Press 0> :");
		    int input20=readInteger();
			while(input20!=1 && input20!=0){
				System.out.println("Invalid Input! YES <Press 1> | NO <Press 0> :");
			    input20=readInteger();
			}
			if(input20==1){
				System.out.println("Type new Date: ");
				String input3=in.next();	
				setDate(input3);
			}else {
					//do nothing
			}

		}
	}
	
	
	public int readInteger(){
		Scanner input=new Scanner(System.in);
		boolean flag=false;
		while (!flag){
			int value=0;
				try {
					String x=input.nextLine();
					value = Integer.parseInt(x);	    			
			    	return value;
				} catch (Exception e) { }
				System.out.println("Error: Invalid Input!");

		}
    	return 0;
			
	}

	
}
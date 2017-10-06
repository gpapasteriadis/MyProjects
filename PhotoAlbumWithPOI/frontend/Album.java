package src.frontend;
import java.io.FileOutputStream; 
import java.io.IOException;



import org.apache.poi.xslf.usermodel.XMLSlideShow;

import src.poiExtractor.*;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Album {
	
	protected ArrayList<Page> pages = new ArrayList<Page>(); 
	public ArrayList<Page> daterow = new ArrayList();
	protected ArrayList<IPageExtractorToPoi> POIpages = new ArrayList<IPageExtractorToPoi>(); 
	protected ArrayList<IPageExtractorToPoi> timerowPOIpages = new ArrayList<IPageExtractorToPoi>(); 
	abstract String getName();
	
	public void editAlbum(){
		while(true){
				
			System.out.println("What do you want to do about this Album?");
			System.out.println("");
			System.out.println("Insert a new Page. <Press 1 >");
			System.out.println("Edit a Page. <Press 2 >");
			System.out.println("Delete a Page. <Press 3 >");
			System.out.println("Exit 'Edit Album'. <Press 0 > :");
				
			Scanner in=new Scanner(System.in);
		    int input=readInteger();
			while(input<0 || input>4){
				System.out.println("Invalid Input! Type 1 or 2 or 3 or 4!");
				input=in.nextInt();
			}
			if(input==0){
					break;
			}
			else if(input==1){
				System.out.println("What kind of Page do you want to Insert?");
				System.out.println("");
				System.out.println("Insert a new Empty Page. <Press 1 > ");
				System.out.println("Insert a new Title Page. <Press 2 > ");
				System.out.println("Insert a new Text Page. <Press 3 > ");
				System.out.println("Insert a new Photo Page. <Press 4 > :");
				System.out.println("Go Back. <Press 0 > :");

			    int input2=readInteger();
				while(input2<0 || input2>4){
					System.out.println("Invalid Input! Press 1 or 2 or 3 or 4! :");
					input2=readInteger();
					
				}
				if(input2==0){
					continue;
				}
				
				if(input2==1){
					System.out.println("Give Date (ex:19/12/1995) and Press <Enter> :");
					String input13=in.nextLine();
					ArrayList<String> key=new ArrayList();
					System.out.println("Give Keys. (Stop giving Keys by Press 0.) :");

					while(true){
						String input14=in.next();
						if(input14.equals("0")){
							break;
						}
						else{
							key.add(input14);
						}
					}
					Emptypages y = new Emptypages(input13, key);
					pages.add(y);
					BlankPageExtractor xy= new  BlankPageExtractor();
					POIpages.add(xy);
					
					System.out.println("A new Emptypage has been added succesfully! :");
    				System.out.println("");
    				

				}else if(input2==2){
					System.out.println("Give Title and Press <Enter> :");
					//in.nextLine();
					String input4=in.nextLine();
					System.out.println("Give Date (ex:19/12/1995) and Press <Enter> :");
					String input15=in.nextLine();
					ArrayList<String> key=new ArrayList();
					System.out.println("Give Keys.(Stop giving Keys by Press 0.) :");
					while(true){
						String input14=in.next();
						if(input14.equals("0")){
							break;
						}
						else{
							key.add(input14);
						}
					}
					Titlepages z = new Titlepages(input4,input15,key);
					pages.add(z);	
					TitlePageExtractor xy=new TitlePageExtractor(input4);
					POIpages.add(xy);
					System.out.println("A new Titlepage has been added succesfully!");
    				System.out.println("");
    				
    				
				}else if(input2==3){
					System.out.println("Give Title and Press <Enter> :");
					String input5=in.nextLine();
					System.out.println("Give Text and Press <Enter> :");
					String input6=in.nextLine();
					System.out.println("Give Date (ex:19/12/1995) and Press <Enter> :");
					String input7=in.nextLine();
					ArrayList<String> key=new ArrayList();
					System.out.println("Give Keys. (Stop giving Keys by Press 0. :");
					while(true){
						String input14=in.next();
						if(input14.equals("0")){
							break;
						}
						else{
							key.add(input14);
						}
					}
					Textpages w = new Textpages(input5,input6,input7,key);
					pages.add(w);
					ContentPageExtractor xy = new ContentPageExtractor(input5,input6);
					POIpages.add(xy);
					System.out.println("A new Textpage has been added successfully");
    				System.out.println("");
    				
    				
				}else if(input2==4){
					System.out.println("Give Title and Press <Enter> :");
					
					String input8=in.nextLine();
					System.out.println("Give Path (ex:/home/pictures/image.png) and Press <Enter> :");
					String input9=in.nextLine();
					System.out.println("Give Date (ex:19/12/1995) and Press <Enter> :");
					String input10=in.nextLine();
					ArrayList<String> key=new ArrayList();
					System.out.println("Give Keys. (Stop giving Keys by Press 0. ) :");
					while(true){
						String input14=in.next();
						if(input14.equals("0")){
							break;
						}
						else{
							key.add(input14);
						}
					}
					Photopages j = new Photopages(input8,input9,input10,key);
					pages.add(j);
					PicturePageWResizablePicExtractor pp1 = new PicturePageWResizablePicExtractor(input8, input9);
					POIpages.add(pp1);
					System.out.println("A new Photopage has been added succesfully!");
    				System.out.println("");
				}
			}
			else if(input==2){
				
					System.out.println("Pages of Album:");
					if(pages.isEmpty()){
						System.out.println("No Pages!");
						break;
					}else{
						for(int i=0;i<pages.size();i++){ 
							System.out.println(i+"."+pages.get(i).getType());
						}
					}
					System.out.println("Press the Number in front of the Page you want to Edit:");
					System.out.println("Go back. <Press -1>");
				    int input11=readInteger();
					if(input11==-1){ break;}
					while(input11>pages.size()|| input11<0){
						System.out.println("Invalid Input! Press a correct Number!");
						input11=in.nextInt();
					}
					Page r= pages.get(input11);
					r.EditPage();
					//exei ginei edit...ara prepei na bgaloume to slide ap to POI kai na baloume to kainourio
					pages.set(input11,r);
					System.out.println("Page Number "+input11+" has been Updated!");
    				System.out.println("");
    				
			}
			else if(input==3){
				System.out.println("Pages of Album:");
				if(pages.isEmpty()){
					System.out.println("No Pages!");
					break;
				}else{
					for(int i=0;i<pages.size();i++){ 
						System.out.println(i+"."+pages.get(i).getType());
					}
				}
				System.out.println("Press the Number in front of the Page you want to Delete");
				System.out.println("Go back. <Press -1>");
			    int input3=readInteger();
				if(input3==-1){ break;}

				while(input3<0 || input3>pages.size()){
					System.out.println("Invalid Input! Press a correct Number!");
					input3=in.nextInt();
				}
				pages.remove(input3);
				System.out.println("Page Number "+input3+" has been Deleted!");
				System.out.println("");
			}
			
		}
		
	}	

	public ArrayList<Page> Search(String key){
		
		ArrayList<Page> arrofpages = new ArrayList<Page>();
	
		
			for(int j=0;j<pages.size();j++){
				if(pages.get(j).getType().equals("Textpage")){
					
						if((pages.get(j).getKey().contains(key))){
							arrofpages.add(pages.get(j));
						}
				}
				else if(pages.get(j).getType().equals("Titlepage")){
				
						if((pages.get(j).getKey().contains(key))){
							arrofpages.add(pages.get(j));
						}
				}
				else if(pages.get(j).getType().equals("Photopage")){
						
					if((pages.get(j).getKey().contains(key))){
							arrofpages.add(pages.get(j));
						}
				}
				else if(pages.get(j).getType().equals("Emptypage")){
					
					if((pages.get(j).getKey().contains(key))){
						arrofpages.add(pages.get(j));
					}
				}
		
			}
		return arrofpages;
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

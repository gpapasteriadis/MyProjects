package src.frontend;

import java.util.*;   
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import static java.lang.System.out;

import org.apache.poi.xslf.usermodel.XMLSlideShow;

import src.poiExtractor.*;

public class controller {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final Date invalidDate = new Date(0);

	public static void main(String[] args) {
		
		ArrayList<Album> albums = new ArrayList();
	
	 	System.out.println("WELCOME TO PHOTOALBUM!!");
		System.out.println("Exit anytime. <Press -1> ");
		System.out.println(" ");	
	
	    mainloop:
	    while(true){
		    
			Scanner in = new Scanner(System.in);
	 		System.out.println("Show the list of Albums. <Press 1> ");
		   	System.out.println("Show the contents of an Album. <Press 2> ");
			System.out.println("Show the contents of an Album by Time Order. <Press 3> ");
		    System.out.println("Add a new Empty Album. <Press 4> ");
		    System.out.println("Edit an Album (Insert | Delete | Edit Page) <Press 5> ");
		    System.out.println("Add a new Album Dynamically by Search a Key. <Press 6> ");
		    System.out.println("Exit Program. <Press 0> ");	
		    System.out.println("");
		    
		    int input=readInteger();
			switch (input){

				case 0:
		    		System.out.println("I hope you Enjoyed");
					break mainloop;
				
				case 1:  
			    		for(int i=0;i<albums.size();i++){
			    			System.out.println((i+1)+". "+albums.get(i).getName());
			    		}
			    		System.out.println("");
			    		break;
				case 2:
					System.out.println("Albums: ");
					for(int i=0;i<albums.size();i++){
		    				System.out.println((i+1)+". "+albums.get(i).getName());
		    		}
					
					System.out.println("Press the Number in front of Album you want to see its contents: ");
					System.out.println("Go back. <Press 0> ");
				    int input1=readInteger();
				    if(input1==0) break;
		    		if(input1==-1) break mainloop;
	
		    		while((input1<0  || input1>albums.size())){
		    			System.out.println("Invalid number! Please insert number between'1' and "+albums.size());
		    			input1=readInteger();
		    		}
		    		//MADE BY ROW
			    	String s=albums.get(input1-1).getName();
			    	AlbumExtractor albumExtractor = new AlbumExtractor(s);
			    	for(int i=0;i<albums.get(input1-1).pages.size();i++){
			    		albumExtractor.addSlideExtractor(albums.get(input1-1).POIpages.get(i));
			    	}
			    	albumExtractor.exportPOISlideShow();
			    	System.out.println("Album you've chosen has beed ExportedToPOI,with its contents");
			    	System.out.println("");
		    		break;
		    		
		    		
				case 3:
						System.out.println("Albums: ");
	
		    			for(int i=0;i<albums.size();i++){
		    				System.out.println((i+1)+". " + albums.get(i).getName());
		    			}
						System.out.println("Press the Number in front of Album you want to see its contents by Time order: ");
					    int input0=readInteger();
			    		if(input0==-1) break mainloop;
			    		
			    		while((input0<0  || input0>albums.size())){
			    			System.out.println("Invalid Input! Please Press a number between '1' and "+albums.size());
			    			input0=readInteger();
			    		}
			    		System.out.println("Contents of ALbum (Showing by Time order): ");
			    		if(albums.get(input0-1).pages.size()==0){			    		
			    			
			    			System.out.println("There are not pages! Nothing to Show!");
			    			System.out.println("");
			    		}
			    		if(albums.get(input0-1).pages.size()==1){
			    			Album w=albums.get(input0-1);
					    	String str=albums.get(input0-1).getName();
					    	AlbumExtractor albumExt = new AlbumExtractor(str);
					    	for(int i=0;i<albums.get(input0-1).pages.size();i++){
					    		albumExt.addSlideExtractor(albums.get(input0-1).POIpages.get(i));
					    	}
					    	
					    	albumExt.exportPOISlideShow();
					    	System.out.println("Album you've chosen has beed ExportedToPOI,with it's contents");
					    	System.out.println("");
					    	break;
		    			}
			    		//ROW BY TIME
			    		ArrayList<Page> daterow = new ArrayList();
			    		int xr=albums.get(input0-1).pages.size();
		    			Date [] t =new Date[xr];
		    			
		    			for(int i=0;i<xr;i++){
		    				String w=albums.get(input0-1).pages.get(i).getDate();
		    				t[i]=fromString(w);
		    			}
		    			Arrays.sort(t);
		    			//Collections.reverse(Arrays.asList(t));
		    			for( Date tg: t ) {
		    	            print( tg );
		    	        }
		    			
		    			for(int j=0;j<albums.get(input0-1).pages.size();j++){
			    			String reportDate = dateFormat.format(t[j]);
		    				for(int i=0;i<albums.get(input0-1).pages.size();i++){
		    					if(albums.get(input0-1).pages.get(i).getDate().equals(reportDate)){
							    	daterow.add(albums.get(input0-1).pages.get(i));
		    					}
		    				}	
		    			}
		    			for(int i=0;i<albums.get(input0-1).pages.size();i++){
							if(daterow.get(i).getType()=="Emptypage"){
								BlankPageExtractor xy= new  BlankPageExtractor();
								albums.get(input0-1).timerowPOIpages.add(xy);
							}
							if(daterow.get(i).getType()=="Titlepage"){
								
								TitlePageExtractor xy=new TitlePageExtractor(daterow.get(i).getTitle());
								albums.get(input0-1).timerowPOIpages.add(xy);
							}
							if(daterow.get(i).getType()=="Textpage"){
								ContentPageExtractor xy = new ContentPageExtractor(daterow.get(i).getTitle(),daterow.get(i).getText());
								albums.get(input0-1).timerowPOIpages.add(xy);
							}
							if(daterow.get(i).getType()=="Photopage"){
								PicturePageWResizablePicExtractor pp1 = new PicturePageWResizablePicExtractor(daterow.get(i).getTitle(),daterow.get(i).getPath());
								albums.get(input0-1).timerowPOIpages.add(pp1);
							}
						}
		    			Album w=albums.get(input0-1);
				    	String str=albums.get(input0-1).getName();
				    	AlbumExtractor albumExt = new AlbumExtractor(str);
				    	for(int i=0;i<daterow.size();i++){
				    		albumExt.addSlideExtractor(albums.get(input0-1).timerowPOIpages.get(i));
				    	}
				    	albumExt.exportPOISlideShow();
				    	System.out.println("Album you've chosen has beed ExportedToPOI,with it's contents");
				    	System.out.println("");
				    			    			
		    			break;
				case 4:
		    			System.out.println("Type the name of the new Empty Album you want to Create: ");
	
		    			String input3 = in.next();
			    		if(input3.equals("-1")) break mainloop;
	
		    			BasicAlbum y = new BasicAlbum(input3);
		    			albums.add(y);
		    			System.out.println("A new Empty Album named "+input3+" has added!");
	    				System.out.println("");
	    				
	    				break;
	    				    				
				case 5:
					 	System.out.println("Albums: ");
	
			    		for(int i=0;i<albums.size();i++){
			    			System.out.println((i+1)+". "+albums.get(i).getName());
			    		}
						System.out.println("Press the Number in front of Album you want to Edit: ");
						
					    int input2=readInteger();
			    		if(input2==-1) break mainloop;
	
			    		while(input2>albums.size()){
							System.out.println("Invalid Input! Press a number between '1' and :"+albums.size());
							input2=readInteger();
			    		}
			    		albums.get(input2-1).editAlbum();
			    		System.out.println("");
			    		
			    		break;
			    					    		
				case 6:
					System.out.println("");
					String Key;
	
					System.out.println("Give Key: ");
					Key=in.next();
						
				
					ArrayList<Page> q = new ArrayList();
					
					System.out.println("Give the name of the Album you want to Create: ");
					String input17=in.next();
					
					DerivativeAlbum z = new DerivativeAlbum(input17);
					
					for(int i=0;i<albums.size();i++){
						q=albums.get(i).Search(Key);
						if(!q.isEmpty()){
							for(int a=0;a<q.size();a++){
								z.pages.add(q.get(a));
							}
						}
					}
					
					for(int i=0;i<z.pages.size();i++){
						if(z.pages.get(i).getType()=="Emptypage"){
							BlankPageExtractor xy= new  BlankPageExtractor();
							z.POIpages.add(xy);
						}
						if(z.pages.get(i).getType()=="Titlepage"){
							
							TitlePageExtractor xy=new TitlePageExtractor(z.pages.get(i).getTitle());
							z.POIpages.add(xy);
						}
						if(z.pages.get(i).getType()=="Textpage"){
							ContentPageExtractor xy = new ContentPageExtractor(z.pages.get(i).getTitle(),z.pages.get(i).getText());
							z.POIpages.add(xy);
						}
						if(z.pages.get(i).getType()=="Photopage"){
							PicturePageWResizablePicExtractor pp1 = new PicturePageWResizablePicExtractor(z.pages.get(i).getTitle(),z.pages.get(i).getPath());
							z.POIpages.add(pp1);
						}
					}
					albums.add(z);
	    			System.out.println("A new Album named "+input17+" has been created!");
    				System.out.println("");
    				
    				break;
    				
	    	}
	    }
	}
	public static int readInteger(){
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
	
	private static final Date fromString( String spec ) {
        try {
            return dateFormat.parse( spec );
        } catch( ParseException dfe ) {
            return invalidDate;
        }
    }
    private static final void print( Date date ) {
        if( date == invalidDate ) {
            out.println("Invalid date");
        } else {
            out.println( dateFormat.format( date ) );
        }
    }
	
	
}

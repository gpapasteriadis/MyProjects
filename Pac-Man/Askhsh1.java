import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Askhsh1 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		Square choosenSquare = new Square();
		ArrayList<String> openList = new ArrayList();
		ArrayList<String> closeList = new ArrayList();
		ArrayList<Square> openListobj = new ArrayList();
		ArrayList<Square> closeListobj = new ArrayList();
		
		/*int p=7;
		int N=10;
		int Sx = 3;
		int Sy = 3;
		int G1x = 5;
		int G1y = 6;
		int G2x = 6;
		int G2y = 6;*/
		
		System.out.println("Dwse to megethos tou plegmatos!");
		int N = in.nextInt();
		System.out.println("Dwse tis suntetagmenes tou S");
		System.out.print("Dwse to x tou S:");
		int Sx = in.nextInt();
		System.out.print("Dwse to y tou S:");
		int Sy = in.nextInt();
		System.out.println("Dwse tis suntetagmenes tou G1");
		System.out.print("Dwse to x tou G1:");
		int G1x = in.nextInt();
		System.out.print("Dwse to y tou G1:");
		int G1y = in.nextInt();
		System.out.println("Dwse tis suntetagmenes tou G2");
		System.out.print("Dwse to x tou G2:");
		int G2x = in.nextInt();
		System.out.print("Dwse to y tou G2:");
		int G2y = in.nextInt();
		System.out.print("Dwse to p, wste na xeis pithanothta 1/p na uparxei toixos se kapo:");
		int p = in.nextInt();
	
		String field[][] = new String[N][N];
		String goal1 = field[G1x][G1y];
		String goal2 = field[G2x][G2y];
		field[Sx][Sy] = "Start ";
		field[G1x][G1y] = "G1    ";
		field[G2x][G2y] = "G2    ";
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				if(field[i][j]=="Start " || field[i][j]=="G1    " || field[i][j]=="G2    "){
				}else{
					boolean val = rand.nextInt(p)==0;
					if(val == true){
						field[i][j]="Wall  ";
					}else{
						field[i][j]="_____ ";
					}
				}
			}
		}
		for(int i=0; i<N; i++){
			System.out.print("|");
			for(int j=0; j<N; j++){
				System.out.print(field[i][j]+", ");
			}
			System.out.println("|");
		}
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		Square sq1 = new Square(Sx,Sy);
		openListobj.add(sq1);
		choosenSquare = sq1;
		chooseSquare(Sx,Sy,G1x,G1y,field,openListobj,closeListobj,choosenSquare);     //~Code for Start box
		
		Collections.sort(openListobj, new Comparator<Square>()          //sort the openListobj
		{
			public int compare(Square sq1, Square sq2){
				return Double.valueOf(sq1.getf()).compareTo(sq2.getf());
			}
		});
		
		   for(Square sqr: openListobj){
			   System.out.println(sqr.getf());
		   }
		   if(!(openListobj.size()==0)){
			   Square sqr = openListobj.get(0); 
			   field[sqr.getx()][sqr.gety()]="  X   ";
			   choosenSquare = sqr;
			   closeListobj.add(sqr);
			   openListobj.remove(0);
		   }else{
			   System.out.println("Cant move to nowhere,because there are Walls0 :(");
			   return;
		   }
		   
		   for(int i=0; i<N; i++){
				System.out.print("|");
				for(int j=0; j<N; j++){
					System.out.print(field[i][j]+", ");
				}
				System.out.println("|");
			}
		   
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		while((!closeList.contains(goal1)) || !openList.isEmpty()){
		   int x = chooseSquare(choosenSquare.getx(),choosenSquare.gety(),G1x,G1y,field,openListobj,closeListobj,choosenSquare);
		   if(x==1){
			   break;
		   }
		   /*for(Square sqr: closeListobj){
			   System.out.println("Father ->"+sqr.getParent()[0]+","+sqr.getParent()[1]);
		   }*/
		   if(x==-1){
			   System.out.println("Cant move to nowhere,because there are Walls1 :(");
			   break;
		   }
		   Collections.sort(openListobj, new Comparator<Square>()     //Auksousa taksinomish ths openListobj ws pros F.
		   {
				public int compare(Square sq1, Square sq2){
					return Double.valueOf(sq1.getf()).compareTo(sq2.getf());
				}
		   });
		   for(Square sqr: openListobj){				     //Ektupwnw to f gia kathe tetragwno.
				System.out.println(sqr.getf());
		   }
		   if(!(openListobj.size()==0)){					 //Ftiaxnw to monopati.
			   Square sqr = openListobj.get(0); 
			   field[sqr.getx()][sqr.gety()]="  X   ";
			   choosenSquare = sqr;							//kanw to choosenSquare iso me to 1o stoixeio ths taksinomimenhs openListobj
			   closeListobj.add(sqr);						//bazw to 1o stoixeio ths openListobj sthn closeListobj gia na mhn to ksanaelegksw
			   openListobj.remove(0);
		   }else{
			   System.out.println("Cant move to nowhere,because there are Walls2 :(");
			   break;
		   }
		   
		   for(int i=0; i<N; i++){							 //Ektupwnw ton pinaka
				System.out.print("|");
				for(int j=0; j<N; j++){
					System.out.print(field[i][j]+", ");
				}
				System.out.println("|");
			}
			
		}			//kalw pali thn sunarthsh chooseSquare, gia na elegksw twra ta geitonika tou neou choosenSquare
		   
		
		
		
		
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public static int chooseSquare(int x,int y,int G1x,int G1y,String field[][],ArrayList<Square> openListobj,ArrayList<Square> closeListobj,Square choosenSquare){
		Square sq1 = new Square(x+1,y);
		int k=0;
		int j=0;
		if(field[x+1][y]!="Wall  "){							//elegxei an to tetragwno einai toixos. An einai den kanei kati gi auto to tetragwno
			for(Square sqr: closeListobj){						//Edw elegxei an to tetragwno anoikei sthn kleisth lista. An anoikei den kanei kati
				if(sqr.getx()==x+1 && sqr.gety()==y){
					j+=1;
				}
		    }
			if(j==0){											
				k+=1;
				if(field[x+1][y]=="G1    "){
					System.out.println("G1 Found");
					System.out.println("Sunoliko kostos:"+choosenSquare.getg()+1.5);
					return 1;
				}
				if(field[x+1][y]=="G2    "){
					System.out.println("G2 Found");
					System.out.println("Sunoliko kostos:"+choosenSquare.getg()+1.5);
					return 1;
				}
				int f=0;
				for(Square sqr: openListobj){				
					if(sqr.getx()==x+1 && sqr.gety()==y){
						f+=1;
					}
				}	
				if(f==0){
					sq1.setg(1+choosenSquare.getg());
					sq1.calculateh(G1x, G1y);
					sq1.calculatef();
					sq1.setParent(choosenSquare.getx(),choosenSquare.gety());
					openListobj.add(sq1);
				}
				else{
					for(Square sqr: openListobj){				
						if(sqr.getx()==x+1 && sqr.gety()==y){
							if(sqr.getg()<1+choosenSquare.getg()){
								System.out.println("Den sumferei");
							}
							else{
								sqr.setParent(choosenSquare.getx(),choosenSquare.gety());
								sqr.setg(1+choosenSquare.getg());
								sqr.calculateh(G1x, G1y);
								sqr.calculatef();
							}
						}
					}	
				}
			}
		}
		Square sq2 = new Square(x-1,y);
		j=0;
		if(field[x-1][y]!="Wall  "){
			for(Square sqr: closeListobj){				
				if(sqr.getx()==x-1 && sqr.gety()==y){
					j+=1;
				}
		    }
			if(j==0){
				k+=1;
				if(field[x-1][y]=="G1    "){
					System.out.println("G1 Found");
					System.out.println("Sunoliko kostos:"+choosenSquare.getg()+1.5);
					return 1;
				}
				if(field[x-1][y]=="G2    "){
					System.out.println("G2 Found");
					System.out.println("Sunoliko kostos:"+choosenSquare.getg()+1.5);
					return 1;
				}
				int f=0;
				for(Square sqr: openListobj){				
					if(sqr.getx()==x-1 && sqr.gety()==y){
						f+=1;
					}
				}	
				if(f==0){
					sq2.setg(1+choosenSquare.getg());
					sq2.calculateh(G1x, G1y);
					sq2.calculatef();
					sq2.setParent(choosenSquare.getx(),choosenSquare.gety());
					openListobj.add(sq2);
				}
				else{
					for(Square sqr: openListobj){				
						if(sqr.getx()==x-1 && sqr.gety()==y){
							if(sqr.getg()<1+choosenSquare.getg()){
								System.out.println("Den sumferei");
							}
							else{
								System.out.println("Sumferei1 "+sqr.getf());
								sqr.setParent(choosenSquare.getx(),choosenSquare.gety());
								sqr.setg(1+choosenSquare.getg());
								sqr.calculateh(G1x, G1y);
								sqr.calculatef();
								System.out.println("Sumferei2 "+sqr.getf());
							}
						}
					}	
				}
			}
		}
		Square sq3 = new Square(x,y+1);
		j=0;
		if(field[x][y+1]!="Wall  "){
			for(Square sqr: closeListobj){				
				if(sqr.getx()==x && sqr.gety()==y+1){
					j+=1;
				}
		    }
			if(j==0){
				k+=1;
				if(field[x][y+1]=="G1    "){
					System.out.println("G1 Found");
					System.out.println("Sunoliko kostos:"+choosenSquare.getg()+1);
					return 1;
				}
				if(field[x][y+1]=="G2    "){
					System.out.println("G2 Found");
					System.out.println("Sunoliko kostos:"+choosenSquare.getg()+1);
					return 1;
				}
				int f=0;
				for(Square sqr: openListobj){				
					if(sqr.getx()==x && sqr.gety()==y+1){
						f+=1;
					}
				}	
				if(f==0){
					sq3.setg(0.5+choosenSquare.getg());
					sq3.calculateh(G1x, G1y);
					sq3.calculatef();
					sq3.setParent(choosenSquare.getx(),choosenSquare.gety());
					openListobj.add(sq3);
				}
				else{
					for(Square sqr: openListobj){				
						if(sqr.getx()==x && sqr.gety()==y+1){
							if(sqr.getg()<0.5+choosenSquare.getg()){
								System.out.println("Den sumferei");
							}
							else{
								sqr.setParent(choosenSquare.getx(),choosenSquare.gety());
								sqr.setg(0.5+choosenSquare.getg());
								sqr.calculateh(G1x, G1y);
								sqr.calculatef();
							}
						}
					}	
				}
			}
		}
		Square sq4 = new Square(x,y-1);
		j=0;
		if(field[x][y-1]!="Wall  "){
			for(Square sqr: closeListobj){				
				if(sqr.getx()==x && sqr.gety()==y-1){
					j+=1;
				}
		    }
			if(j==0){
				k+=1;
				if(field[x][y-1]=="G1    "){
					System.out.println("G1 Found");
					System.out.println("Sunoliko kostos:"+choosenSquare.getg()+1);
					return 1;
				}
				if(field[x][y-1]=="G2    "){
					System.out.println("G2 Found");
					System.out.println("Sunoliko kostos:"+choosenSquare.getg()+1);
					return 1;
				}
				int f=0;
				for(Square sqr: openListobj){				
					if(sqr.getx()==x && sqr.gety()==y-1){
						f+=1;
					}
				}	
				if(f==0){
					sq4.setg(0.5+choosenSquare.getg());
					sq4.calculateh(G1x, G1y);
					sq4.calculatef();
					sq4.setParent(choosenSquare.getx(),choosenSquare.gety());
					openListobj.add(sq4);
				}
				else{
					for(Square sqr: openListobj){				
						if(sqr.getx()==x && sqr.gety()==y-1){
							if(sqr.getg()<0.5+choosenSquare.getg()){
								System.out.println("Den sumferei");
							}
							else{
								sqr.setParent(choosenSquare.getx(),choosenSquare.gety());
								sqr.setg(0.5+choosenSquare.getg());
								sqr.calculateh(G1x, G1y);
								sqr.calculatef();
							}
						}
					}	
				}
			}
		}
		if(openListobj.size()>=1){
			closeListobj.add(openListobj.get(openListobj.size()-k-1));   //bazw sthn closeListobj to tetragwno to opoio exw elegksei ta geitonika tou, gia na mhn to ksana elegksw
			openListobj.remove(openListobj.size()-k-1);					//Bgazw apo thn openListobj to tetragwno pou elegksa
		}
		if(k==0){
			return -1;
		}
		else{
			return 0;
		}
		
	}
}

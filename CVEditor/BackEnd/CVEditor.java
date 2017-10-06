package BackEnd;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class CVEditor {

	private static int cvForUpdate=0; //check if we are creating new CV or we are Update mode
	private static int index; //krataei ton arithmo tou CV pou dialekse o hristis gia Update
	private static int indexItem; //krataei ton arithmo tou Item pou dialekse o hristis gia Update
	private static int index2;//krataei ton arithmo tou 2ou CV pou dialekse o hristis gia Comparison
	
	private static CV cv; //Edo kratame to epilegmeno CV mehri o hristis na teleiosei ta update
	private static BulletListItem item; //Edo kratame to epilegmeno Item mehri o hristis na teleiosei ta update
	private static Paragraph par;//Edo kratame to epilegmeno Paragraph mehri o hristis na teleiosei ta update
	
	public CVEditor(){}
	
	private static ArrayList<CV> CVs = new ArrayList(); //Lista pou krataei ta dimiourgimena CVs
	
	public static int getCvForUpdate()
	{
		return cvForUpdate;
	}
	
	public static int getIndex()
	{
		return index;
	}
	
	public static int getIndex2()
	{
		return index2;
	}
	
	public static int getIndexItem()
	{
		return indexItem;
	}
	
	public static BulletListItem getItem()
	{
		return item;
	}
	
	public static CV getCV()
	{
		return cv;
	}
	
	public static Paragraph getParagraph()
	{
		return par;
	}
	
	public static ArrayList<CV> getCVs()
	{
		return CVs;
	}
	
	public static void setCvForUpdate(int x)
	{
		cvForUpdate = x;
	}
	
	public static void setIndex(int x)
	{
		index = x;
	}
	
	public static void setIndex2(int x)
	{
		index2 = x;
	}
	
	public static void setIndexItem(int x)
	{
		indexItem = x;
	}
	
	public static void setCV(CV x)
	{
		cv = x;
	}
	
	public static void setItem(BulletListItem x)
	{
		item = x;
	}
	
	public static void setParagraph(Paragraph x)
	{
		par = x;
	}
	
	public static void setCVs(CV x)
	{
		CVs.add(x);
	}


}

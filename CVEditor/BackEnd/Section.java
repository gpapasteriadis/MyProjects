package BackEnd;

import java.util.ArrayList;


public class Section {
	private String title ;
	private Paragraph paragraph;
	private ArrayList<BulletListItem> bulletList = new ArrayList();
	
	public Section(){}
	
	public Section(String title)
	{
		this.title = title;
	}
	
	public void addParagraph(Paragraph p)
	{
		paragraph = p;
	}
	public void addBulletListItem(BulletListItem i)
	{
		bulletList.add(i);
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Paragraph getParagraph()
	{
		return paragraph;
	}
	
	public ArrayList<BulletListItem> getBulletList()
	{
		return bulletList;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setParagraph(Paragraph p)
	{
		paragraph = p;
	}
	
	public void setBulletList(ArrayList<BulletListItem> b)
	{
		bulletList = b;
	}

}

package BackEnd;

import java.util.Date;
import java.util.ArrayList;
public class BulletListItem {
	private String content1, content2, content3;
	private Date date;
	private ArrayList<Paragraph> bulletList = new ArrayList();
	
	public BulletListItem(){}
	
	public BulletListItem(String content1, String content2, Date date) //Company Type1 (for Functional CVs)
	{
		this.content1 = content1;
		this.content2 = content2;
		this.date = date;
	}
	
	public BulletListItem(String content1, String content2, String content3, Date date) //Qualification, Course
	{
		this.content1 = content1;
		this.content2 = content2;
		this.content3 = content3;
		this.date = date;
	}
	
	public BulletListItem(String content1, String content2, Date date, String content3) //Company Type2(for Chronological and Combined CVs)
	{
		this.content1 = content1;
		this.content2 = content2;
		this.content3 = content3;
		this.date = date;
	}
	
	public BulletListItem(String content1) //Skill
	{
		this.content1 = content1;
	}
	
	public String getContent1(){
		return content1;
	}
	
	public String getContent2(){
		return content2;
	}
	
	public String getContent3(){
		return content3;
	}
	
	public Date getDate(){
		return date;
	}
	
	public ArrayList<Paragraph> getBulletList()
	{
		return bulletList;
	}
	
	public void setContent1(String c)
	{
		content1 = c;
	}
	
	public void setContent2(String c)
	{
		content2 = c;
	}
	
	public void setContent3(String c)
	{
		content3 = c;
	}
	
	public void setDate(Date d)
	{
		date = d;
	}
	
	public void setBulletList(ArrayList<Paragraph> b)
	{
		bulletList = b;
	}

}

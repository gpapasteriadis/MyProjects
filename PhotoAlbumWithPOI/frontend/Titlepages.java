package src.frontend;

import java.util.ArrayList;

public class Titlepages extends Page {
	
	private String type="Titlepage";
	protected String title;
	protected String date;
	protected ArrayList<String> key = new ArrayList();
	
	
	
	public Titlepages(String title,String date ,ArrayList<String> key){
		this.title=title;
		this.date=date;
		this.key=key;
	}
	
	public String getType(){
		return type;
	}
	public String getTitle() {
		return title;
	}
	public ArrayList<String> getKey() {
		return  key;
	}
	public String getDate() {
		return date;
	}
	public void setTitle(String x){
		this.title=x;
	}
	public void setDate(String x) {
		this.date=x;
	}
	public void setKey(ArrayList<String> x) {
		this.key=x;
	}
	
		
	
	@Override
	String getText(){
		return null;
	}
	@Override
	void setText(String x) {
	}
	@Override
	String getPath() {
		return null;
	}
	@Override
	void setPath(String x) {}

}

package src.frontend;

import java.util.ArrayList;

public class Photopages extends Page {
	
	private String type="Photopage";
	protected String title;
	protected String path;
	protected String date;
	protected ArrayList<String> key = new ArrayList();	
	
	public Photopages(String title,String date,String path,ArrayList<String> key){
		this.title=title;
		this.path=path;
		this.date=date;
		this.key= key;
	}
	
	public String getType(){
		return type;
	}
	public String getPath(){
		return path;
	}
	public String getTitle() {
		return title;
	}
	public String getDate() {
		return date;
	}
	public ArrayList<String> getKey() {
		return key;
	}
	public void setPath(String x){
		this.path=x;
	}
	public void setTitle(String x) {
		this.title=x;
	}
	public void setDate(String x) {
		this.date=x;
	}
	void setKey(ArrayList<String> x) {
		this.key=x;
	}
	
	
	@Override
	void setText(String x) {}
	@Override
	String getText(){
		return null;
	}
	
}	

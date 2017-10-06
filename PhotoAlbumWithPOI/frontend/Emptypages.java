package src.frontend;

import java.util.ArrayList;

public class Emptypages extends Page {

	private String type="Emptypage";
	protected String date;
	protected ArrayList<String> key = new ArrayList();	

	
	public Emptypages(String date,ArrayList<String> key){
		this.date=date;
		this.key=key;
	}
	
	public String getType(){
		return type;
	}
	public String getDate() {
		return date;
	}
	public ArrayList<String> getKey() {
		return key;
	}
	public void setDate(String x) {
		this.date=x;
	}
	public void setKey(ArrayList<String> x) {
		this.key=x;
	}
	
	
	
	@Override
	String getPath() {
		return null;
	}
	@Override
	void setTitle(String x) {}
	@Override
	void setText(String x) {}
	@Override
	void setPath(String x) {}
	@Override
	String getTitle() {
		return null;
	}
	@Override
	String getText() {
		return null;
	}
}
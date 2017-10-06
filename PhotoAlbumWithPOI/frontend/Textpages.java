package src.frontend;

import java.util.ArrayList;

public class Textpages extends Page{
	
	private String type="Textpage";
	protected String title;
	protected String text;
	protected String date;
	protected ArrayList<String> key = new ArrayList();	
	
	
		public Textpages(String title,String text,String date,ArrayList<String> key){
			this.title=title;
			this.text=text;
			this.date=date;
			this.key=key;
		}
		
		
		public String getType(){
			return type;
		}
		public String getText(){
			return text;
		}
		public String getTitle(){
			return title;
		}
		public String getDate() {
			return date;
		}
		public ArrayList<String> getKey() {
			return  key;
		}
	
		public void setTitle(String x){
			this.title=x;
		}
		public void setText(String x){
			this.text=x;
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
		void setPath(String x) {}
}
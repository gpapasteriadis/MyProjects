package BackEnd;


public class Paragraph {
	private String contents;
	
	public Paragraph(){}
	
	public Paragraph(String contents)
	{
		this.contents = contents;
	}
	
	public String getContents(){
		return contents;
	}
	
	public void setContents(String contents){
		this.contents = contents;
	}
}

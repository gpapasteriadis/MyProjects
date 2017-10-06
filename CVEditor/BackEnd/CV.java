package BackEnd;
import java.util.ArrayList;
import java.util.Date;
public class CV {
	private String name, address, teleHome, teleMobile, email;
	private ArrayList<Section> sections = new ArrayList();
	
	public CV()
	{
	}
	
	public void addSection(Section section)
	{
		sections.add(section);
	}
	 
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getTeleHome()
	{
		return teleHome;
	}
	
	public String getTeleMobile()
	{
		return teleMobile;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public ArrayList<Section> getSections()
	{
		return sections;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setTeleHome(String teleHome)
	{
		this.teleHome = teleHome;
	}
	
	public void setTeleMobile(String teleMobile)
	{
		this.teleMobile = teleMobile;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setSections(ArrayList<Section> s)
	{
		sections = s;
	}
	

}

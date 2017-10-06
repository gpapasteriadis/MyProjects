package src.frontend;

import java.util.ArrayList;

public class BasicAlbum extends Album{
	
	private String albumName;

	public BasicAlbum(String albumName){
		this.albumName=albumName;
	}
	public String getName(){
		return albumName;
	}
	public void editAlbum(){
		super.editAlbum();
	}
	public ArrayList<Page> Search(String key){
		return super.Search(key);
	}
}

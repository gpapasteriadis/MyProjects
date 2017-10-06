package src.frontend;

import java.util.ArrayList;
public class DerivativeAlbum extends Album{
	
	private String albumName;
	
	public DerivativeAlbum(String albumName){
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

package src.poiExtractor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xslf.usermodel.XMLSlideShow;



public class AlbumExtractor implements IAlbumExtractorToPoi {

	public AlbumExtractor(String aName){
		pageExtractors = new ArrayList<IPageExtractorToPoi>();
		ppt = new XMLSlideShow();
		name = aName;
	}
	@Override
	public void addSlideExtractor(IPageExtractorToPoi ipe) {
		pageExtractors.add(ipe);
	}

	@Override
	public void exportPOISlideShow() {
		for(IPageExtractorToPoi ipe: pageExtractors){
			ipe.extractPageToPoi(ppt);
		}
		saveToFile(name);
	}

    private void saveToFile(String filename) {
        try {
        	if(filename.endsWith(".pptx") == false) {
        		filename += ".pptx";
        	}
        	FileOutputStream out = new FileOutputStream(filename);
            ppt.write(out);
			out.close();
		} catch (IOException e) {	// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	private ArrayList<IPageExtractorToPoi> pageExtractors;
	private XMLSlideShow ppt;
	private String name; 
}

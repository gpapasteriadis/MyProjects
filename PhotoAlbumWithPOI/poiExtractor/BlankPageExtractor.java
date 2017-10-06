package src.poiExtractor;


import org.apache.poi.xslf.usermodel.XMLSlideShow;

public class BlankPageExtractor implements IPageExtractorToPoi {
	
	public BlankPageExtractor() {
		;
	}
	
	@Override
	public void extractPageToPoi(XMLSlideShow ppt) {
		ppt.createSlide();
	}

}
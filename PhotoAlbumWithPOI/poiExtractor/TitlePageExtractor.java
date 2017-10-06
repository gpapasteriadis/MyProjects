package src.poiExtractor;

import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class TitlePageExtractor implements IPageExtractorToPoi {

	public TitlePageExtractor(String aTitle) {
		title = aTitle;
		
	}
	
	@Override
	public void extractPageToPoi(XMLSlideShow ppt) {
		XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
		XSLFSlideLayout titleLayout = defaultMaster.getLayout(SlideLayout.TITLE_ONLY);
    	XSLFSlide slide = ppt.createSlide(titleLayout);
		XSLFTextShape slideTitle = slide.getPlaceholder(0);
		slideTitle.setText(title);
	}
	private String title;

}

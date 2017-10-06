package src.poiExtractor;

import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class ContentPageExtractor implements IPageExtractorToPoi {

	public ContentPageExtractor(String aTitle, String content) {
		title = aTitle;
		slideData = content;
	}
	@Override
	public void extractPageToPoi(XMLSlideShow ppt) {
		XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
    	XSLFSlide slide = null;
		XSLFSlideLayout titleBodyLayout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
		slide = ppt.createSlide(titleBodyLayout);
		XSLFTextShape slideTitle = slide.getPlaceholder(0);
		slideTitle.setText(title);
        XSLFTextShape body = slide.getPlaceholder(1);
        body.clearText();
        body.addNewTextParagraph().addNewTextRun().setText(slideData);
	}

	private String title;
	private String slideData;
}

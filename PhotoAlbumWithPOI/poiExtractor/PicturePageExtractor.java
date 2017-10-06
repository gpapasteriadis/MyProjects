package src.poiExtractor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class PicturePageExtractor implements IPageExtractorToPoi {

	public PicturePageExtractor(String aTitle, String picture) {
		title = aTitle;
		slideData = picture;
	}
	@Override
	public void extractPageToPoi(XMLSlideShow ppt) {		
		XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
    	XSLFSlide slide = null;
		XSLFSlideLayout titleLayout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
		slide = ppt.createSlide(titleLayout);
		ArrayList<XSLFShape> shapes= (ArrayList<XSLFShape>) slide.getShapes();
		XSLFShape orgPic =  shapes.get(1);
        java.awt.geom.Rectangle2D anchor = orgPic.getAnchor();
        
		byte[] pictureData = null;
		try {
			pictureData = IOUtils.toByteArray(new FileInputStream(slideData));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		XSLFTextShape slideTitle = slide.getPlaceholder(0);
		slideTitle.setText(title);
		XSLFPictureData pd = ppt.addPicture(pictureData, PictureData.PictureType.PNG);
		XSLFPictureShape pic = slide.createPicture(pd);
		slide.removeShape(orgPic);
		
		/* @ToDo: fix re-adjustment
		//size of the original anchor is here
		double maxX = anchor.getMaxX();
		double maxY = anchor.getMaxY();
		double minX = anchor.getMinX();
		double minY = anchor.getMinY();
		double width = anchor.getWidth();
		double height = anchor.getHeight();
		
		@ToDo: find the picture's size too 
		@ToDo: recalibrate size of image s.t. it is rescaled to fit
		//pic.setAnchor(newAanchor);
		*/
	}
	private String title;
	private String slideData;


}

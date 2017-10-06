package src.poiExtractor;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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


/**
 * Alternates on the previous PicturePageExtractor by
 * resizing the slide to fit the picture!
 * Can be useful sometimes.
 * Alternative implementation to PicturePageWResizablePicExtractor that resized the pic to fit the slide
 * 
 * @author libathos
 * @since  2016-11-28
 *
 */
public class PicturePageWResizableSlideExtractor implements IPageExtractorToPoi {

	public PicturePageWResizableSlideExtractor(String aTitle, String picture) {
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
		int width = -1; int height = -1;
		try {
			/*
			 * NOT RECOMMENDED. Reading twice the same file for different properties it has
			 * bufferedImage gives width and height so that we reset the size of the slide if needed
			 * pictureData is used for the POI part
			 * */
			pictureData = IOUtils.toByteArray(new FileInputStream(slideData)); 
	        BufferedImage bimg = ImageIO.read(new File(slideData));
	        width = bimg.getWidth();
	        height = bimg.getHeight(); 
	        
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

		pic.setAnchor(new Rectangle(0,100,width,height));  
		ppt.setPageSize(new java.awt.Dimension(width,height));
		/*

        XSLFSlideLayout title = defaultMaster.getLayout(SlideLayout.TITLE);                
        XSLFSlide slide0 = ppt.createSlide(title);
        
		
        BufferedImage bimg = ImageIO.read(new File(files.get(i)));
        width = bimg.getWidth();
        height = bimg.getHeight(); 
        
        XSLFTextShape title1 = slide0.getPlaceholder(0);
        title1.setAnchor(new Rectangle(0,0,width,100));
        title1.setText("Diachronic Graph");
        
        
        byte[] data = IOUtils.toByteArray(new FileInputStream(files.get(i)));
        int pictureIndex = ppt.addPicture(data, XSLFPictureData.PICTURE_TYPE_JPEG);
        XSLFPictureShape shape = slide0.createPicture(pictureIndex);
        shape.setAnchor(new Rectangle(0,100,width,height));        

        
        ppt.setPageSize(new java.awt.Dimension(width,height));
		*/
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

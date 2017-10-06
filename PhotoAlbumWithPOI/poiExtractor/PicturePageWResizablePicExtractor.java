package src.poiExtractor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
//import org.apache.poi.xssf.usermodel.XSSFDrawing;

/**
 * Improves on the previous PicturePageExtractor by
 * checking whether the picture fits within the slide
 * <p>
 * If not, it resizes the picture.
 * 
 * @author thanosfotn
 * @since   2016-11-28
 *
 */
public class PicturePageWResizablePicExtractor implements IPageExtractorToPoi {

	public PicturePageWResizablePicExtractor(String aTitle, String picture) {
		title = aTitle;
		slideData = picture;
	}
	
	  /**
	   * implements the interface IPageExtractorToPoi
	   * In case you haven't noticed: see how we use ppt _as a parameter_ to the call
	   * This way, we pass the ppt as an argument to the slide's method and not vice versa.
	   * For you, food for thought: can we do it the other way around?
	   * 
	   * @param ppt The slideshow to which the new slide will be added 
	   */
	@Override
	public void extractPageToPoi(XMLSlideShow ppt) {		
		XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
    	XSLFSlide slide = null;
		XSLFSlideLayout titleLayout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
		slide = ppt.createSlide(titleLayout);
		ArrayList<XSLFShape> shapes= (ArrayList<XSLFShape>) slide.getShapes();
		XSLFShape orgPic =  shapes.get(1);
       // java.awt.geom.Rectangle2D anchor = orgPic.getAnchor();
        
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
		
	   // if (image.imagePath.length() > 0) {
	      //byte[] pictureData = IOUtils.toByteArray(new FileInputStream(image.imagePath));
	    //  int idx = ppt.addPicture(pictureData, PictureData.PictureType.PNG);
	    //  XSLFPictureShape pic = slide.createPicture(idx);
	   //   pic.setAnchor(new java.awt.Rectangle(image.x, image.y, image.width, image.height));
	 //   }
		
		XSLFPictureData pd = ppt.addPicture(pictureData, PictureData.PictureType.PNG);
		XSLFPictureShape pic = slide.createPicture(pd);
		
		//getting the current page size
		java.awt.Dimension pgsize = ppt.getPageSize();
  		int pgw = pgsize.width; //slide width in points
  		int pgh = pgsize.height; //slide height in points
  		
 
  		InputStream in = new ByteArrayInputStream(pictureData);
  		BufferedImage buf;   

		try {
			buf = ImageIO.read(in);
			//Get original dimensions from image
			int originalHeight = buf.getHeight();
		    int originalWidth = buf.getWidth();
		    
		    //Set a fixed height and resize while keeping the aspect ratio
		    int newHeight = originalHeight;
		    int newWidth = originalWidth;
		    
		    // first check if we need to scale width
	  	    if (originalWidth > pgw) {
	  	        //scale width to fit
	  	    	newWidth = pgw;
	  	        //scale height to maintain aspect ratio
	  	    	newHeight = (newWidth * originalHeight) / originalWidth;
	  	    }

	  	    // then check if we need to scale even with the new height
	  	    if (newHeight > pgh) {
	  	        //scale height to fit instead
	  	    	newHeight = pgh;
	  	        //scale width to maintain aspect ratio
	  	    	newWidth = (newHeight * originalWidth) / originalHeight;
	  	    }
		    
			pic.setAnchor(new java.awt.Rectangle((pgw-newWidth)/2, (pgh-newHeight)/2, newWidth, newHeight));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		slide.removeShape(orgPic);
		

	}
	private String title;
	private String slideData;


}

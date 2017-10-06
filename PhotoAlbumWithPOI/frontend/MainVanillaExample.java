package src.frontend;

//The following 3 imports are only for the commented-out code
import java.io.FileOutputStream; 

import java.io.IOException;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import src.poiExtractor.*;

/*
 * This is just a "silly" example to show you how to use the src.poiExtractor package.
 * Your code in this package should act as the front-end of the application. 
 * You main() method must be drastically different than this one. 
 */
public class MainVanillaExample {

	public static void main(String[] args) {
		TitlePageExtractor tp = new TitlePageExtractor("This is a title");
		ContentPageExtractor cp = new ContentPageExtractor("a title", "a text");
		
		// OLD: See how pOld is _not_ fitting within the slide + not centered
		PicturePageExtractor pOld = new PicturePageExtractor("image", "resources/aceshigh.jpg");
		
		/*
		 * NEW:
		 * See how we decided to implement an improvement, by _ADDING_ a _NEW_ class,
		 * rather than messing with the old one
		 * <p>
		 * For you, food for thought: how does abstract coupling works here? What is the cost+effort and what are the benefits?
		 */
		PicturePageWResizablePicExtractor pp1 = new PicturePageWResizablePicExtractor("image", "resources/aceshigh.jpg");
		PicturePageWResizablePicExtractor pp2 = new PicturePageWResizablePicExtractor("image", "resources/somewhereintime.jpg");
		PicturePageWResizablePicExtractor pp3 = new PicturePageWResizablePicExtractor("image", "resources/amolad.jpg");
		BlankPageExtractor bp = new BlankPageExtractor();
		
		/* Alternative (NOT recommended): resizes the slides to the picture rather than the other way out.
		 * You can try it out to see what happens:
		 * PicturePageWResizableSlideExtractor ppRS = new PicturePageWResizableSlideExtractor("image", "resources/aceshigh.jpg");
		*/


 // If you comment this out, you can try an even simpler example
 // without AlbumExtractor.
 // Remember to uncomment the method underneath main() too.

		/*XMLSlideShow pptVanilla = new XMLSlideShow(); 
		tp.extractPageToPoi(pptVanilla);		
		cp.extractPageToPoi(pptVanilla);
		pp1.extractPageToPoi(pptVanilla);
		bp.extractPageToPoi(pptVanilla);
		saveToFile(pptVanilla, "tryVanilla");
		System.out.println("Done with vanilla example!");*/
		
		AlbumExtractor albumExtractor = new AlbumExtractor("tryAExtr2454");
		albumExtractor.addSlideExtractor(tp);
		albumExtractor.addSlideExtractor(cp);
		albumExtractor.addSlideExtractor(pOld);
		albumExtractor.addSlideExtractor(pp1);
		albumExtractor.addSlideExtractor(pp2);
		albumExtractor.addSlideExtractor(pp3);
		albumExtractor.addSlideExtractor(bp);
		//albumExtractor.addSlideExtractor(ppRS);
		albumExtractor.exportPOISlideShow();
		System.out.println("Done with AE example!");
		
		
}



 // Useful only if you uncomment the extremely simple example without the AlbumExtractor
  
	private static void saveToFile(XMLSlideShow ppt, String filename) {
	    try {
	    	if(filename.endsWith(".pptx") == false) {
	    		filename += ".pptx";
	    	}
	    	System.out.println("Outputing " + ppt.getSlides().size() + " slides");
	    	FileOutputStream out = new FileOutputStream(filename);
	        ppt.write(out);
			out.close();
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}

}

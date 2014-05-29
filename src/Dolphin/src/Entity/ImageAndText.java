package Dolphin.src.Entity;

public class ImageAndText {
	    protected String imageID;
	    protected String text1;
	    protected String text2;
	    protected String text3;

	    public ImageAndText() {
	    	
	    }
	    public ImageAndText(String imageID, String text1, String text2) {
	        this.imageID = imageID;
	        this.text1 = text1;
	        this.text2 = text2;
	    }
	    public ImageAndText(String imageID, String text1, String text2, String text3) {
	    	 this.imageID = imageID;
		     this.text1 = text1;
		     this.text2 = text2;
		     this.text3 = text3;
	    }
	    
	    public void setImageId(String imageId) {
	    	this.imageID = imageId;
	    }
	    public void setText1(String text1) {
	        this.text1 = text1; 
	    }
	    public void setText2(String text2) {
	        this.text2 = text2; 
	    }
	    public void setText3(String text3) {
	        this.text3 = text3; 
	    }
	    
	    public String getImageID() {
	        return imageID;
	    }
	    public String getText1() {
	        return text1;
	    }
	    public String getText2() {
	        return text2;
	    }
	    public String getText3() {
	        return text3;
	    }
}

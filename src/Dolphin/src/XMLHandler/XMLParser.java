/**
 * 
 */
package Dolphin.src.XMLHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import Dolphin.src.Entity.SitesList;
import android.content.Context;
import android.util.Log;
import android.view.InflateException;

/**
 * @author Administrator
 * 
 */
public class XMLParser {
	/** Create Object For SiteList Class */
	SitesList sitesList = null;
	private static final String TAG = "TAG_XMLParser";
	Context ctx;
	private Integer[] mImageIds;
	private ArrayList<String> category = new ArrayList<String>();
	private ArrayList<String> brand = new ArrayList<String>();
	/** Called when the activity is first created. */
	public XMLParser() {
		Log.i(TAG,"In XMLParser");
	}

	public XMLParser(Context ctx) {
		this.ctx = ctx;
	}

	private InputStream readDataFromFile() throws IOException {
		InputStream inStream = null;
		try {
			Log.i(TAG, "readStreamIn");
			inStream = ctx.getAssets().open("productlist.xml");
		} catch (InflateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inStream;
	}

	public void readXML() {
		try {

			/** Handling XML */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			/** Send URL to parse XML Tags */
			// URL sourceUrl = new URL(
			// "http://www.androidpeople.com/wp-content/uploads/2010/06/example.xml");
			InputSource inputSource = new InputSource(new InputStreamReader(
					readDataFromFile(), "gb2312"));
			/** Create handler to handle XML Tags ( extends DefaultHandler ) */
			MyXMLHandler myXMLHandler = new MyXMLHandler();
			xr.setContentHandler(myXMLHandler);
			xr.parse(inputSource);
			// xr.parse(new InputSource(sourceUrl.openStream()));

		} catch (Exception e) {
			System.out.println("XML Pasing Excpetion = " + e);
		}

		/** Get result from MyXMLHandler SitlesList Object */
		sitesList = MyXMLHandler.sitesList;
		mImageIds = new Integer[sitesList.getName().size()];
		Log.i(TAG,"The number of items in XML is :"+sitesList.getName().size());
		/** Set the result text in textview and add it to layout */
		for (int i = 0; i < sitesList.getName().size(); i++) {
			mImageIds[i] = ctx.getResources().getIdentifier(sitesList.getName().get(i),
					"drawable", ctx.getPackageName());
			sitesList.getName().get(i);
		}
		brand = sitesList.getBrand();
		category = sitesList.getCategory();
	}
	
	public ArrayList<String> getBrand() {
		return brand;
	}
	
	public ArrayList<String> getCategory() {
		return category;
	}
	
	public Integer[] getmImageIds() {
		return mImageIds;
	}
}

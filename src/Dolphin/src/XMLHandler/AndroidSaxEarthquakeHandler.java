/**
 * 
 */
package Dolphin.src.XMLHandler;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.xml.sax.Attributes;

import Dolphin.src.Util.EarthquakeEntry;
import android.location.Location;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Xml;

/**
 * @author Administrator
 *
 */
public class AndroidSaxEarthquakeHandler {

	   //xml解析用到的Tag  
    private String kRootElementName = "feed";  
    private String kEntryElementName = "entry";  
    private String kLinkElementName = "link";  
    private String kTitleElementName = "title";  
    private String kUpdatedElementName = "updated";  
    private String kGeoRSSPointElementName = "point";  
    private String kGeoRSSElevElementName = "elev";  
    static final String ATOM_NAMESPACE = "http://www.w3.org/2005/Atom";   
    static final String GEORSS_NAMESPACE = "http://www.georss.org/georss";  
    //用于保存xml解析获取的结果  
    private ArrayList<EarthquakeEntry> earthquakeEntryList;  
    private EarthquakeEntry earthquakeEntry;  
      
    //解析xml数据  
    public ArrayList<EarthquakeEntry> parse(InputStream inStream)  
    {  
        earthquakeEntryList = new ArrayList<EarthquakeEntry>();  
        RootElement root = new RootElement(ATOM_NAMESPACE, kRootElementName);  
        Element entry = root.getChild(ATOM_NAMESPACE, kEntryElementName);  
        //具体解析xml  
        //处理entry标签  
        entry.setStartElementListener(new StartElementListener() {        
            public void start(Attributes arg0) {
				// TODO Auto-generated method stub
				earthquakeEntry = new EarthquakeEntry();  
				
			}  
        });  
        entry.setEndElementListener(new EndElementListener() {    
            public void end() {  
                // TODO Auto-generated method stub  
                earthquakeEntryList.add(earthquakeEntry);  
            }  
        });  
        //处理title标签  
        entry.getChild(ATOM_NAMESPACE, kTitleElementName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
                // TODO Auto-generated method stub  
                //提取强度信息  
                String magnitudeString = currentData.split(" ")[1];  
                int end =  magnitudeString.length()-1;  
                magnitudeString = magnitudeString.substring(0, end);  
                double magnitude = Double.parseDouble(magnitudeString);  
                earthquakeEntry.setMagnitude(magnitude);  
                //提取位置信息  
                String place = currentData.split(",")[1].trim();  
                earthquakeEntry.setPlace(place);                  
            }  
        });  
        //处理updated标签  
        entry.getChild(ATOM_NAMESPACE, kUpdatedElementName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
                // TODO Auto-generated method stub  
                //构造更新时间  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");  
                Date qdate = new GregorianCalendar(0,0,0).getTime();  
                try {  
                  qdate = sdf.parse(currentData);  
                } catch (ParseException e) {  
                  e.printStackTrace();  
                }  
                earthquakeEntry.setDate(qdate);               
            }  
        });  
        //处理point标签  
        entry.getChild(GEORSS_NAMESPACE, kGeoRSSPointElementName).setEndTextElementListener(new EndTextElementListener() {    
            public void end(String currentData) {  
                // TODO Auto-generated method stub  
                //提取经纬度信息  
                String[] latLongitude = currentData.split(" ");  
                Location location = new Location("dummyGPS");  
                location.setLatitude(Double.parseDouble(latLongitude[0]));  
                location.setLongitude(Double.parseDouble(latLongitude[1]));  
                earthquakeEntry.setLocation(location);  
            }  
        });  
        //处理elev标签  
        entry.getChild(GEORSS_NAMESPACE, kGeoRSSElevElementName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
                // TODO Auto-generated method stub  
                //提取海拔高度信息  
                double evel;  
         
                //因为USGS数据有可能会输错，比如为"--10000"，多了一个"-"号  
                try {  
                    evel = Double.parseDouble(currentData);  
                } catch (Exception e) {  
                    // TODO: handle exception  
                    e.printStackTrace();  
                    evel = 0;  
                }  
                Log.v("Sax_Elev", String.valueOf(evel));  
                earthquakeEntry.setElev(evel);            
            }  
        });  
        //处理link标签  
        entry.getChild(ATOM_NAMESPACE, kLinkElementName).setStartElementListener(new StartElementListener() {         
            public void start(Attributes attributes) {  
                // TODO Auto-generated method stub  
                //获取link链接  
                String webLink = attributes.getValue("href");  
                earthquakeEntry.setLink(webLink);  
            }  
        });  
        //调用android.util.Xml开始解析  
        try {  
            Xml.parse(inStream, Xml.Encoding.UTF_8, root.getContentHandler());  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        return earthquakeEntryList;  
    }  
}  

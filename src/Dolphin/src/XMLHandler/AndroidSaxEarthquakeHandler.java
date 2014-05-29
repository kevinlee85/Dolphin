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

	   //xml�����õ���Tag  
    private String kRootElementName = "feed";  
    private String kEntryElementName = "entry";  
    private String kLinkElementName = "link";  
    private String kTitleElementName = "title";  
    private String kUpdatedElementName = "updated";  
    private String kGeoRSSPointElementName = "point";  
    private String kGeoRSSElevElementName = "elev";  
    static final String ATOM_NAMESPACE = "http://www.w3.org/2005/Atom";   
    static final String GEORSS_NAMESPACE = "http://www.georss.org/georss";  
    //���ڱ���xml������ȡ�Ľ��  
    private ArrayList<EarthquakeEntry> earthquakeEntryList;  
    private EarthquakeEntry earthquakeEntry;  
      
    //����xml����  
    public ArrayList<EarthquakeEntry> parse(InputStream inStream)  
    {  
        earthquakeEntryList = new ArrayList<EarthquakeEntry>();  
        RootElement root = new RootElement(ATOM_NAMESPACE, kRootElementName);  
        Element entry = root.getChild(ATOM_NAMESPACE, kEntryElementName);  
        //�������xml  
        //����entry��ǩ  
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
        //����title��ǩ  
        entry.getChild(ATOM_NAMESPACE, kTitleElementName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
                // TODO Auto-generated method stub  
                //��ȡǿ����Ϣ  
                String magnitudeString = currentData.split(" ")[1];  
                int end =  magnitudeString.length()-1;  
                magnitudeString = magnitudeString.substring(0, end);  
                double magnitude = Double.parseDouble(magnitudeString);  
                earthquakeEntry.setMagnitude(magnitude);  
                //��ȡλ����Ϣ  
                String place = currentData.split(",")[1].trim();  
                earthquakeEntry.setPlace(place);                  
            }  
        });  
        //����updated��ǩ  
        entry.getChild(ATOM_NAMESPACE, kUpdatedElementName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
                // TODO Auto-generated method stub  
                //�������ʱ��  
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
        //����point��ǩ  
        entry.getChild(GEORSS_NAMESPACE, kGeoRSSPointElementName).setEndTextElementListener(new EndTextElementListener() {    
            public void end(String currentData) {  
                // TODO Auto-generated method stub  
                //��ȡ��γ����Ϣ  
                String[] latLongitude = currentData.split(" ");  
                Location location = new Location("dummyGPS");  
                location.setLatitude(Double.parseDouble(latLongitude[0]));  
                location.setLongitude(Double.parseDouble(latLongitude[1]));  
                earthquakeEntry.setLocation(location);  
            }  
        });  
        //����elev��ǩ  
        entry.getChild(GEORSS_NAMESPACE, kGeoRSSElevElementName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
                // TODO Auto-generated method stub  
                //��ȡ���θ߶���Ϣ  
                double evel;  
         
                //��ΪUSGS�����п��ܻ��������Ϊ"--10000"������һ��"-"��  
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
        //����link��ǩ  
        entry.getChild(ATOM_NAMESPACE, kLinkElementName).setStartElementListener(new StartElementListener() {         
            public void start(Attributes attributes) {  
                // TODO Auto-generated method stub  
                //��ȡlink����  
                String webLink = attributes.getValue("href");  
                earthquakeEntry.setLink(webLink);  
            }  
        });  
        //����android.util.Xml��ʼ����  
        try {  
            Xml.parse(inStream, Xml.Encoding.UTF_8, root.getContentHandler());  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        return earthquakeEntryList;  
    }  
}  

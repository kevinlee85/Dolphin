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
import Dolphin.src.Entity.NewsItem;
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
public class NewsListSaxXMLParserHandler {

	   //xml解析用到的Tag  
    private String kRootElementName = "feed";  
    private String kEntryElementName = "entry";  
    private String kIdElementName = "id";
    private String kTitleElementName = "title";  
    private String kUpdatedElementName = "updated";
    private String kImageElemntName = "image";
    private String kBriefElemntName = "brief";
    private String kContentElemntName = "content";
    //用于保存xml解析获取的结果  
    private ArrayList<NewsItem> newsItemList;  
    private NewsItem newsItem;  
    static final String ATOM_NAMESPACE = "http://www.chinacoal.com";   
    static final String GEORSS_NAMESPACE = "http://www.chinacoal.com";
    private static final String TAG = "NewsListSaxXMLParserHandler";
      
    //解析xml数据  
    public ArrayList<NewsItem> parse(InputStream inStream)  
    {  
    	newsItemList = new ArrayList<NewsItem>();  
        RootElement root = new RootElement(ATOM_NAMESPACE, kRootElementName);  
        Element entry = root.getChild(ATOM_NAMESPACE, kEntryElementName);  

        entry.setStartElementListener(new StartElementListener() {        
            public void start(Attributes arg0) {
				// TODO Auto-generated method stub
            	newsItem = new NewsItem();  
				
			}  
        });  
        entry.setEndElementListener(new EndElementListener() {    
            public void end() {  
                // TODO Auto-generated method stub  
            	newsItemList.add(newsItem);  
            }  
        });  
        //处理title标签  
        entry.getChild(ATOM_NAMESPACE, kIdElementName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
            	Log.i(TAG,"Id is :"+ currentData);
            	newsItem.setId(currentData);
            }  
        });  
        entry.getChild(ATOM_NAMESPACE, kTitleElementName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
            	Log.i(TAG,"titel is :"+ currentData);
            	newsItem.setText1(currentData);
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
                newsItem.setText3(qdate.toString());              
            }  
        });
        entry.getChild(ATOM_NAMESPACE, kImageElemntName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
            	Log.i(TAG,"Image is :"+ currentData);
            	newsItem.setImageId(currentData);
            }  
        }); 
        entry.getChild(ATOM_NAMESPACE, kBriefElemntName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
            	Log.i(TAG,"Brief is :"+ currentData);
            	newsItem.setText2(currentData);
            }  
        }); 
        entry.getChild(ATOM_NAMESPACE, kContentElemntName).setEndTextElementListener(new EndTextElementListener() {  
            public void end(String currentData) {  
            	Log.i(TAG,"Content is :"+ currentData);
            	newsItem.setContent(currentData);
            }  
        }); 
        // Use Xml to do parse of the Steam.
        try {  
            Xml.parse(inStream, Xml.Encoding.UTF_8, root.getContentHandler());  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
        return newsItemList;  
    }  
}  

/**
 * 
 */
package Dolphin.src.XMLHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import Dolphin.src.R;
import Dolphin.src.Util.EarthquakeEntry;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Administrator
 *
 */
public class AndroidXMLDemoSaxII extends Activity {  
    /** Called when the activity is first created. */  
    //定义显示的List相关变量  
    ListView list;  
    ArrayAdapter<EarthquakeEntry> adapter;  
    ArrayList<EarthquakeEntry> earthquakeEntryList;  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.text_temp);  
          
        //获取地震数据流  
        InputStream earthquakeStream = readEarthquakeDataFromFile();  
        //Android Sax方式进行解析  
        AndroidSaxEarthquakeHandler androidSaxHandler = new AndroidSaxEarthquakeHandler();  
        earthquakeEntryList = androidSaxHandler.parse(earthquakeStream);  
        //用ListView进行显示  
        list = (ListView)this.findViewById(R.id.listtest);  
        adapter = new ArrayAdapter<EarthquakeEntry>(this, android.R.layout.simple_list_item_1, earthquakeEntryList);  
        list.setAdapter(adapter);  
    }  
      
    private InputStream readEarthquakeDataFromFile()  
    {  
        //从本地获取地震数据  
        InputStream inStream = null;  
        try {  
            inStream = this.getAssets().open("testlist.xml");  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return inStream;  
    }  
    @SuppressWarnings("unused")
	private InputStream readEarthquakeDataFromInternet()  
    {  
        //从网络上获取实时地震数据  
        URL infoUrl = null;  
        InputStream inStream = null;  
        try {  
            infoUrl = new URL("http://earthquake.usgs.gov/earthquakes/catalogs/1day-M2.5.xml");  
            URLConnection connection = infoUrl.openConnection();  
            HttpURLConnection httpConnection = (HttpURLConnection)connection;  
            int responseCode = httpConnection.getResponseCode();  
            if(responseCode == HttpURLConnection.HTTP_OK)  
            {  
                inStream = httpConnection.getInputStream();  
            }  
        } catch (MalformedURLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return inStream;  
    }  
}  

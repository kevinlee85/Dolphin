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
    //������ʾ��List��ر���  
    ListView list;  
    ArrayAdapter<EarthquakeEntry> adapter;  
    ArrayList<EarthquakeEntry> earthquakeEntryList;  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.text_temp);  
          
        //��ȡ����������  
        InputStream earthquakeStream = readEarthquakeDataFromFile();  
        //Android Sax��ʽ���н���  
        AndroidSaxEarthquakeHandler androidSaxHandler = new AndroidSaxEarthquakeHandler();  
        earthquakeEntryList = androidSaxHandler.parse(earthquakeStream);  
        //��ListView������ʾ  
        list = (ListView)this.findViewById(R.id.listtest);  
        adapter = new ArrayAdapter<EarthquakeEntry>(this, android.R.layout.simple_list_item_1, earthquakeEntryList);  
        list.setAdapter(adapter);  
    }  
      
    private InputStream readEarthquakeDataFromFile()  
    {  
        //�ӱ��ػ�ȡ��������  
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
        //�������ϻ�ȡʵʱ��������  
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

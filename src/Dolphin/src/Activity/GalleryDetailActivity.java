/**
 * 
 */
package Dolphin.src.Activity;

import Dolphin.src.R;
import Dolphin.src.Adapter.ImageAdapterNew;
import Dolphin.src.DatabaseProvider.DBAdapter;
import Dolphin.src.Util.GalleryFlow;
import Dolphin.src.XMLHandler.XMLParser;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Administrator
 *
 */
public class GalleryDetailActivity extends Activity {
	private final static String tag = "GalleryDetailShow";
	public String fatherid;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
		
	        super.onCreate(savedInstanceState);
	    	requestWindowFeature(Window.FEATURE_NO_TITLE); //use this function to hidden the up application title.
	        setContentView(R.layout.dolphin_gallery_others);
//	        GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.mygallery);
	        Log.i(tag,"In gallery Show");
	        Intent intent = this.getIntent();
			Bundle bundle = intent.getExtras();
//			mImageIds = new Integer[5];
//			Integer id = (Integer) bundle.get("DataKey0");
			fatherid = (String)bundle.get("DataKey");
//	        mImageIds[0]=id; 
//	        id = (Integer) bundle.get("DataKey1");
//	        mImageIds[1]=id;
//	        id = (Integer) bundle.get("DataKey2");
//	        mImageIds[2]=id;
//	        id = (Integer) bundle.get("DataKey3");
//	        mImageIds[3]=id;
//	        id = (Integer) bundle.get("DataKey4");
//	        mImageIds[4]=id;
//	        Log.i(tag,"Date Config has been done!:"+ mImageIds[0]+" "+mImageIds[1]+" "+mImageIds[2]+" "+mImageIds[3]+" "+mImageIds[4]);
	        setImageIdsByDB();
	        ImageAdapterNew adapter = new ImageAdapterNew(this, mImageIds);
	        Log.i(tag,"new ImageAdapter has been done");
//	        adapter.createReflectedImages();
			
//			galleryFlow.setAdapter(adapter);
		
//			galleryFlow.setOnItemClickListener(new OnItemClickListener() {
//	            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//	                Toast.makeText(GalleryDetailActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//	            }
//	            
//	        });
	        
	        // We also want to show context menu for longpressed items in the gallery
//	        registerForContextMenu(galleryFlow);
	    }

	    @Override
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    	menu.add("Hello World");
	    }
	    
	    @Override
	    public boolean onContextItemSelected(MenuItem item) {
	        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	        Toast.makeText(this, "Longpress: " + info.position, Toast.LENGTH_SHORT).show();
	        return true;
	    }

	    public int setImageIdsByXML(){
        	XMLParser myXmlParser =  new XMLParser(this);
        	myXmlParser.readXML();
        	mImageIds = myXmlParser.getmImageIds();
        	return 1;
        }
	    public int setImageIdsByDB() {
			// XMLParser myXmlParser = new XMLParser(this);
			// myXmlParser.readXML();
			// mImageIds = myXmlParser.getmImageIds();
			DBAdapter adapter = new DBAdapter(this);
			final String DATABASE_TABLE_DETAIL = "imageresdetail";
			adapter.setDateBaseTableName(DATABASE_TABLE_DETAIL);
			adapter.open();
			int res_id = 0;
			Cursor cursor = adapter.getTitleFromDetailImages(fatherid);
			cursor.moveToFirst();
			Log.i(tag, "The Data QUERY has been done");
			int i = -1;
			if (cursor != null) {
				i = cursor.getCount();
				Log.i(tag, "The Data num is " + i);
			} else {
				Log.i(tag, "The Data QUERY result cursor is null");
			}
			res_id = getResources().getIdentifier(cursor.getString(2), "drawable",
					getPackageName());
			Log.i(tag, "Resource is is " + res_id);
			mImageIds = new Integer[i];
			String result = "";
			int count = 0;
			while (count < 5 && i == 5) {
				Log.i(tag, "cursor is " + cursor.getString(2));
				res_id = getResources().getIdentifier(cursor.getString(2),
						"drawable", getPackageName());
				Log.i(tag, "Resource is is " + res_id);
				mImageIds[count] = res_id;
				Log.i(tag, "The count number is " + count);
				count++;
				Log.i(tag, "DataBase : " + result + " i==" + i + " position="
						+ Integer.toString(cursor.getPosition()));
				cursor.moveToNext();
			}// while (i > 0)
			if (i == 1) {
				res_id = getResources().getIdentifier(cursor.getString(2),
						"drawable", getPackageName());
				mImageIds[0] = res_id;
			}
			cursor.close();
			adapter.close();
			return 1;

		}
//	    public class ImageAdapter extends BaseAdapter {
//	        int mGalleryItemBackground;
//	        
//	        public ImageAdapter(Context c) {
//	            mContext = c;
//	            // See res/values/attrs.xml for the <declare-styleable> that defines
//	            // Gallery1.
//	            TypedArray a = obtainStyledAttributes(R.styleable.GalleryShow);
//	            mGalleryItemBackground = a.getResourceId(
//	                    R.styleable.GalleryShow_android_galleryItemBackground, 0);
//	            a.recycle();
//	        }
//
//	        public int getCount() {
//	            return mImageIds.length;
//	        }
//
//	        public Object getItem(int position) {
//	            return position;
//	        }
//
//	        public long getItemId(int position) {
//	            return position;
//	        }
//
//	        public View getView(int position, View convertView, ViewGroup parent) {
//	            ImageView i = new ImageView(mContext);
//
//	            i.setImageResource(mImageIds[position]);
//	            i.setScaleType(ImageView.ScaleType.FIT_XY);
//	            i.setLayoutParams(new Gallery.LayoutParams(250, 200));
//	            
//	            // The preferred Gallery item background
//	            i.setBackgroundResource(mGalleryItemBackground);
//	            
//	            return i;
//	        }
//
//	        private Context mContext;
//
//	       
//	        
//	      
//	    }
	    
	    private Integer[] mImageIds = {
//                R.drawable.mlb201010yj001,
//                R.drawable.mlb201010yj002,
//                R.drawable.mlb201010yj003,
//                R.drawable.mlb201010yj004,
//                R.drawable.mlb201010yj005,
//                R.drawable.mlb201010yj006,
//                R.drawable.mlb201010yj007,
//                R.drawable.mlb201010yj008
        };

}

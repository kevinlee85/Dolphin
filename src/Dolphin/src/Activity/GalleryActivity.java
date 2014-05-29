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
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;

/**
 * @author Kevin
 * This class will be depressed someday since Gallery has been depressed by Google.
 * 
 */
public class GalleryActivity extends Activity implements OnClickListener {
	private final static String tag = "GalleryShow";
	public String[] imageMark;
	public String[] description;
	private ImageAdapterNew adapter;
	private CapContentAdapter contentadapter;
	private ImageButton back;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //use this function to hidden the up application title.
		setContentView(R.layout.dolphin_gallery_others);
		
		GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.mygallery);
		Gallery galleryContent = (Gallery) findViewById(R.id.gallery_textview);
		Log.i(tag, "In gallery Show");

		setImageIdsByDB();

		adapter = new ImageAdapterNew(this, mImageIds);
//		 adapter.createReflectedImages();

		galleryFlow.setAdapter(adapter);
		galleryFlow.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(GalleryActivity.this, "hihi" + (position % mImageIds.length),
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(GalleryActivity.this,
						DolphinSpecificDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("DataKey", imageMark[position]);
				intent.putExtras(bundle);
				startActivityForResult(intent, 100);
				return false;
			}

		});
		galleryFlow.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(GalleryActivity.this, "" + (position % mImageIds.length),
						Toast.LENGTH_SHORT).show();
			}
		});


		// We also want to show context menu for longpressed items in the
		// gallery
		registerForContextMenu(galleryFlow);

	    contentadapter = new CapContentAdapter(this,mImageIds);
		galleryContent.setAdapter(contentadapter);
		// Below codes are used to seup the second gallery.
		
		
		//multi touch function addiction.
		galleryFlow.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				int aid = (int) adapter.getItemId(position);
				              // 更新歌曲Gallery
				contentadapter.notifyDataSetChanged(aid);
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		back = (ImageButton)findViewById(R.id.imagebutton_back);
		back.setOnClickListener(this);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add("Hello World");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Toast.makeText(this, "Longpress: " + (info.position % mImageIds.length), Toast.LENGTH_SHORT)
				.show();
		return true;
	}

	public int setImageIdsByXML() {
		XMLParser myXmlParser = new XMLParser(this);
		myXmlParser.readXML();
		mImageIds = myXmlParser.getmImageIds();
		return 1;
	}

	public int setImageIdsByDB() {

		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		int res_id = 0;
		Cursor cursor = adapter.getAllTitles();
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
		imageMark = new String[i];
		description = new String[i];
		String result = "";
		int count = 0;
		while (count < i) {
			Log.i(tag, "cursor is " + cursor.getString(2));
			res_id = getResources().getIdentifier(cursor.getString(2),
					"drawable", getPackageName());
			Log.i(tag, "Resource is is " + res_id);
			mImageIds[count] = res_id;
			imageMark[count] = cursor.getString(2);
			description[count]=cursor.getString(3);
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

	// New adapter for text show;
	public class CapContentAdapter extends BaseAdapter {

		private Context context;
		int mark=0;
		private Integer[] mImageIds;
		
		public CapContentAdapter(Context context, Integer[] ImageIds) {
			this.context = context;
			this.mImageIds = ImageIds;
		}
		public int getCount() {
			if (mImageIds != null) {
				if (mImageIds.length > 1)
					return Integer.MAX_VALUE;
				else
					return mImageIds.length;
			}
			return 0;
		}

		public Object getItem(int position) {
			Log.i(tag,"getItem :" +position);
			return position;
		}

		public long getItemId(int position) {
			Log.i(tag,"getItemId :" +position);
//		}
//			if (cursor != null) {
//				cursor.moveToPosition(position);
//				return cursor.getInt(cursor
//						.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
//			}
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
//			cursor.moveToPosition(position);
//			Log.i(tag,"getView :" +position);
			TextView t = new TextView(context);
			String title = description[mark];
//			String title = cursor.getString(cursor
//					.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
			t.setLayoutParams(new Gallery.LayoutParams(
					LayoutParams.WRAP_CONTENT, 50));
			t.setText(title);
			t.setTextColor(Color.BLACK);
			return t;
		}

		/**
		 * 当专辑改变了，调用此方法更新adapter的数据
		 * 
		 * @param albumId
		 *            专辑ID
		 */
		public void notifyDataSetChanged(int position) {
			Log.i(tag,"notifyDataSetChanged :" +position);
			mark=position % mImageIds.length;
			super.notifyDataSetChanged();
		}

	}

	// end

	private Integer[] mImageIds = {
	// R.drawable.mlb201010yj001,
	// R.drawable.mlb201010yj002,
	// R.drawable.mlb201010yj003,
	// R.drawable.mlb201010yj004,
	// R.drawable.mlb201010yj005,
	// R.drawable.mlb201010yj006,
	// R.drawable.mlb201010yj007,
	// R.drawable.mlb201010yj008
	};
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
		case R.id.imagebutton_back:{
			finish();
			break;
		}
		default:{
			break;
		}
		}
		
	}

}

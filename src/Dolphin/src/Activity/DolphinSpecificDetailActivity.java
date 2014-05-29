
package Dolphin.src.Activity;

import java.util.HashMap;
import java.util.Map;

import Dolphin.src.R;
import Dolphin.src.Adapter.HorizontalScrollViewAdapter;
import Dolphin.src.DatabaseProvider.DBAdapter;
import Dolphin.src.Util.HorizontalScrollViewLinearLayout;
import Dolphin.src.XMLHandler.XMLParser;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

/**
 * @author Kevin 
 * This Activity is call in from DolphinActivity.
 */
public class DolphinSpecificDetailActivity extends Activity implements
		OnClickListener {
	static private String TAG = "TAG_SpecificDeatail";
	private String fatherid = null;
	// private String KEY_DESCRITPION = null;
	// private String KEY_COLOR = null;
	// private String KEY_SIZE = null;
	// private String KEY_PRICE = null;
	private Button buttonMoreInfo;
	private Button buttonSaveIt;
	private Button buttonBuyIt;
	private ImageButton imageButton331;
	private ImageButton imageButton332;
	private ImageButton imageButton333;
	private ImageButton imageButton334;
	private ImageButton imageButtonBack;
	private TextView description;
	private TextView color;
	private TextView size;
	private TextView price;

	// As gallery has been depressed, i add new HorizontalScrollView to replace
	// it. 2014-3-29
	private HorizontalScrollViewLinearLayout hsvLinearLayout = null;
	private HorizontalScrollViewAdapter hsvAdapter = null;
	// Dummy data only for test, and the real data will load by the DB.
	private Integer[] mImageIds = { R.drawable.mlb201010yj001,
			R.drawable.mlb201010yj002, R.drawable.mlb201010yj003,
			R.drawable.mlb201010yj004, R.drawable.mlb201010yj005,
			R.drawable.mlb201010yj006, R.drawable.mlb201010yj007,
			R.drawable.mlb201010yj008 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		fatherid = (String) bundle.get("DataKey");
		Log.i(TAG, "The father id is " + fatherid);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // use this function to
														// hidden the up
														// application title.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.dolphin_specific_detail);

		description = (TextView) findViewById(R.id.textview_description);
		color = (TextView) findViewById(R.id.detail_color_textview_info);
		price = (TextView) findViewById(R.id.detail_prices_textview_info);
		size = (TextView) findViewById(R.id.detail_size_textview_info);

		buttonMoreInfo = (Button) findViewById(R.id.button_detail_info);
		buttonSaveIt = (Button) findViewById(R.id.button_save_it);
		buttonBuyIt = (Button) findViewById(R.id.button_buy);
		buttonMoreInfo.setOnClickListener(this);
		buttonSaveIt.setOnClickListener(this);
		buttonBuyIt.setOnClickListener(this);

		imageButton331 = (ImageButton) findViewById(R.id.imagebutton_331);
		imageButton332 = (ImageButton) findViewById(R.id.imagebutton_332);
		imageButton333 = (ImageButton) findViewById(R.id.imagebutton_333);
		imageButton334 = (ImageButton) findViewById(R.id.imagebutton_334);
		imageButtonBack = (ImageButton) findViewById(R.id.imagebutton_back);
		imageButton331.setOnClickListener(this);
		imageButton332.setOnClickListener(this);
		imageButton333.setOnClickListener(this);
		imageButton334.setOnClickListener(this);
		imageButtonBack.setOnClickListener(this);

		setupScreen();
	}

	@Override
	public void onPause() {
		Log.i(TAG, "onPause");
		super.onPause();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_back: {
			Log.i(TAG, "Back button has been pressed!");
			finish();
			break;
		}
		case R.id.button_buy: {
			Intent intent = new Intent(DolphinSpecificDetailActivity.this,
					BuyFormActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton_back: {
			Log.i(TAG, "back button");
			finish();
			break;
		}
		case R.id.button_detail_info: {
			if (checkHaveDetail(fatherid)) {
				Intent intent = new Intent(DolphinSpecificDetailActivity.this,
						CapDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("DataKey", fatherid);
				intent.putExtras(bundle);
				startActivityForResult(intent, 100);
			} else {
				Intent intent = new Intent(DolphinSpecificDetailActivity.this,
						OneBigImageShowActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("DataKey", fatherid);
				intent.putExtras(bundle);
				startActivityForResult(intent, 100);
			}
			break;
		}
		case R.id.button_save_it: {
			Intent intent = new Intent(DolphinSpecificDetailActivity.this,
					GalleryDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", fatherid);
			bundle.putInt("DataKey0", mImageIds[0]);
			bundle.putInt("DataKey1", mImageIds[1]);
			bundle.putInt("DataKey2", mImageIds[2]);
			bundle.putInt("DataKey3", mImageIds[3]);
			bundle.putInt("DataKey4", mImageIds[4]);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			finish();
			// Intent intent = new Intent(SpecificDetailActivity.this,
			// GalleryActivity.class);
			// startActivityForResult(intent, 100);
			break;
		}
		default: {
			Log.i(TAG, "Deatil Image Button has been pressed!");
			break;
		}
		}
	}

	public void setSpecificImage(String fid) {
		ImageView iv = (ImageView) findViewById(R.id.detail_specific_iv);
		iv.setBackgroundResource(getResources().getIdentifier(fid, "drawable",
				getPackageName()));
	}

	// Setup Screen is the internal function to setup the image data and add
	// them on to Layout.
	private void setupScreen() {
		if (setImageIdsByDB() == 1) {

			// As Gallery has been depressed by Google, i involved
			// HorizontalScrollView there.
			hsvLinearLayout = (HorizontalScrollViewLinearLayout) findViewById(R.id.hsv_linearlayout);
			hsvAdapter = new HorizontalScrollViewAdapter(this);
			// Setup the content of HorizontalScrollViewAdapter.
			for (int i = 0; i < mImageIds.length; i++) {
				Log.i(TAG, "in the for map screen: i is :" + i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("image", mImageIds[i]);
				// map.put("image", getResources().getDrawable(images[i]));
				map.put("index", (i + 1));
				hsvAdapter.addObject(map);
			}

			// Due to the ImageIds length is 1, we need resize the width to make
			// a better view.
			if (mImageIds.length == 1) {
				// Get LayoutParams variables.
				android.view.ViewGroup.LayoutParams params = hsvLinearLayout
						.getLayoutParams();
				// We have only one image there, so we need resize the
				// width to make it looks better.
				params.width = params.width / 2;
				hsvLinearLayout.setLayoutParams(params);
			}

			// Add adapter to the LinearLayout.
			hsvLinearLayout.setAdapter(hsvAdapter);
		}

		// // We also want to show context menu for longpressed items in the
		// // gallery
		// registerForContextMenu(g);
	}

	public void setupTextViews() {
		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		if (fatherid != null) {
			Cursor cursor = adapter.getTitleFromImagesDB(fatherid);
			if (cursor != null) {
				description.setText(cursor.getString(2));
				price.setText(cursor.getString(3));
				color.setText(cursor.getString(4));
				size.setText(cursor.getString(7));
			}
			cursor.close();
		} else {
			Log.i(TAG, "father is null");
		}
		adapter.close();

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
		Toast.makeText(this, "Longpress: " + info.position, Toast.LENGTH_SHORT)
				.show();
		return true;
	}

	public int setImageIdsByXML() {
		XMLParser myXmlParser = new XMLParser(this);
		myXmlParser.readXML();
		mImageIds = myXmlParser.getmImageIds();
		return mImageIds.length;
	}

	public int setImageIdsByDB() {
		DBAdapter adapter = new DBAdapter(this);
		final String DATABASE_TABLE_DETAIL = "imageresdetail";
		adapter.setDateBaseTableName(DATABASE_TABLE_DETAIL);
		adapter.open();
		int res_id = 0;
		Cursor cursor = adapter.getTitleFromDetailImages(fatherid);
		cursor.moveToFirst();
		Log.i(TAG, "The Data QUERY has been done");
		int i = -1;
		if (!cursor.isNull(0)) {
			i = cursor.getCount();
			Log.i(TAG, "The Data num is " + i);
		} else {
			Log.i(TAG, "The Data QUERY result cursor is null");
			return 0;
		}
		res_id = getResources().getIdentifier(cursor.getString(2), "drawable",
				getPackageName());
		Log.i(TAG, "Resource is is " + res_id);
		mImageIds = new Integer[i];
		String result = "";
		int count = 0;
		while (count < 5 && i == 5) {
			Log.i(TAG, "cursor is " + cursor.getString(2));
			res_id = getResources().getIdentifier(cursor.getString(2),
					"drawable", getPackageName());
			Log.i(TAG, "Resource is is " + res_id);
			mImageIds[count] = res_id;
			Log.i(TAG, "The count number is " + count);
			count++;
			Log.i(TAG, "DataBase : " + result + " i==" + i + " position="
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

	public boolean checkHaveDetail(String imageId) {
		DBAdapter adapter = new DBAdapter(this);
		final String DATABASE_TABLE_DETAIL = "imageresdetail";
		adapter.setDateBaseTableName(DATABASE_TABLE_DETAIL);
		adapter.open();
		Cursor cursor = adapter.getTitleFromDetailImages(imageId);
		if (cursor.getCount() <= 1) {
			cursor.close();
			adapter.close();
			return false;
		}
		cursor.close();
		adapter.close();
		return true;
	}
}

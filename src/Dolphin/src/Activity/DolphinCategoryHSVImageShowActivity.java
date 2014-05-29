
package Dolphin.src.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Dolphin.src.R;
import Dolphin.src.DatabaseProvider.DBAdapter;
import Dolphin.src.Util.HorizontalScrollViewLinearLayout;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * @author Kevin This activity is used to show how to use ImageSwitcher and
 *         HorizontalScrollView. Before that is used to show how to use Gallery,
 *         but its out of date, hence i updated with new mechanism here.
 *         2014-3-30
 * 
 */
public class DolphinCategoryHSVImageShowActivity extends Activity implements
		ViewFactory, OnClickListener {
	/** Called when the activity is first created. */
	ImageSwitcher mSwitcher;
	private final static String TAG = "DolphinCategoryHSVImageShowActivity";
	private Integer[] mThumbIds;
	private String[] imageMark;
	private Integer[] mImageIds;
	private ImageButton back;
	private int nCount = 0;

	// 2014-3-30 new add HorizontalScrollView to replace gallery.
	private HorizontalScrollViewLinearLayout hsvLinearLayout = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.dolphin_category_hsv_image_show);
		back = (ImageButton) findViewById(R.id.imagebutton_back);
		back.setOnClickListener(this);
		setImageIdsByDB();
		setTitle("ImageShowActivity");

		mSwitcher = (ImageSwitcher) findViewById(R.id.ImageSwitcher01);

		mSwitcher.setFactory(this);
		mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		//Setup the HSV linearLayout and its adapter.
		if (this.setupHSV()) {
			mSwitcher.setBackgroundColor(0xFFFFFF);
			mSwitcher.setImageResource(mImageIds[nCount]);
		}
	}

	private boolean setupHSV() {
		hsvLinearLayout = (HorizontalScrollViewLinearLayout) findViewById(R.id.hsv_linearlayout);
		ImageAdapterMyself imageAdapter = new ImageAdapterMyself(this);
		// Setup the content of HorizontalScrollViewAdapter.
		for (int i = 0; i < mImageIds.length; i++) {
			Log.i(TAG, "in the for map screen: i is :" + i);
			Map<String, Object> map = new HashMap<String, Object>();
			// If the DB have too many images there and we add them all into the
			// LinearLayout, it will be out of memory.
			// so i only allow add 10 images on to it.
			if (i == 10)
				break;
			map.put("image", mImageIds[i]);
			// map.put("image", getResources().getDrawable(images[i]));
			map.put("index", (i + 1));
			imageAdapter.addObject(map);
		}

		hsvLinearLayout.setAdapter(imageAdapter);
		return true;

	}


	public View makeView() {
		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setBackgroundColor(0xFFFFF0);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		return imageView;
	}
	
	//Internal Adapter only for DolphinCategoryHSVImageShowActivity use, this one is colon one form Adapter/HorizontalScrollViewAdapter class.
	private class ImageAdapterMyself extends BaseAdapter {
		private Context mContext;
		private List<Map<String, Object>> list;

		public ImageAdapterMyself(Context c) {
			mContext = c;
			this.list = new ArrayList<Map<String, Object>>();
		}

		public void addObject(Map<String, Object> map) {
			list.add(map);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Map<String, Object> getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// This Adapter is designed for HorizontalScrollView to add some
			// ImageView items on it.
			ImageView imageView = new ImageView(mContext);
			Map<String, Object> map = getItem(position); // 获取当前的Item
			imageView.setBackgroundResource((Integer) map.get("image"));
			final int local_count = position;
			//If user press any item on the HSV LinearLayout, we should update the ImageSwitcher.
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mSwitcher.setBackgroundColor(0xFFFFFF);
					mSwitcher.setImageResource(mImageIds[local_count]);
					 Toast.makeText(DolphinCategoryHSVImageShowActivity.this, "hihi" +
							 local_count,
					 Toast.LENGTH_SHORT).show();
				}
			});
			return imageView;
		}
	}

	public int setImageIdsByDB() {

		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		int res_id = 0;
		Cursor cursor = adapter.getAllTitles();
		cursor.moveToFirst();
		Log.i(TAG, "The Data QUERY has been done");
		int i = -1;
		if (!cursor.isNull(0)) {
			i = cursor.getCount();
			Log.i(TAG, "The Data num is " + i);
		} else {
			Log.i(TAG, "The Data QUERY result cursor is null");
		}
		res_id = getResources().getIdentifier(cursor.getString(2), "drawable",
				getPackageName());
		Log.i(TAG, "Resource is is " + res_id);
		mImageIds = new Integer[i];
		mThumbIds = new Integer[i];

		imageMark = new String[i];
		// description = new String[i];
		String result = "";
		int count = 0;
		while (count < i) {
			Log.i(TAG, "cursor is " + cursor.getString(2));
			res_id = getResources().getIdentifier(cursor.getString(2),
					"drawable", getPackageName());
			Log.i(TAG, "Resource is is " + res_id);
			mImageIds[count] = res_id;
			mThumbIds[count] = res_id;
			imageMark[count] = cursor.getString(2);
			// description[count]=cursor.getString(3);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.imagebutton_back: {
			finish();
			break;
		}
		default:
			break;
		}

	}

}

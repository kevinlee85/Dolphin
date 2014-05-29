/**
 * 
 */
package Dolphin.src.Activity;

import Dolphin.src.R;
import Dolphin.src.DatabaseProvider.DBAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * @author Administrator
 * 
 */
public class CapDetailActivity extends Activity implements OnClickListener {

	private Button buttonBuy = null;
	private ImageButton imagebuttondetail1_1 = null;
	private ImageButton imagebuttondetail1_2 = null;
	private ImageButton imagebuttondetail2_1 = null;
	private ImageButton imagebuttondetail2_2 = null;
	static private String tag = "TAG_CapDeatil";
	private String fatherid = null;
	private String dibs1_1 = null;
	private String dibs1_2 = null;
	private String dibs2_1 = null;
	private String dibs2_2 = null;
	private ImageButton imageButtonBack;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("TAG", "Detail buttton has been pressed.");
		requestWindowFeature(Window.FEATURE_NO_TITLE); //use this function to hidden the up application title.
		setContentView(R.layout.dolphin_detail_category);
		buttonBuy = (Button) findViewById(R.id.button_buy);
		buttonBuy.setOnClickListener(this);
		
		imageButtonBack = (ImageButton) findViewById(R.id.imagebutton_back);
		imageButtonBack.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(arg0.getId() == R.id.imagebutton_back)
					finish();
				
			}
			
		});

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		fatherid = (String) bundle.get("DataKey");
		Log.i(tag, "The father id is " + fatherid);
		if(checkHaveDetail(fatherid))
			detailImageSetup();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_buy: {
			Log.i(tag, "Buy button has been pressed!");
			Intent intent = new Intent(CapDetailActivity.this,
					BuyFormActivity.class);
			startActivityForResult(intent, 100);
			// finishActivity(100);
//			finish();
			// finishActivityFromChild(this.f,100);
			break;
		}
		case R.id.detailimagebutton1_1: {
			Intent intent = new Intent(CapDetailActivity.this,
					OneBigImageShowActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", dibs1_1);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			Log.i(tag, "detailimagebutton1_1 has been pressed!");
			break;
		}
		case R.id.detailimagebutton1_2: {
			Intent intent = new Intent(CapDetailActivity.this,
					OneBigImageShowActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", dibs1_2);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.detailimagebutton2_1: {
			Intent intent = new Intent(CapDetailActivity.this,
					OneBigImageShowActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", dibs2_1);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.detailimagebutton2_2: {
			Intent intent = new Intent(CapDetailActivity.this,
					OneBigImageShowActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", dibs2_2);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		}
	}

	public void detailImageSetup() {
		imagebuttondetail1_1 = (ImageButton) findViewById(R.id.detailimagebutton1_1);
		imagebuttondetail1_1.setOnClickListener(this);
		imagebuttondetail1_2 = (ImageButton) findViewById(R.id.detailimagebutton1_2);
		imagebuttondetail1_2.setOnClickListener(this);
		imagebuttondetail2_1 = (ImageButton) findViewById(R.id.detailimagebutton2_1);
		imagebuttondetail2_1.setOnClickListener(this);
		imagebuttondetail2_2 = (ImageButton) findViewById(R.id.detailimagebutton2_2);
		imagebuttondetail2_2.setOnClickListener(this);

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
		String result = "";
		int count = 0;
		while (count < 4) {
			Log.i(tag, "cursor is " + cursor.getString(2));
			res_id = getResources().getIdentifier(cursor.getString(2),
					"drawable", getPackageName());
			Log.i(tag, "Resource is is " + res_id);
			try {
				switch (count) {
				case 0: {
					imagebuttondetail1_1.setBackgroundResource(res_id);
					dibs1_1 = cursor.getString(2);
					Log.i(tag, "The count ==0");
					break;
				}
				case 1: {
					imagebuttondetail1_2.setBackgroundResource(res_id);
					dibs1_2 = cursor.getString(2);
					Log.i(tag, "The count ==1");
					break;
				}
				case 2: {
					imagebuttondetail2_1.setBackgroundResource(res_id);
					dibs2_1 = cursor.getString(2);
					Log.i(tag, "The count ==2");
					break;
				}
				case 3: {
					imagebuttondetail2_2.setBackgroundResource(res_id);
					dibs2_2 = cursor.getString(2);
					Log.i(tag, "The count ==3");
					break;
				}
				}
			} catch (Exception e) {
				Log.i("TAG", "Exception is " + e.toString());

			}

			Log.i(tag, "The count number is " + count);
			count++;
			result = result
					+ (Integer.toString(cursor.getPosition()) + ","
							+ Integer.toString(cursor.getInt(0)) + ","
							+ cursor.getString(1) + "," + cursor.getString(2)
							+ "," + cursor.getString(3) + "\n");
			Log.i(tag, "DataBase : " + result + " i==" + i + " position="
					+ Integer.toString(cursor.getPosition()));
			cursor.moveToNext();
		}// while (i > 0)
		adapter.close();
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

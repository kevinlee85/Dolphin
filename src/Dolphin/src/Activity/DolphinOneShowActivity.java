/**
 * 
 */
package Dolphin.src.Activity;

import Dolphin.src.DolphinActivity;
import Dolphin.src.R;
import Dolphin.src.DatabaseProvider.DBAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Administrator
 * 
 */
public class DolphinOneShowActivity extends Activity implements OnClickListener {
	static private String tag = "DolphinONEShow";
	private ImageButton buttonPreview = null;
	private ImageButton buttonNext = null;
	private ImageButton imagebuttonOneShow = null;
	private ImageButton imagebuttonfourshow = null;
	private ImageButton imagebuttononeshow = null;
	private int page_main = 1;
	private String imageshow = null;
	private TextView description;
	private ImageButton imageButtonBack;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); //use this function to hidden the up application title.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//use this to disable the auto rotate function. Only allow Portrait.
		
		setContentView(R.layout.dolphin_dolphin_one_show);

		buttonPreview = (ImageButton) findViewById(R.id.button_preview);
		buttonPreview.setOnClickListener(this);

		buttonNext = (ImageButton) findViewById(R.id.button_next);
		buttonNext.setOnClickListener(this);
		
		description = (TextView) findViewById(R.id.oneshow_textview);

		imagebuttonOneShow = (ImageButton) findViewById(R.id.imagebutton_oneshow);
		imagebuttonOneShow.setOnClickListener(this);
		
		imagebuttonfourshow=(ImageButton) findViewById(R.id.fourshow);
		imagebuttonfourshow.setOnClickListener(this);
		
		imagebuttononeshow=(ImageButton) findViewById(R.id.oneshow);
		imagebuttononeshow.setOnClickListener(this);
		
		imageButtonBack=(ImageButton) findViewById(R.id.imagebutton_back);
		imageButtonBack.setOnClickListener(this);
		
		installImage(1);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_preview: {
			Log.i("TAG", "Preview buttton has been pressed.");
			if (page_main != 1) {
				page_main--;
			}
			if (installImage(page_main) == 0) {
				buttonNext.setBackgroundResource(R.drawable.next2);
			}
			if (isLastPage(page_main)) {
				buttonPreview.setBackgroundResource(R.drawable.pre1);
			}
			break;
		}
		case R.id.button_next: {
			Log.i("TAG", "Next buttton has been pressed.");
			if (!isLastPage(page_main))
				page_main++;
			if (isLastPage(page_main))
				buttonNext.setBackgroundResource(R.drawable.next1);
			if (page_main > 1) {
				buttonPreview.setBackgroundResource(R.drawable.pre2);
			}
			// If the result code of this function is 1 which means we got the
			// end page of the db.
			if (installImage(page_main) == 1) {
				// buttonPreview.setBackgroundResource(R.drawable.pre2);
				buttonNext.setBackgroundResource(R.drawable.next1);
			}

			break;
		}
		case R.id.fourshow: {
			Log.i("TAG", "detailimagebutton2_2 has been pressed.");
			Intent intent = new Intent(DolphinOneShowActivity.this,
					DolphinActivity.class);
			startActivity(intent);

			break;
		}
		case R.id.oneshow: {
			Toast.makeText(this, "OneShowCategory ", Toast.LENGTH_SHORT).show();
			break;
		}
		case R.id.imagebutton_oneshow:{
			Intent intent = new Intent(DolphinOneShowActivity.this, DolphinSpecificDetailActivity.class);
			Bundle bundle = new Bundle();
			Log.i(tag,"Imagebutton has been pressed the button id is "+ imageshow);
			bundle.putString("DataKey", imageshow);
			intent.putExtras(bundle);
			this.startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton_back:{
			finish();
			break;
		}
		}
	}

	/**
	 * ${tags} installImage() This function is used to load all the caps images
	 * name to map with resource file R and setup in the mian_category.xml file.
	 */
	public int installImage(int page) {
		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		int res_id = 0;
		Cursor cursor = adapter.getAllTitles();
		cursor.moveToFirst();
		Log.i(tag, "The Data QUERY has been done");
		if (page == 1) {
			buttonPreview.setBackgroundResource(R.drawable.pre1);
			buttonNext.setBackgroundResource(R.drawable.next2);
		}

		if (cursor.getCount() <= 0) {
			Log.i(tag, "The lasting db is smaller then nil.");
			cursor.close();
			adapter.close();
			return 0;
		}
		while (page > 1) {
			cursor.moveToNext();
			page--;
		}
		res_id = getResources().getIdentifier(cursor.getString(2), "drawable",
				getPackageName());
		imagebuttonOneShow.setBackgroundResource(res_id);
		description.setText(cursor.getString(3));
		imageshow = cursor.getString(2);
		Log.i(tag,"imageshow is "+imageshow);
		cursor.close();
		adapter.close();
		return 0;
	}

	protected boolean isLastPage(int page) {
		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		Cursor cursor = adapter.getAllTitles();
		cursor.moveToFirst();
		int count = cursor.getCount();
		cursor.close();
		adapter.close();
		if (page == count) {
			return true;
		}
		return false;
	}
}

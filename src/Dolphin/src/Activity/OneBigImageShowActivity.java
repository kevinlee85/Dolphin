/**
 * 
 */
package Dolphin.src.Activity;

import Dolphin.src.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * @author Administrator
 * 
 */
public class OneBigImageShowActivity extends Activity implements
		OnClickListener {

	private String fatherid;
	private ImageView miv;
	private ImageButton imageButtonBack;
	private Button buttonbuy;
	private static final String tag = "OneBigImageShowActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(tag, "In");
		requestWindowFeature(Window.FEATURE_NO_TITLE); // use this function to
														// hidden the up
														// application title.
		setContentView(R.layout.dolphin_one_big_image_show);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null)
			fatherid = (String) bundle.get("DataKey");

		imageButtonBack = (ImageButton) findViewById(R.id.toolbar_button_back);
		imageButtonBack.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				if (arg0.getId() == R.id.toolbar_button_back)
					finish();
			}

		});

		buttonbuy = (Button) findViewById(R.id.button_buy);
		buttonbuy.setOnClickListener(this);
		miv = (ImageView) findViewById(R.id.detail_specific_iv);
		if (fatherid != null) {
			int res_id = getResources().getIdentifier(fatherid, "drawable",
					getPackageName());
			miv.setBackgroundResource(res_id);
		}
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
			Intent intent = new Intent(OneBigImageShowActivity.this,
					BuyFormActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		default: {
			break;
		}
		}
	}

}

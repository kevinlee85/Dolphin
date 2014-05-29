package Dolphin.src.Activity;

import Dolphin.src.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewsDetailInformationActivity extends Activity implements
		OnClickListener {
	private ImageButton toolbarButton_back = null;
	private TextView tv_content = null;
	private TextView tv_title = null;
	private static String TAG = "NewsDetailInformationActivity";
	private String newsContent = "The News Content is coming soon";
	private String newsTitle = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // use this function to
														// hidden the up
														// application title.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//use this to disable the auto rotate function. Only allow Portrait.
		
		setContentView(R.layout.news_function_news_detail_information_show);
		
		toolbarButton_back = (ImageButton) findViewById(R.id.toolbar_button_back);
		toolbarButton_back.setOnClickListener(this);
		tv_content = (TextView) findViewById(R.id.news_information_content);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		newsContent = (String) bundle.get("DataKey");
		tv_content.setText(newsContent);
		
		tv_title = (TextView) findViewById(R.id.toolbar_title);
		newsTitle = (String) bundle.get("DataKey_Title");
		if(newsTitle != null)
			tv_title.setText(newsTitle);

	}

	@Override
	public void onClick(View v) {
		// Handle the toolbar button events
		switch (v.getId()) {
		//Back button, if user pressed it and back to the news list screen.
		case R.id.toolbar_button_back: {
			Log.i(TAG, "toolbarButton_back button has been pressed!");
			Intent intent = new Intent(NewsDetailInformationActivity.this,
					NewsListViewActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		}
	}
}

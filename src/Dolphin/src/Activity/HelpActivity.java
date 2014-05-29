/**
 * 
 */
package Dolphin.src.Activity;

import Dolphin.src.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Administrator
 *
 */
public class HelpActivity extends Activity implements OnClickListener {
	private Button buttonBack = null;
	static private String tag = "TAG_HelpActivity";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //use this function to hidden the up application title.
		setContentView(R.layout.help);
		Log.i(tag, "Help screen");
		buttonBack = (Button) findViewById(R.id.help_back);
		buttonBack.setOnClickListener(this);
	}
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.help_back: {
			Log.i(tag, "Back button has been pressed!");
			finish();
			break;
		}
		default: {
			Log.i(tag, "Deatil Image Button has been pressed!");
			break;
		}
		}
		// TODO Auto-generated method stub
		
	}

}

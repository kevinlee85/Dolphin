/**
 * 
 */
package Dolphin.src.Activity;

import Dolphin.src.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

/**
 * @author Administrator
 *
 */
public class BuyFormActivity extends Activity {
	
	static private String tag = "TAG_BuyForm";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //use this function to hidden the up application title.
		setContentView(R.layout.buy_form);
		Log.i(tag,"buyformactivity");
	}

}

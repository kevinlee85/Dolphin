package Dolphin.src.Activity;

import Dolphin.src.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class DolphinBrowsingTabsFragmentActivity extends FragmentActivity {
	private ImageButton back;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.dolphin_browsing_tabs_fragment);
	        back = (ImageButton) findViewById(R.id.imagebutton_back);
			back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				} 
				
			});
	    }
}

package Dolphin.src.Activity;

import Dolphin.src.R;
import Dolphin.src.Fragments.FragmentFactory;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Build;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DolphinBrowsingTabsFragmentNewActivity extends Activity {
	private FragmentManager fragmentManager;
	private RadioGroup radioGroup;
	final static private String TAG = "DolphinBrowsingTabsFragmentNewActivity";

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dolphin_browsing_tabs_fragment_new);

		fragmentManager = getFragmentManager();
		radioGroup = (RadioGroup) findViewById(R.id.rg_tab);
		final RadioButton rb1=(RadioButton)findViewById(R.id.myRadioButton1);
		final RadioButton rb2=(RadioButton)findViewById(R.id.myRadioButton2);
		final RadioButton rb3=(RadioButton)findViewById(R.id.myRadioButton3);
		final RadioButton rb4=(RadioButton)findViewById(R.id.myRadioButton4);
		final RadioButton rb5=(RadioButton)findViewById(R.id.myRadioButton5);
		
		radioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						FragmentTransaction transaction = fragmentManager
								.beginTransaction();
						Log.d(TAG, "checkId is :" + checkedId);
						int fragmentId = 0;
						if (rb1.getId() == checkedId)
							fragmentId =1;
						if (rb2.getId() == checkedId)
							fragmentId =2;
						if (rb3.getId() == checkedId)
							fragmentId =3;
						if (rb4.getId() == checkedId)
							fragmentId =4;
						if (rb5.getId() == checkedId)
							fragmentId =5;
						
						Fragment fragment = FragmentFactory
								.getInstanceByIndex(fragmentId);
						transaction.replace(R.id.content, fragment);
						transaction.commit();
					}
				});
	}

}

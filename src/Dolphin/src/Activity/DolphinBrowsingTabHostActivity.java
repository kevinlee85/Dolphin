/**
 * 
 */
package Dolphin.src.Activity;

import Dolphin.src.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;

/**
 * @author Administrator
 * 
 */
public class DolphinBrowsingTabHostActivity extends TabActivity implements OnClickListener {
	private static String tag = "TabHostActivity";
	private ImageButton back;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //use this function to hidden the up application title.
		setContentView(R.layout.dolphin_brwosing_tabhost);
		back = (ImageButton) findViewById(R.id.imagebutton_back);
		back.setOnClickListener(this);
		Log.i(tag, "in");
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent,intent1,intent2; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, ListViewActivity.class);
		intent1 = new Intent().setClass(this, ListViewActivityPriceDown.class);
		intent2 = new Intent().setClass(this, ListViewActivityPriceUp.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("dolphin").setIndicator("By Price Down",
				res.getDrawable(R.drawable.showmethod1)).setContent(intent1);
		
		// without icon
		// spec = tabHost.newTabSpec("dolphin").setIndicator("By Price Down",
		// null)
		// .setContent(intent);
		tabHost.addTab(spec);
		

		// Do the same for the other tabs
		intent = new Intent().setClass(this, ListViewActivity.class);
		spec = tabHost.newTabSpec("listview").setIndicator("By Price Up",
				res.getDrawable(R.drawable.showmethod2)).setContent(intent2);
		// spec = tabHost.newTabSpec("listview").setIndicator("By Price Up",
		// null)
		// .setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ListViewActivity.class);
		spec = tabHost.newTabSpec("gallery").setIndicator("By Date",
				res.getDrawable(R.drawable.showmethod3)).setContent(intent);
		// spec = tabHost.newTabSpec("gallery").setIndicator("By Date",
		// null)
		// .setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(2);
//		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {   
//			  
//            public void onTabChanged(String tabId) {   
//                Dialog dialog = new AlertDialog.Builder(TabHostActivity.this)   
//                        .setTitle("ÌבÊ¾").setMessage(   
//"The Tab " + tabId + " has been selected!")
//						.setIcon(R.drawable.icon1).setPositiveButton("OK",
//								new DialogInterface.OnClickListener(){   
//  
//                                    public void onClick(DialogInterface dialog,   
//                                            int which) {   
//                                        // TODO Auto-generated method stub   
//                                           
//                                    }   
//                                       
//                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){   
//  
//                                    public void onClick(DialogInterface dialog,   
//                                            int which) {   
//                                    	
//                                        // TODO Auto-generated method stub   
//                                            }}).create();   
//                dialog.show();   
//  
//            }   
//  
//        });   

		tabHost.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imagebutton_back: {
			finish();
			break;
		}

		}
	}

}

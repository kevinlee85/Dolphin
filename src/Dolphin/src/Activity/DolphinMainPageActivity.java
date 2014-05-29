/**
 * 
 */
package Dolphin.src.Activity;

import Dolphin.src.DolphinActivity;
import Dolphin.src.R;
import Dolphin.src.DatabaseProvider.DBSetup;
import Dolphin.src.Dialog.HelpDialog;
import Dolphin.src.Dialog.LoginDialog;
import Dolphin.src.Dialog.RegisterDialog;
import Dolphin.src.XMLHandler.AndroidXMLDemoSaxII;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * @author Administrator
 *
 */
public class DolphinMainPageActivity extends Activity implements OnClickListener {
	static private String tag = "mainActivity";
	private ImageButton button_search;
	private Button buttonmlb;
	private Button butttonothers;
	private Button buttoncategory;
	private Button buttonbrowsing;
	private ImageButton buttonhome;
	private ImageButton buttonhelp;
	private ImageButton buttonlogin;
	public Intent intent;
	private EditText search;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); //use this function to hidden the up application title.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//use this to disable the auto rotate function. Only allow Portrait.
		
		setContentView(R.layout.dolphin_mlb);
		
		button_search = (ImageButton)findViewById(R.id.button_search);
		buttonmlb = (Button)findViewById(R.id.buttonmlb);
		butttonothers = (Button)findViewById(R.id.butttonothers);
		buttoncategory = (Button)findViewById(R.id.buttoncategory);
		buttonbrowsing = (Button)findViewById(R.id.buttonbrowsing);
		buttonhome = (ImageButton)findViewById(R.id.buttonhome);
		buttonhelp = (ImageButton)findViewById(R.id.buttonhelp);
		buttonlogin = (ImageButton)findViewById(R.id.buttonlogin);
		search = (EditText)findViewById(R.id.edittext_search);
		search.setOnClickListener(this);
		button_search.setOnClickListener(this);
		buttonmlb.setOnClickListener(this);
		butttonothers.setOnClickListener(this);
		buttoncategory.setOnClickListener(this);
		buttonbrowsing.setOnClickListener(this);
		buttonhome.setOnClickListener(this);
		buttonhelp.setOnClickListener(this);
		buttonlogin.setOnClickListener(this);
		
	}


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_search: {
			Log.i(tag,"button search has been pressed");
			Toast.makeText(this, "Search Content is "+search.getText(), Toast.LENGTH_SHORT)
			.show();
			search.setText("");
			break;
			}
		case R.id.buttonmlb:{
			Log.i(tag,"buttonmlb has been pressed");
			Intent intent = new Intent(DolphinMainPageActivity.this, DolphinActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.butttonothers:{
			Log.i(tag,"buttonOthers has been pressed");
			Intent intent = new Intent(DolphinMainPageActivity.this, GalleryActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.buttoncategory:{
			Log.i(tag,"buttonCategory has been pressed");
			Intent intent = new Intent(DolphinMainPageActivity.this, DolphinCategoryHSVImageShowActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.buttonbrowsing:{
			Log.i(tag,"buttonBrowsing has been pressed");
//			Intent intent = new Intent(DolphinMainPageActivity.this, DolphinBrowsingTabHostActivity.class);
			//2014-3-31 New add use tabs fragment Activity to show the tab function.
//			Intent intent = new Intent(DolphinMainPageActivity.this, DolphinBrowsingTabsFragmentActivity.class);
			//2014-4-2 New add fragment works for the new function.
			Intent intent = new Intent(DolphinMainPageActivity.this, DolphinBrowsingTabsFragmentNewActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.buttonhome:{
			Log.i(tag,"buttonmlb has been pressed");
			RegisterDialog rd = new RegisterDialog(this);
			rd.createDialogAdd().show();
			break;
		}
		case R.id.buttonhelp:{
			Log.i(tag,"buttonmlb has been pressed");
			HelpDialog hpd = new HelpDialog(this);
			hpd.show();
			break;
		}
		case R.id.buttonlogin:{
			Log.i(tag,"buttonmlb has been pressed");
			LoginDialog rd = new LoginDialog(this);
			rd.createDialog().show();
			break;
		}
		case R.id.edittext_search:{
			search.setText("");
			break;
		}
		default:{
			Log.i(tag,"button has been pressed");
			break;
		}
		}
	}
	
	//Below codes is about menu feature adding.
	// 2010-10-19 Added Menu Handler
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, "Loadingscreen");
		menu.add(0, 1, 0, "XMLTest");
		menu.add(0, 2, 0, "Register");
		menu.add(0, 3, 0, "Login");
		menu.add(0, 4, 0, "Help");
		menu.add(0, 5, 0, "Favourate");
	    menu.add(0, 6, 0, "InitDataBase");
		menu.add(0, 7, 0, "Exit");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 0:
			intent = new Intent(DolphinMainPageActivity.this,
					LoadingScreenActivity.class);
			startActivityForResult(intent, 100);
			break;
		case 1:   //XMLTest
			intent = new Intent(DolphinMainPageActivity.this, AndroidXMLDemoSaxII.class);
			startActivityForResult(intent, 100);
			break;
		case 2: { //Register
			RegisterDialog rd = new RegisterDialog(this);
			rd.createDialogAdd().show();
			break;
		}
		case 3:{  //Login
			LoginDialog rd = new LoginDialog(this);
			rd.createDialog().show();
			finish();
			break;
		}
		case 4: {  //Help
			HelpDialog hpd = new HelpDialog(this);
			hpd.show();
			// Intent intent = new Intent(Dolphin.this, HelpActivity.class);
			// startActivityForResult(intent, 100);
			break;
		}
		case 5: { // My favourate
			Toast.makeText(this, "Search button be pressed!", Toast.LENGTH_SHORT)
			.show();
			break;
		}
		case 6:{
		    initDatabase();
		    break;
		}
		case 7: {
		    finish();
			// setContentView(R.layout.register_form);
			break;
		}
		default:
			Log.i(tag, "Menu Default handler");
			break;
		}
		return false;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}
	
	protected void initDatabase(){
        // ** Database setup code which should open on the first run.
        DBSetup setup = new DBSetup(this);
        if(setup.mainDBSetup()!=1)
        {
        Log.i(tag,"Something is wrong during the mianDBSetup function");
        return;
        }
        //Insert Detail images.
        //setup = new DBSetup(this);
        if(setup.detailDBSetup()!=1)
        {
        Log.i(tag,"Something is wrong during the detailDBSetup function");
        return;
        }
        // DB setup over.
        // Register DB create
        // DBSetup setup = new DBSetup(this);
        setup.registerDBSetup();
   } 
}

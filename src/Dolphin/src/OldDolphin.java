package Dolphin.src;

import Dolphin.src.Activity.BuyFormActivity;
import Dolphin.src.Activity.CapDetailActivity;
import Dolphin.src.Activity.GalleryActivity;
import Dolphin.src.Activity.ListViewActivity;
import Dolphin.src.Activity.LoadingScreenActivity;
import Dolphin.src.Activity.DolphinBrowsingTabHostActivity;
import Dolphin.src.DatabaseProvider.DBAdapter;
import Dolphin.src.Dialog.HelpDialog;
import Dolphin.src.Dialog.LoginDialog;
import Dolphin.src.Dialog.RegisterDialog;
import Dolphin.src.XMLHandler.AndroidXMLDemoSaxII;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class OldDolphin extends Activity implements OnClickListener {
	private static final int CONFIRM_DIALOG = 0;
	private Button buttonPreview = null;
	private Button buttonNext = null;
	private Button buttonDetail = null;
	private Button buttonExit = null;
	private ImageButton imagebutton1_1 = null;
	private ImageButton imagebutton1_2 = null;
	private ImageButton imagebutton1_3 = null;
	private ImageButton imagebutton2_1 = null;
	private ImageButton imagebutton2_2 = null;
	private ImageButton imagebutton2_3 = null;
	private ImageButton imagebutton3_1 = null;
	private ImageButton imagebutton3_2 = null;
	private ImageButton imagebutton3_3 = null;
	private String ibs1_1 = null;
	private String ibs1_2 = null;
	private String ibs1_3 = null;
	private String ibs2_1 = null;
	private String ibs2_2 = null;
	private String ibs2_3 = null;
	private String ibs3_1 = null;
	private String ibs3_2 = null;
	private String ibs3_3 = null;

	private int page_main = 1;

	static private String tag = "Dolphin";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// DBSetup setup = new DBSetup(this);
		// if(setup.mainDBSetup()!=1)
		// {
		// Log.i(tag,"Something is wrong during the mianDBSetup function");
		// return;
		// }
		// //Insert Detail images.
		// //setup = new DBSetup(this);
		// if(setup.detailDBSetup()!=1)
		// {
		// Log.i(tag,"Something is wrong during the detailDBSetup function");
		// return;
		// }
		// DB setup over.
		// Register DB create
//		DBSetup setup = new DBSetup(this);
//		setup.registerDBSetup();
		
		setContentView(R.layout.main_category);
		buttonPreview = (Button) findViewById(R.id.button_preview);
		buttonPreview.setOnClickListener(this);

		buttonNext = (Button) findViewById(R.id.button_next);
		buttonNext.setOnClickListener(this);

		buttonDetail = (Button) findViewById(R.id.button_detail);
		buttonDetail.setOnClickListener(this);

		buttonExit = (Button) findViewById(R.id.button_exit);
		buttonExit.setOnClickListener(this);

		imagebutton1_1 = (ImageButton) findViewById(R.id.imagebutton1_1);
		imagebutton1_2 = (ImageButton) findViewById(R.id.imagebutton1_2);
		imagebutton1_3 = (ImageButton) findViewById(R.id.imagebutton1_3);
		imagebutton2_1 = (ImageButton) findViewById(R.id.imagebutton2_1);
		imagebutton2_2 = (ImageButton) findViewById(R.id.imagebutton2_2);
		imagebutton2_3 = (ImageButton) findViewById(R.id.imagebutton2_3);
		imagebutton3_1 = (ImageButton) findViewById(R.id.imagebutton3_1);
		imagebutton3_2 = (ImageButton) findViewById(R.id.imagebutton3_2);
		imagebutton3_3 = (ImageButton) findViewById(R.id.imagebutton3_3);

		imagebutton1_1.setOnClickListener(this);
		imagebutton1_2.setOnClickListener(this);
		imagebutton1_3.setOnClickListener(this);
		imagebutton2_1.setOnClickListener(this);
		imagebutton2_2.setOnClickListener(this);
		imagebutton2_3.setOnClickListener(this);
		imagebutton3_1.setOnClickListener(this);
		imagebutton3_2.setOnClickListener(this);
		imagebutton3_3.setOnClickListener(this);

		installImage(1);
	}

	/**
	 * ${tags} installImage() This function is used to load all the caps images
	 * name to map with resource file R and setup in the mian_category.xml file.
	 */
	public void installImage(int page) {
		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		int res_id = 0;
		Cursor cursor = adapter.getAllTitles();
		cursor.moveToFirst();
		Log.i("TAG", "The Data QUERY has been done");
		int i = -1;
		if (cursor.getCount() <= 0) {
			Log.i(tag, "The lasting db is smaller then nil.");
			cursor.close();
			adapter.close();
			return;
		}
		String result = "";
		int count = 0;
		i = cursor.getCount();
		while (page > 1) {
			for (int k = 0; k < 9; k++) {
				cursor.moveToNext();
			}
			page--;
			i = i - 9;
		}
		Log.i(tag, "the iiiiiiiiiii numbe is:" + i);
		while (i > 0) {
			Log.i(tag, "cursor is " + cursor.getString(3));
			res_id = getResources().getIdentifier(cursor.getString(3),
					"drawable", getPackageName());
			Log.i(tag, "Resource is is " + res_id);
			try {
				switch (count) {
				case 0: {
					imagebutton1_1.setBackgroundResource(res_id);
					ibs1_1 = cursor.getString(3);
					break;
				}
				case 1: {
					imagebutton1_2.setBackgroundResource(res_id);
					ibs1_2 = cursor.getString(3);
					break;
				}
				case 2: {
					imagebutton1_3.setBackgroundResource(res_id);
					ibs1_3 = cursor.getString(3);
					break;
				}
				case 3: {
					imagebutton2_1.setBackgroundResource(res_id);
					ibs2_1 = cursor.getString(3);
					break;
				}
				case 4: {
					imagebutton2_2.setBackgroundResource(res_id);
					ibs2_2 = cursor.getString(3);
					break;
				}
				case 5: {
					imagebutton2_3.setBackgroundResource(res_id);
					ibs2_3 = cursor.getString(3);
					break;
				}
				case 6: {
					imagebutton3_1.setBackgroundResource(res_id);
					ibs3_1 = cursor.getString(3);
					break;
				}
				case 7: {
					imagebutton3_2.setBackgroundResource(res_id);
					ibs3_2 = cursor.getString(3);
					break;
				}
				case 8: {
					imagebutton3_3.setBackgroundResource(res_id);
					ibs3_3 = cursor.getString(3);
					break;
				}
				}
			} catch (Exception e) {
				Log.i("TAG", "Exception is " + e.toString());

			}
			count++;
			if (count == 9)
				break;
			result = result
					+ (Integer.toString(cursor.getPosition()) + ","
							+ Integer.toString(cursor.getInt(0)) + ","
							+ cursor.getString(1) + "," + cursor.getString(2)
							+ "," + cursor.getString(3) + "\n");
			// Log.i(tag, "DataBase : " + result + " i==" + i + " position="
			// + Integer.toString(cursor.getPosition()));
			cursor.moveToNext();
			i--;
		}// while (i > 0)
		// This while loop is used to set the white background for the final
		// page of main screen.
		while (count < 9) {
			try {
				switch (count) {
				case 0: {
					imagebutton1_1.setBackgroundColor(Color.WHITE);
					break;
				}
				case 1: {
					imagebutton1_2.setBackgroundColor(Color.WHITE);
					Log.i("TAG", "The count ==1");
					break;
				}
				case 2: {
					imagebutton1_3.setBackgroundColor(Color.WHITE);
					Log.i("TAG", "The count ==2");
					break;
				}
				case 3: {
					imagebutton2_1.setBackgroundColor(Color.WHITE);
					break;
				}
				case 4: {
					imagebutton2_2.setBackgroundColor(Color.WHITE);
					break;
				}
				case 5: {
					imagebutton2_3.setBackgroundColor(Color.WHITE);
					break;
				}
				case 6: {
					imagebutton3_1.setBackgroundColor(Color.WHITE);
					break;
				}
				case 7: {
					imagebutton3_2.setBackgroundColor(Color.WHITE);
					break;
				}
				case 8: {
					imagebutton3_3.setBackgroundColor(Color.WHITE);
					break;
				}
				}
			} catch (Exception e) {
				Log.i("TAG", "Exception is " + e.toString());

			}
			count++;
			if (count == 9)
				break;
			// Log.i(tag, "DataBase : " + result + " i==" + i + " position="
			// + Integer.toString(cursor.getPosition()));
		}// while (i > 0)
		cursor.close();
		adapter.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View) This
	 * function is used to handle the button pressed during this image.
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_preview: {
			Log.i("TAG", "Preview buttton has been pressed.");
			if (page_main != 1)
				page_main--;
			installImage(page_main);
			break;
		}
		case R.id.button_next: {
			Log.i("TAG", "Next buttton has been pressed.");
			if (page_main != 5)
				page_main++;
			installImage(page_main);
			break;
		}
		case R.id.button_detail: {
			Log.i("TAG", "Detail button has been pressed.");
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			startActivityForResult(intent, 100);
			// setContentView(R.layout.detail_category);
			// buttonBack = (Button) findViewById(R.id.button_back);
			// buttonBack.setOnClickListener(this);
			break;
		}
		case R.id.button_back: {
			Log.i("TAG", "Detail buttton has been pressed.");
			setContentView(R.layout.main_category);
			installImage(1);
			break;
		}
		case R.id.button_exit: {
			Log.i("TAG", "Exit buttton has been pressed.");
			finish();
			break;
		}
		case R.id.imagebutton1_1: {
			Log.i("TAG", "detailimagebutton1_1 has been pressed.");
			if (!checkHaveDetail(ibs1_1)) {
				Log.i(tag, "No detail images have been saved in database");
				showDialog(CONFIRM_DIALOG);
				return;
			}
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs1_1);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton1_2: {
			Log.i("TAG", "detailimagebutton1_1 has been pressed.");
			if (!checkHaveDetail(ibs1_2)) {
				Log.i(tag, "No detail images have been saved in database");
				showDialog(CONFIRM_DIALOG);
				return;
			}
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs1_2);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton1_3: {
			Log.i("TAG", "detailimagebutton1_1 has been pressed.");
			if (!checkHaveDetail(ibs1_3)) {
				Log.i(tag, "No detail images have been saved in database");
				showDialog(CONFIRM_DIALOG);
				return;
			}
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs1_3);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton2_1: {
			Log.i("TAG", "detailimagebutton1_1 has been pressed.");
			if (!checkHaveDetail(ibs2_1)) {
				Log.i(tag, "No detail images have been saved in database");
				showDialog(CONFIRM_DIALOG);
				return;
			}
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs2_1);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton2_2: {
			Log.i("TAG", "detailimagebutton2_2 has been pressed.");
			if (!checkHaveDetail(ibs2_2)) {
				Log.i(tag, "No detail images have been saved in database");
				showDialog(CONFIRM_DIALOG);
				return;
			}
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs2_2);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton2_3: {
			Log.i("TAG", "detailimagebutton2_3 has been pressed.");
			if (!checkHaveDetail(ibs2_3)) {
				Log.i(tag, "No detail images have been saved in database");
				showDialog(CONFIRM_DIALOG);
				return;
			}
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs2_3);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton3_1: {
			Log.i("TAG", "detailimagebutton1_1 has been pressed.");
			if (!checkHaveDetail(ibs3_1)) {
				Log.i(tag, "No detail images have been saved in database");
				showDialog(CONFIRM_DIALOG);
				return;
			}
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs3_1);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton3_2: {
			Log.i("TAG", "detailimagebutton1_1 has been pressed.");
			if (!checkHaveDetail(ibs3_2)) {
				Log.i(tag, "No detail images have been saved in database");
				showDialog(CONFIRM_DIALOG);
				return;
			}
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs3_2);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton3_3: {
			Log.i("TAG", "detailimagebutton1_1 has been pressed.");
			if (!checkHaveDetail(ibs3_3)) {
				Log.i(tag, "No detail images have been saved in database");
				showDialog(CONFIRM_DIALOG);
				return;
			}
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs3_3);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		default: {
			Log.i(tag, "ImageButton has been pressed!");
			break;
		}
		}
		// TODO Auto-generated method stub
	}

	// 2010-10-19 Added Menu Handler
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, "Preview");
		menu.add(0, 1, 0, "Next");
		menu.add(0, 2, 0, "Detail");
		menu.add(0, 3, 0, "Exit");
		menu.add(0, 4, 0, "Help");
		menu.add(0, 5, 0, "Buy");
		menu.add(0, 6, 0, "Register");
		menu.add(0, 7, 0, "Login");
		menu.add(0, 8, 0, "ListView");
		menu.add(0, 9, 0, "Gallery");
		menu.add(0, 10, 0, "TabHost");
		menu.add(0, 11, 0, "XMLTest");
		menu.add(0, 12, 0, "Loadingscreen");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 0:
			installImage(1);
			break;
		case 1:
			installImage(2);
			break;
		case 2: {
			Intent intent = new Intent(OldDolphin.this, CapDetailActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 3:
			finish();
			break;
		case 4:{
			HelpDialog hpd = new HelpDialog(this);
			hpd.show();
//			Intent intent = new Intent(Dolphin.this, HelpActivity.class);
//			startActivityForResult(intent, 100);
			break;
		}
		case 5:{
			Intent intent = new Intent(OldDolphin.this, BuyFormActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 6:{
			RegisterDialog rd =  new RegisterDialog(this);
			rd.createDialogAdd().show();
			//setContentView(R.layout.register_form);
			break;
		}
		case 7:{
			LoginDialog rd =  new LoginDialog(this);
			rd.createDialog().show();
			//setContentView(R.layout.register_form);
			break;
		}
		case 8:{
			Intent intent = new Intent(OldDolphin.this, ListViewActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 9:{
			Intent intent = new Intent(OldDolphin.this, GalleryActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 10:{
			Intent intent = new Intent(OldDolphin.this, DolphinBrowsingTabHostActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 11:{
			Intent intent = new Intent(OldDolphin.this, AndroidXMLDemoSaxII.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 12:{
			Intent intent = new Intent(OldDolphin.this, LoadingScreenActivity.class);
			startActivityForResult(intent, 100);
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

	public boolean checkHaveDetail(String imageId) {
		DBAdapter adapter = new DBAdapter(this);
		final String DATABASE_TABLE_DETAIL = "imageresdetail";
		adapter.setDateBaseTableName(DATABASE_TABLE_DETAIL);
		adapter.open();
		Cursor cursor = adapter.getTitleFromDetailImages(imageId);
		if (cursor.getCount() == 0) {
			cursor.close();
			adapter.close();
			return false;
		}
		cursor.close();
		adapter.close();
		return true;
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case CONFIRM_DIALOG: {
			return new AlertDialog.Builder(this).setIcon(
					android.R.drawable.ic_dialog_alert).setTitle(
					"NO DETAIL INFO").setPositiveButton("Confirm",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Log.i(tag, "");

						}
					}).setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Log.i(tag, "");
						}
					}).create();
			}//case
		}//switch
		return null;
	}
	
	

}
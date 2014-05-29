package Dolphin.src;

import Dolphin.src.Activity.BuyFormActivity;
import Dolphin.src.Activity.CapDetailActivity;
import Dolphin.src.Activity.DolphinOneShowActivity;
import Dolphin.src.Activity.GalleryActivity;
import Dolphin.src.Activity.ListViewActivity;
import Dolphin.src.Activity.LoadingScreenActivity;
import Dolphin.src.Activity.DolphinSpecificDetailActivity;
import Dolphin.src.Activity.DolphinBrowsingTabHostActivity;
import Dolphin.src.DatabaseProvider.DBAdapter;
import Dolphin.src.DatabaseProvider.DBSetup;
import Dolphin.src.Dialog.FragmentDialog;
import Dolphin.src.Dialog.FragmentDialog.FragmentDialogListener;
import Dolphin.src.Dialog.HelpDialog;
import Dolphin.src.Dialog.LoginDialog;
import Dolphin.src.Dialog.RegisterDialog;
import Dolphin.src.XMLHandler.AndroidXMLDemoSaxII;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//Add FragmentDiaglogListener to handle Dialog events.
public class DolphinActivity extends Activity implements OnClickListener,
		FragmentDialogListener {
	private static final int CONFIRM_DIALOG = 0;
	private ImageButton buttonPreview = null;
	private ImageButton buttonNext = null;
	private ImageButton imagebutton1_1 = null;
	private ImageButton imagebutton1_2 = null;
	private ImageButton imagebutton2_1 = null;
	private ImageButton imagebutton2_2 = null;
	private ImageButton imagebuttonfourshow = null;
	private ImageButton imagebuttononeshow = null;
	private ImageButton imageButtonBack;
	private TextView textview1_1 = null;
	private TextView textview1_2 = null;
	private TextView textview2_1 = null;
	private TextView textview2_2 = null;

	private static final int KEY_IMAGEID = 2;
	private static final int KEY_DESCRIPTION = 3;

	private String ibs1_1 = null;
	private String ibs1_2 = null;
	private String ibs2_1 = null;
	private String ibs2_2 = null;

	private int page_main = 1;

	static private String tag = "Dolphin";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// // ** Database setup code which should open on the first run.
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
		// // DB setup over.
		// // Register DB create
		// // DBSetup setup = new DBSetup(this);
		// setup.registerDBSetup();
		requestWindowFeature(Window.FEATURE_NO_TITLE); // use this function to
														// hidden the up
														// application title.
		setContentView(R.layout.dolphin_gallery_category_4);
		buttonPreview = (ImageButton) findViewById(R.id.button_preview);
		buttonPreview.setOnClickListener(this);

		buttonNext = (ImageButton) findViewById(R.id.button_next);
		buttonNext.setOnClickListener(this);

		imagebuttonfourshow = (ImageButton) findViewById(R.id.fourshow);
		imagebuttonfourshow.setOnClickListener(this);

		imagebuttononeshow = (ImageButton) findViewById(R.id.oneshow);
		imagebuttononeshow.setOnClickListener(this);

		imagebutton1_1 = (ImageButton) findViewById(R.id.imagebutton1_1);
		imagebutton1_2 = (ImageButton) findViewById(R.id.imagebutton1_2);
		imagebutton2_1 = (ImageButton) findViewById(R.id.imagebutton2_1);
		imagebutton2_2 = (ImageButton) findViewById(R.id.imagebutton2_2);

		imageButtonBack = (ImageButton) findViewById(R.id.imagebutton_back);
		imageButtonBack.setOnClickListener(this);
		imagebutton1_1.setOnClickListener(this);
		imagebutton1_2.setOnClickListener(this);
		imagebutton2_1.setOnClickListener(this);
		imagebutton2_2.setOnClickListener(this);

		textview1_1 = (TextView) findViewById(R.id.textview1_1);
		textview1_2 = (TextView) findViewById(R.id.textview1_2);
		textview2_1 = (TextView) findViewById(R.id.textview2_1);
		textview2_2 = (TextView) findViewById(R.id.textview2_2);

		installImage(1);
	}

	/**
	 * ${tags} installImage() This function is used to load all the caps images
	 * name to map with resource file R and setup in the mian_category.xml file.
	 */
	public int installImage(int page) {
		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		int res_id = 0;
		String description = "";
		Cursor cursor = adapter.getAllTitles();
		cursor.moveToFirst();
		Log.i(tag, "The Data QUERY has been done");
		int i = -1;

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
		String result = "";
		int count = 0;
		i = cursor.getCount();
		if (page > ((i + 3) / 4)) {
			Toast.makeText(this, "Page is: End " + page_main,
					Toast.LENGTH_SHORT).show();
			return 1;
		}
		while (page > 1) {
			for (int k = 0; k < 4; k++) {
				cursor.moveToNext();
			}
			page--;
			i = i - 4;
		}
		while (i > 0) {
			Log.i(tag, "cursor is " + cursor.getString(KEY_IMAGEID));
			res_id = getResources()
					.getIdentifier(cursor.getString(KEY_IMAGEID), "drawable",
							getPackageName());
			description = cursor.getString(KEY_DESCRIPTION);
			if (description == null)
				description = "";
			Log.i(tag, "Resource is is " + res_id);
			try {
				switch (count) {
				case 0: {
					imagebutton1_1.setBackgroundResource(res_id);
					textview1_1.setText(description);
					ibs1_1 = cursor.getString(KEY_IMAGEID);
					break;
				}
				case 1: {
					imagebutton1_2.setBackgroundResource(res_id);
					textview1_2.setText(description);
					ibs1_2 = cursor.getString(KEY_IMAGEID);
					break;
				}
				case 2: {
					imagebutton2_1.setBackgroundResource(res_id);
					textview2_1.setText(description);
					ibs2_1 = cursor.getString(KEY_IMAGEID);
					break;
				}
				case 3: {
					imagebutton2_2.setBackgroundResource(res_id);
					textview2_2.setText(description);
					ibs2_2 = cursor.getString(KEY_IMAGEID);
					break;
				}
				}
			} catch (Exception e) {
				Log.i("TAG", "Exception is " + e.toString());

			}
			count++;
			if (count == 4)
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

		Toast.makeText(this, "Page is: " + page_main, Toast.LENGTH_SHORT)
				.show();
		// This while loop is used to set the white background for the final
		// page of main screen.
		description = " ";
		while (count < 4) {
			try {
				switch (count) {
				case 0: {
					imagebutton1_1.setBackgroundColor(Color.WHITE);
					textview1_1.setText(description);
					break;
				}
				case 1: {
					imagebutton1_2.setBackgroundColor(Color.WHITE);
					textview1_2.setText(description);
					break;
				}
				case 2: {
					imagebutton2_1.setBackgroundColor(Color.WHITE);
					textview2_1.setText(description);
					break;
				}
				case 3: {
					imagebutton2_2.setBackgroundColor(Color.WHITE);
					textview2_2.setText(description);
					break;
				}

				}
			} catch (Exception e) {
				Log.i("TAG", "Exception is " + e.toString());

			}
			count++;
			if (count == 4)
				break;
			// Log.i(tag, "DataBase : " + result + " i==" + i + " position="
			// + Integer.toString(cursor.getPosition()));
		}// while (i > 0)
		cursor.close();
		adapter.close();
		return 0;
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
				showNoticeDialog();
				return;
			}
			Intent intent = new Intent(DolphinActivity.this,
					DolphinSpecificDetailActivity.class);
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
				showNoticeDialog();
				return;
			}
			Intent intent = new Intent(DolphinActivity.this,
					DolphinSpecificDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs1_2);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.imagebutton2_1: {
			Log.i("TAG", "detailimagebutton1_1 has been pressed.");
			if (!checkHaveDetail(ibs2_1)) {
				Log.i(tag, "No detail images have been saved in database");
				showNoticeDialog();
				return;
			}
			Intent intent = new Intent(DolphinActivity.this,
					DolphinSpecificDetailActivity.class);
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
				showNoticeDialog();
				return;
			}
			Intent intent = new Intent(DolphinActivity.this,
					DolphinSpecificDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("DataKey", ibs2_2);
			intent.putExtras(bundle);
			startActivityForResult(intent, 100);
			break;
		}
		case R.id.fourshow: {
			Toast.makeText(this, "FourShowCategory ", Toast.LENGTH_SHORT)
					.show();
			break;
		}
		case R.id.oneshow: {
			Log.i("TAG", "detailimagebutton2_2 has been pressed.");
			Intent intent = new Intent(DolphinActivity.this,
					DolphinOneShowActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.imagebutton_back: {
			finish();
			break;
		}
		default: {
			Log.i(tag, "ImageButton has been pressed!");
			break;
		}
		}
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
		menu.add(0, 13, 0, "InitDataBase");
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
			Intent intent = new Intent(DolphinActivity.this,
					CapDetailActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 3:
			finish();
			break;
		case 4: {
			HelpDialog hpd = new HelpDialog(this);
			hpd.show();
			break;
		}
		case 5: {
			Intent intent = new Intent(DolphinActivity.this,
					BuyFormActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 6: {
			RegisterDialog rd = new RegisterDialog(this);
			rd.createDialogAdd().show();
			// setContentView(R.layout.register_form);
			break;
		}
		case 7: {
			LoginDialog rd = new LoginDialog(this);
			rd.createDialog().show();
			// setContentView(R.layout.register_form);
			break;
		}
		case 8: {
			Intent intent = new Intent(DolphinActivity.this,
					ListViewActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 9: {
			Intent intent = new Intent(DolphinActivity.this,
					GalleryActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 10: {
			Intent intent = new Intent(DolphinActivity.this,
					DolphinBrowsingTabHostActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 11: {
			Intent intent = new Intent(DolphinActivity.this,
					AndroidXMLDemoSaxII.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 12: {
			Intent intent = new Intent(DolphinActivity.this,
					LoadingScreenActivity.class);
			startActivityForResult(intent, 100);
			break;
		}
		case 13: {
			initDatabase();
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

	protected boolean isLastPage(int page) {
		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		Cursor cursor = adapter.getAllTitles();
		cursor.moveToFirst();
		int count = cursor.getCount();
		cursor.close();
		adapter.close();
		if (page == ((count + 3) / 4)) {
			return true;
		}
		return false;
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case CONFIRM_DIALOG: {
			return new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("NO DETAIL INFO")
					.setPositiveButton("Confirm",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									Log.i(tag, "");

								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									Log.i(tag, "");
								}
							}).create();
		}
		}
		return null;
	}

	protected void initDatabase() {
		// ** Database setup code which should open on the first run.
		DBSetup setup = new DBSetup(this);
		if (setup.mainDBSetup() != 1) {
			Log.i(tag, "Something is wrong during the mianDBSetup function");
			return;
		}
		// Insert Detail images.
		// setup = new DBSetup(this);
		if (setup.detailDBSetup() != 1) {
			Log.i(tag, "Something is wrong during the detailDBSetup function");
			return;
		}
		// DB setup over.
		// Register DB create
		// DBSetup setup = new DBSetup(this);
		setup.registerDBSetup();
	}

	// I add this function to replace the shwoDialog function which has be
	// depressed since SDK4, new FragmentDialog function is the updated method
	// to handle dialog problem.
	public void showNoticeDialog() {
		// Create an instance of the dialog fragment and show it
		DialogFragment dialog = FragmentDialog.newInstance("Which?");
		dialog.show(getFragmentManager(), "FragmentDialog");
	}

	// Since i add "FragmentDialogListener" interface to this activity, i need
	// to implement the following two methods. 2014-3-28
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// ---perform steps when user clicks on OK---
		Log.d("DialogFragmentExample", "User clicks on OK");
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// ---perform steps when user clicks on Cancel---
		Log.d("DialogFragmentExample", "User clicks on Cancel");
	}
}
/**
 * 
 */
package Dolphin.src.Activity;

import java.util.ArrayList;
import java.util.List;

import Dolphin.src.R;
import Dolphin.src.Adapter.ImageAndTextListAdapter;
import Dolphin.src.DatabaseProvider.DBAdapter;
import Dolphin.src.Entity.ImageAndText;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

/**
 * @author Administrator
 * 
 */
public class ListViewActivity extends Activity {
	private ListView list;
	private static String tag = "ListViewActivity";
	private static final int CONFIRM_DIALOG = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //use this function to hidden the up application title.
		setContentView(R.layout.listview);
		list = (ListView) findViewById(R.id.list);
		List<ImageAndText> dataArray = new ArrayList<ImageAndText>();
		// ImageAndText test=new ImageAndText("mlb201010yj001", "test");
		// ImageAndText test1=new ImageAndText("mlb201010yj002", "test1");
		// ImageAndText test2=new ImageAndText("mlb201010yj003", "test2");
		DBAdapter adapter = new DBAdapter(this);
		adapter.open();
		Cursor cursor = adapter.getAllTitles();
		cursor.moveToFirst();
		Log.i(tag, "The Data QUERY has been done");
		int i = -1;
		if (cursor.getCount() <= 0) {
			Log.i(tag, "The lasting db is smaller then nil.");
			cursor.close();
			adapter.close();
			return;
		}
		i = cursor.getCount();
//		ImageAndText test;
		while (i > 0) {
			Log.i(tag, "i=" + i+"  id is :"+cursor.getString(3));
			ImageAndText test = new ImageAndText(cursor.getString(2), cursor.getString(3),
					cursor.getString(4));			
			dataArray.add(test);
			cursor.moveToNext();
			i--;
		}
		cursor.close();
		adapter.close();
		ImageAndTextListAdapter listViewAdapter = new ImageAndTextListAdapter(
				this, dataArray, list);
		list.setAdapter(listViewAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, "Back");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 0: {
			finish();
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
		}// case
		}// switch
		return null;
	}

}

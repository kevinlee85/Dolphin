/**
 * 
 */
package Dolphin.src.Activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import Dolphin.src.R;
import Dolphin.src.Adapter.NewsListFunctionAdapter;
import Dolphin.src.Entity.NewsItem;
import Dolphin.src.XMLHandler.NewsListSaxXMLParserHandler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * @author Administrator
 * 
 */
public class NewsListViewActivity extends Activity {
	private ListView list;
	private ImageButton toolbarButton_right = null;
	private static String tag = "NewsListViewActivity";
	private static final int CONFIRM_DIALOG = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // use this function to
														// hidden the up
														// application title.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//use this to disable the auto rotate function. Only allow Portrait.
		
		setContentView(R.layout.news_function_screen);
		
		list = (ListView) findViewById(R.id.newslistview01);

		toolbarButton_right = (ImageButton) findViewById(R.id.toolbar_button_right);
		toolbarButton_right.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Log.i(tag, "toolbarButton_right has been pressed");
				Intent intent = new Intent(NewsListViewActivity.this,
						DolphinMainPageActivity.class);
				startActivityForResult(intent, 100);
			}

		});

		List<NewsItem> dataArray = new ArrayList<NewsItem>();
		
		// Below part is used to test loading data from XML file.
		InputStream newsListStream = readNewsListDataFromFile(); // we need use
																	// this
																	// function
																	// to load
																	// file
																	// content
																	// as
																	// stream.
		NewsListSaxXMLParserHandler newsListSaxXMLParserHandler = new NewsListSaxXMLParserHandler();
		dataArray = newsListSaxXMLParserHandler
				.parse(newsListStream);  //After the parse function, all data have been retrieved.

		NewsListFunctionAdapter listViewAdapter = new NewsListFunctionAdapter(
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
		}// case
		}// switch
		return null;
	}

	private InputStream readNewsListDataFromFile() {
		// Private function to locate the XML file and use Asset function to open it.
		InputStream inStream = null;
		try {
			inStream = this.getAssets().open("newslist_20140325.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inStream;
	}

//Not implemented, thid function will be used to load XML file from internet.
	@SuppressWarnings("unused")
	private InputStream readNewsListDataFromInternet() {
		// 从网络上获取实时地震数据
		URL infoUrl = null;
		InputStream inStream = null;
		try {
			infoUrl = new URL(
					"http://earthquake.usgs.gov/earthquakes/catalogs/1day-M2.5.xml");
			URLConnection connection = infoUrl.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				inStream = httpConnection.getInputStream();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inStream;
	}
}

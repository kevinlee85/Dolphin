package Dolphin.src.Adapter;

import java.util.List;

import Dolphin.src.R;
import Dolphin.src.Activity.NewsDetailInformationActivity;
import Dolphin.src.Entity.NewsItem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

//Current this class is used by NewsList Activity.
public class NewsListFunctionAdapter extends
		ArrayAdapter<NewsItem> {

	private ListView listView;
	private String tag = "ListView";

	public NewsListFunctionAdapter(Activity activity,
			List<NewsItem> newsItem, ListView listView) {
		super(activity, 0, newsItem);
		this.listView = listView;
	}

	// Dump
	public int getChineNumber() {
		return listView.getChildCount();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final Activity activity = (Activity) getContext();
		Log.i(tag, "getView");
		TextView textViewTitle;
		TextView textViewContent;
		TextView textViewDate;
		ImageButton imageButton_listView;
		View rowView = convertView;
		Log.i(tag, "position is:" + position);
		final NewsItem newsItem = getItem(position);
		final String imageid;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.news_function_listview_row_news_item, null);
			// rowView.setTag(imageAndText);
		} else {
			// imageAndText = (ImageAndText) rowView.getTag();
		}

		textViewTitle = (TextView) rowView.findViewById(R.id.text_news_title);
		textViewContent = (TextView) rowView.findViewById(R.id.text_listview);
		textViewDate = (TextView) rowView.findViewById(R.id.text_date);
		imageButton_listView = (ImageButton) rowView
				.findViewById(R.id.imagebutton_listview);
		imageid = newsItem.getImageID();
		imageButton_listView.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getContext(), NewsDetailInformationActivity.class);
				Bundle bundle = new Bundle();
				//Use this bundle to send DataKey and DataKey_Title information to the popup Activity.
				bundle.putString("DataKey", newsItem.getContent());
				bundle.putString("DataKey_Title", newsItem.getText2());
				intent.putExtras(bundle);
				activity.startActivityForResult(intent, 100);
			}

		});
		String imageUrl = newsItem.getImageID();
		if (imageUrl != null) {
			int rid = activity.getResources().getIdentifier(imageUrl,
					"drawable", activity.getPackageName());
			imageButton_listView.setBackgroundResource(rid);
		}
		Log.i(tag, "text is:" + newsItem.getText1());

		// Use SpannableString to highlight some words there.2014-3-25, although it seems not work well.
		SpannableString s = new SpannableString(newsItem.getText2());
		int start = 0;
		int end = newsItem.getText2().length() - 1;
		s.setSpan(new ForegroundColorSpan(Color.BLACK), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textViewContent.setText(s);

		textViewTitle.setText(newsItem.getText1());
		textViewDate.setText(newsItem.getText3());
		return rowView;
	}
}

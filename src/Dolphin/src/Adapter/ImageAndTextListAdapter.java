package Dolphin.src.Adapter;

import java.util.List;

import Dolphin.src.R;
import Dolphin.src.Activity.DolphinSpecificDetailActivity;
import Dolphin.src.DatabaseProvider.DBAdapter;
import Dolphin.src.Entity.ImageAndText;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class ImageAndTextListAdapter extends ArrayAdapter<ImageAndText> {

	private ListView listView;
	private String tag = "ListView";
	private static final int CONFIRM_DIALOG = 1;

	public ImageAndTextListAdapter(Activity activity,
			List<ImageAndText> imageAndTexts, ListView listView) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
	}

	// Dump
	public int getChineNumber() {
		return listView.getChildCount();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final Activity activity = (Activity) getContext();
		Log.i(tag, "getView");
		TextView textView;
		TextView textView2;
		ImageButton imageButton_listView;
		View rowView = convertView;
		Log.i(tag,"position is:"+position);
		ImageAndText imageAndText = getItem(position);
		final String imageid;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.listview_row, null);
//			rowView.setTag(imageAndText);
		} else {
//			imageAndText = (ImageAndText) rowView.getTag();
		}

		textView = (TextView) rowView.findViewById(R.id.text_listview);
		textView2 = (TextView) rowView.findViewById(R.id.text_listview2);
		imageButton_listView = (ImageButton) rowView
				.findViewById(R.id.imagebutton_listview);
		imageid = imageAndText.getImageID();
		imageButton_listView.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (checkHaveDetail(imageid)) {
					Log.i(tag,"The detail intent should be opened there! imageid is:"+imageid);
					Intent intent = new Intent(getContext(), DolphinSpecificDetailActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("DataKey", imageid);
					intent.putExtras(bundle);
					activity.startActivityForResult(intent, 100);
				} else {
					Log.i(tag, "No detail image");
					activity.showDialog(CONFIRM_DIALOG);
					return;
				}
				Log.i(tag, "Image has been pressed");
			}

		});
		String imageUrl = imageAndText.getImageID();
		if (imageUrl != null) {
			int rid = activity.getResources().getIdentifier(imageUrl,
					"drawable", activity.getPackageName());
			imageButton_listView.setBackgroundResource(rid);
		}
		Log.i(tag, "text is:"+imageAndText.getText1());
		textView.setText(imageAndText.getText1());
		textView2.setText(imageAndText.getText2());
		return rowView;
	}

	public boolean checkHaveDetail(String imageId) {
		DBAdapter adapter = new DBAdapter(getContext());
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
}

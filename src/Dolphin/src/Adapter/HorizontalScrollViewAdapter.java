package Dolphin.src.Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class HorizontalScrollViewAdapter extends BaseAdapter {

	private List<Map<String, Object>> list;
	private Context context;

	public HorizontalScrollViewAdapter(Context context) {
		this.context = context;
		this.list = new ArrayList<Map<String, Object>>();
	}

	public void addObject(Map<String, Object> map) {
		list.add(map);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Map<String, Object> getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//This Adapter is designed for HorizontalScrollView to add some ImageView items on it.
		ImageView imageView = new ImageView(context);
		Map<String, Object> map = getItem(position); // 获取当前的Item
		imageView.setBackgroundResource((Integer) map.get("image"));
		return imageView;
	}

}

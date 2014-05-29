package Dolphin.src.Util;

import java.util.Map;

import Dolphin.src.Activity.OneBigImageShowActivity;
import Dolphin.src.Adapter.HorizontalScrollViewAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HorizontalScrollViewLinearLayout extends LinearLayout {

	private Context context;

	public HorizontalScrollViewLinearLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public HorizontalScrollViewLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	// This function is designed for DolphinsPecificOne.
	public void setAdapter(HorizontalScrollViewAdapter adapter) {
		for (int i = 0; i < adapter.getCount(); i++) {
			final Map<String, Object> map = adapter.getItem(i);
			View view = adapter.getView(i, null, null);
			view.setPadding(10, 0, 10, 0);
			// 为视图设定点击监听器
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(context, "您选择了" + map.get("index"),
							Toast.LENGTH_SHORT).show();

					Intent intent = new Intent(context,
							OneBigImageShowActivity.class);
					// Bundle bundle = new Bundle();
					// bundle.putString("DataKey", fatherid);
					// intent.putExtras(bundle);
					context.startActivity(intent);
					// Intent intent = new Intent();
					// intent.setAction(AppConstant.UPDATE_IMAGE_ACTION);
					// intent.putExtra("index", (Integer)map.get("index"));
					// context.sendBroadcast(intent);
				}
			});
			this.setOrientation(HORIZONTAL);
			this.addView(view, new LinearLayout.LayoutParams(
			/* LayoutParams.WRAP_CONTENT */300, LayoutParams.WRAP_CONTENT));
		}
	}

	public void setAdapter(BaseAdapter adapter) {
		for (int i = 0; i < adapter.getCount(); i++) {
//			final Map<String, Object> map = (Map<String, Object>) adapter
//					.getItem(i);
			View view = adapter.getView(i, null, null);
			view.setPadding(10, 0, 10, 0);
			this.setOrientation(HORIZONTAL);
			this.addView(view, new LinearLayout.LayoutParams(
			/* LayoutParams.WRAP_CONTENT */300, LayoutParams.WRAP_CONTENT));
		}
	}
}

/**
 * 
 */
package Dolphin.src.Widget;

import java.util.Calendar;
import Dolphin.src.DolphinActivity;
import Dolphin.src.R;
import Dolphin.src.Activity.DolphinMainPageActivity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * @author Administrator
 * 
 */
public class AdvertisementWidget extends AppWidgetProvider {
	private String tag = "AdvertisementWidget";
	private static final String FRESH = "Dolphin.widget";
	private Context mContext;
	private static boolean run = true;
	private int count = 0;
	private boolean myswitch = false;
	/**
	 * 获得系统每秒
	 */
	// BroadcastReceiver mBroadcast = new BroadcastReceiver() {
	// public void onReceive(Context context, Intent intent) {
	// String action = intent.getAction();
	// Log.d(tag, "onReceive_BroadCast");
	// if (action.equals(Intent.ACTION_TIME_TICK)) {
	// mContext.sendBroadcast(new Intent(FRESH));
	// }
	// }
	// };
	/**
	 * 通知Widget每个1秒刷新一次, 起一个线程去不断给自己发intent 然后自己去收，然后再变动显示。
	 */
	Thread myThread = new Thread() {
		public void run() {
			while (run) {
				try {
					 Thread.sleep(1000);
//					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Log.d(tag, "ThreadRunning The run value is" + run);
				mContext.sendBroadcast(new Intent(FRESH));// 通知刷新Widget的Intent

			}
		};
	};

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {// 这个是每次Widget更新时调用的函数 用来给Widget刷新界面显示
		Log.d(tag, "onUpdate");
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		mContext = context;
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.advertisementwidget);
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toLocaleString());
		views.setTextViewText(R.id.txttim, cal.getTime().toLocaleString());

		appWidgetManager.updateAppWidget(appWidgetIds, views);

		myThread.start();
		/**
		 * 本类作为一个bracastReveiver能自己再，注册个监听器 （可以取消注释，看报什么错误）
		 */

		// context.registerReceiver(mBroadcast, new
		// IntentFilter(Intent.ACTION_TIME_TICK));
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		// Log.d(tag, "onReceive:the action is " + action);
		if (FRESH.equals(action)) {
			// if (count == 5) {
			// showAdvertiseImage(context);
			// count = 0;
			// }
			// count++;
			showAdvertiseImage(context);
			showTime(context);

		} else if (Intent.ACTION_TIME_TICK.equals(action)) {
			// if (count == 5) {
			// showAdvertiseImage(context, count);
			// count = 0;
			// }
			// count++;
			showTime(context);
		}
		super.onReceive(context, intent);
	}

	private void showTime(Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.advertisementwidget);
		Calendar cal = Calendar.getInstance();
		/**
		 * 获取时间图像的标志位
		 */
		int hour01 = cal.getTime().getHours() / 10;
		int hour02 = cal.getTime().getHours() % 10;
		int minute01 = cal.getTime().getMinutes() / 10;
		int minute02 = cal.getTime().getMinutes() % 10;
		// Log.i(tag,"Hour01 is : "+hour01+" Hour02 is:"+hour02+" minute01 is:"+minute01+" minute02 is:"+minute02);
		views.setTextViewText(R.id.txttim, cal.getTime().toLocaleString());
		views.setImageViewResource(R.id.hour01Image, this
				.getImageNumber(hour01));
		views.setImageViewResource(R.id.hour02Image, this
				.getImageNumber(hour02));
		views.setImageViewResource(R.id.minute01Image, this
				.getImageNumber(minute01));
		views.setImageViewResource(R.id.minute02Image, this
				.getImageNumber(minute02));

		/**
		 * 设置点击views启动DetailForecastActivity
		 */
		Intent detailIntent = new Intent(context, DolphinMainPageActivity.class);

		/**
		 * Note that the activity will be started outside of the context of an
		 * existing activity, so you must use the Intent.FLAG_ACTIVITY_NEW_TASK
		 * launch flag in the Intent.
		 * 获取一个即将启动新的activity的PendingIntent对象，如同调用Context.startActivity(Intent).
		 * 注意：将要启动的activity将在当前存在的activity的上下文之外启动，所以必须使用Intent.
		 * FLAG_ACTIVITY_NEW_TASK
		 * 
		 * 参数： context PendingIntent将在该Context内启动activity requestCode Private
		 * request code for the sender (currently not used). intent
		 * 启动activity的intent flags Intent.fillIn()提供的flags 例如：FLAG_ONE_SHOT,
		 * FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT
		 */
		PendingIntent pending = PendingIntent.getActivity(context, 0,
				detailIntent, 0);

		/**
		 * 相当于调用setOnClickListener，注册点击启动的PendingIntent。
		 * 参数：1、启动PendingIntent时用户所点击的view的ID；2、点击发送的PendingIntent
		 */
		views.setOnClickPendingIntent(R.id.absoluteLayout, pending);
		// views.setOnClickPendingIntent(R.id.button1, pending);

		ComponentName thisWidget = new ComponentName(context,
				AdvertisementWidget.class);
		AppWidgetManager.getInstance(context)
				.updateAppWidget(thisWidget, views);
	}

	public void showAdvertiseImage(Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.advertisementwidget);
//		Log.i(tag, "In ShowAdvertiseImage Function");
		Calendar cal = Calendar.getInstance();
		int count = cal.getTime().getSeconds() % 10;
//		Log.i(tag,"count is :"+count);
		switch (count) {
		case 1: {
			views.setImageViewResource(R.id.advertiseImage,
					R.drawable.mlb201010yj001);
			break;
		}
		case 2: {
			views.setImageViewResource(R.id.advertiseImage,
					R.drawable.mlb201010yj002);
			break;
		}
		case 3: {
			views.setImageViewResource(R.id.advertiseImage,
					R.drawable.mlb201010yj003);
			break;
		}
		case 4: {
			views.setImageViewResource(R.id.advertiseImage,
					R.drawable.mlb201010yj004);
			break;
		}
		case 5: {
			views.setImageViewResource(R.id.advertiseImage,
					R.drawable.mlb201010yj005);
			break;
		}
		case 6: {
			views.setImageViewResource(R.id.advertiseImage,
					R.drawable.mlb201010yj006);
			break;
		}
		case 7: {
			views.setImageViewResource(R.id.advertiseImage,
					R.drawable.mlb201010yj007);
			break;
		}
		case 8: {
			views.setImageViewResource(R.id.advertiseImage,
					R.drawable.mlb201010yj008);
			break;
		}
		case 9: {
			views.setImageViewResource(R.id.advertiseImage,
					R.drawable.mlb201010yj009);
			break;
		}
		
		default:
			break;
		}
		ComponentName thisWidget = new ComponentName(context,
				AdvertisementWidget.class);
		AppWidgetManager.getInstance(context)
				.updateAppWidget(thisWidget, views);
	}

	@Override
	public void onEnabled(Context context) {
		Log.d(tag, "onEnabled");
		super.onEnabled(context);
		run = true;
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.d(tag, "onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		Log.d(tag, "onDisabled run is " + run);
		run = false;
		super.onDisabled(context);

	}

	/**
	 * 获取表示时间的图标
	 * 
	 * @param num
	 *            图标对应的数字
	 * @return
	 */
	public int getImageNumber(int num) {
		if (num == 0) {
			return R.drawable.number_0_tahoma;
		} else if (num == 1) {
			return R.drawable.number_1_tahoma;
		} else if (num == 2) {
			return R.drawable.number_2_tahoma;
		} else if (num == 3) {
			return R.drawable.number_3_tahoma;
		} else if (num == 4) {
			return R.drawable.number_4_tahoma;
		} else if (num == 5) {
			return R.drawable.number_5_tahoma;
		} else if (num == 6) {
			return R.drawable.number_6_tahoma;
		} else if (num == 7) {
			return R.drawable.number_7_tahoma;
		} else if (num == 8) {
			return R.drawable.number_8_tahoma;
		} else if (num == 9) {
			return R.drawable.number_9_tahoma;
		} else {
			return -1;
		}
	}
}

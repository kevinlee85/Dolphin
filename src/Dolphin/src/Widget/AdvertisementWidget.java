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
	 * ���ϵͳÿ��
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
	 * ֪ͨWidgetÿ��1��ˢ��һ��, ��һ���߳�ȥ���ϸ��Լ���intent Ȼ���Լ�ȥ�գ�Ȼ���ٱ䶯��ʾ��
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
				mContext.sendBroadcast(new Intent(FRESH));// ֪ͨˢ��Widget��Intent

			}
		};
	};

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {// �����ÿ��Widget����ʱ���õĺ��� ������Widgetˢ�½�����ʾ
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
		 * ������Ϊһ��bracastReveiver���Լ��٣�ע��������� ������ȡ��ע�ͣ�����ʲô����
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
		 * ��ȡʱ��ͼ��ı�־λ
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
		 * ���õ��views����DetailForecastActivity
		 */
		Intent detailIntent = new Intent(context, DolphinMainPageActivity.class);

		/**
		 * Note that the activity will be started outside of the context of an
		 * existing activity, so you must use the Intent.FLAG_ACTIVITY_NEW_TASK
		 * launch flag in the Intent.
		 * ��ȡһ�����������µ�activity��PendingIntent������ͬ����Context.startActivity(Intent).
		 * ע�⣺��Ҫ������activity���ڵ�ǰ���ڵ�activity��������֮�����������Ա���ʹ��Intent.
		 * FLAG_ACTIVITY_NEW_TASK
		 * 
		 * ������ context PendingIntent���ڸ�Context������activity requestCode Private
		 * request code for the sender (currently not used). intent
		 * ����activity��intent flags Intent.fillIn()�ṩ��flags ���磺FLAG_ONE_SHOT,
		 * FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT
		 */
		PendingIntent pending = PendingIntent.getActivity(context, 0,
				detailIntent, 0);

		/**
		 * �൱�ڵ���setOnClickListener��ע����������PendingIntent��
		 * ������1������PendingIntentʱ�û��������view��ID��2��������͵�PendingIntent
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
	 * ��ȡ��ʾʱ���ͼ��
	 * 
	 * @param num
	 *            ͼ���Ӧ������
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

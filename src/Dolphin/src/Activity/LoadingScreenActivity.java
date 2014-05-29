/**
 * 
 */
package Dolphin.src.Activity;

import Dolphin.src.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * @author Administrator
 *
 */
public class LoadingScreenActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);

		View loginscreen = (View)findViewById(R.id.mainscreen);
		AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
		aa.setDuration(1000);
		loginscreen.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener(){

            public void onAnimationEnd(Animation arg0) {
                // TODO Auto-generated method stub
            	
               // Intent intent = new Intent(LoadingScreenActivity.this,mainActivity.class);
               // I add new intent to review the news listview 2014-3-25
            	Intent intent = new Intent(LoadingScreenActivity.this,NewsListViewActivity.class);
            	startActivityForResult(intent, 100);
                finish();
                
            }

            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub
                
            }

            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub
                
            }
		    
		});
//		Thread myThread = new Thread() {
//			public void run() {			 
//					try {
//						Thread.sleep(20000);
//						Intent intent = new Intent(LoadingScreenActivity.this,Dolphin.class);
//						startActivityForResult(intent, 100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//		
//				
//			};
//		};
//		myThread.start();
	}
}

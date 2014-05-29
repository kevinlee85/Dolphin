/**
 * 
 */
package Dolphin.src.Util;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

/**
 * @author Administrator
 * Currently is a dump class which was copied from some web page, in case
 * it maybe used in someday.
 */
public class BitMapHandler {

	private static final boolean VERBOSE = false;
	private static final String TAG = "BitMapHandler";
	public Uri myuri;
	Context context;
	BitMapHandler(Uri uri , Context context)
	{	
		myuri = uri;
		this.context = context;
	}
	Bitmap getBitpMap() {
		ParcelFileDescriptor pfd;
		try {
			pfd = context.getContentResolver().openFileDescriptor(myuri, "r");
		} catch (IOException ex) {
			return null;
		}
		java.io.FileDescriptor fd = pfd.getFileDescriptor();
		BitmapFactory.Options options = new BitmapFactory.Options();
		// 先指定原始大小
		options.inSampleSize = 1;
		// 只进行大小判断
		options.inJustDecodeBounds = true;
		// 调用此方法得到options得到图片的大小
		BitmapFactory.decodeFileDescriptor(fd, null, options);
		// 我们的目标是在800pixel的画面上显示。
		// 所以需要调用computeSampleSize得到图片缩放的比例
		options.inSampleSize = computeSampleSize(options, 800);
		// OK,我们得到了缩放的比例，现在开始正式读入BitMap数据
		options.inJustDecodeBounds = false;
		options.inDither = false;
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		// 根据options参数，减少所需要的内存
		Bitmap sourceBitmap = BitmapFactory.decodeFileDescriptor(fd, null,
				options);
		return sourceBitmap;
	}

	// 这个函数会对图片的大小进行判断，并得到合适的缩放比例，比如2即1/2,3即1/3
	static int computeSampleSize(BitmapFactory.Options options, int target) {
		int w = options.outWidth;
		int h = options.outHeight;
		int candidateW = w / target;
		int candidateH = h / target;
		int candidate = Math.max(candidateW, candidateH);
		if (candidate == 0)
			return 1;
		if (candidate > 1) {
			if ((w > target) && (w / candidate) < target)
				candidate -= 1;
		}
		if (candidate > 1) {
			if ((h > target) && (h / candidate) < target)
				candidate -= 1;
		}
		if (VERBOSE)
			Log.v(TAG, "for w/h " + w + "/" + h + " returning " + candidate
					+ "(" + (w / candidate) + " / " + (h / candidate));
		return candidate;
	}

}

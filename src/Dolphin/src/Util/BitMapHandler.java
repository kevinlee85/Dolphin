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
		// ��ָ��ԭʼ��С
		options.inSampleSize = 1;
		// ֻ���д�С�ж�
		options.inJustDecodeBounds = true;
		// ���ô˷����õ�options�õ�ͼƬ�Ĵ�С
		BitmapFactory.decodeFileDescriptor(fd, null, options);
		// ���ǵ�Ŀ������800pixel�Ļ�������ʾ��
		// ������Ҫ����computeSampleSize�õ�ͼƬ���ŵı���
		options.inSampleSize = computeSampleSize(options, 800);
		// OK,���ǵõ������ŵı��������ڿ�ʼ��ʽ����BitMap����
		options.inJustDecodeBounds = false;
		options.inDither = false;
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		// ����options��������������Ҫ���ڴ�
		Bitmap sourceBitmap = BitmapFactory.decodeFileDescriptor(fd, null,
				options);
		return sourceBitmap;
	}

	// ����������ͼƬ�Ĵ�С�����жϣ����õ����ʵ����ű���������2��1/2,3��1/3
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

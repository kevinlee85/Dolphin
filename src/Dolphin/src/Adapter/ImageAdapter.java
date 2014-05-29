package Dolphin.src.Adapter;

import Dolphin.src.Util.GalleryFlow;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	int mGalleryItemBackground;
	private Context mContext;
	private Integer[] mImageIds;
	private ImageView[] mImages;
	private final static String tag = "ImageAdapter";
	private Bitmap originalImage;
	private Bitmap reflectionImage;
	private Bitmap bitmapWithReflection;

	public ImageAdapter(Context c, Integer[] ImageIds) {
		mContext = c;
		mImageIds = ImageIds;
		mImages = new ImageView[mImageIds.length];
	}

	public boolean createReflectedImages() {
		final int reflectionGap = 4;
		int index = 0;
		Log.i(tag, "Before the loop");
		for (int imageId : mImageIds) {
			originalImage = BitmapFactory.decodeResource(mContext
					.getResources(), imageId);
			Log.i(tag, "new Bitmap");
			int width = originalImage.getWidth();
			int height = originalImage.getHeight();

			Matrix matrix = new Matrix();
			matrix.preScale(1, -1);

			reflectionImage = Bitmap.createBitmap(originalImage, 0, height / 2,
					width, height / 2, matrix, false);

			bitmapWithReflection = Bitmap.createBitmap(width,
					(height + height / 2), Config.ARGB_8888);

			Canvas canvas = new Canvas(bitmapWithReflection);

			canvas.drawBitmap(originalImage, 0, 0, null);

			Paint deafaultPaint = new Paint();
			canvas.drawRect(0, height, width, height + reflectionGap,
					deafaultPaint);

			canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

			Paint paint = new Paint();
			LinearGradient shader = new LinearGradient(0, originalImage
					.getHeight(), 0, bitmapWithReflection.getHeight()
					+ reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);

			paint.setShader(shader);

			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));

			canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
					+ reflectionGap, paint);

			ImageView imageView = new ImageView(mContext);
			imageView.setImageBitmap(bitmapWithReflection);
			imageView.setLayoutParams(new GalleryFlow.LayoutParams(190, 150));

			// imageView.setScaleType(ScaleType.MATRIX);
			mImages[index++] = imageView;
		}
		return true;
	}

	public int getCount() {
		// return mImageIds.length;
		if (mImageIds.length > 1)
			return Integer.MAX_VALUE;
		else
			return mImageIds.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// return mImages[position];
		if (mImages.length > 1)
			return mImages[position % mImages.length];
		else
			return mImages[position];
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

	public void clean() {
		originalImage.recycle();
		reflectionImage.recycle();
		bitmapWithReflection.recycle();
		System.gc();
	}

}

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

/**
 * @author Administrator
 * @Content This class is the new imageAdapter which is a copied and enhanced
 *          imageAdapter, but it no means we will discard the use of the old
 *          one. Because the old one is used in the SpecificActivity Show. This
 *          new adapter is changed the show way, which no need to call
 *          createReflectedImages function before add it to some views. Hence,
 *          we only need to new a instant of it and add to the target view,
 *          therefore it can work well. The bitmap show will be created on the
 *          getView function which was implemented in createReflectedImages
 *          function.
 */
public class ImageAdapterNew extends BaseAdapter {

	int mGalleryItemBackground;
	private Context mContext;
	private Integer[] mImageIds;
	private ImageView[] mImages;
	private ImageView miv;
	private static final String tag = "ImageAdapterNew";

	public ImageAdapterNew(Context c, Integer[] ImageIds) {
		mContext = c;
		mImageIds = ImageIds;
		mImages = new ImageView[mImageIds.length];
	}

	// public boolean createReflectedImages() {
	// final int reflectionGap = 4;
	// int index = 0;
	//
	// for (int imageId : mImageIds) {
	// Bitmap originalImage = BitmapFactory.decodeResource(mContext
	// .getResources(), imageId);
	// int width = originalImage.getWidth();
	// int height = originalImage.getHeight();
	//
	// Matrix matrix = new Matrix();
	// matrix.preScale(1, -1);
	//
	// Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
	// height / 2, width, height / 2, matrix, false);
	//
	// Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
	// (height + height / 2), Config.ARGB_8888);
	//
	// Canvas canvas = new Canvas(bitmapWithReflection);
	//
	// canvas.drawBitmap(originalImage, 0, 0, null);
	//
	// Paint deafaultPaint = new Paint();
	// canvas.drawRect(0, height, width, height + reflectionGap,
	// deafaultPaint);
	//
	// canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
	//
	// Paint paint = new Paint();
	// LinearGradient shader = new LinearGradient(0, originalImage
	// .getHeight(), 0, bitmapWithReflection.getHeight()
	// + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
	//
	// paint.setShader(shader);
	//
	// paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
	//
	// canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
	// + reflectionGap, paint);
	//
	// ImageView imageView = new ImageView(mContext);
	// imageView.setImageBitmap(bitmapWithReflection);
	// // imageView.setLayoutParams(new GalleryFlow.LayoutParams(190,
	// // 150));
	// imageView.setScaleType(ScaleType.MATRIX);
	// mImages[index++] = imageView;
	// }
	// return true;
	// }

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
			setupImageView((position % mImages.length));
		else
			setupImageView(1);
		return miv;
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

	public void setupImageView(int position11) {
		miv = new ImageView(mContext);
		Log.i(tag, "Postition:" + position11);
		final int reflectionGap = 4;
		Bitmap originalImage = BitmapFactory.decodeResource(mContext
				.getResources(), mImageIds[position11]);
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);

		canvas.drawBitmap(originalImage, 0, 0, null);

		Paint deafaultPaint = new Paint();
		canvas
				.drawRect(0, height, width, height + reflectionGap,
						deafaultPaint);

		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
						+ reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);

		paint.setShader(shader);

		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));

		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		miv.setImageBitmap(bitmapWithReflection);
		miv.setLayoutParams(new GalleryFlow.LayoutParams(240, 240));

	}

}

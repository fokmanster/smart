package com.smart.vervel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.grocal.smart.R;

public class ChangeColorIconWithTextView extends View {

	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Paint mPaint;

	private int mColor = 0xFF45C01A;
	private float mAlpha = 0f;
	private Bitmap mIconBitmap;
	private Rect mIconRect;
	private String mText = "微信";
	private int mTextSize = (int) TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());

	private Paint mTextPaint;
	private Rect mTextBound = new Rect();

	public ChangeColorIconWithTextView(Context context) {
		super(context);
	}

	public ChangeColorIconWithTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public ChangeColorIconWithTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ChangeColorIconView);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.ChangeColorIconView_icon:
				BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
				mIconBitmap = drawable.getBitmap();
				break;
			case R.styleable.ChangeColorIconView_color:
				mColor = a.getColor(attr, 0x45C01A);
				break;
			case R.styleable.ChangeColorIconView_text:
				mText = a.getString(attr);
				break;
			case R.styleable.ChangeColorIconView_text_size:
				mTextSize = (int) a.getDimension(attr, TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10,
								getResources().getDisplayMetrics()));
				break;

			}
		}
		a.recycle();
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(0xff555555);
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int bitmapWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
				- getPaddingRight(), getMeasuredHeight() - getPaddingTop()
				- getPaddingBottom() - mTextBound.height());
		int left = getMeasuredWidth()/2 - bitmapWidth/2;
		int top = (getMeasuredHeight()-mTextBound.height())/2 - bitmapWidth/2;
		mIconRect = new Rect(left, top, left+bitmapWidth, top+bitmapWidth);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int alpha = (int) Math.ceil(255*mAlpha);
		canvas.drawBitmap(mIconBitmap, null, mIconRect,null);
		
	}

}

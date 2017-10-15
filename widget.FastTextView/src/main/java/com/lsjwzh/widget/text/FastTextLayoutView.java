package com.lsjwzh.widget.text;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * A custom view for rendering layout directly.
 */
public class FastTextLayoutView extends View {
  private static final String TAG = FastTextLayoutView.class.getSimpleName();
  private Layout mLayout;

  public FastTextLayoutView(Context context) {
    super(context);
  }

  public FastTextLayoutView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public FastTextLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public FastTextLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    long start = System.currentTimeMillis();
    super.onDraw(canvas);
    canvas.save();
    if (mLayout != null) {
      canvas.translate(getPaddingLeft(), getPaddingTop());
      mLayout.draw(canvas);
    }
    canvas.restore();
    long end = System.currentTimeMillis();
    if (BuildConfig.DEBUG) {
      Log.d(TAG, "onDraw cost:" + (end - start));
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    long start = System.currentTimeMillis();
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (mLayout != null) {
      setMeasuredDimension(
          getPaddingLeft() + getPaddingRight() + mLayout.getWidth(),
          getPaddingTop() + getPaddingBottom() + mLayout.getHeight());
    }
    long end = System.currentTimeMillis();
    if (BuildConfig.DEBUG) {
      Log.d(TAG, "onMeasure cost:" + (end - start));
    }
  }

  public void setTextLayout(Layout layout) {
    mLayout = layout;
    requestLayout();
  }

  public Layout getTextLayout() {
    return mLayout;
  }
}

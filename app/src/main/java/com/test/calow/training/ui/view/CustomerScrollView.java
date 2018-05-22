package com.test.calow.training.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by calow on 18/5/22.
 */

public class CustomerScrollView extends ScrollView {

    public static final String TAG = "CustomerScrollView";
    private static final float OVER_SCROLL_RATIO = 0.5f;
    private static final int REBOUND_DURATION = 200;
    private Context mContext;
    private View innerView;
    private float oldY = -1;
    private Rect mRect = new Rect();
    private boolean isOverScroll = false;

    public CustomerScrollView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CustomerScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            innerView = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {//when set RelativeLayout clickable onTouchEvent will not catch down action
            oldY = ev.getY();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (innerView != null) {
            if (handleTouchEvent(ev)) {
                return true;
            }
        }
        return super.onTouchEvent(ev);
    }

    public boolean handleTouchEvent(MotionEvent ev) {
        final float curY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldY = curY;
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = (curY - oldY) * OVER_SCROLL_RATIO;
                oldY = curY;
                if (isOverScroll || needOverScroll(deltaY)) {
                    if (mRect.isEmpty()) {
                        mRect.set(innerView.getLeft(), innerView.getTop(), innerView.getRight(), innerView.getBottom());
                    }
                    if (deltaY > 0) {
                        innerView.layout(innerView.getLeft(), innerView.getTop() + (int) Math.ceil(deltaY), innerView.getRight(), innerView.getBottom() + (int) Math.ceil(deltaY));
                    } else {
                        innerView.layout(innerView.getLeft(), innerView.getTop() + (int) Math.floor(deltaY), innerView.getRight(), innerView.getBottom() + (int) Math.floor(deltaY));
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (needRebound()) {
                    rebound();
                }
                reset();
                break;
            default:
                reset();
                break;
        }
        return false;

    }

    public boolean needOverScroll(float deltaY) {
        final int offset = innerView.getMeasuredHeight() - getHeight();
        final float scrollY = getScrollY();
        if ((scrollY == 0 && deltaY > 0) || (scrollY == offset && deltaY < 0)) {
            isOverScroll = true;
            return true;
        }
        return false;
    }

    public boolean needRebound() {
        return innerView.getTop() != mRect.top;
    }

    public void rebound() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, innerView.getTop(), mRect.top);
        animation.setDuration(REBOUND_DURATION);
        innerView.startAnimation(animation);
        innerView.layout(mRect.left, mRect.top, mRect.right, mRect.bottom);
    }

    private void reset() {
        oldY = -1;
        isOverScroll = false;
        if (!mRect.isEmpty()) {
            mRect.setEmpty();
        }
    }

}

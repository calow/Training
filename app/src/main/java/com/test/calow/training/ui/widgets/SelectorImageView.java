package com.test.calow.training.ui.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.view.View;

/**
 * Created by calow on 18/6/5.
 */

public class SelectorImageView extends ImageView implements View.OnTouchListener {

    public SelectorImageView(Context context) {
        super(context, null);
    }

    public SelectorImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public SelectorImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setColorFilter(Color.TRANSPARENT);
                break;
        }
        return false;
    }
}

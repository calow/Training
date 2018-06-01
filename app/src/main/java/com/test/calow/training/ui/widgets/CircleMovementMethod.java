package com.test.calow.training.ui.widgets;

import android.content.Context;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.BaseMovementMethod;
import android.text.method.Touch;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

import com.test.calow.training.R;

/**
 * Created by calow on 18/5/31.
 */

public class CircleMovementMethod extends BaseMovementMethod {

    public static final String TAG = CircleMovementMethod.class.getSimpleName();
    private static final int DEFAULT_COLOR_ID = R.color.transparent;
    private static final int DEFAULT_CLICKABLE_COLORID = R.color.default_clickable_color;

    private int textViewBgColot;
    private int clickableSpanBgColor;
    private boolean isPassToTextView;

    private ClickableSpan[] mClickLinks;
    private BackgroundColorSpan mBgSpan;

    public CircleMovementMethod(Context context) {
        textViewBgColot = context.getResources().getColor(DEFAULT_COLOR_ID);
        clickableSpanBgColor = context.getResources().getColor(DEFAULT_CLICKABLE_COLORID);
    }

    public CircleMovementMethod(Context context, int clickableSpanBgColor) {
        textViewBgColot = context.getResources().getColor(DEFAULT_COLOR_ID);
        this.clickableSpanBgColor = clickableSpanBgColor;
    }

    public CircleMovementMethod(int clickableSpanBgColor, int textViewBgColor) {
        this.textViewBgColot = textViewBgColor;
        this.clickableSpanBgColor = clickableSpanBgColor;
    }

    public boolean isPassToTextView() {
        return isPassToTextView;
    }

    public void setPassToTextView(boolean passToTextView) {
        isPassToTextView = passToTextView;
    }

    @Override
    public boolean onTouchEvent(TextView widget, Spannable text, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int offset = layout.getOffsetForHorizontal(line, x);

            mClickLinks = text.getSpans(offset, offset, ClickableSpan.class);
            if (mClickLinks.length > 0) {//click span area
                setPassToTextView(false);
                Selection.setSelection(text, text.getSpanStart(mClickLinks[0]), text.getSpanEnd(mClickLinks[0]));
                mBgSpan = new BackgroundColorSpan(clickableSpanBgColor);
                text.setSpan(mBgSpan, text.getSpanStart(mClickLinks[0]), text.getSpanEnd(mClickLinks[0]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {//click textView
                setPassToTextView(true);
                widget.setBackgroundColor(textViewBgColot);
            }
        } else if (action == MotionEvent.ACTION_UP) {
            if (mClickLinks.length > 0){
                mClickLinks[0].onClick(widget);
                if (mBgSpan != null){
                    text.removeSpan(mBgSpan);
                }
            } else {

            }
            Selection.removeSelection(text);
            widget.setBackgroundResource(R.color.transparent);
        } else if (action == MotionEvent.ACTION_MOVE) {
            //move don't operator now
        } else {
            if (mBgSpan != null){
                text.removeSpan(mBgSpan);
            }
            widget.setBackgroundResource(R.color.transparent);
        }
        return Touch.onTouchEvent(widget, text, event);
    }
}

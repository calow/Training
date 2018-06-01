package com.test.calow.training.ui.widgets;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;

import com.test.calow.training.R;

/**
 * Created by calow on 18/5/31.
 */

public abstract class SpannableClickable extends ClickableSpan {

    private int DEFAULT_COLOR_ID = R.color.name_color;

    private int textColor;

    public SpannableClickable(Context context) {
        textColor = context.getResources().getColor(DEFAULT_COLOR_ID);
    }

    public SpannableClickable(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(textColor);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}

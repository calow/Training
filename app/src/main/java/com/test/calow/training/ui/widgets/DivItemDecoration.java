package com.test.calow.training.ui.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by calow on 18/5/23.
 */

public class DivItemDecoration extends RecyclerView.ItemDecoration {

    private int mHeight;
    private boolean hasHead;

    public DivItemDecoration(int mHeight, boolean hasHead) {
        this.mHeight = mHeight;
        this.hasHead = hasHead;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (hasHead && position == 0){
            return;
        }
        outRect.bottom = mHeight;
    }
}

package com.test.calow.training.ui.viewholder;

import android.view.View;
import android.view.ViewStub;

import com.test.calow.training.R;
import com.test.calow.training.ui.widgets.MultiImageView;

/**
 * Created by calow on 18/6/5.
 */

public class ImageViewHolder extends DynamicViewHolder {

    public MultiImageView multiImageView;

    public ImageViewHolder(View itemView) {
        super(itemView, TYPE_IMAGE);
    }


    @Override
    public void initViewStub(int viewType, ViewStub viewStub) {
        if (viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }
        viewStub.setLayoutResource(R.layout.viewstub_imgbody);
        View subView = viewStub.inflate();

        MultiImageView multiImageView = subView.findViewById(R.id.multi_iv);
        if (multiImageView != null){
            this.multiImageView = multiImageView;
        }
    }
}

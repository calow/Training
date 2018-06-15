package com.test.calow.training.ui.viewholder;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.calow.training.R;

/**
 * Created by calow on 18/6/5.
 */

public class URLViewHolder extends DynamicViewHolder {

    public LinearLayout body;
    public ImageView urlIv;
    public TextView urlTv;

    public URLViewHolder(View itemView) {
        super(itemView, TYPE_URL);
    }


    @Override
    public void initViewStub(int viewType, ViewStub viewStub) {
        if (viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }

        viewStub.setLayoutResource(R.layout.viewstub_urlbode);
        View subView = viewStub.inflate();
        LinearLayout urlBodyView = subView.findViewById(R.id.url_body);
        if (urlBodyView != null){
            body = urlBodyView;
            urlIv = subView.findViewById(R.id.url_iv);
            urlTv = subView.findViewById(R.id.url_tv);
        }
    }
}

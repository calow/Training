package com.test.calow.training.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.test.calow.training.ui.presenter.DynamicCirclePresenter;

/**
 * Created by calow on 18/5/31.
 */

public class DynamicCircleAdapter extends BaseRecyclerViewAdapter {

    public static final String TAG = "DynamicCircleAdapter";

    private Context mContext;
    private DynamicCirclePresenter presenter;

    public DynamicCircleAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public DynamicCirclePresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(DynamicCirclePresenter presenter) {
        this.presenter = presenter;
    }
}

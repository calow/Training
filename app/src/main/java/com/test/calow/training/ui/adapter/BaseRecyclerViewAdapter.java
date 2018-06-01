package com.test.calow.training.ui.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calow on 18/5/31.
 */

public abstract class BaseRecyclerViewAdapter<T, WH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<WH> {

    public static final String TAG = "BaseRecyclerViewAdapter";

    protected List<T> mDatas = new ArrayList<T>();

    public List<T> getDatas(){
        if (mDatas == null){
            return new ArrayList<T>();
        }
        return mDatas;
    }

    public void setDatas(List<T> datas){
        this.mDatas = datas;
    }

}

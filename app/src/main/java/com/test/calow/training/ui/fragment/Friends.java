package com.test.calow.training.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.calow.training.R;
import com.test.calow.training.ui.adapter.MessageAdapter;
import com.test.calow.training.ui.custom.TitleBarView;
import com.test.calow.training.ui.presenter.MessagePresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by calow on 18/5/18.
 */

public class Friends extends Fragment {

    public static final String TAG = "Friends";

    @InjectView(R.id.title_bar)
    TitleBarView titleBar;
    @InjectView(R.id.rv_list)
    RecyclerView rvList;
    private Context mContext;
    private View mBaseView;
    private MessagePresenter mMsgPresenter;
    private MessageAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mBaseView = inflater.inflate(R.layout.fragment_message, null);
        ButterKnife.inject(this, mBaseView);
        initTitle();
        initData();
        return mBaseView;
    }

    public void initTitle(){
        titleBar.setCommonTitle(View.GONE, View.VISIBLE, View.GONE);
        titleBar.setTitleText(R.string.string_friends);
    }

    public void initData() {
//        mMsgPresenter = new MessagePresenter();
//        mMsgPresenter.attachView(this);
//        mMsgPresenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}

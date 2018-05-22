package com.test.calow.training.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.calow.training.R;
import com.test.calow.training.ui.adapter.MessageAdapter;
import com.test.calow.training.ui.custom.TitleBarView;
import com.test.calow.training.ui.model.MessageEntity;
import com.test.calow.training.ui.presenter.MessagePresenter;
import com.test.calow.training.ui.view.IMessageView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by calow on 18/5/18.
 */

public class Message extends Fragment implements IMessageView {

    public static final String TAG = "Message";

    @InjectView(R.id.title_bar)
    TitleBarView titleBar;
    @InjectView(R.id.rv_list)
    RecyclerView rvList;
    @InjectView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;

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
        init();
        initTitle();
        initData();
        return mBaseView;
    }

    public void init(){
        rvList.setLayoutManager(new LinearLayoutManager(mContext.getApplicationContext()));
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMsgPresenter.refresh();
            }
        });
    }

    public void initTitle() {
        titleBar.setCommonTitle(View.GONE, View.VISIBLE, View.GONE);
        titleBar.setTitleText(R.string.string_chat);
    }

    public void initData() {
        mMsgPresenter = MessagePresenter.getInstance();
        mMsgPresenter.attachView(this);
        mMsgPresenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        mMsgPresenter.detachView();
    }

    @Override
    public void showToast(String string) {

    }

    @Override
    public void setAdapter(List<MessageEntity> list) {
        mAdapter = new MessageAdapter(mContext, list);
        rvList.setAdapter(mAdapter);
    }

    @Override
    public void notifyAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefresh() {
        srlRefresh.setRefreshing(false);
    }
}

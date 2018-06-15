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

    public static final int REFRESH_TYPE = 0;
    public static final int UPLOAD_TYPE = 1;

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
    private SwipeRefreshLayout.OnRefreshListener refreshListener;

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
        firstTimeRefresh();
        return mBaseView;
    }

    public void initTitle() {
        titleBar.setCommonTitle(View.GONE, View.VISIBLE, View.GONE);
        titleBar.setTitleText(R.string.string_chat);
    }

    public void initData(){
        mMsgPresenter = new MessagePresenter(this);
        mAdapter = new MessageAdapter(mContext);
        mAdapter.setPresenter(mMsgPresenter);
        rvList.setAdapter(mAdapter);

        rvList.setLayoutManager(new LinearLayoutManager(mContext.getApplicationContext()));
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMsgPresenter.getData(REFRESH_TYPE);
            }
        };
        srlRefresh.setOnRefreshListener(refreshListener);
    }

    public void firstTimeRefresh() {
        srlRefresh.post(new Runnable() {
            @Override
            public void run() {
                srlRefresh.setRefreshing(true);
                refreshListener.onRefresh();
            }
        });
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
    public void update2view(int type, List<MessageEntity> list) {
        if (type == REFRESH_TYPE){
            srlRefresh.setRefreshing(false);
            mAdapter.setmDatas(list);
        } else if (type == UPLOAD_TYPE){
            mAdapter.getmDatas().addAll(list);
        }
        mAdapter.notifyDataSetChanged();
    }

}

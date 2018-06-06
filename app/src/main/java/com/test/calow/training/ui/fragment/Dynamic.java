package com.test.calow.training.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.test.calow.training.R;
import com.test.calow.training.ui.adapter.MessageAdapter;
import com.test.calow.training.ui.custom.TitleBarView;
import com.test.calow.training.ui.presenter.MessagePresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by calow on 18/5/18.
 */

public class Dynamic extends Fragment {
    public static final String TAG = "Dynamic";

    @InjectView(R.id.title_bar)
    TitleBarView titleBar;
    @InjectView(R.id.rl_round)
    RelativeLayout rlRound;
    @InjectView(R.id.rl_scan)
    RelativeLayout rlScan;
    @InjectView(R.id.rl_shake)
    RelativeLayout rlShake;
    @InjectView(R.id.rl_search)
    RelativeLayout rlSearch;
    @InjectView(R.id.rl_near)
    RelativeLayout rlNear;
    @InjectView(R.id.rl_float)
    RelativeLayout rlFloat;
    @InjectView(R.id.rl_shopping)
    RelativeLayout rlShopping;
    @InjectView(R.id.rl_game)
    RelativeLayout rlGame;
    @InjectView(R.id.rl_little_app)
    RelativeLayout rlLittleApp;
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
        mBaseView = inflater.inflate(R.layout.fragment_dynamic, null);
        ButterKnife.inject(this, mBaseView);
        initTitle();
        initData();
        return mBaseView;
    }

    public void initTitle() {
        titleBar.setCommonTitle(View.GONE, View.VISIBLE, View.GONE);
        titleBar.setTitleText(R.string.string_dynamic);
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

    @OnClick({R.id.rl_round, R.id.rl_scan, R.id.rl_shake, R.id.rl_search, R.id.rl_near, R.id.rl_float, R.id.rl_shopping, R.id.rl_game, R.id.rl_little_app})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_round:
                Log.d(TAG, "-------onViewClicked: --------");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.user.dynamic");
                startActivity(intent);
                break;
            case R.id.rl_scan:
                break;
            case R.id.rl_shake:
                break;
            case R.id.rl_search:
                break;
            case R.id.rl_near:
                break;
            case R.id.rl_float:
                break;
            case R.id.rl_shopping:
                break;
            case R.id.rl_game:
                break;
            case R.id.rl_little_app:
                break;
        }
    }
}

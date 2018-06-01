package com.test.calow.training.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.test.calow.training.R;
import com.test.calow.training.ui.adapter.DynamicCircleAdapter;
import com.test.calow.training.ui.custom.TitleBarView;
import com.test.calow.training.ui.presenter.DynamicCirclePresenter;
import com.test.calow.training.ui.widgets.DivItemDecoration;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DynamicActivity extends Activity {

    public static final String TAG = "DynamicActivity";
    @InjectView(R.id.title_bar)
    TitleBarView titleBar;
    @InjectView(R.id.rc_super)
    SuperRecyclerView rcSuper;

    private DynamicCirclePresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        ButterKnife.inject(this);

        initTitle();
        initData();
    }

    public void initTitle() {
        titleBar.setCommonTitle(View.GONE, View.VISIBLE, View.GONE);
        titleBar.setTitleText(R.string.string_round);
    }

    public void initData() {

        mPresenter = new DynamicCirclePresenter();

        rcSuper.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rcSuper.addItemDecoration(new DivItemDecoration(2, true));
        rcSuper.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

        rcSuper.setAdapter(new DynamicCircleAdapter());
    }


}

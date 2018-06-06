package com.test.calow.training.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.test.calow.training.R;
import com.test.calow.training.ui.adapter.DynamicCircleAdapter;
import com.test.calow.training.ui.custom.TitleBarView;
import com.test.calow.training.ui.model.DynamicEntity;
import com.test.calow.training.ui.presenter.DynamicCirclePresenter;
import com.test.calow.training.ui.view.IMainView;
import com.test.calow.training.ui.widgets.DivItemDecoration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DynamicActivity extends Activity implements IMainView{

    public static final String TAG = "DynamicActivity";
    private final static int TYPE_PULLREFRESH = 1;
    private final static int TYPE_UPLOADREFRESH = 2;
    @InjectView(R.id.title_bar)
    TitleBarView titleBar;
    @InjectView(R.id.rc_super)
    SuperRecyclerView rcSuper;

    private DynamicCirclePresenter mPresenter;
    private DynamicCircleAdapter mAdapter;
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        ButterKnife.inject(this);

        initTitle();
        initData();
        firstTimeRefresh();
    }

    public void initTitle() {
        titleBar.setCommonTitle(View.GONE, View.VISIBLE, View.GONE);
        titleBar.setTitleText(R.string.string_round);
    }

    public void initData() {

        mPresenter = new DynamicCirclePresenter(this);
        mAdapter = new DynamicCircleAdapter(this);
        mAdapter.setPresenter(mPresenter);

        rcSuper.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rcSuper.addItemDecoration(new DivItemDecoration(2, true));
        rcSuper.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

        rcSuper.setAdapter(mAdapter);

        rcSuper.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.loadData(TYPE_PULLREFRESH);
                    }
                }, 2000);
            }
        };
        rcSuper.setRefreshListener(mRefreshListener);
    }

    public void firstTimeRefresh(){
        rcSuper.getSwipeToRefresh().post(new Runnable() {
            @Override
            public void run() {
                rcSuper.setRefreshing(true);
                mRefreshListener.onRefresh();
            }
        });
    }


    @Override
    public void update2loadData(int loadType, List<DynamicEntity> datas) {
        if (loadType == TYPE_PULLREFRESH){
            rcSuper.setRefreshing(false);
            mAdapter.setDatas(datas);
        } else if (loadType == TYPE_UPLOADREFRESH) {
            mAdapter.getDatas().addAll(datas);
        }
        mAdapter.notifyDataSetChanged();

        if (mAdapter.getDatas().size() < 45 + mAdapter.HEADVIEW_SIZE){
            rcSuper.setupMoreListener(new OnMoreListener() {
                @Override
                public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.loadData(TYPE_UPLOADREFRESH);
                        }
                    }, 2000);
                }
            }, 1);
        } else {
            rcSuper.removeMoreListener();
            rcSuper.hideMoreProgress();
        }
    }
}

package com.test.calow.training.ui.presenter;

import com.test.calow.training.ui.model.DynamicModel;
import com.test.calow.training.ui.view.IMainView;

/**
 * Created by calow on 18/5/31.
 */

public class DynamicCirclePresenter implements IDynamicPresenter {

    public static final String TAG = "DynamicCirclePresenter";

    private DynamicModel model;
    private IMainView view;

    public DynamicCirclePresenter(IMainView view) {
        model = new DynamicModel();
        this.view = view;
    }

    @Override
    public void loadData(int loadType) {

    }
}

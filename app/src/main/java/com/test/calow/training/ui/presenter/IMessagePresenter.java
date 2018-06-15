package com.test.calow.training.ui.presenter;

import com.test.calow.training.ui.callback.ILoadCallback;

/**
 * Created by calow on 18/5/18.
 */

public interface IMessagePresenter {
    void detachView();
    void getData(int type);
    void loadMore(int page, int pageSize, ILoadCallback callback);
}

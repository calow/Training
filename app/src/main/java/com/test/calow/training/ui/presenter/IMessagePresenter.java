package com.test.calow.training.ui.presenter;

import android.support.annotation.NonNull;

import com.test.calow.training.ui.callback.ILoadCallback;
import com.test.calow.training.ui.fragment.Message;

/**
 * Created by calow on 18/5/18.
 */

public interface IMessagePresenter {
    void attachView(@NonNull Message view);
    void detachView();
    void getData();
    void refresh();
    void loadMore(int page, int pageSize, ILoadCallback callback);
}

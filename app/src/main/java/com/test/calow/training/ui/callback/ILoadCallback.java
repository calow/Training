package com.test.calow.training.ui.callback;

import java.util.List;

/**
 * Created by calow on 18/5/21.
 */

public interface ILoadCallback<T> {
    void loadSuccess(List<T> data);
    void loadFailure();
}

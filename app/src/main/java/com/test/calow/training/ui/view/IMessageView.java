package com.test.calow.training.ui.view;

import com.test.calow.training.ui.model.MessageEntity;

import java.util.List;

/**
 * Created by calow on 18/5/18.
 */

public interface IMessageView {
    void showToast(String string);
    void setAdapter(List<MessageEntity> list);
    void notifyAdapter();
    void stopRefresh();
}

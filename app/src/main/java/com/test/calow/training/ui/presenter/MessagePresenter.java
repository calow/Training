package com.test.calow.training.ui.presenter;

import com.test.calow.training.ui.callback.ILoadCallback;
import com.test.calow.training.ui.model.MessageEntity;
import com.test.calow.training.ui.model.MessageModel;
import com.test.calow.training.ui.view.IMessageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by calow on 18/5/18.
 */

public class MessagePresenter implements IMessagePresenter {

    public static final String TAG = "MessagePresenter";
    private IMessageView msg;
    private MessageModel model;

    public MessagePresenter(IMessageView view) {
        this.msg = view;
        model = new MessageModel();
    }

    @Override
    public void detachView() {
        this.msg = null;
    }

    @Override
    public void getData(int type) {
        if (msg != null){
            msg.update2view(type, model.getData());
        }
    }

    @Override
    public void loadMore(final int page, final int pageSize, final ILoadCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<MessageEntity> mDatas = new ArrayList<MessageEntity>();
                for (int i = 1; i < pageSize + 1; i++){
                    MessageEntity entity = new MessageEntity();
                    entity.setContent("content : " + i * page + " ---");
                    entity.setSender("sender : " + i * page + " ---");
                    entity.setTime(new Date());
                    mDatas.add(entity);
                }
                callback.loadSuccess(mDatas);
            }
        }).start();
    }
}

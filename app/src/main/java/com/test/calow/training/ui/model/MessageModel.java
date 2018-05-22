package com.test.calow.training.ui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by calow on 18/5/21.
 */

public class MessageModel implements IMessageModel {

    public static final String TAG = "MessageModel";
    private List<MessageEntity> mDatas;
    private int count = 10;

    public MessageModel() {
        mDatas = new ArrayList<MessageEntity>();
        for (int i = 0; i < count; i++){
            MessageEntity entity = new MessageEntity();
            entity.setContent("content : " + i + " ---");
            entity.setSender("sender : " + i + " ---");
            entity.setTime(new Date());
            mDatas.add(entity);
        }
    }


    @Override
    public List<MessageEntity> getData() {
        return mDatas;
    }
}

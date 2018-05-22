package com.test.calow.training.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by calow on 18/5/18.
 */

public class MessageEntity implements Parcelable{
    private String sender;
    private String content;
    private Date time;

    public MessageEntity() {

    }

    protected MessageEntity(Parcel in) {
        sender = in.readString();
        content = in.readString();
        time = new Date(in.readLong());
    }

    public static final Creator<MessageEntity> CREATOR = new Creator<MessageEntity>() {
        @Override
        public MessageEntity createFromParcel(Parcel in) {
            return new MessageEntity(in);
        }

        @Override
        public MessageEntity[] newArray(int size) {
            return new MessageEntity[size];
        }
    };

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sender);
        dest.writeString(content);
        dest.writeLong(time.getTime());
    }
}

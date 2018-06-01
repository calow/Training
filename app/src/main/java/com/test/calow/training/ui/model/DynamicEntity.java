package com.test.calow.training.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by calow on 18/5/23.
 */

public class DynamicEntity implements Parcelable {

    protected DynamicEntity(Parcel in) {
    }

    public static final Creator<DynamicEntity> CREATOR = new Creator<DynamicEntity>() {
        @Override
        public DynamicEntity createFromParcel(Parcel in) {
            return new DynamicEntity(in);
        }

        @Override
        public DynamicEntity[] newArray(int size) {
            return new DynamicEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}

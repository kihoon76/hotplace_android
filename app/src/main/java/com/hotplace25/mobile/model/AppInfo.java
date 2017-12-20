package com.hotplace25.mobile.model;

import java.io.Serializable;

/**
 * Created by khnam on 2017-12-20.
 */

public class AppInfo implements Serializable {

    private String mVersionName;
    private int mVersionCode;

    public int getmVersionCode() {
        return mVersionCode;
    }

    public void setmVersionCode(int mVersionCode) {
        this.mVersionCode = mVersionCode;
    }

    public String getmVersionName() {
        return mVersionName;
    }

    public void setmVersionName(String mVersionName) {
        this.mVersionName = mVersionName;
    }
}

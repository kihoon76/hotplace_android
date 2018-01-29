package com.hotplace25.mobile.map;

import android.support.v4.app.Fragment;

import com.hotplace25.mobile.BaseFragmentActivity;

/**
 * Created by khnam on 2018-01-29.
 */

public class MapActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return NMapFragment.getInstance();
    }
}

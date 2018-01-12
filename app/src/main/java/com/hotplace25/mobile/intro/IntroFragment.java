package com.hotplace25.mobile.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hotplace25.mobile.model.AppInfo;

/**
 * Created by khnam on 2017-12-20.
 */

public class IntroFragment extends Fragment {
    private static final String TAG = "IntroFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void registGCM() {

    }

    public static IntroFragment getInstance(AppInfo appInfo) {
        return new IntroFragment();
    }
}

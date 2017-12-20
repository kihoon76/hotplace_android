package com.hotplace25.mobile.intro;

import android.support.v4.app.Fragment;

import com.hotplace25.mobile.model.AppInfo;

/**
 * Created by khnam on 2017-12-20.
 */

public class IntroFragment extends Fragment {
    public static IntroFragment getInstance(AppInfo appInfo) {
        return new IntroFragment();
    }
}

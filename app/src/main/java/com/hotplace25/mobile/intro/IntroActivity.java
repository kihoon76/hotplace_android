package com.hotplace25.mobile.intro;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.hotplace25.mobile.BaseFragmentActivity;
import com.hotplace25.mobile.MainActivity;
import com.hotplace25.mobile.model.AppInfo;
import com.hotplace25.mobile.types.LogType;
import com.hotplace25.mobile.utils.Log;
import com.hotplace25.mobile.utils.SecurityManager;

/**
 * Created by khnam on 2017-12-20.
 */

public class IntroActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return IntroFragment.getInstance(SecurityManager.isRooting());
    }


}

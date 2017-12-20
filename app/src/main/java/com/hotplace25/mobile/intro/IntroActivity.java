package com.hotplace25.mobile.intro;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;

import com.hotplace25.mobile.BaseFragmentActivity;
import com.hotplace25.mobile.model.AppInfo;
import com.hotplace25.mobile.types.LogType;

/**
 * Created by khnam on 2017-12-20.
 */

public class IntroActivity extends BaseFragmentActivity {
    private final String TAG = "IntroActivity";

    @Override
    protected Fragment createFragment() {
        PackageInfo packageInfo;
        AppInfo appInfo = new AppInfo();

        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            appInfo.setmVersionName(packageInfo.versionName);
            appInfo.setmVersionCode(packageInfo.versionCode);
        }
        catch (PackageManager.NameNotFoundException e) {
            writeLog(LogType.ERR, TAG, e.getMessage());
        }

        return IntroFragment.getInstance(appInfo);
    }
}

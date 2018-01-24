package com.hotplace25.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.hotplace25.mobile.intro.IntroFragment;
import com.hotplace25.mobile.utils.SecurityManager;

/**
 * Created by khnam on 2018-01-22.
 */

public class MainActivity extends BaseFragmentActivity {
    private final String TAG = MainActivity.class.getName();

    @Override
    protected Fragment createFragment() {
        return MainFragment.getInstance();
    }

    @Override
    public void onBackPressed() {
        final Activity activity = this;
        new AlertDialog.Builder(activity)
            .setTitle("앱을 종료하시겠습니까?")
            .setPositiveButton(this.getString(R.string.app_end), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }})
                .create()
                .show();
    }

}

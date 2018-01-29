package com.hotplace25.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.hotplace25.mobile.intro.IntroActivity;
import com.hotplace25.mobile.intro.IntroFragment;
import com.hotplace25.mobile.utils.SecurityManager;

/**
 * Created by khnam on 2018-01-22.
 */

public class MainActivity extends BaseFragmentActivity {
    private final String TAG = IntroActivity.class.getName();
    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }

        if(System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            ActivityCompat.finishAffinity(this);
            toast.cancel();
        }
    }

    private void showGuide() {
        toast = Toast.makeText(this, "뒤로가기버튼 한번더", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected Fragment createFragment() {
        return MainFragment.getInstance();
    }
}

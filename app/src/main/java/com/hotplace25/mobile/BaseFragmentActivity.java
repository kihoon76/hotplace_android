package com.hotplace25.mobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.hotplace25.mobile.types.LogType;
import com.hotplace25.mobile.utils.Log;

/**
 * Created by khnam on 2017-12-20.
 */

public abstract class BaseFragmentActivity extends AppBaseFragmentActivity {
    protected abstract Fragment createFragment();

    private static final String TAG = "BaseFragmentActivity";
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if(fragment == null) {
            try {
                fragment = createFragment();
                fm.beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit();
            }
            catch(Exception e) {
                Log.e(TAG,  e.getMessage());

            }
        }
    }
}

package com.hotplace25.mobile;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.hotplace25.mobile.types.LogType;

/**
 * Created by khnam on 2017-12-20.
 */

public abstract class AppBaseFragmentActivity extends FragmentActivity {

    private boolean isDebuggable() {
        return (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void writeLog(LogType logType, String tag, String msg) {
        if(isDebuggable()) {
            switch(logType) {
                case ERR :
                    Log.e(tag, msg);
                    break;
                case WARN :
                    Log.w(tag, msg);
                    break;
                case INFO :
                    Log.i(tag, msg);
                    break;
                case DEBUG :
                    Log.d(tag, msg);
                    break;
                default :
                    break;
            }
        }
    }


}

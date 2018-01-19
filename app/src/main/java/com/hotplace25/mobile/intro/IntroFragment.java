package com.hotplace25.mobile.intro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotplace25.mobile.R;
import com.hotplace25.mobile.model.AppInfo;
import com.hotplace25.mobile.model.UserInfo;
import com.hotplace25.mobile.utils.CommandHandler;
import com.hotplace25.mobile.utils.HttpManager;
import com.hotplace25.mobile.utils.HttpThread;

/**
 * Created by khnam on 2017-12-20.
 */

public class IntroFragment extends Fragment {
    private static final String TAG = IntroFragment.class.getName();
    private static final String EXTRA_ROOTING_YN = "com.hotplace25.mobile.rooting_device";

    private HttpManager mHttpManager;
    private boolean mRootingDevice;
    private HttpThread mHttpThread;
    private CommandHandler commandHandler;

    private void registGCM() {

    }

    private void showErrDialog(String title) {
        new AlertDialog.Builder(getActivity())
            .setTitle(title)
                .setPositiveButton(getActivity().getString(R.string.app_end), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .create()
                .show();;
    }

    private void certifyUser() {
        UserInfo user = new UserInfo();
        user.setOsKind("");
        user.setOsVersion(String.valueOf(Build.VERSION.SDK_INT));

        mHttpThread.certifyUser();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        registGCM();
        super.onCreate(savedInstanceState);
        commandHandler = new CommandHandler();
        mHttpManager = new HttpManager();
        mRootingDevice = getArguments().getBoolean(EXTRA_ROOTING_YN);
        mHttpThread = new HttpThread(getActivity());
        mHttpThread.start();
        mHttpThread.getLooper();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHttpThread.quit();
    }

    public static IntroFragment getInstance(boolean isRooting) {
        Bundle args = new Bundle();
        args.putBoolean("EXTRA_ROOTING_YN", isRooting);
        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = null;

        if(!mRootingDevice) {
            v = inflater.inflate(R.layout.f_intro, parent, false);
            TextView textView = v.findViewById(R.id.txtViewVersion);

            try {
                PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            }
            catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            showErrDialog(getActivity().getString(R.string.warn_rooting));
        }

        return v;
    }
}

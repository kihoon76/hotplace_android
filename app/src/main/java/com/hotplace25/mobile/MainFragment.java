package com.hotplace25.mobile;

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
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.hotplace25.mobile.interfaces.AsyncCallback;
import com.hotplace25.mobile.intro.IntroFragment;
import com.hotplace25.mobile.model.UserInfo;
import com.hotplace25.mobile.utils.CommandHandler;
import com.hotplace25.mobile.utils.HttpManager;
import com.hotplace25.mobile.utils.HttpThread;
import com.hotplace25.mobile.utils.Log;
import com.hotplace25.mobile.utils.PreferencesManager;

/**
 * Created by khnam on 2018-01-22.
 */

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getName();
    private WebView webView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static MainFragment getInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_main, parent,false);
        webView = (WebView) v.findViewById(R.id.webContainer);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.setNetworkAvailable(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.0.58:5000/?deviceNum=" + PreferencesManager.getInstanceID(getContext()));





        return v;
    }
}

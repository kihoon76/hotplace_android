package com.hotplace25.mobile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.hotplace25.mobile.map.MapActivity;
import com.hotplace25.mobile.utils.Log;
import com.hotplace25.mobile.utils.PreferencesManager;

/**
 * Created by khnam on 2018-01-22.
 */

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getName();
    private WebView mWebView;

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
        mWebView = (WebView) v.findViewById(R.id.webContainer);
        mWebView.setWebViewClient(new WebViewClient() {

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("nmap:")) {
                    Log.d(TAG, "================================================================nmap");
                    showNMap();
                }

                return true;
            }

            @RequiresApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if(url.startsWith("nmap:")) {
                    Log.d(TAG, "================================================================nmap");
                    //showNMap();
                    Intent i = new Intent(getActivity(), MapActivity.class);
                    startActivity(i);
                    return true;
                }

                return false;
            }
        });
        //webView.setWebChromeClient(new WebChromeClient() {});
        mWebView.setNetworkAvailable(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://192.168.0.58:5000/?deviceNum=" + PreferencesManager.getInstanceID(getContext()));

        return v;
    }

    private void showNMap() {
        Intent i = new Intent(getActivity(), MapActivity.class);
        startActivity(i);
    }
}

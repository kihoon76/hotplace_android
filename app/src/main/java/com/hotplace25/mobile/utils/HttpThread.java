package com.hotplace25.mobile.utils;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.hotplace25.mobile.R;
import com.hotplace25.mobile.exceptions.HttpManagerException;
import com.hotplace25.mobile.interfaces.AsyncCallback;
import com.hotplace25.mobile.interfaces.Command;
import com.hotplace25.mobile.model.TransInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by khnam on 2018-01-19.
 */

public class HttpThread extends HandlerThread {
    private static final String TAG = HttpThread.class.getName();

    private Map<Command, AsyncCallback> callbackMap;
    private Context mContext;
    private Handler mHandler;
    private HttpManager mHttpManager;

    private abstract class HttpDoSome {

        abstract Object getObject(Class<? extends TransInfo> clazz) throws HttpManagerException;

        Object doSome(Class<? extends TransInfo> clazz) {

            Object o = null;

            try {
                o = getObject(clazz);
            }
            catch(HttpManagerException e) {
                o = new TransInfo();

                TransInfo t = (TransInfo)o;
                t.setStatusCode(500);
                t.setStatusMsg(e.getMessage());
            }

            return o;
        }
    }

    private void regist(Command command, AsyncCallback callback) {
        if(callbackMap.get(command) == null) {
            callbackMap.put(command, callback);
        }

        Message message = mHandler.obtainMessage();
    }

    private String getURL(String path) {
        return mContext.getString(R.string.host, path);
    }

    public HttpThread(Context context) {
        super(TAG);
        callbackMap = Collections.synchronizedMap(new HashMap<Command, AsyncCallback>());
        mContext = context;
        mHttpManager = new HttpManager();
    }


    public void clearQueue() {
        mHandler.removeMessages(0);
        callbackMap.clear();
    }

    @Override
    protected void onLooperPrepared() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.obj instanceof Command) {
                    ((Command)msg.obj).execute();
                }
            }
        };
    }

    public void certifyUser(final Object param, AsyncCallback callback) {
        regist(new Command() {
            @Override
            public void execute() {
                final String url = getURL(mContext.getString(R.string.url_certifyUser));

                Object transInfo = new HttpDoSome() {
                    @Override
                    Object getObject(Class<? extends TransInfo> clazz) throws HttpManagerException {
                        return mHttpManager.doPostJson(url, param, clazz);
                    }
                }
                .doSome(TransInfo.class);
                callbackMap.get(this).call(transInfo);
            }
        }, callback);
    }
}

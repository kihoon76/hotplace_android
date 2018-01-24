package com.hotplace25.mobile.services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.hotplace25.mobile.R;
import com.hotplace25.mobile.model.DeviceInfo;
import com.hotplace25.mobile.utils.HttpManager;
import com.hotplace25.mobile.utils.Log;
import com.hotplace25.mobile.utils.PreferencesManager;

/**
 * Created by khnam on 2018-01-24.
 */

public class HpFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = HpFirebaseInstanceIDService.class.getName();

    private void sendRegistrationToServer(String token) {
        PreferencesManager.setInstanceID(getApplicationContext(), token);
    }

    /*
    *  onTokenRefresh in FirebaseInstanceIdService is only called when a new token is generated.
    *  If your app was previously installed and generated a token then onTokenRefresh would not be called.
    *  Try uninstalling and reinstalling the app to force the generation of a new token, this would cause onTokenRefresh to be called.
    * */
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);

        //생성등록된 토큰을 개인 앱서버에 보내 저장해 두었다가 추가 뭔가를 하고 싶으면 할 수 있도록 한다.
        sendRegistrationToServer(token);
    }
}

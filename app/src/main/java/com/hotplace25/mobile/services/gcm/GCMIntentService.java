package com.hotplace25.mobile.services.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.gcm.GoogleCloudMessaging;


/**
 * Created by khnam on 2018-01-10.
 */

public class GCMIntentService extends IntentService {

    /*
     * Sender ID       : API 콘솔로부터 획득한 프로젝트 ID. Sender ID는 모바일 디바이스로 메시지 전송이 허용된 Android 앱을 식별하기 위한 등록 처리에 사용된다.
       Application ID  : 메시지를 수신하기 위해 등록 할 Android 앱. Android 앱은 manifest가 제공하는 패키지 이름으로 식별된다. 그러면 메시지가 적절한 Android 앱을 타갯팅하는 것이 보장된다.
       Registration ID : 메시지를 수신 할 수 있도록 허용된 Android 앱을 위한, GCM에서 발급한 ID. Android 앱이 Registration ID를 갖고 있으며, 그것을 3rd Party 애플리케이션 서버로 전송한다. 애플리케이션 서버는 그것을 사용하여 Android 애플리케이션이 메시지를 받기 위해 등록된 각 디바이스를 식별하는데 사용한다.
                               즉, Registration ID는 특정 디바이스에서 실행중인 특정 Android 앱에 연결된다.
       Google 사용자 계정 : 디바이스가 Android 4.0.4 이하의 버전인 경우 GCM을 동작시키기 위해 모바일 디바이스는 적어도 하나의 Google 계정이 있어야 한다.
       Sender 인증 토큰   : Google 서비스에 대한 액세스 권한을 부여하는, 3'rd Party 애플리케이션 서버에 저장되는 API 키. API 키는 메시지를 보내는 POST 요청 헤더에 포함된다.
    * */

    private static final String TAG = "GCMIntentService";
    private static final String APP_REG_ID = "";

    public GCMIntentService() {
        this(APP_REG_ID);
    }

    public GCMIntentService(String appRegId) {
        super(appRegId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.

        String messageType = gcm.getMessageType(intent);

        if(extras != null && !extras.isEmpty()) {

        }
    }
}

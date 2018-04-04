package com.wangpos.androidservice;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by qiyue on 2018/4/3.
 */

public class CoreService extends Service {

    public static final String TAG = CoreService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return CoreServiceInterface.newInstance();
    }
}

package com.wangpos.androidservice.init;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by qiyue on 2018/4/3.
 */

public class InitService extends InitBinder.Stub{

    private static final String TAG = InitService.class.getSimpleName();

    public static InitService newInstance() {
        return new InitService();
    }

    @Override
    public void init() throws RemoteException {
        Log.i(TAG,"init");
    }

}

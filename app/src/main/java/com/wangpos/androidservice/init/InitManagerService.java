package com.wangpos.androidservice.init;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by qiyue on 2018/4/3.
 */

public class InitManagerService extends InitManager.Stub{

    private static final String TAG = InitManager.class.getSimpleName();

    public static InitManagerService newInstance() {
        return new InitManagerService();
    }

    @Override
    public void init() throws RemoteException {
        Log.i(TAG,"init");
    }

    @Override
    public String getName() throws RemoteException {
        return "I am InitManagerService";
    }

}

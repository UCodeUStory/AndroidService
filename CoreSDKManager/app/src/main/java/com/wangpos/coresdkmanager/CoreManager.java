package com.wangpos.coresdkmanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.wangpos.androidservice.core.CoreServiceManager;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by qiyue on 2018/4/4.
 */

public class CoreManager implements ServiceConnection ,Handler.Callback{

    private static final String TAG = CoreManager.class.getSimpleName();

    private static volatile CoreManager instance;

    private CoreServiceManager coreServiceBinder;

    private InitListener mOnFinishedInitListener;

    private Handler mHandler;

    private CoreManager() {
        mHandler = new Handler(Looper.getMainLooper(),this);
    }

    public static CoreManager getInstance() {
        if (instance == null) {
            synchronized (CoreManager.class) {
                if (instance == null) {
                    instance = new CoreManager();
                }
            }
        }
        return instance;
    }

    public void init(Context context,InitListener aListener){
        mOnFinishedInitListener = aListener;
        ComponentName componentName = new ComponentName("com.wangpos.androidservice","com.wangpos.androidservice.CoreService");
        Intent coreServiceIntent = new Intent();
        coreServiceIntent.setComponent(componentName);
        context.bindService(coreServiceIntent, this, BIND_AUTO_CREATE);
    }


    public IBinder getService(Class<?>tClass){
        Log.i(TAG,"tClass.getSimpleName()="+tClass.getSimpleName());
        IBinder binder = null;
        try {
            binder = coreServiceBinder.getServices(tClass.getSimpleName());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (binder==null)
            Log.i(TAG,"bind is null");
        return binder;
    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i(TAG,"onServiceConnected");
        coreServiceBinder = CoreServiceManager.Stub.asInterface(iBinder);
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i(TAG,"onServiceDisconnected");
        mHandler.sendEmptyMessage(0);
    }


    @Override
    public boolean handleMessage(Message msg) {
        if (mOnFinishedInitListener != null) {
            switch (msg.what) {
                case 0:
                    mOnFinishedInitListener.onError((String) msg.obj);
                    break;
                case 1:
                    mOnFinishedInitListener.onInitOk();
            }
        }
        return false;
    }


}

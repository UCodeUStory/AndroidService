package com.wangpos.coresdkmanager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wangpos.androidservice.CoreServiceBinder;
import com.wangpos.androidservice.init.InitBinder;

import java.util.Random;

/**
 * aidl make project 项目后 才会生成类
 */

public class MainActivity extends AppCompatActivity implements InitListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    CoreSDKManager coreSDKManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coreSDKManager = CoreSDKManager.getInstance();
        coreSDKManager.init(this,this);


    }

    @Override
    public void onError(String obj) {
        Log.i(TAG, "onError");
    }

    @Override
    public void onInitOk() {
        Log.i(TAG, "onInitOk");

        InitBinder service = InitBinder.Stub.asInterface(coreSDKManager.getService(InitBinder.class));

        try {
            service.init();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}

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
import android.widget.Toast;

import com.wangpos.androidservice.init.InitManager;

import java.util.Random;

/**
 * aidl make project 项目后 才会生成类
 */

public class MainActivity extends AppCompatActivity implements InitListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    CoreManager coreSDKManager;

    InitManager initManagerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coreSDKManager = CoreManager.getInstance();
        coreSDKManager.init(this,this);


    }

    @Override
    public void onError(String obj) {
        Log.i(TAG, "onError");
    }

    @Override
    public void onInitOk() {
        Log.i(TAG, "onInitOk");
         initManagerService = InitManager.Stub.asInterface(coreSDKManager.getService(InitManager.class));
    }


    public void getService(View view){
        String msg = null;
        try {
            msg = initManagerService.getName();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}

package com.wangpos.androidservice;

import android.os.IBinder;
import android.os.RemoteException;

import com.wangpos.androidservice.init.InitManager;
import com.wangpos.androidservice.init.InitManagerService;

import java.util.HashMap;

/**
 * Created by qiyue on 2018/4/3.
 */

public class CoreManager extends CoreServiceManager.Stub{

    private HashMap<String,IBinder> serviceMap = new HashMap<>();

    public static CoreManager newInstance(){
        return new CoreManager();
    }

    public CoreManager(){
        serviceMap.put(InitManager.class.getSimpleName(), InitManagerService.newInstance());
    }

    @Override
    public IBinder getServices(String key) throws RemoteException {
        return serviceMap.get(key);
    }
}

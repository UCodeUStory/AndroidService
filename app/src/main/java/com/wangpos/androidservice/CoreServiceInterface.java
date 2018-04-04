package com.wangpos.androidservice;

import android.os.IBinder;
import android.os.RemoteException;

import com.wangpos.androidservice.init.InitBinder;
import com.wangpos.androidservice.init.InitService;

import java.util.HashMap;

/**
 * Created by qiyue on 2018/4/3.
 */

public class CoreServiceInterface extends CoreServiceBinder.Stub{

    private HashMap<String,IBinder> serviceMap = new HashMap<>();

    public static CoreServiceInterface newInstance(){
        return new CoreServiceInterface();
    }

    public CoreServiceInterface(){
        serviceMap.put(InitBinder.class.getSimpleName(),InitService.newInstance());
    }

    @Override
    public IBinder getServices(String key) throws RemoteException {
        return serviceMap.get(key);
    }
}

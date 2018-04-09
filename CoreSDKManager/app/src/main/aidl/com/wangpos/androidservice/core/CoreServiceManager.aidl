// CoreServiceBinder.aidl
package com.wangpos.androidservice.core;

// Declare any non-default types here with import statements

interface CoreServiceManager {

    /**
     * 除了基本数据类型，其他类型的参数都需要标上方向类型：in(输入), out(输出), inout(输入输出)
     */
    IBinder getServices(String key);

}

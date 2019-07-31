package com.chang.test;

import java.lang.reflect.Proxy;

public class RpcClientProxy {

    public <T> T cilentProxy(Class<T> interfaceCls, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{interfaceCls}, new RpcProxyHandler(host, port));
    }
}

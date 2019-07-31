package com.chang.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcProxyHandler implements InvocationHandler {

    String host;
    int port;

    public RpcProxyHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(args);
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);

        return rpcNetTransport.send(rpcRequest);
    }
}

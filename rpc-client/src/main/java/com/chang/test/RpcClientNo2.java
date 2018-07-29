package com.chang.test;

public class RpcClientNo2 {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IService service = rpcClientProxy.cilentProxy(IService.class, "127.0.0.1", 8080);
        System.out.println(service.eatWith("banana"));
    }
}

package com.chang.test;

public class RpcServerMain {
    public static void main(String[] args) {
        IService service = new ServiceImpl();
        RpcServerSender rpcServerSender = new RpcServerSender();
        rpcServerSender.sender(service, 8080);
    }
}

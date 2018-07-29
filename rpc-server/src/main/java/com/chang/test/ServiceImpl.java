package com.chang.test;

public class ServiceImpl implements IService {
    @Override
    public String sayHello(String username) {
        return "Hello " + username;
    }

    @Override
    public String eatWith(String foodName) {
        return "eat " + foodName;
    }
}

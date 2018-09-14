package com.scienjus.sofa.rpc.hystrix;

public class HelloServiceFallback implements HelloService {

    @Override
    public String sayHello(String string) {
        return "fallback " + string + " !";
    }

}
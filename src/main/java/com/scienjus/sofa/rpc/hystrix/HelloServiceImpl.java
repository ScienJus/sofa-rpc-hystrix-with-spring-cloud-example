package com.scienjus.sofa.rpc.hystrix;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String string) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Server receive: " + string);
        return "hello " + string + " ！";
    }

}
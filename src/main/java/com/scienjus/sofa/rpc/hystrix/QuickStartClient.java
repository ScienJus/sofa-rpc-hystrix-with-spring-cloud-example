package com.scienjus.sofa.rpc.hystrix;

import com.alipay.sofa.rpc.common.RpcConfigs;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.hystrix.HystrixConstants;
import com.alipay.sofa.rpc.hystrix.HystrixFilter;
import com.alipay.sofa.rpc.hystrix.SofaHystrixConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Service;

import java.util.Collections;

@EnableCircuitBreaker
@SpringBootApplication
@EnableHystrixDashboard
public class QuickStartClient {

    public static void main(String[] args) {
        SpringApplication.run(QuickStartClient.class, args);
    }

    @Service
    static class QuickStartClientRunner implements CommandLineRunner {

        @Override
        public void run(String... strings) throws Exception {
            ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                    .setInterfaceId(HelloService.class.getName())
                    .setProtocol("bolt")
                    .setFilterRef(Collections.<Filter>singletonList(new HystrixFilter()))
                    .setDirectUrl("bolt://127.0.0.1:12200");

            RpcConfigs.putValue(HystrixConstants.SOFA_HYSTRIX_ENABLED, true);

            SofaHystrixConfig.registerFallback(consumerConfig, new HelloServiceFallback());

            HelloService helloService = consumerConfig.refer();
            while (true) {
                System.out.println(helloService.sayHello("world"));
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }
        }
    }

}

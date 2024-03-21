package com.spm.vasylyshyn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
public class CounterDisplayServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(CounterDisplayServerApplication.class, args);

    }

}

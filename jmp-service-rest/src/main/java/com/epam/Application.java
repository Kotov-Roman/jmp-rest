package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

//@SpringBootApplication(scanBasePackages = {"com.epam"})
@SpringBootApplication()
//@ComponentScan(basePackages = {"com.epam"})
public class Application {

    @Autowired
    MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @PostConstruct
    void after(){
        System.out.println("call");
        myService.hi();
    }

}

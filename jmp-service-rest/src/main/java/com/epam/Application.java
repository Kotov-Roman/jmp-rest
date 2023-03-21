package com.epam;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

//@SpringBootApplication(scanBasePackages = {"com.epam"})
@SpringBootApplication()
@OpenAPIDefinition
public class Application {

    @Autowired
    MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @PostConstruct
    void after(){
        myService.hi();
    }

}

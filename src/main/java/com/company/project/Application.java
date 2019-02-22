package com.company.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

import com.company.project.msg.MsgServer;

@SpringBootApplication
@ServletComponentScan
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
    	// 解决 socket中不能注入的问题
    	SpringApplication springApplication = new SpringApplication(Application.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        MsgServer.setApplicationContext(configurableApplicationContext);
    }
}


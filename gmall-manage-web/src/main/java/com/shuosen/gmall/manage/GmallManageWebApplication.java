package com.shuosen.gmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.shuosen.gmall")
public class GmallManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallManageWebApplication.class, args);
    }

}

package com.shuosen.gmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//这里的mapper scan需要使用 tk.mybatis.spring.annotation.MapperScan;
@MapperScan("com.shuosen.gmall.user.mapper")
@ComponentScan("com.shuosen.gmall")
public class GmallUserManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserManagerApplication.class, args);
    }

}

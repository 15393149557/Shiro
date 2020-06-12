package com.baizhi.zq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
   //注意在这要将org的注解放在tk的下面  解决自定义mapper和自动生成mapper之间的错误
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }

}

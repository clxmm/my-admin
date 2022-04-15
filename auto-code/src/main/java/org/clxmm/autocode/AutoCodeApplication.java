package org.clxmm.autocode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("org.clxmm.autocode.autocode.mapper")
public class AutoCodeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AutoCodeApplication.class, args);

        System.out.println(11);
    }

}

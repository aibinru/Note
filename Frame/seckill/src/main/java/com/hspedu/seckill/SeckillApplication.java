package com.hspedu.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 韩顺平
 * @version 1.0
 */
@SpringBootApplication
@MapperScan("com.hspedu.seckill.mapper")
public class SeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }
}

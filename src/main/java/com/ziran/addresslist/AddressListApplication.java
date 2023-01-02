package com.ziran.addresslist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.ziran.addresslist.mapper")
@EnableScheduling
public class AddressListApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressListApplication.class, args);
    }
    @GetMapping("/")
    public String index() {
        return "ok";
    }
}

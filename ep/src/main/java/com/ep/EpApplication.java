package com.ep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.ep.db", "com.ep.core",
        "com.ep.admin"})
@MapperScan("com.ep.db.dao")
@EnableTransactionManagement
@EnableScheduling
public class EpApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpApplication.class, args);
    }

}

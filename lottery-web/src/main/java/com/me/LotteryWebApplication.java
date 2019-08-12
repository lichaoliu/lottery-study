package com.me;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMethodCache(basePackages="com.me")
@EnableCreateCacheAnnotation
public class LotteryWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryWebApplication.class, args);
    }

}

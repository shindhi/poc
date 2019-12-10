package com.invillia;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Slf4j
@Configuration
public class TestConfig {

    @Bean
    public Faker faker() {
        long seed = System.currentTimeMillis();

        return new Faker(new Random(seed));
    }

}

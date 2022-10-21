package com.keunoh.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    //@SpringBootApplication과 @EnableJpaAuditing을 분리하기 위해 존재하는 클래스
}

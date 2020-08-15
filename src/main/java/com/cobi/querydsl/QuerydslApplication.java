package com.cobi.querydsl;

import com.cobi.querydsl.entity.Order;
import com.cobi.querydsl.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication
public class QuerydslApplication implements CommandLineRunner{
    private final MemberService memberService;

    public static void main(String[] args) {
        SpringApplication.run(QuerydslApplication.class, args);
    }

    @Override
    public void run(String... args) {
        memberService.init();
    }

    @Bean
    CommandLineRunner onStartUp() {
        return args -> {
            Order orderByFavorite = memberService.createOrderByFavorite(1L);
            System.out.println(orderByFavorite);
        };
    }
}

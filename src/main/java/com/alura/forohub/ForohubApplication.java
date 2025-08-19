package com.alura.forohub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ForohubApplication {

    private static final Logger log = LoggerFactory.getLogger(ForohubApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ForohubApplication.class, args);
	}

    @Bean
    CommandLineRunner databaseHealthcheck(JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
                System.out.println("✅ MySQL connection OK (SELECT 1 -> {})");
            } catch (Exception e) {
                System.out.println("❌ MySQL connection FAILED");
                log.error("❌ MySQL connection FAILED", e);
            }
        };
    }
}

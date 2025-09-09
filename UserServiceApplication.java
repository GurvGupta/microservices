package com.example.user_service;

import com.example.user_service.service.UnderWriterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    // Seed default admin at startup
    @Bean
    public CommandLineRunner seedAdmin(UnderWriterService service) {
        return args -> {
            service.registerAdmin("admin", "admin123");
            System.out.println("✅ Default Admin created: username=admin, password=admin123");
        };
    }
}
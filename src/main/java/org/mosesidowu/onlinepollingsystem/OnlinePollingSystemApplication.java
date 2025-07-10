package org.mosesidowu.onlinepollingsystem;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class OnlinePollingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinePollingSystemApplication.class, args);
    }

    @Component
    public static class EnvPrinter {
        @PostConstruct
        public void printEnvVars() {
            System.out.println("DEBUG: Value of SPRING_DATASOURCE_URL: " + System.getenv("SPRING_DATASOURCE_URL"));
            System.out.println("DEBUG: Value of SPRING_DATASOURCE_USERNAME: " + System.getenv("SPRING_DATASOURCE_USERNAME"));
            System.out.println("DEBUG: Value of SPRING_DATASOURCE_PASSWORD: " + System.getenv("SPRING_DATASOURCE_PASSWORD"));
            System.out.println("DEBUG: Value of PORT: " + System.getenv("PORT"));
            System.out.println("DEBUG: Value of spring.datasource.url property: " + System.getProperty("spring.datasource.url"));
        }
    }
}

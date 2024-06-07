package com.example.ebookbackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaAuditing
@ServletComponentScan // support servlet
@SpringBootApplication
public class EBookBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBookBackendApplication.class, args);
    }

}

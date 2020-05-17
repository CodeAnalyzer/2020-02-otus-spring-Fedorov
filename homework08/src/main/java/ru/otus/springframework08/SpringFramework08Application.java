package ru.otus.springframework08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringFramework08Application {

        public static void main(String[] args) throws Exception {
                ApplicationContext context = SpringApplication.run(SpringFramework08Application.class, args);
        }

}

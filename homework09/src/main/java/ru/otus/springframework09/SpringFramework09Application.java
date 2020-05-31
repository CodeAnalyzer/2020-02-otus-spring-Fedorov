package ru.otus.springframework09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.h2.tools.Console;

@SpringBootApplication
public class SpringFramework09Application {

        public static void main(String[] args) throws Exception {
                Console.main(args);
                ApplicationContext context = SpringApplication.run(SpringFramework09Application.class, args);
        }

}

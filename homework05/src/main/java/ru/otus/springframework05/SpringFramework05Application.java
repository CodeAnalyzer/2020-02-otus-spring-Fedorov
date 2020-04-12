package ru.otus.springframework05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.h2.tools.Console;

@SpringBootApplication
public class SpringFramework05Application {

	public static void main(String[] args) throws Exception {
		Console.main(args);
		ApplicationContext context = SpringApplication.run(SpringFramework05Application.class, args);
	}

}

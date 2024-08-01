
package com.daniel.app.TaskManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.Arrays;

@SpringBootApplication
public class TaskManagerApplication {


	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(TaskManagerApplication.class);
		ConfigurableApplicationContext context = springApplication.run(args);
		System.out.println("TaskManager Application started...");

	}


	public static void getBeans(ConfigurableApplicationContext context) {
		String[] beans = context.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans) {
			System.out.println(bean);
		}
	}



}

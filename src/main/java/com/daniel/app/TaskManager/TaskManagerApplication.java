
package com.daniel.app.TaskManager;

import com.daniel.app.TaskManager.Loader.LoadEnv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class TaskManagerApplication {
public static void main(String[] args) throws IOException {

      //  LoadEnv.loadEnv();
		SpringApplication springApplication = new SpringApplication(TaskManagerApplication.class);
		ConfigurableApplicationContext context = springApplication.run(args);

	 //  LoadEnv.printEnv();
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

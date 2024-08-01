package com.daniel.app.TaskManager.task.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile("dev")
public class CheckEnv {

    private final Environment environment;

    public CheckEnv(Environment environment) {
        this.environment = environment;
    }



  @PostConstruct()
    public void printEnvironmentVariables() {
        System.out.println("Application Environment Variables:");
        String[] envs = environment.getActiveProfiles();
        for (String env : envs) {
            System.out.println("Current environment: " + env);
        }
    }
}

package com.daniel.app.TaskManager.Loader;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LoadEnv {
    public static void loadEnv() throws IOException {
        Map<String, String> envMap = new HashMap<>();
        File envFile = new File(".env");
        if (!envFile.exists()) {
            throw new IOException(".env file not found at " + envFile.getAbsolutePath());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(envFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line);
                if (!line.trim().startsWith("#") && line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        envMap.put(parts[0].trim(), parts[1].trim());
                        System.out.println("Loaded key: " + parts[0].trim() + " with value: " + parts[1].trim());
                    }
                }
            }
        }
        envMap.forEach((key, value) -> {
            System.setProperty(key, value);
//            System.out.println("Set system property: " + key + " = " + value);
        });
    }

    public static void printEnv() {
        System.out.println("PROD_DB_HOST: " + System.getProperty("PROD_DB_HOST"));
        System.out.println("PROD_DB: " + System.getProperty("PROD_DB"));
        System.out.println("PROD_DB_USER: " + System.getProperty("PROD_DB_USER"));
        System.out.println("PROD_DB_PASS: " + System.getProperty("PROD_DB_PASS"));
        System.out.println("PROD_JWT_SECRET: " + System.getProperty("PROD_JWT_SECRET"));
        System.out.println("PROD_JWT_EXPIRES: " + System.getProperty("PROD_JWT_EXPIRES"));
    }
}

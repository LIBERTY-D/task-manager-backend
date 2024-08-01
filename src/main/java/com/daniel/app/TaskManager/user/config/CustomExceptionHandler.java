package com.daniel.app.TaskManager.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;


@Component
public class CustomExceptionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        //ObjectMapper is a core class in the Jackson library,
        // a popular JSON processing library for Java.
        // It is used to convert Java objects to JSON (serialization)
        // and JSON to Java objects (deserialization).
        // Here’s a brief overview of its functionality
        // and how it can be utilized in your Spring application:
        HashMap<String,Object> vals = new HashMap<>();
        vals.put("status",HttpServletResponse.SC_FORBIDDEN);
        vals.put("message","You do not have the required permissions to " +
                "access this resource.");
        ObjectMapper objectMapper =  new ObjectMapper();
        String valueToReturn = objectMapper.writeValueAsString(vals);

        response.getOutputStream().println(valueToReturn);

    }
}

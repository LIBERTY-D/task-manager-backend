package com.daniel.app.TaskManager.user.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.jwt-config")
public class Env {

    private  String jwt_secret;
    private  long jwt_expires;

    public long getJwt_expires() {
        return jwt_expires;
    }

    public Env setJwt_expires(long jwt_expires) {
        this.jwt_expires = jwt_expires;
        return this;
    }

    public String getJwt_secret() {
        return jwt_secret;
    }

    public Env setJwt_secret(String jwt_secret) {
        this.jwt_secret = jwt_secret;
        return this;
    }
}

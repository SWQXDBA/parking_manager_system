package com.example.parking_manager_system;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("admin")
@Component
public class ConfigurationPropertiesConfig {
    String authKey;
    String adminName;
    String password;
}

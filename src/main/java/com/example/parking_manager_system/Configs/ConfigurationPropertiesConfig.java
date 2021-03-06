package com.example.parking_manager_system.Configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("admin")
@Component
public class ConfigurationPropertiesConfig {
    String authKey;
    String username;
    String password;
}

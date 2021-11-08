package com.example.parking_manager_system;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ParkingManagerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingManagerSystemApplication.class, args);
    }

}

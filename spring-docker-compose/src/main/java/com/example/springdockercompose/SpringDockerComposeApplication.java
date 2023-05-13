package com.example.springdockercompose;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisConnectionDetails;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringDockerComposeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDockerComposeApplication.class, args);
    }

    @Component
    public class DemoCommandLineRunner implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {

            System.out.println("CommandLineRunner Args: " + Arrays.toString(args));
        }
    }

}

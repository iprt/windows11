package org.iproute.windows11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author bit
 */
@SpringBootApplication
@EnableScheduling
public class Windows11Application {

    public static void main(String[] args) {
        SpringApplication.run(Windows11Application.class, args);
    }
}

package com.tyg.tygyy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TygyyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TygyyApplication.class, args);
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:8081/toIndex");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

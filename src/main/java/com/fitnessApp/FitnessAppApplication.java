//package com.fitnessApp;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class FitnessAppApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(FitnessAppApplication.class, args);
//		System.out.println("Its Running perfectly");
//	}
//
//}


package com.fitnessApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FitnessAppApplication extends SpringBootServletInitializer {

    // Used when deploying WAR to external Tomcat
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FitnessAppApplication.class);
    }

    // Used when running as a JAR (embedded Tomcat)
    public static void main(String[] args) {
        SpringApplication.run(FitnessAppApplication.class, args);
        System.out.println("Its Running perfectly");
    }
}

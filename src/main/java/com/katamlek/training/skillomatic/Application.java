package com.katamlek.training.skillomatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class Application {

    /**
     * Main entry point of the Java application
     *
     * @param args
     */
    public static void main(String[] args) {

        //Spring scans for the @SprintBootApplication annotation and from then on "component scans (@ComponentScan)
        // the current package for more "beans or components"

        //@Component, @Configuration, @Service are all beans, with different aspects associated
        // (check AOP - Aspect Oriented Programming for more information)

        //Tells Spring to start the application context
        SpringApplication.run(Application.class, args);
    }

}

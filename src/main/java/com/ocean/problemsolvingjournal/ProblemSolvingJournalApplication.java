package com.ocean.problemsolvingjournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ocean.problemsolvingjournal"}) // include other packages under com.ocean
public class ProblemSolvingJournalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProblemSolvingJournalApplication.class, args);
    }
}

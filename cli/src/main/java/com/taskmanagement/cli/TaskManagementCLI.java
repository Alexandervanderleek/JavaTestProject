package com.taskmanagement.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@SpringBootApplication
public class TaskManagementCLI {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagementCLI.class, args);
    }
}

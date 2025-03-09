package com.taskmanagement.cli.command;

import com.taskmanagement.cli.config.UserSession;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Component
@Command(
        name = "task",
        description = "Task Management CLI",
        versionProvider = MainCommand.VersionProvider.class,
        mixinStandardHelpOptions = true,
        subcommands = {
                LoginCommand.class,
                TaskCommand.class,
                SprintCommand.class,
                EpicCommand.class
        }
)
public class MainCommand implements Callable<Integer>, CommandLineRunner {

    @Option(names = {"--no-auth-check"}, description = "Skip authentication check")
    private boolean skipAuthCheck;

    @Autowired
    private CommandLine.IFactory factory;

    @Autowired
    private UserSession userSession;

    @Autowired
    private LoginCommand loginCommand;

    private CommandLine commandLine;

    @PostConstruct
    public void init() {
        commandLine = new CommandLine(this, factory);
    }

    @Override
    public void run(String... args) throws Exception {
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        if (skipAuthCheck || userSession.isAuthenticated()) {
            System.out.println("Task Management CLI");
            if (userSession.isAuthenticated()) {
                System.out.println("Logged in as: " + userSession.getUserName() + " (" + userSession.getUserEmail() + ")");
            }
            commandLine.usage(System.out);
            return 0;
        } else {
            System.out.println("You are not authenticated. Please login first.");
            return loginCommand.call();
        }
    }

    static class VersionProvider implements IVersionProvider {
        @Override
        public String[] getVersion() {
            return new String[]{"Task Management CLI v0.1.0"};
        }
    }
}
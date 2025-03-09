package com.taskmanagement.cli.command;

import com.taskmanagement.cli.config.UserSession;
import com.taskmanagement.cli.service.APIService;
import com.taskmanagement.cli.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.awt.Desktop;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.Scanner;

@Component
@Command(name = "login", description = "Authenticate with Google")
public class LoginCommand implements Callable<Integer> {

    @Option(names = {"-h", "--headless"}, description = "Run in headless mode (provide token manually)")
    private boolean headless;

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private APIService apiService;

    @Autowired
    private UserSession userSession;

    @Override
    public Integer call() throws Exception {
        System.out.println("Starting authentication with Google...");

        String idToken;

        if (headless) {
            System.out.println("Running in headless mode. Please authenticate in a browser and paste the ID token here:");
            Scanner scanner = new Scanner(System.in);
            idToken = scanner.nextLine().trim();
        } else {
            // Generate auth URL and open in browser
            URI authUrl = oAuthService.getAuthorizationUrl();

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                System.out.println("Opening browser for authentication...");
                Desktop.getDesktop().browse(authUrl);
            } else {
                System.out.println("Please open the following URL in your browser to authenticate:");
                System.out.println(authUrl);
            }

            // Start local server to receive the auth callback
            System.out.println("Waiting for authentication...");
            idToken = oAuthService.waitForAuthorizationCode();

            if (idToken == null) {
                System.err.println("Failed to obtain authentication token. Please try again.");
                return 1;
            }
        }

        // Exchange Google ID token for application JWT token
        System.out.println("Authenticating with server...");

        try {
            Map<String, Object> response = apiService.authenticate(idToken);

            String token = (String) response.get("token");
            String name = (String) response.get("name");
            String email = (String) response.get("email");

            // Save token to session
            userSession.setToken(token);
            userSession.setUserName(name);
            userSession.setUserEmail(email);
            userSession.saveToFile();

            System.out.println("Successfully authenticated as " + name + " (" + email + ")");
            return 0;
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
            return 1;
        }
    }
}
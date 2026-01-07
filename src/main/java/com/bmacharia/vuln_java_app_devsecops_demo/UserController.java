package com.bmacharia.vuln_java_app_devsecops_demo;

import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@RestController
public class UserController {

    // 🔴 Hardcoded secret (picked by TruffleHog / detect-secrets)
    private static final String API_KEY = "sk_live_51HARD_CODED_SECRET_EXAMPLE";

    // 🔴 Hardcoded database credentials
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "password123";

    /**
     * 🔴 SQL Injection vulnerability
     * SAST tools will flag string concatenation in SQL queries
     */
    @GetMapping("/user")
    public String getUser(@RequestParam String username) throws Exception {
        String query = "SELECT * FROM users WHERE username = '" + username + "'";

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/appdb",
                DB_USER,
                DB_PASSWORD);

        Statement stmt = conn.createStatement();
        stmt.execute(query);

        return "Executed query: " + query;
    }

    /**
     * 🔴 Command Injection vulnerability
     * Runtime.exec with user input
     */
    @GetMapping("/ping")
    public String ping(@RequestParam String host) throws Exception {
        Runtime.getRuntime().exec("ping " + host);
        return "Ping command executed for host: " + host;
    }

    /**
     * 🔴 Sensitive data exposure
     * API key returned in response
     */
    @GetMapping("/config")
    public String getConfig() {
        return "Using API key: " + API_KEY;
    }

    /**
     * 🔴 Insecure logging of secrets
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
            @RequestParam String password) {

        System.out.println("User login attempt: " + username);
        System.out.println("Password used: " + password); // 🚨 secret in logs

        return "Login attempted";
    }
}
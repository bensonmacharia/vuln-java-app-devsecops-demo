package com.bmacharia.vuln_java_app_devsecops_demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/user")
    public String getUser(@RequestParam String username) {
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        return query;
    }

    @GetMapping("/ping")
    public String ping(@RequestParam String host) throws Exception {
        Runtime.getRuntime().exec("ping " + host);
        return "Ping sent";
    }

    private static final String API_KEY = "sk_test_1234567890abcdef";
}

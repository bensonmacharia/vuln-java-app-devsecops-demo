package com.bmacharia.vuln_java_app_devsecops_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure/echo")
    public String safeEcho(@RequestParam String input) {
        String sanitized = input.replaceAll("[<>\"']", "");
        return "Safe input: " + sanitized;
    }
}
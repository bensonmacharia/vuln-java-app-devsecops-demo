package com.bmacharia.vuln_java_app_devsecops_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VulnerableController {

    // ⚠️ Example: reflected input (XSS-style demo)
    @GetMapping("/vuln/echo")
    public String echo(@RequestParam String input) {
        return "You sent: " + input;
    }
}

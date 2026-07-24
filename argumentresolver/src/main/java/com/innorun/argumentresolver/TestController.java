package com.innorun.argumentresolver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/request")
    public String requestParamDemo(@CustomRequestParam String name) {
        return "name=" + name;
    }
}
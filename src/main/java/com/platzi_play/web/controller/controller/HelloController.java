package com.platzi_play.web.controller.controller;

import com.platzi_play.domain.service.PlatziPlayAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final PlatziPlayAiService aiService;

    public HelloController(PlatziPlayAiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.aiService.generateGreeting();
    }
}

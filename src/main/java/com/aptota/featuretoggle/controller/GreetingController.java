package com.aptota.featuretoggle.controller;

import com.aptota.featuretoggle.model.Greeting;
import com.aptota.featuretoggle.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;

    @GetMapping("/greeting")
    public ResponseEntity<Greeting> getMessage(){
        return ResponseEntity.ok(greetingService.getMessage());
    }

}

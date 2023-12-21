package com.example.server.controller;

import com.example.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService service;


    @PostMapping("exchange-code")
    public ResponseEntity<?> exchangeCodeForToken(@RequestBody String code) {

        var response = service.getAccessToken(code);

        if (response.getStatusCode().is2xxSuccessful()) {
            String token = response.getBody();
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error retrieving the access token");
        }
    }

}
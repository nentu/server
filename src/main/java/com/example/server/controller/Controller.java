package com.example.server.controller;

import com.example.server.service.ControllerService;
import com.example.server.storage.dto.CoordinateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController()
@EnableWebMvc
@SessionAttributes(value = "user")
@RequestMapping("coordinate")
class Controller {

    private final ControllerService service;

    Controller(ControllerService service) {
        this.service = service;
    }
    @PostMapping("/")
    ResponseEntity<?> coordinate(@RequestBody CoordinateDTO coordinate, @AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(
                service.addCoordinate(coordinate, principal)
        );
    }

    @GetMapping("/table")
    ResponseEntity<?> getAll(@AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(
                service.getTable(principal)
        );
    }

    @Transactional
    @GetMapping("/clear")
    public void clearAll(@AuthenticationPrincipal Jwt principal) {
        service.clear(principal);
    }
}

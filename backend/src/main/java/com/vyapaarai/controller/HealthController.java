package com.vyapaarai.controller;

import com.vyapaarai.dto.HealthResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<HealthResponseDTO> getHealthStatus() {
        HealthResponseDTO response = new HealthResponseDTO("success", "VyapaarAI backend is running");
        return ResponseEntity.ok(response);
    }
}

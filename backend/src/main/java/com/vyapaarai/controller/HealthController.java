package com.vyapaarai.controller;

import com.vyapaarai.dto.HealthResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api")
public class HealthController {

    private final DataSource dataSource;

    public HealthController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/health")
    public ResponseEntity<HealthResponseDTO> getHealthStatus() {
        String dbStatus = "Connected (H2 In-Memory DB)";
        try (Connection conn = dataSource.getConnection()) {
            if (conn == null || conn.isClosed()) {
                dbStatus = "Disconnected";
            }
        } catch (Exception e) {
            dbStatus = "Error: " + e.getMessage();
        }

        String timestampStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        HealthResponseDTO response = new HealthResponseDTO(
                "success",
                "VyapaarAI backend is running",
                dbStatus,
                "1.0.0",
                timestampStr
        );
        return ResponseEntity.ok(response);
    }
}


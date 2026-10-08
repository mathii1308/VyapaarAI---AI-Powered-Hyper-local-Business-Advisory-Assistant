package com.vyapaarai.controller;

import com.vyapaarai.dto.HealthResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testHealthEndpointReturnsSuccess() {
        String url = "http://localhost:" + port + "/api/health";
        ResponseEntity<HealthResponseDTO> response = restTemplate.getForEntity(url, HealthResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        HealthResponseDTO body = response.getBody();
        assertNotNull(body);
        assertEquals("success", body.getStatus());
        assertEquals("VyapaarAI backend is running", body.getMessage());
        assertNotNull(body.getDatabase());
        assertEquals("1.0.0", body.getVersion());
    }
}

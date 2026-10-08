package com.vyapaarai.controller;

import com.vyapaarai.dto.ChatRequestDTO;
import com.vyapaarai.dto.ChatResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testChatEndpointWithGeneralMessage() {
        String url = "http://localhost:" + port + "/api/chat";

        ChatRequestDTO request = new ChatRequestDTO();
        request.setMessage("How can I improve my business profit margin?");

        ResponseEntity<ChatResponseDTO> response = restTemplate.postForEntity(url, request, ChatResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ChatResponseDTO body = response.getBody();
        assertNotNull(body);
        assertNotNull(body.getMessage());
        assertFalse(body.getMessage().trim().isEmpty());
    }
}

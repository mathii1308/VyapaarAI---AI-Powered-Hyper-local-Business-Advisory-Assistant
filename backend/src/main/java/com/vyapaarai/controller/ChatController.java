package com.vyapaarai.controller;

import com.vyapaarai.dto.ChatRequestDTO;
import com.vyapaarai.dto.ChatResponseDTO;
import com.vyapaarai.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ChatResponseDTO> handleChatMessage(@Valid @RequestBody ChatRequestDTO requestDTO) {
        ChatResponseDTO response = chatService.processChatMessage(requestDTO);
        return ResponseEntity.ok(response);
    }
}

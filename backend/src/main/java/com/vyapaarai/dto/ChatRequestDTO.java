package com.vyapaarai.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class ChatRequestDTO {

    @NotBlank(message = "Message content is required")
    private String message;

    private Long businessProfileId;

    private List<ChatMessageDTO> history;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getBusinessProfileId() {
        return businessProfileId;
    }

    public void setBusinessProfileId(Long businessProfileId) {
        this.businessProfileId = businessProfileId;
    }

    public List<ChatMessageDTO> getHistory() {
        return history;
    }

    public void setHistory(List<ChatMessageDTO> history) {
        this.history = history;
    }
}

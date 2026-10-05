package com.vyapaarai.dto;

import java.util.List;
import java.util.Map;

public class ChatResponseDTO {

    private String message;
    private Long businessProfileId;
    private Map<String, Object> financialContext;
    private Map<String, Object> strategyRecommendations;
    private List<Map<String, String>> knowledgeSources;

    public ChatResponseDTO() {}

    public ChatResponseDTO(String message, Long businessProfileId, Map<String, Object> financialContext) {
        this.message = message;
        this.businessProfileId = businessProfileId;
        this.financialContext = financialContext;
    }

    public ChatResponseDTO(String message, Long businessProfileId, Map<String, Object> financialContext, Map<String, Object> strategyRecommendations, List<Map<String, String>> knowledgeSources) {
        this.message = message;
        this.businessProfileId = businessProfileId;
        this.financialContext = financialContext;
        this.strategyRecommendations = strategyRecommendations;
        this.knowledgeSources = knowledgeSources;
    }

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

    public Map<String, Object> getFinancialContext() {
        return financialContext;
    }

    public void setFinancialContext(Map<String, Object> financialContext) {
        this.financialContext = financialContext;
    }

    public Map<String, Object> getStrategyRecommendations() {
        return strategyRecommendations;
    }

    public void setStrategyRecommendations(Map<String, Object> strategyRecommendations) {
        this.strategyRecommendations = strategyRecommendations;
    }

    public List<Map<String, String>> getKnowledgeSources() {
        return knowledgeSources;
    }

    public void setKnowledgeSources(List<Map<String, String>> knowledgeSources) {
        this.knowledgeSources = knowledgeSources;
    }
}


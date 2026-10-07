package com.vyapaarai.service;

import com.vyapaarai.dto.ChatRequestDTO;
import com.vyapaarai.dto.ChatResponseDTO;
import com.vyapaarai.dto.BusinessProfileResponseDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    private final BusinessProfileService businessProfileService;
    private final StrategyService strategyService;
    private final KnowledgeBaseService knowledgeBaseService;
    private final LLMService llmService;

    public ChatService(BusinessProfileService businessProfileService,
                       StrategyService strategyService,
                       KnowledgeBaseService knowledgeBaseService,
                       LLMService llmService) {
        this.businessProfileService = businessProfileService;
        this.strategyService = strategyService;
        this.knowledgeBaseService = knowledgeBaseService;
        this.llmService = llmService;
    }

    public ChatResponseDTO processChatMessage(ChatRequestDTO requestDTO) {
        Long profileId = requestDTO.getBusinessProfileId();
        BusinessProfileResponseDTO profile = null;

        // Tool 1: Business Profile & Tool 2: Financial Analysis Engine
        if (profileId != null) {
            try {
                profile = businessProfileService.getProfileById(profileId);
            } catch (Exception e) {
                profile = null;
            }
        }

        // Tool 3: Strategy Engine
        Map<String, Object> strategyEval = null;
        if (profile != null) {
            strategyEval = strategyService.evaluateStrategies(profile);
        }

        // Tool 4: Knowledge Base Retrieval
        List<KnowledgeBaseService.KnowledgeItem> knowledgeItems =
                knowledgeBaseService.searchKnowledge(requestDTO.getMessage(), profile != null ? profile.getBusinessCategory() : null);

        StringBuilder kbContext = new StringBuilder();
        List<Map<String, String>> sourcesList = new ArrayList<>();

        if (!knowledgeItems.isEmpty()) {
            kbContext.append("=== RELEVANT KNOWLEDGE & SCHEME BASE ===\n");
            for (KnowledgeBaseService.KnowledgeItem ki : knowledgeItems) {
                kbContext.append("- ").append(ki.getTitle()).append(" (").append(ki.getCategory()).append("):\n");
                kbContext.append("  ").append(ki.getContent()).append("\n");
                kbContext.append("  Source Citation: ").append(ki.getSourceCitation()).append("\n\n");

                Map<String, String> srcMap = new HashMap<>();
                srcMap.put("title", ki.getTitle());
                srcMap.put("category", ki.getCategory());
                srcMap.put("citation", ki.getSourceCitation());
                sourcesList.add(srcMap);
            }
        }

        // Strategy Context for LLM
        StringBuilder strategyContext = new StringBuilder();
        if (strategyEval != null && strategyEval.containsKey("topStrategy")) {
            @SuppressWarnings("unchecked")
            Map<String, Object> top = (Map<String, Object>) strategyEval.get("topStrategy");
            if (top != null) {
                strategyContext.append("=== DETERMINISTIC STRATEGY EVALUATION ===\n");
                strategyContext.append("Top Recommended Strategy: ").append(top.get("name")).append(" (Score: ").append(top.get("viabilityScore")).append("/100 - ").append(top.get("tier")).append(")\n");
                strategyContext.append("Summary: ").append(top.get("summary")).append("\n");
                strategyContext.append("Action Plan Steps: ").append(top.get("actionSteps")).append("\n\n");
            }
        }

        String combinedToolContext = (strategyContext.toString() + kbContext.toString()).trim();

        // Call LLM Service layer with aggregated tool context
        String aiResponse = llmService.generateAdvisoryResponse(
                requestDTO.getMessage(),
                profile,
                combinedToolContext,
                requestDTO.getHistory()
        );

        // Build financial context map for front-end consumption
        Map<String, Object> financialContext = new HashMap<>();
        if (profile != null) {
            financialContext.put("businessName", profile.getBusinessName());
            financialContext.put("monthlyRevenue", profile.getMonthlyRevenue());
            financialContext.put("monthlyExpenses", profile.getMonthlyExpenses());
            financialContext.put("netProfit", profile.getNetMonthlyProfit());
            financialContext.put("profitMargin", profile.getProfitMarginPercent());
            financialContext.put("dscr", profile.getDebtServiceCoverageRatio());
            financialContext.put("isDebtFree", profile.getIsDebtFree());
            financialContext.put("financialHealthScore", profile.getFinancialHealthScore());
            financialContext.put("financialHealthTier", profile.getFinancialHealthTier());
        }

        return new ChatResponseDTO(aiResponse, profileId, financialContext, strategyEval, sourcesList);
    }
}

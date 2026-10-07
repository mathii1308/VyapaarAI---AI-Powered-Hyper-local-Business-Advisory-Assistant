package com.vyapaarai.service;

import com.vyapaarai.dto.ChatMessageDTO;
import com.vyapaarai.dto.BusinessProfileResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class LLMService {

    private static final Logger logger = LoggerFactory.getLogger(LLMService.class);

    @Value("${llm.api.key:${LLM_API_KEY:}}")
    private String apiKey;

    @Value("${llm.model:${LLM_MODEL:gemini-1.5-flash}}")
    private String model;

    private final RestTemplate restTemplate;

    public LLMService() {
        this.restTemplate = new RestTemplate();
    }

    public String generateAdvisoryResponse(String userMessage, BusinessProfileResponseDTO profile, List<ChatMessageDTO> history) {
        return generateAdvisoryResponse(userMessage, profile, null, history);
    }

    public String generateAdvisoryResponse(String userMessage, BusinessProfileResponseDTO profile, String additionalToolContext, List<ChatMessageDTO> history) {
        String systemPrompt = buildSystemPrompt();
        String businessContext = buildBusinessContextString(profile);

        if (additionalToolContext != null && !additionalToolContext.trim().isEmpty()) {
            businessContext = businessContext + "\n\n" + additionalToolContext.trim();
        }

        // If LLM API Key is configured, attempt calling the external LLM provider (Google Gemini REST API)
        if (apiKey != null && !apiKey.trim().isEmpty()) {
            try {
                return callGeminiAPI(systemPrompt, businessContext, userMessage, history);
            } catch (Exception e) {
                logger.error("LLM API call failed, falling back to structured advisory response: {}", e.getMessage());
                return getFallbackAdvisoryResponse(userMessage, profile);
            }
        }

        // Fallback / Demo Advisory Engine if no API key is provided
        return getFallbackAdvisoryResponse(userMessage, profile);
    }

    private String buildSystemPrompt() {
        return """
            You are VyapaarAI — an AI-powered business advisory assistant for rural and small-scale micro-entrepreneurs.
            
            GUIDELINES & CONSTRAINTS:
            1. Understand the entrepreneur's question thoroughly.
            2. Use their actual business context and provided financial metrics as the authoritative source of truth.
            3. Explain financial metrics (Revenue, Expenses, Net Profit, Profit Margin, DSCR, Debt Burden, Health Score) in simple, clear, and accessible language.
            4. Provide practical, actionable operational and financial suggestions tailored to micro-enterprises.
            5. Ask for missing information when necessary.
            6. Clearly distinguish verified facts from assumptions or estimates.
            7. NEVER invent market statistics.
            8. NEVER guarantee profits or business returns.
            9. NEVER guarantee loan approval.
            10. NEVER invent government scheme eligibility.
            11. NEVER recalculate financial metrics when the backend already provides them. Use the provided calculations as the exact source of truth.
            12. Format your response cleanly using structured Markdown headers (e.g. ### Key Insights, ### Recommendation, ### Next Steps).
            """;
    }

    private String buildBusinessContextString(BusinessProfileResponseDTO p) {
        if (p == null) {
            return "NO BUSINESS PROFILE LOADED. Provide general micro-enterprise business advice based on sound financial principles.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== BUSINESS PROFILE CONTEXT ===\n");
        sb.append("Business Name: ").append(p.getBusinessName() != null ? p.getBusinessName() : "N/A").append("\n");
        sb.append("Entrepreneur Name: ").append(p.getEntrepreneurName() != null ? p.getEntrepreneurName() : "N/A").append("\n");
        sb.append("Business Category: ").append(p.getBusinessCategory() != null ? p.getBusinessCategory() : "N/A").append("\n");
        sb.append("Location: ").append(p.getLocation() != null ? p.getLocation() : "N/A").append("\n");
        sb.append("Years in Business: ").append(p.getYearsInBusiness() != null ? p.getYearsInBusiness() : 0).append("\n");
        sb.append("Business Description: ").append(p.getDescription() != null ? p.getDescription() : "N/A").append("\n");
        sb.append("Primary Business Goal: ").append(p.getBusinessGoal() != null ? p.getBusinessGoal() : "N/A").append("\n");
        sb.append("Goal Details: ").append(p.getGoalDescription() != null ? p.getGoalDescription() : "N/A").append("\n\n");

        sb.append("=== FINANCIAL DATA (MONTHLY DECLARED) ===\n");
        sb.append("Available Investment / Reserves: ₹").append(p.getAvailableInvestment()).append("\n");
        sb.append("Monthly Revenue: ₹").append(p.getMonthlyRevenue()).append("\n");
        sb.append("Monthly Expenses: ₹").append(p.getMonthlyExpenses()).append("\n");
        sb.append("Existing Loan Outstanding: ₹").append(p.getExistingLoan()).append("\n");
        sb.append("Monthly Loan Payment (EMI): ₹").append(p.getMonthlyLoanPayment()).append("\n\n");

        sb.append("=== BACKEND CALCULATED FINANCIAL METRICS (SOURCE OF TRUTH) ===\n");
        sb.append("Net Monthly Profit: ₹").append(p.getNetMonthlyProfit()).append("\n");
        sb.append("Profit Margin: ").append(p.getProfitMarginPercent()).append("%\n");
        sb.append("Debt Service Coverage Ratio (DSCR): ").append(Boolean.TRUE.equals(p.getIsDebtFree()) ? "Debt-Free" : p.getDebtServiceCoverageRatio()).append("\n");
        sb.append("Debt Burden Ratio: ").append(p.getDebtBurdenPercent()).append("%\n");
        sb.append("Financial Health Score: ").append(p.getFinancialHealthScore()).append("/100 (").append(p.getFinancialHealthTier()).append(")\n");
        sb.append("Max Safe Monthly EMI Cap: ₹").append(p.getMaxSafeMonthlyEmi()).append("\n");
        sb.append("Estimated Max Safe Loan Capacity: ₹").append(p.getEstimatedMaxLoanAmount()).append("\n");

        return sb.toString();
    }

    private String callGeminiAPI(String systemPrompt, String businessContext, String userMessage, List<ChatMessageDTO> history) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();

        // System Instruction (System Prompt + Business Context)
        Map<String, Object> systemInstructionObj = new HashMap<>();
        systemInstructionObj.put("parts", List.of(Map.of("text", systemPrompt + "\n\n" + businessContext)));
        requestBody.put("system_instruction", systemInstructionObj);

        List<Map<String, Object>> contents = new ArrayList<>();

        if (history != null && !history.isEmpty()) {
            for (ChatMessageDTO msg : history) {
                if (msg == null || msg.getRole() == null || msg.getContent() == null || msg.getContent().trim().isEmpty()) {
                    continue;
                }
                String role = "assistant".equalsIgnoreCase(msg.getRole()) ? "model" : "user";
                
                // Gemini contents MUST start with 'user' role
                if (contents.isEmpty() && "model".equals(role)) {
                    continue;
                }
                
                // Avoid consecutive duplicate roles
                if (!contents.isEmpty()) {
                    String lastRole = (String) contents.get(contents.size() - 1).get("role");
                    if (lastRole.equals(role)) {
                        continue;
                    }
                }
                
                Map<String, Object> item = new HashMap<>();
                item.put("role", role);
                item.put("parts", List.of(Map.of("text", msg.getContent())));
                contents.add(item);
            }
        }

        // If contents ends with 'user', remove it to avoid consecutive 'user' turns when adding userMessage
        if (!contents.isEmpty()) {
            String lastRole = (String) contents.get(contents.size() - 1).get("role");
            if ("user".equals(lastRole)) {
                contents.remove(contents.size() - 1);
            }
        }

        // Add current user prompt
        Map<String, Object> userItem = new HashMap<>();
        userItem.put("role", "user");
        userItem.put("parts", List.of(Map.of("text", userMessage)));
        contents.add(userItem);

        requestBody.put("contents", contents);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.exchange(url, java.util.Objects.requireNonNull(HttpMethod.POST), entity, Map.class);

        Map<?, ?> responseBody = response.getBody();
        if (response.getStatusCode() == HttpStatus.OK && responseBody != null) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            if (candidates != null && !candidates.isEmpty()) {
                Map<String, Object> candidate = candidates.get(0);
                if (candidate != null) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> content = (Map<String, Object>) candidate.get("content");
                    if (content != null) {
                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                        if (parts != null && !parts.isEmpty()) {
                            Map<String, Object> part = parts.get(0);
                            if (part != null) {
                                return (String) part.get("text");
                            }
                        }
                    }
                }
            }
        }

        throw new RuntimeException("Empty or invalid response received from Gemini API");
    }

    /**
     * Deterministic, contextual Fallback Advisory Engine when LLM API Key is absent or unreachable.
     */
    private String getFallbackAdvisoryResponse(String query, BusinessProfileResponseDTO p) {
        String msg = query != null ? query.toLowerCase() : "";
        double revenue = p != null ? p.getMonthlyRevenue() : 50000;
        double netProfit = p != null ? p.getNetMonthlyProfit() : 15000;
        double margin = p != null ? p.getProfitMarginPercent() : 30;
        double emi = p != null ? p.getMonthlyLoanPayment() : 0;
        double maxEmi = p != null ? p.getMaxSafeMonthlyEmi() : 5000;
        double savings = p != null ? p.getAvailableInvestment() : 20000;
        String bName = p != null ? p.getBusinessName() : "your business";
        String category = p != null ? p.getBusinessCategory() : "micro-enterprise";

        if (msg.contains("perform") || msg.contains("how is my business")) {
            return String.format("""
                ### Financial Performance Analysis for %s

                ### Summary
                Your business generates **₹%,.0f** in monthly revenue with **₹%,.0f** in net profit, giving you a **%.1f%% profit margin**. Your financial health score is **%d/100** (%s).

                ### Key Observations
                - **Net Profit:** You retain ₹%,.0f each month after covering operating costs.
                - **Debt Status:** %s.
                - **Liquidity Buffer:** You have ₹%,.0f available in reserve capital.

                ### Next Steps
                1. Reinvest 20%% of net profit (₹%,.0f) into fast-moving inventory.
                2. Maintain a daily digital ledger (UPI/Bank) to build credit history for future bank loan applications.
                """, bName, revenue, netProfit, margin, p != null ? p.getFinancialHealthScore() : 75, p != null ? p.getFinancialHealthTier() : "Optimal", netProfit, p != null && p.getIsDebtFree() ? "Currently Debt-Free" : "Monthly EMI obligation of ₹" + String.format("%,.0f", emi), savings, netProfit * 0.2);
        } else if (msg.contains("expand")) {
            return String.format("""
                ### Expansion Advisory for %s

                ### Recommendation
                Expansion is **feasible** based on your current net monthly profit of **₹%,.0f**.

                ### Why?
                Your business maintains a **%.1f%% profit margin** and has ₹%,.0f in available capital reserves.

                ### Based on Your Numbers
                - **Available Reserves:** ₹%,.0f
                - **Max Safe Monthly EMI Cap:** ₹%,.0f/month
                - **Estimated Safe Borrowing Capacity:** Up to ₹%,.0f

                ### Next Steps
                1. Conduct a localized customer demand survey before committing capital.
                2. Use your available savings (₹%,.0f) for initial equipment instead of over-leveraging with debt.
                3. *Note: Consult with your local bank officer for formal credit scheme requirements.*
                """, bName, netProfit, margin, savings, savings, maxEmi, p != null ? p.getEstimatedMaxLoanAmount() : 150000, savings * 0.5);
        } else if (msg.contains("loan") || msg.contains("afford")) {
            return String.format("""
                ### Debt Affordability & Loan Assessment

                ### Recommendation
                Based on your cash flow, your maximum recommended monthly loan EMI is **₹%,.0f/month**.

                ### Based on Your Numbers
                - **Net Monthly Surplus:** ₹%,.0f
                - **Existing Monthly EMI:** ₹%,.0f
                - **Safe Additional Debt Capacity:** ₹%,.0f/month (40%% net surplus rule)
                - **Estimated Loan Limit:** Up to ₹%,.0f

                ### Next Steps
                1. Compare institutional loan options like **PM MUDRA (Kishore/Tarun)** or **CGTMSE collateral-free guarantee**.
                2. Prepare 6 months of bank account statements and sales bills.
                3. *Disclaimer: Final loan approval and interest rates depend on bank underwriting.*
                """, maxEmi, netProfit, emi, maxEmi, p != null ? p.getEstimatedMaxLoanAmount() : 150000);
        } else if (msg.contains("profit") || msg.contains("improve")) {
            return String.format("""
                ### Profit Optimization Strategy

                ### Recommendation
                Target a **3–5%% margin improvement** by reducing raw material procurement costs and minimizing waste.

                ### Why?
                Your current profit margin is **%.1f%%**. In the %s sector, benchmark margins range between 20%% and 35%%.

                ### Based on Your Numbers
                - **Current Monthly Revenue:** ₹%,.0f
                - **Current Expenses:** ₹%,.0f
                - **Potential Monthly Savings:** ₹%,.0f (by reducing expenses by 5%%)

                ### Next Steps
                1. Renegotiate bulk purchase terms with wholesale suppliers.
                2. Focus on top 20%% high-margin products or services.
                """, margin, category, revenue, p != null ? p.getMonthlyExpenses() : 35000, (p != null ? p.getMonthlyExpenses() : 35000) * 0.05);
        } else {
            return String.format("""
                ### VyapaarAI Business Advisory Response

                ### Based on Your Business Profile (%s)
                - **Category:** %s
                - **Monthly Revenue:** ₹%,.0f
                - **Net Monthly Profit:** ₹%,.0f (Margin: %.1f%%)
                - **Financial Health Score:** %d/100

                ### Recommendation
                To address your inquiry regarding "%s":
                1. Prioritize cash flow stability before taking on new financial commitments.
                2. Maintain a 3-month operating reserve (₹%,.0f).
                3. For formal banking decisions, verify all requirements with institutional lenders.
                """, bName, category, revenue, netProfit, margin, p != null ? p.getFinancialHealthScore() : 75, query, (p != null ? p.getMonthlyExpenses() : 35000) * 3);
        }
    }
}

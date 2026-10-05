package com.vyapaarai.service;

import com.vyapaarai.dto.BusinessProfileResponseDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StrategyService {

    public Map<String, Object> evaluateStrategies(BusinessProfileResponseDTO p) {
        Map<String, Object> result = new HashMap<>();

        if (p == null) {
            result.put("status", "no_profile");
            result.put("message", "No business profile provided for strategy evaluation.");
            return result;
        }

        double netProfit = p.getNetMonthlyProfit() != null ? p.getNetMonthlyProfit() : 0.0;
        double profitMargin = p.getProfitMarginPercent() != null ? p.getProfitMarginPercent() : 0.0;
        double investment = p.getAvailableInvestment() != null ? p.getAvailableInvestment() : 0.0;
        double expenses = p.getMonthlyExpenses() != null ? p.getMonthlyExpenses() : 0.0;
        Double dscr = p.getDebtServiceCoverageRatio();
        boolean isDebtFree = Boolean.TRUE.equals(p.getIsDebtFree());
        int healthScore = p.getFinancialHealthScore() != null ? p.getFinancialHealthScore() : 50;

        List<Map<String, Object>> strategies = new ArrayList<>();

        // Strategy 1: Operational Cost & Expense Optimization
        int costScore = 85;
        if (profitMargin < 15.0) costScore += 10;
        costScore = Math.min(100, costScore);
        strategies.add(createStrategy(
                "Cost & Inventory Optimization",
                costScore,
                costScore >= 75 ? "Highly Recommended" : "Feasible",
                "Reduce material acquisition cost by 3–5% and eliminate excess slow-moving stock.",
                List.of("Negotiate bulk discounts with tier-1 wholesalers", "Transition to a 15-day inventory reorder cycle", "Track daily cash outflow using a digital ledger")
        ));

        // Strategy 2: Local Marketing & Customer Loyalty
        int mktScore = 75;
        if (investment >= 5000) mktScore += 15;
        mktScore = Math.min(100, mktScore);
        strategies.add(createStrategy(
                "Local Marketing & Customer Loyalty",
                mktScore,
                mktScore >= 80 ? "Highly Recommended" : "Feasible",
                "Increase repeat purchase rate by 20% using localized WhatsApp cataloging and QR payments.",
                List.of("Set up WhatsApp Business catalog for easy ordering", "Offer 5% cashback or discount on UPI payments", "Distribute targeted local flyers or market banners")
        ));

        // Strategy 3: High-Margin Product Diversification
        int divScore = 60;
        if (profitMargin >= 20.0) divScore += 20;
        if (investment >= 15000) divScore += 15;
        divScore = Math.min(100, divScore);
        strategies.add(createStrategy(
                "Product & Service Diversification",
                divScore,
                divScore >= 75 ? "Highly Recommended" : "Feasible",
                "Introduce complementary, high-margin products requiring minimal capital investment.",
                List.of("Identify top 3 customer-requested add-on products", "Test small batch stock using available reserves (₹" + String.format("%.0f", investment * 0.3) + ")", "Evaluate 30-day sales velocity before scaling")
        ));

        // Strategy 4: Business Expansion & Equipment Scaling
        int expScore = 40;
        if (netProfit > 20000) expScore += 25;
        if (isDebtFree || (dscr != null && dscr >= 1.5)) expScore += 25;
        if (investment >= 25000) expScore += 10;
        expScore = Math.min(100, expScore);
        strategies.add(createStrategy(
                "Capacity & Business Expansion",
                expScore,
                expScore >= 75 ? "Highly Recommended" : (expScore >= 55 ? "Feasible" : "Requires Caution"),
                "Expand physical outlet or procure automated equipment to increase throughput.",
                List.of("Conduct a local neighborhood demand check", "Cap monthly financing EMI at ₹" + String.format("%.0f", p.getMaxSafeMonthlyEmi()), "Use available capital reserves (₹" + String.format("%.0f", investment) + ") as initial equity buffer")
        ));

        // Sort strategies descending by score
        strategies.sort((a, b) -> Integer.compare((Integer) b.get("viabilityScore"), (Integer) a.get("viabilityScore")));

        result.put("strategies", strategies);
        result.put("topStrategy", strategies.get(0));
        result.put("businessName", p.getBusinessName());
        result.put("financialHealthScore", healthScore);

        return result;
    }

    private Map<String, Object> createStrategy(String name, int score, String tier, String summary, List<String> steps) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("viabilityScore", score);
        map.put("tier", tier);
        map.put("summary", summary);
        map.put("actionSteps", steps);
        return map;
    }
}

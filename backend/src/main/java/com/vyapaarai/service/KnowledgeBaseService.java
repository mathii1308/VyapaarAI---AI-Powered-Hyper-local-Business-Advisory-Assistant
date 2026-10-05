package com.vyapaarai.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KnowledgeBaseService {

    public static class KnowledgeItem {
        private String title;
        private String category; // "Government Scheme", "Financial Rule", "Operational Guidance"
        private String content;
        private String sourceCitation;

        public KnowledgeItem(String title, String category, String content, String sourceCitation) {
            this.title = title;
            this.category = category;
            this.content = content;
            this.sourceCitation = sourceCitation;
        }

        public String getTitle() { return title; }
        public String getCategory() { return category; }
        public String getContent() { return content; }
        public String getSourceCitation() { return sourceCitation; }
    }

    private final List<KnowledgeItem> knowledgeRepository = new ArrayList<>();

    public KnowledgeBaseService() {
        // Populate local micro-enterprise knowledge repository
        knowledgeRepository.add(new KnowledgeItem(
                "PM MUDRA Yojana (Micro Units Development & Refinance Agency)",
                "Government Scheme",
                "PM MUDRA provides collateral-free loans to non-corporate, non-farm micro/small enterprises. " +
                        "Category 1: Shishu (Up to ₹50,000 for startup/small equipment). " +
                        "Category 2: Kishore (₹50,000 to ₹5 Lakh for expanding business/stock). " +
                        "Category 3: Tarun (₹5 Lakh to ₹10 Lakh for mature scaling). " +
                        "Key requirement: 6 months of bank account history, sales records, and business ID proof.",
                "Ministry of Micro, Small & Medium Enterprises (MSME), Govt. of India — MUDRA Guidelines"
        ));

        knowledgeRepository.add(new KnowledgeItem(
                "CGTMSE (Credit Guarantee Fund Trust for Micro & Small Enterprises)",
                "Government Scheme",
                "Provides collateral-free loan guarantee cover up to ₹2 Crore for MSMEs. " +
                        "Allows micro-entrepreneurs to secure bank loans without mortgaging property or fixed assets. " +
                        "The guarantee fee is covered or subsidized for rural micro-enterprises.",
                "CGTMSE Trust Guidelines & SIDBI Micro-Credit Rules"
        ));

        knowledgeRepository.add(new KnowledgeItem(
                "The 40% EMI Debt Safety Rule",
                "Financial Rule",
                "A micro-business should never commit more than 40% of its net monthly surplus toward total debt EMI payments. " +
                        "Formula: Max Safe EMI = Net Monthly Profit × 0.40 − Existing EMI obligations. " +
                        "This protects the enterprise against seasonal sales dips and sudden supplier price hikes.",
                "VyapaarAI Financial Risk Management Benchmark"
        ));

        knowledgeRepository.add(new KnowledgeItem(
                "3-Month Liquidity Operating Reserve",
                "Financial Rule",
                "Before undertaking major capital expansion or taking new loans, an enterprise must maintain liquid capital reserves " +
                        "equal to at least 3 months of mandatory operating expenses (Rent + Raw Materials + Basic Wages).",
                "Reserve Bank of India (RBI) Micro-Enterprise Cash Flow Guidelines"
        ));

        knowledgeRepository.add(new KnowledgeItem(
                "Digital Payment History & Credit Building",
                "Operational Guidance",
                "Conducting sales transactions through UPI (PhonePe, Google Pay, Paytm) or bank transfers creates an auditable digital trail. " +
                        "Institutional lenders use digital turnover history to approve working capital loans faster without requiring complex audited IT returns.",
                "National Payments Corporation of India (NPCI) & Micro-Finance Advisory"
        ));
    }

    public List<KnowledgeItem> searchKnowledge(String query, String businessCategory) {
        if (query == null) query = "";
        String qLower = query.toLowerCase();

        List<KnowledgeItem> matches = new ArrayList<>();

        for (KnowledgeItem item : knowledgeRepository) {
            String combined = (item.getTitle() + " " + item.getContent() + " " + item.getCategory()).toLowerCase();
            if (combined.contains(qLower) ||
                (qLower.contains("loan") && item.getCategory().equals("Government Scheme")) ||
                (qLower.contains("expand") && item.getTitle().contains("Reserve")) ||
                (qLower.contains("profit") && item.getCategory().equals("Financial Rule"))) {
                matches.add(item);
            }
        }

        // If no query match, return top general financial/scheme rules
        if (matches.isEmpty()) {
            return knowledgeRepository.subList(0, Math.min(3, knowledgeRepository.size()));
        }

        return matches;
    }
}

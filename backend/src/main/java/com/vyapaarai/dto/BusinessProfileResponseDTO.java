package com.vyapaarai.dto;

import com.vyapaarai.model.BusinessProfile;
import java.time.LocalDateTime;

public class BusinessProfileResponseDTO {

    private Long id;
    private String entrepreneurName;
    private String location;
    private String contactPreference;
    private String businessName;
    private String businessCategory;
    private String description;
    private Integer yearsInBusiness;
    private Double availableInvestment;
    private Double monthlyRevenue;
    private Double monthlyExpenses;
    private Double existingLoan;
    private Double monthlyLoanPayment;
    private String businessGoal;
    private String goalDescription;
    private LocalDateTime createdAt;
    
    // Deterministic financial diagnostic metrics
    private Double netMonthlyProfit;
    private Double profitMarginPercent;
    private Double debtServiceCoverageRatio;
    private Boolean isDebtFree;
    private Double debtBurdenPercent;
    private Integer financialHealthScore;
    private String financialHealthTier;
    private Double maxSafeMonthlyEmi;
    private Double estimatedMaxLoanAmount;

    public static BusinessProfileResponseDTO fromEntity(BusinessProfile profile) {
        BusinessProfileResponseDTO dto = new BusinessProfileResponseDTO();
        dto.setId(profile.getId());
        dto.setEntrepreneurName(profile.getEntrepreneurName());
        dto.setLocation(profile.getLocation());
        dto.setContactPreference(profile.getContactPreference());
        dto.setBusinessName(profile.getBusinessName());
        dto.setBusinessCategory(profile.getBusinessCategory());
        dto.setDescription(profile.getDescription());
        dto.setYearsInBusiness(profile.getYearsInBusiness() != null ? profile.getYearsInBusiness() : 0);
        dto.setAvailableInvestment(profile.getAvailableInvestment() != null ? profile.getAvailableInvestment() : 0.0);
        dto.setMonthlyRevenue(profile.getMonthlyRevenue() != null ? profile.getMonthlyRevenue() : 0.0);
        dto.setMonthlyExpenses(profile.getMonthlyExpenses() != null ? profile.getMonthlyExpenses() : 0.0);
        dto.setExistingLoan(profile.getExistingLoan() != null ? profile.getExistingLoan() : 0.0);
        dto.setMonthlyLoanPayment(profile.getMonthlyLoanPayment() != null ? profile.getMonthlyLoanPayment() : 0.0);
        dto.setBusinessGoal(profile.getBusinessGoal());
        dto.setGoalDescription(profile.getGoalDescription());
        dto.setCreatedAt(profile.getCreatedAt());

        double revenue = dto.getMonthlyRevenue();
        double expenses = dto.getMonthlyExpenses();
        double monthlyEmi = dto.getMonthlyLoanPayment();

        // 1. Net Monthly Profit = Revenue - Expenses
        double netProfit = revenue - expenses;
        dto.setNetMonthlyProfit(netProfit);

        // 2. Profit Margin (%) = (Net Monthly Profit / Revenue) * 100
        double profitMargin = revenue > 0 ? (netProfit / revenue) * 100 : 0.0;
        dto.setProfitMarginPercent(Math.round(profitMargin * 100.0) / 100.0);

        // 3. DSCR = Net Monthly Profit / Monthly Loan Payment (when debt > 0)
        boolean debtFree = monthlyEmi <= 0;
        dto.setIsDebtFree(debtFree);
        if (debtFree) {
            dto.setDebtServiceCoverageRatio(null);
        } else {
            double dscr = netProfit > 0 ? netProfit / monthlyEmi : 0.0;
            dto.setDebtServiceCoverageRatio(Math.round(dscr * 100.0) / 100.0);
        }

        // 4. Debt Burden (%) = (Monthly EMI / Monthly Revenue) * 100
        double debtBurden = revenue > 0 ? (monthlyEmi / revenue) * 100 : 0.0;
        dto.setDebtBurdenPercent(Math.round(debtBurden * 100.0) / 100.0);

        // 5. Debt Capacity & Loan Eligibility (Cap debt service at 40% of net profit)
        double maxSafeEmi = Math.max(0.0, (netProfit * 0.40) - monthlyEmi);
        dto.setMaxSafeMonthlyEmi(Math.round(maxSafeEmi * 100.0) / 100.0);

        // Estimated loan principal assuming 12% APR over 36 months (multiplier ~ 30)
        double estLoan = Math.round(maxSafeEmi * 30.0);
        dto.setEstimatedMaxLoanAmount(estLoan);

        // 6. Deterministic Financial Health Score Calculation (0 to 100)
        int score = calculateDeterministicScore(netProfit, profitMargin, monthlyEmi, dto.getDebtServiceCoverageRatio(), debtFree, dto.getAvailableInvestment(), expenses, dto.getYearsInBusiness());
        dto.setFinancialHealthScore(score);

        if (score >= 80) {
            dto.setFinancialHealthTier("Optimal Health");
        } else if (score >= 60) {
            dto.setFinancialHealthTier("Moderate Capacity");
        } else if (score >= 40) {
            dto.setFinancialHealthTier("Fair Stability");
        } else {
            dto.setFinancialHealthTier("Critical Deficit");
        }

        return dto;
    }

    private static int calculateDeterministicScore(double netProfit, double profitMargin, double monthlyEmi, Double dscr, boolean isDebtFree, double investment, double expenses, int years) {
        if (netProfit <= 0) {
            return 20; // Default baseline for non-profitable / starting profile
        }

        int score = 0;

        // Profitability (Max 40 pts)
        if (profitMargin >= 25.0) score += 40;
        else if (profitMargin >= 15.0) score += 30;
        else if (profitMargin >= 5.0) score += 20;
        else score += 10;

        // Solvency / DSCR (Max 30 pts)
        if (isDebtFree) {
            score += 30;
        } else if (dscr != null) {
            if (dscr >= 1.5) score += 30;
            else if (dscr >= 1.2) score += 20;
            else if (dscr >= 1.0) score += 10;
        }

        // Liquidity Buffer (Max 15 pts)
        double reserveRatio = expenses > 0 ? investment / expenses : 1.0;
        if (reserveRatio >= 1.0) score += 15;
        else if (reserveRatio >= 0.5) score += 10;
        else if (reserveRatio > 0) score += 5;

        // Business Stability (Max 15 pts)
        if (years >= 3) score += 15;
        else if (years >= 1) score += 10;
        else score += 5;

        return Math.min(100, Math.max(0, score));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEntrepreneurName() { return entrepreneurName; }
    public void setEntrepreneurName(String entrepreneurName) { this.entrepreneurName = entrepreneurName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContactPreference() { return contactPreference; }
    public void setContactPreference(String contactPreference) { this.contactPreference = contactPreference; }

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getBusinessCategory() { return businessCategory; }
    public void setBusinessCategory(String businessCategory) { this.businessCategory = businessCategory; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getYearsInBusiness() { return yearsInBusiness; }
    public void setYearsInBusiness(Integer yearsInBusiness) { this.yearsInBusiness = yearsInBusiness; }

    public Double getAvailableInvestment() { return availableInvestment; }
    public void setAvailableInvestment(Double availableInvestment) { this.availableInvestment = availableInvestment; }

    public Double getMonthlyRevenue() { return monthlyRevenue; }
    public void setMonthlyRevenue(Double monthlyRevenue) { this.monthlyRevenue = monthlyRevenue; }

    public Double getMonthlyExpenses() { return monthlyExpenses; }
    public void setMonthlyExpenses(Double monthlyExpenses) { this.monthlyExpenses = monthlyExpenses; }

    public Double getExistingLoan() { return existingLoan; }
    public void setExistingLoan(Double existingLoan) { this.existingLoan = existingLoan; }

    public Double getMonthlyLoanPayment() { return monthlyLoanPayment; }
    public void setMonthlyLoanPayment(Double monthlyLoanPayment) { this.monthlyLoanPayment = monthlyLoanPayment; }

    public String getBusinessGoal() { return businessGoal; }
    public void setBusinessGoal(String businessGoal) { this.businessGoal = businessGoal; }

    public String getGoalDescription() { return goalDescription; }
    public void setGoalDescription(String goalDescription) { this.goalDescription = goalDescription; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Double getNetMonthlyProfit() { return netMonthlyProfit; }
    public void setNetMonthlyProfit(Double netMonthlyProfit) { this.netMonthlyProfit = netMonthlyProfit; }

    public Double getProfitMarginPercent() { return profitMarginPercent; }
    public void setProfitMarginPercent(Double profitMarginPercent) { this.profitMarginPercent = profitMarginPercent; }

    public Double getDebtServiceCoverageRatio() { return debtServiceCoverageRatio; }
    public void setDebtServiceCoverageRatio(Double debtServiceCoverageRatio) { this.debtServiceCoverageRatio = debtServiceCoverageRatio; }

    public Boolean getIsDebtFree() { return isDebtFree; }
    public void setIsDebtFree(Boolean isDebtFree) { this.isDebtFree = isDebtFree; }

    public Double getDebtBurdenPercent() { return debtBurdenPercent; }
    public void setDebtBurdenPercent(Double debtBurdenPercent) { this.debtBurdenPercent = debtBurdenPercent; }

    public Integer getFinancialHealthScore() { return financialHealthScore; }
    public void setFinancialHealthScore(Integer financialHealthScore) { this.financialHealthScore = financialHealthScore; }

    public String getFinancialHealthTier() { return financialHealthTier; }
    public void setFinancialHealthTier(String financialHealthTier) { this.financialHealthTier = financialHealthTier; }

    public Double getMaxSafeMonthlyEmi() { return maxSafeMonthlyEmi; }
    public void setMaxSafeMonthlyEmi(Double maxSafeMonthlyEmi) { this.maxSafeMonthlyEmi = maxSafeMonthlyEmi; }

    public Double getEstimatedMaxLoanAmount() { return estimatedMaxLoanAmount; }
    public void setEstimatedMaxLoanAmount(Double estimatedMaxLoanAmount) { this.estimatedMaxLoanAmount = estimatedMaxLoanAmount; }
}

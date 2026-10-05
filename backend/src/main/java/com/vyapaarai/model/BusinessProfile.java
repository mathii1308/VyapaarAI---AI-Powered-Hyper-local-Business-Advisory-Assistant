package com.vyapaarai.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "business_profiles")
public class BusinessProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entrepreneur_name", nullable = false)
    private String entrepreneurName;

    @Column(nullable = false)
    private String location;

    @Column(name = "contact_preference")
    private String contactPreference;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "business_category", nullable = false)
    private String businessCategory;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "years_in_business")
    private Integer yearsInBusiness;

    @Column(name = "available_investment")
    private Double availableInvestment;

    @Column(name = "monthly_revenue")
    private Double monthlyRevenue;

    @Column(name = "monthly_expenses")
    private Double monthlyExpenses;

    @Column(name = "existing_loan")
    private Double existingLoan;

    @Column(name = "monthly_loan_payment")
    private Double monthlyLoanPayment;

    @Column(name = "business_goal")
    private String businessGoal;

    @Column(name = "goal_description", columnDefinition = "TEXT")
    private String goalDescription;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public BusinessProfile() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntrepreneurName() {
        return entrepreneurName;
    }

    public void setEntrepreneurName(String entrepreneurName) {
        this.entrepreneurName = entrepreneurName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactPreference() {
        return contactPreference;
    }

    public void setContactPreference(String contactPreference) {
        this.contactPreference = contactPreference;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearsInBusiness() {
        return yearsInBusiness;
    }

    public void setYearsInBusiness(Integer yearsInBusiness) {
        this.yearsInBusiness = yearsInBusiness;
    }

    public Double getAvailableInvestment() {
        return availableInvestment;
    }

    public void setAvailableInvestment(Double availableInvestment) {
        this.availableInvestment = availableInvestment;
    }

    public Double getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(Double monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }

    public Double getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(Double monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public Double getExistingLoan() {
        return existingLoan;
    }

    public void setExistingLoan(Double existingLoan) {
        this.existingLoan = existingLoan;
    }

    public Double getMonthlyLoanPayment() {
        return monthlyLoanPayment;
    }

    public void setMonthlyLoanPayment(Double monthlyLoanPayment) {
        this.monthlyLoanPayment = monthlyLoanPayment;
    }

    public String getBusinessGoal() {
        return businessGoal;
    }

    public void setBusinessGoal(String businessGoal) {
        this.businessGoal = businessGoal;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

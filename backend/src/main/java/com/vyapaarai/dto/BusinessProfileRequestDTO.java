package com.vyapaarai.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BusinessProfileRequestDTO {

    @NotBlank(message = "Entrepreneur name is required")
    private String entrepreneurName;

    @NotBlank(message = "Location is required")
    private String location;

    private String contactPreference;

    @NotBlank(message = "Business name is required")
    private String businessName;

    @NotBlank(message = "Business category is required")
    private String businessCategory;

    private String description;

    @Min(value = 0, message = "Years in business must be a positive number")
    private Integer yearsInBusiness;

    @Min(value = 0, message = "Available investment cannot be negative")
    private Double availableInvestment;

    @NotNull(message = "Monthly revenue is required")
    @Min(value = 0, message = "Monthly revenue cannot be negative")
    private Double monthlyRevenue;

    @NotNull(message = "Monthly expenses are required")
    @Min(value = 0, message = "Monthly expenses cannot be negative")
    private Double monthlyExpenses;

    @Min(value = 0, message = "Existing loan amount cannot be negative")
    private Double existingLoan;

    @Min(value = 0, message = "Monthly loan payment cannot be negative")
    private Double monthlyLoanPayment;

    @NotBlank(message = "Business goal is required")
    private String businessGoal;

    private String goalDescription;

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
}

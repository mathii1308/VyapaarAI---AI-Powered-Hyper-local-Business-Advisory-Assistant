package com.vyapaarai.service;

import com.vyapaarai.dto.BusinessProfileRequestDTO;
import com.vyapaarai.dto.BusinessProfileResponseDTO;
import com.vyapaarai.model.BusinessProfile;
import com.vyapaarai.repository.BusinessProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessProfileService {

    private final BusinessProfileRepository repository;

    public BusinessProfileService(BusinessProfileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BusinessProfileResponseDTO createProfile(BusinessProfileRequestDTO requestDTO) {
        BusinessProfile profile = new BusinessProfile();
        profile.setEntrepreneurName(requestDTO.getEntrepreneurName());
        profile.setLocation(requestDTO.getLocation());
        profile.setContactPreference(requestDTO.getContactPreference());
        profile.setBusinessName(requestDTO.getBusinessName());
        profile.setBusinessCategory(requestDTO.getBusinessCategory());
        profile.setDescription(requestDTO.getDescription());
        profile.setYearsInBusiness(requestDTO.getYearsInBusiness() != null ? requestDTO.getYearsInBusiness() : 0);
        profile.setAvailableInvestment(requestDTO.getAvailableInvestment() != null ? requestDTO.getAvailableInvestment() : 0.0);
        profile.setMonthlyRevenue(requestDTO.getMonthlyRevenue() != null ? requestDTO.getMonthlyRevenue() : 0.0);
        profile.setMonthlyExpenses(requestDTO.getMonthlyExpenses() != null ? requestDTO.getMonthlyExpenses() : 0.0);
        profile.setExistingLoan(requestDTO.getExistingLoan() != null ? requestDTO.getExistingLoan() : 0.0);
        profile.setMonthlyLoanPayment(requestDTO.getMonthlyLoanPayment() != null ? requestDTO.getMonthlyLoanPayment() : 0.0);
        profile.setBusinessGoal(requestDTO.getBusinessGoal());
        profile.setGoalDescription(requestDTO.getGoalDescription());

        BusinessProfile savedProfile = repository.save(profile);
        return BusinessProfileResponseDTO.fromEntity(savedProfile);
    }

    @Transactional(readOnly = true)
    public BusinessProfileResponseDTO getProfileById(Long id) {
        java.util.Objects.requireNonNull(id, "ID must not be null");
        BusinessProfile profile = repository.findById(id)
                .orElseThrow(() -> new com.vyapaarai.exception.ResourceNotFoundException("Business profile not found with ID: " + id));
        return BusinessProfileResponseDTO.fromEntity(profile);
    }

    @Transactional(readOnly = true)
    public List<BusinessProfileResponseDTO> getAllProfiles() {
        return repository.findAll().stream()
                .map(BusinessProfileResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}

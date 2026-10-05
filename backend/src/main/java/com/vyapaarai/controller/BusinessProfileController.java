package com.vyapaarai.controller;

import com.vyapaarai.dto.BusinessProfileRequestDTO;
import com.vyapaarai.dto.BusinessProfileResponseDTO;
import com.vyapaarai.service.BusinessProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/business")
public class BusinessProfileController {

    private final BusinessProfileService businessProfileService;

    public BusinessProfileController(BusinessProfileService businessProfileService) {
        this.businessProfileService = businessProfileService;
    }

    @PostMapping
    public ResponseEntity<BusinessProfileResponseDTO> createBusinessProfile(
            @Valid @RequestBody BusinessProfileRequestDTO requestDTO) {
        BusinessProfileResponseDTO created = businessProfileService.createProfile(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessProfileResponseDTO> getBusinessProfileById(@PathVariable Long id) {
        BusinessProfileResponseDTO profile = businessProfileService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }

    @GetMapping
    public ResponseEntity<List<BusinessProfileResponseDTO>> getAllBusinessProfiles() {
        List<BusinessProfileResponseDTO> profiles = businessProfileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }
}

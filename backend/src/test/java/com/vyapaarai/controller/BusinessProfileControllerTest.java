package com.vyapaarai.controller;

import com.vyapaarai.dto.BusinessProfileRequestDTO;
import com.vyapaarai.dto.BusinessProfileResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BusinessProfileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateAndGetBusinessProfile() {
        String baseUrl = "http://localhost:" + port + "/api/business";

        BusinessProfileRequestDTO request = new BusinessProfileRequestDTO();
        request.setEntrepreneurName("Ramesh Kumar");
        request.setLocation("Anantapur, AP");
        request.setContactPreference("WhatsApp");
        request.setBusinessName("Lakshmi Store");
        request.setBusinessCategory("Retail / Kirana");
        request.setDescription("Kirana shop selling daily essentials");
        request.setYearsInBusiness(3);
        request.setAvailableInvestment(50000.0);
        request.setMonthlyRevenue(80000.0);
        request.setMonthlyExpenses(50000.0);
        request.setExistingLoan(0.0);
        request.setMonthlyLoanPayment(0.0);
        request.setBusinessGoal("Expand existing business");
        request.setGoalDescription("Add cold storage freezer");

        ResponseEntity<BusinessProfileResponseDTO> createResponse = restTemplate.postForEntity(baseUrl, request, BusinessProfileResponseDTO.class);

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        BusinessProfileResponseDTO createdBody = createResponse.getBody();
        assertNotNull(createdBody);
        assertNotNull(createdBody.getId());
        assertEquals("Lakshmi Store", createdBody.getBusinessName());
        assertEquals(30000.0, createdBody.getNetMonthlyProfit());
        assertEquals(37.5, createdBody.getProfitMarginPercent());
        assertTrue(createdBody.getIsDebtFree());

        Long createdId = createdBody.getId();
        ResponseEntity<BusinessProfileResponseDTO> getResponse = restTemplate.getForEntity(baseUrl + "/" + createdId, BusinessProfileResponseDTO.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        BusinessProfileResponseDTO getBody = getResponse.getBody();
        assertNotNull(getBody);
        assertEquals("Ramesh Kumar", getBody.getEntrepreneurName());
    }
}

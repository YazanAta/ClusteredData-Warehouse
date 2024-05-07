package com.progresssoft.controller;

import com.progresssoft.dto.DealRequestDTO;
import com.progresssoft.model.Deal;
import com.progresssoft.service.DealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class DealsControllerTest {

    @Mock
    private DealServiceImpl dealService;

    @InjectMocks
    private DealsController dealsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDeals() {
        // Given
        List<Deal> expectedDeals = Arrays.asList(new Deal(), new Deal());
        // Mock
        when(dealService.getAllDeals()).thenReturn(expectedDeals);

        // When
        ResponseEntity<List<Deal>> responseEntity = dealsController.getAllDeals();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedDeals, responseEntity.getBody());
    }

    @Test
    void testGetDealById() {
        // Given
        Long dealId = 1L;
        Deal expectedDeal = new Deal();

        // Mock
        when(dealService.getDealById(dealId)).thenReturn(expectedDeal);

        // When
        ResponseEntity<Deal> responseEntity = dealsController.getDealById(dealId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedDeal, responseEntity.getBody());
    }

    @Test
    void testImportDeal() {
        // Given
        DealRequestDTO dealRequestDTO = new DealRequestDTO();
        dealRequestDTO.setFromCurrencyIsoCode("USD");
        dealRequestDTO.setToCurrencyIsoCode("EUR");
        dealRequestDTO.setDealTimestamp(new Timestamp(System.currentTimeMillis()));
        dealRequestDTO.setDealAmount(100.0);

        Deal savedDeal = new Deal();

        // Mock
        when(dealService.importDeal(any())).thenReturn(savedDeal);

        // When
        ResponseEntity<Deal> responseEntity = dealsController.importDeal(dealRequestDTO);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedDeal, responseEntity.getBody());
    }
}
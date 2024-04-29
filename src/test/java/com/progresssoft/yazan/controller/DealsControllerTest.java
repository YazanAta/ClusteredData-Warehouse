//package com.progresssoft.yazan.controller;
//
//import com.progresssoft.yazan.dto.DealRequestDTO;
//import com.progresssoft.yazan.model.Deal;
//import com.progresssoft.yazan.service.DealService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@ExtendWith(MockitoExtension.class)
//class DealsControllerTest {
//
//    @Mock
//    private DealService dealService;
//
//    @InjectMocks
//    private DealsController dealsController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testGetAllDeals() {
//        // Arrange
//        List<Deal> deals = new ArrayList<>();
//        deals.add(new Deal("1", "Deal 1"));
//        deals.add(new Deal("2", "Deal 2"));
//        when(dealService.getAllDeals()).thenReturn(deals);
//
//        // Act
//        ResponseEntity<List<Deal>> response = dealsController.getAllDeals();
//
//        // Assert
//        verify(dealService, times(1)).getAllDeals();
//        assert response.getBody() != null;
//        assert response.getBody().size() == 2;
//    }
//
//    @Test
//    public void testGetDealById() {
//        // Arrange
//        String dealUniqueId = "1";
//        Deal deal = new Deal(dealUniqueId, "Deal 1");
//        when(dealService.getDealById(anyString())).thenReturn(deal);
//
//        // Act
//        ResponseEntity<Deal> response = dealsController.getDealById(dealUniqueId);
//
//        // Assert
//        verify(dealService, times(1)).getDealById(anyString());
//        assert response.getStatusCode() == HttpStatus.OK;
//        assert response.getBody() != null;
//        assert response.getBody().getId().equals(dealUniqueId);
//    }
//
//    @Test
//    public void testImportDeal() {
//        // Arrange
//        DealRequestDTO dealRequestDTO = new DealRequestDTO("Deal 1");
//        Deal deal = new Deal("1", "Deal 1");
//        when(dealService.importDeal(any(DealRequestDTO.class))).thenReturn(deal);
//
//        // Act
//        ResponseEntity<Deal> response = dealsController.importDeal(dealRequestDTO);
//
//        // Assert
//        verify(dealService, times(1)).importDeal(any(DealRequestDTO.class));
//        assert response.getStatusCode() == HttpStatus.CREATED;
//        assert response.getBody() != null;
//        assert response.getBody().getId().equals("1");
//    }
//}
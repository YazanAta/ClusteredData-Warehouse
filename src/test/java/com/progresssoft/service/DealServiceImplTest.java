package com.progresssoft.service;

import com.progresssoft.dto.DealRequestDTO;
import com.progresssoft.exceptions.DealNotFoundException;
import com.progresssoft.exceptions.NoDealsFoundException;
import com.progresssoft.model.Deal;
import com.progresssoft.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DealServiceImplTest {

    @InjectMocks
    private DealServiceImpl dealService;

    @Mock
    private DealRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void should_return_all_deals() {
        // Given
        Deal deal1 = new Deal(1L, "USD", "EUR", new Timestamp(System.currentTimeMillis()), 100.0);
        Deal deal2 = new Deal(2L, "EUR", "GBP", new Timestamp(System.currentTimeMillis()), 200.0);
        List<Deal> expectedDeals = Arrays.asList(deal1, deal2);
        when(repository.findAll()).thenReturn(expectedDeals);

        // When
        List<Deal> actualDeals = dealService.getAllDeals();

        // Then
        assertEquals(expectedDeals.size(), actualDeals.size());
        assertEquals(expectedDeals, actualDeals);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetAllDealsWhenNoDealsFound() {
        // Given
        // Mock
        when(repository.findAll()).thenReturn(Arrays.asList());

        // When & Then
        assertThrows(NoDealsFoundException.class, () -> {
            dealService.getAllDeals();
        });
        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_deal_based_on_id(){
        // Given
        Long id = 1L;
        Deal expectedDeal = new Deal(id, "USD", "EUR", new Timestamp(System.currentTimeMillis()), 100.0);

        // Mock call
        when(repository.findById(id))
                .thenReturn(Optional.of(expectedDeal));
        // When
        Deal actualDeal = dealService.getDealById(id);

        // Then
        assertEquals(expectedDeal, actualDeal);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testGetDealByIdWhenNotFound() {
        // Given
        when(repository.findById(1L))
                .thenThrow(DealNotFoundException.class);
        // When & Then
        assertThrows(DealNotFoundException.class, () -> {
            dealService.getDealById(1L);
        });
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void testImportDeal() {
        // Given
        DealRequestDTO dealRequestDTO = new DealRequestDTO(
                "USD",
                "JOR",
                new Timestamp(System.currentTimeMillis()),
                100.0
        );
        Deal expectedImportedDeal = new Deal(
                1L,
                "USD",
                "JOR",
                new Timestamp(System.currentTimeMillis()),
                100.0
        );
        // Mock
        when(repository.save(any(Deal.class)))
                .thenReturn(expectedImportedDeal);

        // When
        Deal importedDeal = dealService.importDeal(dealRequestDTO);

        // Then
        assertEquals(importedDeal, expectedImportedDeal);
        verify(repository, times(1)).save(any(Deal.class));
    }

    @Test
    public void testImportDealWhenExceptionThrown() {
        // Given
        DealRequestDTO dealRequest = new DealRequestDTO();
        dealRequest.setFromCurrencyIsoCode("USD");
        dealRequest.setToCurrencyIsoCode("EUR");
        dealRequest.setDealAmount(100.0);

        // Mock
        when(repository.save(any(Deal.class))).thenThrow(new RuntimeException("Unable to save deal"));

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            dealService.importDeal(dealRequest);
        });
        verify(repository, times(1)).save(any(Deal.class));
    }

}
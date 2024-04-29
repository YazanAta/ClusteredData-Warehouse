package com.progresssoft.yazan.service;

import com.progresssoft.yazan.dto.DealRequestDTO;
import com.progresssoft.yazan.exceptions.DealAlreadyExistException;
import com.progresssoft.yazan.model.Deal;
import com.progresssoft.yazan.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DealService {
    private final DealRepository dealRepository;

    @Autowired
    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public Deal getDealById(String dealUniqueId) {
        return dealRepository.findById(dealUniqueId)
                .orElseThrow(() -> new RuntimeException("Deal not found with id: " + dealUniqueId));
    }

    @Transactional
    public Deal importDeal(DealRequestDTO dealRequest) {

        // Check for duplicate deal_unique_id
        if (dealRepository.existsByDealUniqueId(dealRequest.getDealUniqueId())) {
            throw new DealAlreadyExistException("FX deal with the same unique ID already exists.");
        }

        Deal deal = new Deal();
        deal.setdealUniqueId(dealRequest.getDealUniqueId());
        deal.setFromCurrencyIsoCode(dealRequest.getFromCurrencyIsoCode());
        deal.setToCurrencyIsoCode(dealRequest.getToCurrencyIsoCode());
        deal.setDealTimestamp(dealRequest.getDealTimestamp());
        deal.setDealAmount(dealRequest.getDealAmount());

        return dealRepository.save(deal);
    }

}

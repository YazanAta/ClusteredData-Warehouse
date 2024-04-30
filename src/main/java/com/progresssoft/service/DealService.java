package com.progresssoft.service;

import com.progresssoft.dto.DealRequestDTO;
import com.progresssoft.model.Deal;

import java.util.List;

public interface DealService {
    List<Deal> getAllDeals();
    Deal getDealById(Long dealUniqueId);
    Deal importDeal(DealRequestDTO dealRequest);
}

package com.progresssoft.service;

import com.progresssoft.dto.DealRequestDTO;
import com.progresssoft.exceptions.DealNotFoundException;
import com.progresssoft.exceptions.DealValidationException;
import com.progresssoft.model.Deal;
import com.progresssoft.repository.DealRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private static final Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);

    @Autowired
    private DealRepository dealRepository;

    @Override
    public List<Deal> getAllDeals() {
        logger.info("Getting all deals");
        return dealRepository.findAll();
    }

    @Override
    public Deal getDealById(Long dealUniqueId) {
        logger.info("Getting deal by ID: {}", dealUniqueId);
        return dealRepository.findById(dealUniqueId)
                .orElseThrow(() -> {
                    logger.error("Deal not found with id: {}", dealUniqueId);
                    return new DealNotFoundException("Deal not found with id: " + dealUniqueId);
                });
    }

    @Transactional
    @Override
    public Deal importDeal(DealRequestDTO dealRequest) {
        try {
            Deal deal = new Deal();
            BeanUtils.copyProperties(deal, dealRequest);
            Deal importedDeal = dealRepository.save(deal);
            logger.info("Deal imported");
            return importedDeal;
        } catch (Exception e) {
            logger.error("Error importing deal", e);
            throw new DealValidationException(e.getMessage());
        }
    }
}
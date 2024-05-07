package com.progresssoft.service;

import com.progresssoft.dto.DealRequestDTO;
import com.progresssoft.exceptions.DealNotFoundException;
import com.progresssoft.exceptions.NoDealsFoundException;
import com.progresssoft.model.Deal;
import com.progresssoft.repository.DealRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private static final Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);

    private final DealRepository dealRepository;

    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public List<Deal> getAllDeals() {
        logger.info("Getting all deals");
        List<Deal> deals = dealRepository.findAll();
        if (deals.isEmpty()) {
            throw new NoDealsFoundException("No deals found");
        }
        return deals;
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
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
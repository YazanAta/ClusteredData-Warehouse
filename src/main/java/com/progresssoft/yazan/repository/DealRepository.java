package com.progresssoft.yazan.repository;

import com.progresssoft.yazan.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal, String> {
    boolean existsByDealUniqueId(String dealUniqueId);

}

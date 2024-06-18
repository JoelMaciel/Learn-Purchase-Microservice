package com.joel.purchase.domain.repositories;

import com.joel.purchase.domain.models.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

    Page<Purchase> findAllByUserId(UUID userId, Pageable pageable);
}

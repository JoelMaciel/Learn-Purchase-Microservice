package com.joel.purchase.domain.services;

import com.joel.purchase.domain.dtos.PurchaseDTO;
import com.joel.purchase.domain.dtos.PurchaseRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PurchaseService {

    Page<PurchaseDTO> findAllPurchaseOfUser(UUID userId, Pageable pageable);

    PurchaseDTO save(PurchaseRequestDTO purchaseRequestDTO);
}

package com.joel.purchase.domain.services.converter;

import com.joel.purchase.domain.dtos.PurchaseDTO;
import com.joel.purchase.domain.dtos.PurchaseRequestDTO;
import com.joel.purchase.domain.models.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PurchaseConverter {

    public Page<PurchaseDTO> purchaseDTOPage(Page<Purchase> purchases) {
        return purchases.map(this::toDTO);
    }

    public PurchaseDTO toDTO(Purchase purchase) {
        return PurchaseDTO.builder()
                .purchaseId(purchase.getPurchaseId())
                .userId(purchase.getUserId())
                .courseId(purchase.getCourseId())
                .title(purchase.getTitle())
                .price(purchase.getPrice())
                .orderDate(LocalDateTime.now())
                .build();
    }

    public Purchase toEntity(PurchaseRequestDTO purchaseRequestDTO) {
        return Purchase.builder()
                .userId(purchaseRequestDTO.getUserId())
                .courseId(purchaseRequestDTO.getCourseId())
                .title(purchaseRequestDTO.getTitle())
                .price(purchaseRequestDTO.getPrice())
                .orderDate(LocalDateTime.now())
                .build();
    }
}

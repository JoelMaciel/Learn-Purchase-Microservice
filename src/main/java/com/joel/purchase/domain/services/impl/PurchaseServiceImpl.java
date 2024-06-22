package com.joel.purchase.domain.services.impl;

import com.joel.purchase.api.security.AuthenticationCurrentUserService;
import com.joel.purchase.domain.dtos.PurchaseDTO;
import com.joel.purchase.domain.dtos.PurchaseRequestDTO;
import com.joel.purchase.domain.excptions.UserDoesNotHavePermissionException;
import com.joel.purchase.domain.models.Purchase;
import com.joel.purchase.domain.repositories.PurchaseRepository;
import com.joel.purchase.domain.services.PurchaseService;
import com.joel.purchase.domain.services.converter.PurchaseConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseConverter purchaseConverter;
    private final AuthenticationCurrentUserService authenticationCurrentUserService;

    @Override
    public Page<PurchaseDTO> findAllPurchaseOfUser(UUID userId, Pageable pageable) {
        UUID currentUserid = authenticationCurrentUserService.getCurrentUser().getUserId();
        if (currentUserid.equals(userId)) {
            Page<Purchase> purchases = purchaseRepository.findAllByUserId(userId, pageable);
            return purchaseConverter.purchaseDTOPage(purchases);
        } else {
            throw new UserDoesNotHavePermissionException("Forbidden");
        }
    }

    @Transactional
    @Override
    public PurchaseDTO save(PurchaseRequestDTO purchaseRequestDTO) {
        Purchase purchase = purchaseConverter.toEntity(purchaseRequestDTO);
        return purchaseConverter.toDTO(purchaseRepository.save(purchase));
    }
}

package com.joel.purchase.api.controllers;

import com.joel.purchase.domain.dtos.PurchaseDTO;
import com.joel.purchase.domain.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping("/{userId}")
    public Page<PurchaseDTO> getAllPurchaseOfUser(
            @PathVariable UUID userId,
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return purchaseService.findAllPurchaseOfUser(userId, pageable);
    }
}

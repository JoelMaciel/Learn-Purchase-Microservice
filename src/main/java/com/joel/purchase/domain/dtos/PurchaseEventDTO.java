package com.joel.purchase.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseEventDTO {

    private UUID userId;
    private UUID courseId;
    private String title;
    private Double price;
    private OffsetDateTime orderDate;
}

package com.joel.purchase.domain.dtos;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseRequestDTO {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID courseId;

    @NotBlank
    private String title;

    @NotNull
    @PositiveOrZero
    private Double price;
}

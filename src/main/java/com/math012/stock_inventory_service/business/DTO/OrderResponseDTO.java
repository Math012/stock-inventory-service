package com.math012.stock_inventory_service.business.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderResponseDTO {
    private String productName;
    int totalQuantity;
    private double productPrice;
    private String userEmail;
}
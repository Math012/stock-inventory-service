package com.math012.stock_inventory_service.infra.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "inventory_products")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_category")
    private String productCategory;
    @Column(name = "product_price")
    private double productPrice;
    @Column(name = "total_quantity")
    private int totalQuantity;
    @Column(name = "available_quantity")
    private int availableQuantity;
    @Column(name = "reserved_quantity")
    private int reservedQuantity;
}
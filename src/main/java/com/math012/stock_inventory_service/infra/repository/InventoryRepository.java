package com.math012.stock_inventory_service.infra.repository;

import com.math012.stock_inventory_service.infra.model.InventoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {

    Optional<InventoryEntity> findByProductName(String productName);

    @Transactional
    @Modifying
    @Query("UPDATE inventory_products i SET i.availableQuantity = i.availableQuantity - :qtd, i.reservedQuantity = i.reservedQuantity + :qtd WHERE i.productName = :productName")
    void updateReservedQuantity(@Param("qtd") int qtd, @Param("productName")String productName);

    @Transactional
    @Modifying
    @Query("UPDATE inventory_products i SET i.availableQuantity = i.availableQuantity + :qtd, i.reservedQuantity = i.reservedQuantity - :qtd WHERE i.productName = :productName")
    void updateRollbackProduct(@Param("qtd") int qtd, @Param("productName")String productName);

    @Transactional
    @Modifying
    @Query("UPDATE inventory_products i SET i.reservedQuantity = i.reservedQuantity - :qtd, i.totalQuantity = i.totalQuantity - :qtd WHERE i.productName = :productName")
    void updateTotalQuantity(@Param("qtd") int qtd, @Param("productName")String productName);
}
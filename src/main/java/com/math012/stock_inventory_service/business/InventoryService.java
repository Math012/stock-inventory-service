package com.math012.stock_inventory_service.business;

import com.math012.stock_inventory_service.business.DTO.OrderResponseDTO;
import com.math012.stock_inventory_service.business.converter.ConvertJson;
import com.math012.stock_inventory_service.business.topic.kafka.producer.InventoryProducer;
import com.math012.stock_inventory_service.infra.exception.ResourceNotFoundException;
import com.math012.stock_inventory_service.infra.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryService {

    @Autowired
    ConvertJson convertJson;

    @Autowired
    InventoryProducer producer;

    @Autowired
    InventoryRepository repository;

    public void getOrder(String order){
        OrderResponseDTO response = convertJson.readJson(order, OrderResponseDTO.class);
        var product = repository.findByProductName(response.getProductName()).orElseThrow(()-> new ResourceNotFoundException("The product name: " + response.getProductName() + ", not found!"));

        if (product.getAvailableQuantity() >= response.getTotalQuantity()){
            repository.updateReservedQuantity(response.getTotalQuantity(),response.getProductName());
            producer.sendProductHasStockTopic(response);
        }

        if (product.getAvailableQuantity() < response.getTotalQuantity()){
            producer.sendProductOutOfStockTopic(response);
            log.info("sem estoque");
        }
    }

    public void paymentConfirmed(String order){
        OrderResponseDTO response = convertJson.readJson(order, OrderResponseDTO.class);
        repository.updateTotalQuantity(response.getTotalQuantity(), response.getProductName());
    }

    public void rollbackProduct(String order){
        OrderResponseDTO response = convertJson.readJson(order, OrderResponseDTO.class);
        repository.updateRollbackProduct(response.getTotalQuantity(),response.getProductName());
    }
}
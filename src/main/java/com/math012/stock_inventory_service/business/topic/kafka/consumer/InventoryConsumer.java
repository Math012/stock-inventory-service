package com.math012.stock_inventory_service.business.topic.kafka.consumer;

import com.math012.stock_inventory_service.business.InventoryService;
import com.math012.stock_inventory_service.business.converter.ConvertJson;
import com.math012.stock_inventory_service.business.topic.kafka.producer.InventoryProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryConsumer {

    @Autowired
    private InventoryService service;

    @Autowired
    private ConvertJson converter;

    @Autowired
    private InventoryProducer producer;

    @KafkaListener(
            topics = "${spring.kafka.consumer.inventory.requesting.product.stock.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void requestingProductStock(String order){
        log.info("requestingProductStock: Consumindo o tópico requesting.product.stock.topic");
        service.getOrder(order);
    }

    @KafkaListener(
            topics = "${spring.kafka.consumer.order.payment.success.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void orderConfirmedPayment(String order){
        log.info("orderConfirmedPayment: Consumindo o tópico order.payment.success.topic");
        service.paymentConfirmed(order);
    }

    @KafkaListener(
            topics = "${spring.kafka.consumer.inventory.rollback.product.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void orderRollback(String order){
        log.info("orderRollback: Consumindo o tópico rollback.product.topic");
        service.rollbackProduct(order);
    }
}
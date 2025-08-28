package com.math012.stock_inventory_service.business.topic.kafka.producer;

import com.math012.stock_inventory_service.business.DTO.OrderResponseDTO;
import com.math012.stock_inventory_service.business.converter.ConvertJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ConvertJson converter;

    @Value("${spring.kafka.producer.inventory.product.out.of.stock.topic}")
    private String productOutOfStock;

    @Value("${spring.kafka.producer.inventory.product.has.stock.topic}")
    private String productHasStock;

    public void sendProductOutOfStockTopic(OrderResponseDTO dto){
        log.info("sendProductOutOfStockTopic: Convertendo o objeto dto: {} para Json", dto);
        String content = converter.convertToJson(dto);
        log.info("sendProductOutOfStockTopic: DTO convertido com sucesso");
        log.info("sendProductOutOfStockTopic: enviando o tópico: {}", productOutOfStock);
        kafkaTemplate.send(productOutOfStock,content);
        log.info("sendProductOutOfStockTopic: Tópico: {} enviado com sucesso", productOutOfStock);
    }

    public void sendProductHasStockTopic(OrderResponseDTO dto){
        log.info("sendProductHasStockTopic: Convertendo o objeto dto: {} para Json", dto);
        String content = converter.convertToJson(dto);
        log.info("sendProductHasStockTopic: DTO convertido com sucesso");
        log.info("sendProductHasStockTopic: enviando o tópico: {}", productHasStock);
        kafkaTemplate.send(productHasStock,content);
        log.info("sendProductHasStockTopic: Tópico: {} enviado com sucesso", productHasStock);
    }
}
package com.math012.stock_inventory_service.business.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertJson {

    @Autowired
    private ObjectMapper objectMapper;

    public String convertToJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            return "error: " + e.getMessage();
        }
    }

    public <T> T readJson(String jsonString, Class<T> classConverter){
        try {
            return objectMapper.readValue(jsonString,classConverter);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
package com.math012.stock_inventory_service.infra.exception.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StructException {
    private String msg;
    private Date timestamp;
    private String detail;
}
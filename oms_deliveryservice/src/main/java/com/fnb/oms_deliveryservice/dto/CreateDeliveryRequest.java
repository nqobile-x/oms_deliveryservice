package com.fnb.oms_deliveryservice.dto;

import lombok.Data;

@Data
public class CreateDeliveryRequest {
    private Long orderId;
    private Long customerId;
    private String deliveryAddress;
    private String driverName;
}

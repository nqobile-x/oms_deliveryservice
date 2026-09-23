package com.fnb.oms_deliveryservice.dto;

import com.fnb.oms_deliveryservice.entity.DeliveryStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DeliveryResponse {
    private Long deliveryId;
    private Long orderId;
    private Long customerId;
    private DeliveryStatus status;
    private String deliveryAddress;
    private String driverName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package com.fnb.oms_deliveryservice.dto;

import com.fnb.oms_deliveryservice.entity.DeliveryStatus;
import lombok.Data;

@Data
public class UpdateDeliveryStatusRequest {
    private DeliveryStatus status;
}

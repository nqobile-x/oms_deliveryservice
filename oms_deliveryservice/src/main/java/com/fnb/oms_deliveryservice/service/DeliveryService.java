package com.fnb.oms_deliveryservice.service;

import com.fnb.oms_deliveryservice.dto.CreateDeliveryRequest;
import com.fnb.oms_deliveryservice.dto.DeliveryResponse;
import com.fnb.oms_deliveryservice.dto.UpdateDeliveryStatusRequest;

import java.util.List;

public interface DeliveryService {
    DeliveryResponse createDelivery(CreateDeliveryRequest request);
    DeliveryResponse getDeliveryById(Long deliveryId);
    DeliveryResponse getDeliveryByOrderId(Long orderId);
    List<DeliveryResponse> getDeliveriesByCustomer(Long customerId);
    DeliveryResponse updateStatus(Long deliveryId, UpdateDeliveryStatusRequest request);
}

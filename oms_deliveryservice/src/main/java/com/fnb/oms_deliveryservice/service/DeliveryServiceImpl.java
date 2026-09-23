package com.fnb.oms_deliveryservice.service;

import com.fnb.oms_deliveryservice.dto.CreateDeliveryRequest;
import com.fnb.oms_deliveryservice.dto.DeliveryResponse;
import com.fnb.oms_deliveryservice.dto.UpdateDeliveryStatusRequest;
import com.fnb.oms_deliveryservice.entity.Delivery;
import com.fnb.oms_deliveryservice.entity.DeliveryStatus;
import com.fnb.oms_deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public DeliveryResponse createDelivery(CreateDeliveryRequest request) {
        if (deliveryRepository.findByOrderId(request.getOrderId()).isPresent()) {
            throw new RuntimeException("Delivery already exists for order " + request.getOrderId());
        }
        Delivery delivery = Delivery.builder()
                .orderId(request.getOrderId())
                .customerId(request.getCustomerId())
                .status(DeliveryStatus.PENDING)
                .deliveryAddress(request.getDeliveryAddress())
                .driverName(request.getDriverName())
                .build();
        return toResponse(deliveryRepository.save(delivery));
    }

    @Override
    public DeliveryResponse getDeliveryById(Long deliveryId) {
        return toResponse(findById(deliveryId));
    }

    @Override
    public DeliveryResponse getDeliveryByOrderId(Long orderId) {
        Delivery delivery = deliveryRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("No delivery found for order " + orderId));
        return toResponse(delivery);
    }

    @Override
    public List<DeliveryResponse> getDeliveriesByCustomer(Long customerId) {
        return deliveryRepository.findByCustomerId(customerId)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public DeliveryResponse updateStatus(Long deliveryId, UpdateDeliveryStatusRequest request) {
        Delivery delivery = findById(deliveryId);
        delivery.setStatus(request.getStatus());
        return toResponse(deliveryRepository.save(delivery));
    }

    private Delivery findById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found: " + deliveryId));
    }

    private DeliveryResponse toResponse(Delivery d) {
        return DeliveryResponse.builder()
                .deliveryId(d.getDeliveryId())
                .orderId(d.getOrderId())
                .customerId(d.getCustomerId())
                .status(d.getStatus())
                .deliveryAddress(d.getDeliveryAddress())
                .driverName(d.getDriverName())
                .createdAt(d.getCreatedAt())
                .updatedAt(d.getUpdatedAt())
                .build();
    }
}

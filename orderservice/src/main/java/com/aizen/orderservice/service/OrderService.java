package com.aizen.orderservice.service;

import com.aizen.bookingservice.event.BookingEvent;
import com.aizen.orderservice.client.InventoryServiceClient;
import com.aizen.orderservice.entity.Order;
import com.aizen.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    OrderRepository orderRepository;
    InventoryServiceClient inventoryServiceClient;
    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }
    @KafkaListener(topics = "bookingTopic", groupId = "order_service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Order Service received booking event: {}", bookingEvent);
        Order order = CreateOrder(bookingEvent);
        orderRepository.saveAndFlush(order);
        inventoryServiceClient.updateEventCapacity(bookingEvent.getEventId(), bookingEvent.getNumberOfTickets());
        log.info("Order saved: {}", order);

    }
    private Order CreateOrder(BookingEvent bookingEvent) {
        Order order = Order.builder()
                .customerId(bookingEvent.getCustomerId())
                .eventId(bookingEvent.getEventId())
                .quantity(bookingEvent.getNumberOfTickets())
                .total(bookingEvent.getTotalPrice())
                .build();
        log.info("Order created: {}", order);
        return order;
    }
}

package com.aizen.bookingservice.service;

import com.aizen.bookingservice.client.InventoryServiceClient;
import com.aizen.bookingservice.entity.Customer;
import com.aizen.bookingservice.event.BookingEvent;
import com.aizen.bookingservice.repository.CustomerRepository;
import com.aizen.bookingservice.request.BookingRequest;
import com.aizen.bookingservice.response.BookingResponse;
import com.aizen.bookingservice.response.InventoryResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class BookingService {
    CustomerRepository customerRepository;
    InventoryServiceClient inventoryServiceClient;
    KafkaTemplate<String, BookingEvent> kafkaTemplate;
    @Autowired
    public BookingService(CustomerRepository customerRepository, InventoryServiceClient inventoryServiceClient,
                          KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.kafkaTemplate = kafkaTemplate;
    }
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Customer customer = customerRepository.findById(bookingRequest.getCustomerId()).orElse(null);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        InventoryResponse inventoryResponse = inventoryServiceClient.getEventInventory(bookingRequest.getEventId());
       log.info("Inventory Response: {}", inventoryResponse);
        if(bookingRequest.getNumberOfTickets()>inventoryResponse.getTotalCapacity()){
            throw new RuntimeException("Total Capacity Exceeded");
        }
        BookingEvent bookingEvent = createBookingEvent(bookingRequest, customer, inventoryResponse);
        log.info("Booking Event: {}", bookingEvent);
        kafkaTemplate.send("bookingTopic", bookingEvent);
        return BookingResponse.builder()
                .eventId(inventoryResponse.getEventId())
                .totalPrice(bookingEvent.getTotalPrice())
                .numberOfTickets(bookingEvent.getNumberOfTickets())
                .customerId(bookingEvent.getCustomerId())
                .build();
    }
    private BookingEvent createBookingEvent(BookingRequest bookingRequest,
                                            Customer customer,
                                            InventoryResponse inventoryResponse) {
        return BookingEvent.builder()
                .customerId(customer.getId())
                .eventId(inventoryResponse.getEventId())
                .numberOfTickets(bookingRequest.getNumberOfTickets())
                .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getNumberOfTickets())))
                .build();
    }
}

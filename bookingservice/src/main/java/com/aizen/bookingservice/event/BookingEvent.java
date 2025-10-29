package com.aizen.bookingservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEvent {
    private Long customerId;
    private Long eventId;
    private Long numberOfTickets;
    private BigDecimal totalPrice;
}

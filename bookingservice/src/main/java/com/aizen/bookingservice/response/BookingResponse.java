package com.aizen.bookingservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Long customerId;
    private Long eventId;
    private Long numberOfTickets;
    private BigDecimal totalPrice;
}

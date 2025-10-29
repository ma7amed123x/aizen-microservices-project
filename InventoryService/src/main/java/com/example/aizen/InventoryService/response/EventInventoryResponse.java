package com.example.aizen.InventoryService.response;

import com.example.aizen.InventoryService.entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryResponse {
    private Long eventId;
    private String eventName;
    private Long totalCapacity;
    private Venue venue;
    private BigDecimal ticketPrice;
}

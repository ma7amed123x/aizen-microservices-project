package com.example.aizen.InventoryService.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueInventoryResponse {
    private String venueName;
    private String venueAddress;
    private Long totalCapacity;

}

package com.aizen.bookingservice.client;

import com.aizen.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {
    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;
    public InventoryResponse getEventInventory(Long eventId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl + "/event/" + eventId, InventoryResponse.class);
    }
}

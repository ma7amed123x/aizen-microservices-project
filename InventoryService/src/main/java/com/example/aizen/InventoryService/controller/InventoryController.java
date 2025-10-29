package com.example.aizen.InventoryService.controller;

import com.example.aizen.InventoryService.entity.Venue;
import com.example.aizen.InventoryService.response.EventInventoryResponse;
import com.example.aizen.InventoryService.response.VenueInventoryResponse;
import com.example.aizen.InventoryService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class InventoryController {
    private InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @GetMapping("/inventory/events")
    public @ResponseBody List<EventInventoryResponse> getInventoryEvents() {
        return inventoryService.getAllEvents();
    }
    @GetMapping("/inventory/venue/{venueId}")
    public @ResponseBody VenueInventoryResponse getVenue(@PathVariable("venueId") Long venueId) {
        return inventoryService.getVenueById(venueId);
    }
    @GetMapping("/inventory/event/{eventId}")
    public @ResponseBody EventInventoryResponse getEventById(@PathVariable("eventId") Long eventId) {
        return inventoryService.getEventInventory(eventId);
    }
    @PutMapping("/inventory/event/{eventId}/capacity/{ticketsBooked}")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable("eventId") Long eventId,
                                              @PathVariable("ticketsBooked") Long ticketsBooked) {
        inventoryService.updateEventCapacity(eventId, ticketsBooked);
        return ResponseEntity.ok().build();
    }

}

package com.example.aizen.InventoryService.service;

import com.example.aizen.InventoryService.Repository.EventRepository;
import com.example.aizen.InventoryService.Repository.VenueRepository;
import com.example.aizen.InventoryService.entity.Event;
import com.example.aizen.InventoryService.entity.Venue;
import com.example.aizen.InventoryService.response.EventInventoryResponse;
import com.example.aizen.InventoryService.response.VenueInventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventoryService {
    private EventRepository eventRepository;
    private VenueRepository venueRepository;
    @Autowired
    public InventoryService(EventRepository eventRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }
    public List<EventInventoryResponse> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> EventInventoryResponse.builder()
                .eventName(event.getName())
                .totalCapacity(event.getTotalCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .build()).toList();
    }
    public VenueInventoryResponse getVenueById(Long venueId){
        Venue venue = venueRepository.findById(venueId).orElse(null);
        return VenueInventoryResponse.builder()
                .totalCapacity(venue.getTotalCapacity())
                .venueName(venue.getName())
                .venueAddress(venue.getAddress())
                .build();
    }
    public EventInventoryResponse getEventInventory(Long eventId){
        Event event = eventRepository.findById(eventId).orElse(null);
        return EventInventoryResponse.builder()
                .eventId(event.getId())
                .eventName(event.getName())
                .totalCapacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .build();
    }
    public void updateEventCapacity(Long eventId, Long ticketsBooked) {
        Event event = eventRepository.findById(eventId).orElse(null);
        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepository.saveAndFlush(event);
        log.info("Updated event capacity for eventId {}: leftCapacity {}", eventId, event.getLeftCapacity());
    }
}

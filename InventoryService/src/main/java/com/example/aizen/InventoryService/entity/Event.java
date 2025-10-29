package com.example.aizen.InventoryService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "total_capacity")
    private Long totalCapacity;
    @Column(name = "left_capacity")
    private Long leftCapacity;
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;


}

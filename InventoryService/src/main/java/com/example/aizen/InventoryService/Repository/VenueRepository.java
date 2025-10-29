package com.example.aizen.InventoryService.Repository;

import com.example.aizen.InventoryService.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue,Long> {

}

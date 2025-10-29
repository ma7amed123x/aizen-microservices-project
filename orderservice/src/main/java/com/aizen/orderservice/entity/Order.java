package com.aizen.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
CREATE TABLE `order` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total DECIMAL(10,2) NOT NULL,
    quantity BIGINT NOT NULL,
    placed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    customer_id BIGINT ,
    event_id BIGINT ,
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
    ON DELETE SET NULL,
    CONSTRAINT fk_order_event FOREIGN KEY (event_id) REFERENCES event(id)
    ON DELETE SET NULL
);
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "quantity")
    private Long quantity;
    @CreationTimestamp
    @Column(name = "placed_at", updatable = false, nullable = false )
    private LocalDateTime placedAt;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "event_id")
    private Long eventId;
}

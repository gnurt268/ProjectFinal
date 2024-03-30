package org.trung.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class OrderTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    @ManyToOne
    private Order order;

    @ManyToOne
    private Event event;

    private String type;

    private int quantity;

    private Integer price;

    private Integer discountedPrice;
    private Long userId;

    private LocalDateTime orderDate;

    public OrderTicket() {

    }
}

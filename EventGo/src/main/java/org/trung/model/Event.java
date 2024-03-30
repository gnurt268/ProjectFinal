package org.trung.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private int price;
    @Column(name="discounted_price")
    private int discountedPrice;
    @Column(name="discount_persent")
    private int discountPersent;
    @Column(name="quantity")
    private int quantity;
    @Column(name="brand")
    private String brand;
    @Column(name="color")
    private String color;

    @Embedded
    @ElementCollection
    @Column(name="types")
    private Set<Type> types =new HashSet<>();
    @Column(name="image_url")
    private String imageUrl;
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating>ratings=new ArrayList<>();
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review>reviews=new ArrayList<>();
    @Column(name = "num_ratings")
    private int numRatings;
    @ManyToOne()
    @JoinColumn(name="category_id")
    private Category category;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event() {

    }
}

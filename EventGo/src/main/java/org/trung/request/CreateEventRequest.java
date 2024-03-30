package org.trung.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.trung.model.Type;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
public class CreateEventRequest {

    private String title;
    private String description;

    private int price;

    private int discountedPrice;
    private int discountPersent;

    private int quantity;

    private String brand;

    private String color;

    private Set<Type> type = new HashSet<>();

    private String imageUrl;

    private String topLavelCategory;

    private String secondCategory;

    private String thirdCategory;


}

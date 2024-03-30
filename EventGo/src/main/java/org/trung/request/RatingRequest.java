package org.trung.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingRequest {

    private Long eventId;
    private double rating;

}

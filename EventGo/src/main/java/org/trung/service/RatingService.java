package org.trung.service;


import org.trung.exception.EventException;
import org.trung.model.Rating;
import org.trung.model.User;
import org.trung.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest req, User user) throws EventException;

    public List<Rating> getEventsRating(Long eventId);
}

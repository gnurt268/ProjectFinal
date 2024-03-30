package org.trung.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.trung.exception.EventException;
import org.trung.model.Event;
import org.trung.model.Rating;
import org.trung.model.User;
import org.trung.repository.RatingRepository;
import org.trung.request.RatingRequest;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Data
@AllArgsConstructor
public class RatingServiceImplementation implements RatingService{
    private RatingRepository ratingRepository;
    private EventService eventService;
    @Override
    public Rating createRating(RatingRequest req, User user) throws EventException {
        Event event = eventService.findEventById(req.getEventId());
        Rating rating = new Rating();
        rating.setEvent(event);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getEventsRating(Long eventId) {
        return ratingRepository.getAllEventsRating(eventId);
    }
}

package org.trung.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.trung.exception.EventException;
import org.trung.model.Event;
import org.trung.model.Review;
import org.trung.model.User;
import org.trung.repository.EventRepository;
import org.trung.repository.ReviewRepository;
import org.trung.request.ReviewRequest;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Data
@AllArgsConstructor
public class ReviewServiceImplementation implements ReviewService{
    private ReviewRepository reviewRepository;
    private EventService eventService;
    private EventRepository eventRepository;
    @Override
    public Review createReview(ReviewRequest req, User user) throws EventException {
        Event event = eventService.findEventById(req.getEventId());
        Review review = new Review();
        review.setUser(user);
        review.setEvent(event);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long eventId) {
        return reviewRepository.getAllEventsReview(eventId);
    }
}

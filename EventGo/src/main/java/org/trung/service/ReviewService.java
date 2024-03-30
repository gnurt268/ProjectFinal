package org.trung.service;

import org.trung.exception.EventException;
import org.trung.model.Review;
import org.trung.model.User;
import org.trung.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest req, User user) throws EventException;
    public List<Review> getAllReview(Long eventId);
}

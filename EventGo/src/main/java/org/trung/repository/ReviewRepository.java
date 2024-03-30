package org.trung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trung.model.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("select r from Review r WHERE r.event.id=:eventId")
    public List<Review>getAllEventsReview(@Param("eventId")Long eventId);
}

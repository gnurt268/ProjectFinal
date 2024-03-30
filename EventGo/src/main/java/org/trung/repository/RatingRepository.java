package org.trung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trung.model.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query("SELECT r FROM Rating r WHERE r.event.id=:eventId")
    public List<Rating> getAllEventsRating(@Param("eventId")Long eventId);
}

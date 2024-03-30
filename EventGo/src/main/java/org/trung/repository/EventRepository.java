package org.trung.repository;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trung.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("SELECT e FROM Event e " +
            "WHERE (e.category.name = :category OR :category = '') " +
            "AND (:startDate IS NULL OR e.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR e.endDate <= :endDate)"
    )

    public List<Event> filterEvents(@Param("category") String category,@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate
                                      );
}

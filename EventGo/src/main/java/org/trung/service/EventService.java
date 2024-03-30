package org.trung.service;

import org.springframework.data.domain.Page;
import org.trung.exception.EventException;
import org.trung.model.Event;
import org.trung.request.CreateEventRequest;

import java.util.List;

public interface EventService {

    public Event createEvent(CreateEventRequest req);

    public String deleteEvent(Long eventId) throws EventException;

    public Event updateEvent(Long eventId, Event req) throws EventException;

    public Event findEventById(Long id) throws EventException;

    public List<Event> findEventByCategory(String category);

    public Page<Event> getAllEvent


            (String category, List<String>colors, List<String>sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);


}

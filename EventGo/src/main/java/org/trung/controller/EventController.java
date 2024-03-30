package org.trung.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trung.exception.EventException;
import org.trung.model.Event;
import org.trung.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {
    private EventService eventService;
    @GetMapping("/events")
    public ResponseEntity<Page<Event>>findEventByCategoryHandler(@RequestParam String category,@RequestParam String stock, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Page<Event> res = eventService.getAllEvent(category,stock, pageNumber, pageSize);
        System.out.println("Complete events");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
    @GetMapping("/events/id/{eventId}")
    public ResponseEntity<Event> findEventByIdHandler(@PathVariable Long eventId) throws EventException {
        Event event = eventService.findEventById(eventId);
        return new ResponseEntity<Event>(event,HttpStatus.ACCEPTED);
    }

}

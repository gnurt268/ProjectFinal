package org.trung.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.trung.exception.EventException;
import org.trung.model.Category;
import org.trung.model.Event;
import org.trung.repository.CategoryRepository;
import org.trung.repository.EventRepository;
import org.trung.request.CreateEventRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class EventServiceImplementation implements EventService {

    private EventRepository eventRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;

    @Override
    public Event createEvent(CreateEventRequest req) {

        Category topLevel = categoryRepository.findByName(req.getTopLavelCategory());

        if (topLevel==null) {
            Category topLavelCategory = new Category();
            topLavelCategory.setName(req.getTopLavelCategory());
            topLavelCategory.setLevel(1);
            topLevel = categoryRepository.save(topLavelCategory);
        }

        Category secondLevel = categoryRepository.
                findByNameAndParant(req.getSecondCategory(),topLevel.getName());

        if (secondLevel==null) {
            Category secondLavelCategory = new Category();
            secondLavelCategory.setName(req.getSecondCategory());
            secondLavelCategory.setParentCategory(topLevel);
            secondLavelCategory.setLevel(2);
            secondLevel = categoryRepository.save(secondLavelCategory);
        }

        Category thirdLevel = categoryRepository.
                findByNameAndParant(req.getThirdCategory(),secondLevel.getName());

        if (thirdLevel==null) {
            Category thirdLavelCategory = new Category();
            thirdLavelCategory.setName(req.getThirdCategory());
            thirdLavelCategory.setParentCategory(secondLevel);
            thirdLavelCategory.setLevel(3);
            secondLevel = categoryRepository.save(thirdLavelCategory);
        }

        Event event = new Event();
        event.setTitle(req.getTitle());
        event.setColor(req.getColor());
        event.setDescription(req.getDescription());
        event.setDiscountedPrice(req.getDiscountedPrice());
        event.setDiscountPersent(req.getDiscountPersent());
        event.setImageUrl(req.getImageUrl());
        event.setBrand(req.getBrand());
        event.setPrice(req.getPrice());
        event.setTypes(req.getType()) ;
        event.setQuantity(req.getQuantity());
        event.setCategory(thirdLevel);
        event.setCreatedAt(LocalDateTime.now());

        Event savedEvent = eventRepository.save(event);



        return savedEvent;
    }

    @Override
    public String deleteEvent(Long eventId) throws EventException {
        Event event = findEventById(eventId);
        event.getTypes().clear();
        eventRepository.delete(event);
        return "Event deleted Success";
    }

    @Override
    public Event updateEvent(Long eventId, Event req) throws EventException {
        Event event = findEventById(eventId);

        if (req.getQuantity()!=0) {
            event.setQuantity(req.getQuantity());
        }

        return eventRepository.save(event);
    }

    @Override
    public Event findEventById(Long id) throws EventException {
        Optional<Event>opt= eventRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new EventException("Event not found with id - " + id);
    }

    @Override
    public List<Event> findEventByCategory(String category) {
        return null;
    }

    @Override
    public Page<Event> getAllEvent(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        List<Event> events = eventRepository.filterEvents(category,minPrice,maxPrice,minDiscount,sort);

        if(!colors.isEmpty()) {
            events = events.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }
        if (stock!=null) {
            if (stock.equals("in_stock")) {
                events = events.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }
            else if (stock.equals("out_of_stock")) {
                events = events.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex+pageable.getPageSize(), events.size());
        List<Event> pageContent = events.subList(startIndex,endIndex);
        Page<Event>filteredEvents=new PageImpl<>(pageContent,pageable, events.size());
        return filteredEvents;
    }
}

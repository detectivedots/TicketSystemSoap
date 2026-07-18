package org.ticket.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.ticket.domain.entity.Event;
import org.ticket.repository.EventRepository;
import org.ticket.service.EventService;
import org.ticket.soap.generated.CreateEventRequest;
import org.ticket.soap.generated.EventResponse;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public EventResponse createEvent(CreateEventRequest createEventRequest) {
        String name = createEventRequest.getName();
        int seats = createEventRequest.getAvailableSeats();
        Event event = new Event(name, seats);
        event = eventRepository.create(event);

        EventResponse response = new EventResponse();
        response.setId(event.getId());
        response.setName(event.getName());
        response.setAvailableSeats(event.getSeatsLeft());

        return response;
    }
}

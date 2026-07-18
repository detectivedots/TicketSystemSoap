package org.ticket.service;

import org.ticket.soap.generated.CreateEventRequest;
import org.ticket.soap.generated.EventResponse;

public interface EventService {
    public EventResponse createEvent(CreateEventRequest createEventRequest);
}

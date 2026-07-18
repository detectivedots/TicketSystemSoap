package org.ticket.endpoint;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.ticket.service.EventService;
import org.ticket.soap.generated.CreateEventRequest;
import org.ticket.soap.generated.EventResponse;

@Endpoint
public class EventEndpoint {
    private static final String NAMESPACE_URI = "http://ticket.org/ws";
    private final EventService eventService;

    public EventEndpoint(EventService eventService) {
        this.eventService = eventService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateEventRequest")
    @ResponsePayload
    public EventResponse createEvent(@RequestPayload CreateEventRequest request){
        return eventService.createEvent(request);
    }
}

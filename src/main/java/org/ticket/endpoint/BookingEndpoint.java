package org.ticket.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.ticket.service.BookingService;
import org.ticket.soap.generated.BookingRequest;
import org.ticket.soap.generated.BookingResponse;

@Endpoint
public class BookingEndpoint {
    private static final String NAMESPACE_URI = "http://ticket.org/ws";
    private final BookingService bookingService;

    public BookingEndpoint(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookingRequest")
    @ResponsePayload
    public BookingResponse bookTicket(@RequestPayload BookingRequest request) {
        return bookingService.bookTicket(request);
    }
}

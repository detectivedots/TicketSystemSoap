package org.ticket.service;

import org.ticket.soap.generated.BookingRequest;
import org.ticket.soap.generated.BookingResponse;

public interface BookingService {
    public BookingResponse bookTicket(BookingRequest bookingRequestDto);
}

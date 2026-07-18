package org.ticket.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticket.domain.entity.Event;
import org.ticket.domain.entity.Ticket;
import org.ticket.exception.EventFullException;
import org.ticket.repository.EventRepository;
import org.ticket.repository.TicketRepository;
import org.ticket.service.BookingService;
import org.ticket.soap.generated.BookingRequest;
import org.ticket.soap.generated.BookingResponse;

@Service
public class BookingServiceImpl implements BookingService {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public BookingServiceImpl(EventRepository eventRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public BookingResponse bookTicket(BookingRequest bookingRequestDto) {
        Long eventId = bookingRequestDto.getEventId();
        String ownerName = bookingRequestDto.getOwnerName();
        Event event = eventRepository.findById(eventId).orElseThrow();
        eventRepository.deductSeat(event);

        Ticket ticket = new Ticket(event, ownerName);
        ticket = ticketRepository.create(ticket);

        BookingResponse response = new BookingResponse();
        response.setEventName(event.getName());
        response.setOwnerName(ticket.getOwnerName());
        response.setSerialNum(ticket.generateSerial());

        return response;
    }
}

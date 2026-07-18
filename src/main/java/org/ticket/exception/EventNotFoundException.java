package org.ticket.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(Integer eventId) {
        super("No event with id " + eventId);
    }
}
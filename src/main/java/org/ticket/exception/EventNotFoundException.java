package org.ticket.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(Long eventId) {
        super("No event with id " + eventId);
    }
}
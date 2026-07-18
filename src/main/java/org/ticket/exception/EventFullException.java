package org.ticket.exception;

public class EventFullException extends RuntimeException {
    public EventFullException(Long eventId) {
        super("Event " + eventId + " is full.");
    }
}

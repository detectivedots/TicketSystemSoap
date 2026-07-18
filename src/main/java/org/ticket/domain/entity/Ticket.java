package org.ticket.domain.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private Event event;
    private String ownerName;
    private LocalDateTime bookingTime;

    public Ticket(){
        this.bookingTime = LocalDateTime.now();
    }

    public Ticket(int id, Event event, String ownerName, LocalDateTime bookingTime){
        this.id = id;
        this.event = event;
        this.ownerName = ownerName;
        this.bookingTime = bookingTime;
    }

    public Ticket(Event event, String ownerName){
        this.event = event;
        this.ownerName = ownerName;
        this.bookingTime = LocalDateTime.now();
    }

    public Ticket(int id, Event event, String ownerName){
        this.id = id;
        this.event = event;
        this.ownerName = ownerName;
        this.bookingTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String generateSerial(){
        return "" + id + event.getId() + Timestamp.valueOf(bookingTime);
    }
}

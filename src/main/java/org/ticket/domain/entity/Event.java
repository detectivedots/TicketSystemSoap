package org.ticket.domain.entity;

public class Event {
    private int id;
    private String name;
    private int seatsLeft;

    public Event(){

    }

    public Event(int id, String name, int seatsLeft){
        this.id = id;
        this.name = name;
        this.seatsLeft = seatsLeft;
    }

    public Event(String name, int seatsLeft){
        this.name = name;
        this.seatsLeft = seatsLeft;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }

    public void setSeatsLeft(int seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    public boolean isEventFull(){
        return seatsLeft <= 0;
    }
}

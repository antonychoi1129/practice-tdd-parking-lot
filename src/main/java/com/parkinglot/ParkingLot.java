package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;
    private Map<Ticket, Car> carTicketMap = new HashMap<>();
    private final int capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }
    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) throws NoAvailablePositionException{
        if(isFull()) throw new NoAvailablePositionException();
        Ticket ticket = new Ticket();
        carTicketMap.put(ticket, car);
        return ticket;
    }
    private boolean isFull() {
        return carTicketMap.size() == this.capacity;
    }
    public Car fetch(Ticket ticket) throws UnrecognizedMessageException{
        Car parkedCar = carTicketMap.get(ticket);
        carTicketMap.remove(ticket);
        if(parkedCar == null) throw new UnrecognizedMessageException();
        return parkedCar;
    }
}

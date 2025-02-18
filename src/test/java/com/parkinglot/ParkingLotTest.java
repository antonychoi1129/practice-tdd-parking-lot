package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_a_parkingLot() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        Ticket actual = parkingLot.park(car);
        //then
        assertNotNull(actual);
    }
    @Test
    void should_return_parked_car_when_fetch_given_a_Ticket_and_a_Car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car parkedCar = new Car();
        Ticket ticket = parkingLot.park(parkedCar);
        //when
        Car actual = parkingLot.fetch(ticket);
        //then
        assertEquals(parkedCar, actual);
    }
    @Test
    void should_return_right_car_with_each_ticket_when_fetch_twice_given_two_tickets_and_two_parked_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car parkedCar1 = new Car();
        Car parkedCar2 = new Car();
        Ticket ticket1 = parkingLot.park(parkedCar1);
        Ticket ticket2 = parkingLot.park(parkedCar2);
        //when
        Car actualCar1 = parkingLot.fetch(ticket1);
        Car actualCar2 = parkingLot.fetch(ticket2);
        //then
        assertEquals(parkedCar1, actualCar1);
        assertEquals(parkedCar2, actualCar2);
    }

    @Test
    void should_return_UnrecognizedMessageException_when_fetch_given_unrecognized_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = new Ticket();
        //when
        //then
        Exception exception = assertThrows(UnrecognizedMessageException.class, () ->{
            parkingLot.fetch(ticket);
        });
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    void should_return_UnrecognizedMessageException_when_fetch_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        //then
        Exception exception = assertThrows(UnrecognizedMessageException.class, () ->{
            parkingLot.fetch(ticket);
        });
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
    @Test
    void should_return_NoAvailablePositionException_when_park_given_parkingLot_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        Car car1 = new Car();
        //then
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> {
            parkingLot.park(car1);
        });
        assertEquals("No available position.", exception.getMessage());
    }
}

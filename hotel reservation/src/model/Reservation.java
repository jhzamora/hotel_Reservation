package model;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class Reservation  {
    private Customer customer;
    private IRoom room;
    private Date checkInDate,checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public final void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public final IRoom getRoom() {
        return room;
    }

    public final void setRoom(IRoom room) {
        this.room = room;
    }

    public final Date getCheckInDate() {
        return checkInDate;
    }

    public final void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public final Date getCheckOutDate() {
        return checkOutDate;
    }

    public final void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }

 
}

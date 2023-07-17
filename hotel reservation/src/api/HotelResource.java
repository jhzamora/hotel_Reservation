package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private HotelResource() {
    }

    private static HotelResource singleInstance = new HotelResource();
    public static HotelResource getInstance(){
        return singleInstance;
    }

    public Customer getCustomer(String email) throws Exception {
        return CustomerService.getInstance().getCustomer(email);
    }

    public void createCustomer(String email, String firstName, String lastName) throws Exception {
        CustomerService.getInstance().addCustomer(email,firstName,lastName);
    }

    public IRoom getRoom(String roomNumber){
       return ReservationService.getInstance().getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) throws Exception {
        return ReservationService.getInstance().reserveARoom(CustomerService.getInstance().getCustomer(customerEmail), room,checkInDate,checkOutDate );
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) throws Exception {
        return ReservationService.getInstance().getCustomersReservation(CustomerService.getInstance().getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) throws Exception {
        Collection<IRoom> rooms;
        rooms = ReservationService.getInstance().findRooms(checkIn,checkOut);

        return rooms;


    }

}

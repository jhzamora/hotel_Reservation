package api;

import model.Customer;
import model.IRoom;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private AdminResource() {
    }
    private static AdminResource singleInstance = new AdminResource();
    public static AdminResource getInstance(){
        return singleInstance;
    }
    public Customer getCustomer(String email) throws Exception {
        return CustomerService.getInstance().getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms) throws Exception {
        for(IRoom room:rooms){
            ReservationService.getInstance().addRoom(room);
        }
    }
    public Collection<IRoom> getAllRooms(){
        return ReservationService.getInstance().getAllRooms();
    }
    public Collection<Customer> getAllcustomers(){
        return CustomerService.getInstance().getAllCustomers();
    }
    public void displayAllReservations(){
        ReservationService.getInstance().printAllReservations();
    }

    public void createRoom(String roomNumber,double price, RoomType roomType) throws Exception {
        ReservationService.getInstance().createRoom(roomNumber,price,roomType);
    }

}

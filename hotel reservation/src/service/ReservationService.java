package service;

import model.*;

import java.util.*;

public class ReservationService {
    private static Set<IRoom> rooms = new HashSet<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static ReservationService singleInstance = new ReservationService();

    private ReservationService() {
    }


    public static void createRoom(String roomNumber,double price, RoomType roomType) throws Exception {
        if (price < 0) {
            throw new IllegalArgumentException("price can't be negative");
        }
        IRoom room;

        if(price==0){
            room = new FreeRoom(roomNumber,roomType);
        }
        else {
            room = new Room(roomNumber,price,roomType);
        }
        addRoom(room);
    }

    public static void addRoom(IRoom room) throws Exception {

        if (!rooms.add(room)){
            throw new Exception("Room with number: " + room.getRoomNumber() + " already present");

        }
        else {
            rooms.add(room);
        }

    }


    public static IRoom getARoom(String roomId) {
        Map<String, IRoom> roomsMap = new HashMap<>();
        for (IRoom room : rooms) {
            roomsMap.put(room.getRoomNumber(), room);
        }
        return roomsMap.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(newReservation);
        return newReservation;
    }

    public Collection<IRoom> findRooms(Date checkin, Date checkout) {
        Collection<IRoom> possibleRooms = new ArrayList<>();
        //Make a map of all the rooms as keys and its corresponding reservations as values
        Map<IRoom, Collection<Reservation>> roomMap = new HashMap<>();

        //Add all rooms as keys, initialize an empty reservation list
        for(IRoom room: this.rooms){
            roomMap.put(room, new ArrayList<Reservation>());
        }

        //Add all the reservations to their corresponding rooms
        for (Reservation reservation : this.reservations) {
            IRoom thisRoom = reservation.getRoom();
            Collection<Reservation> list = roomMap.get(thisRoom);
            list.add(reservation);

        }

        //Loop al rooms, see if we can reserve
        for (Map.Entry<IRoom, Collection<Reservation>> pair : roomMap.entrySet()) {
            //See if we can reserve this specific room, if there are no conflicting dates
            boolean canReserve = true;
            //Loop all the reservations for the room
            for (Reservation reservation : pair.getValue()) {
                //See if the target reservation date range is already occupied
                Date thisCheckIn = reservation.getCheckInDate();
                Date thisCheckOut = reservation.getCheckOutDate();

                //If the check in or check out is between this reservation's date, we can't reserve (inclusive)
                //check if our target dates are in between
                if ((checkin.compareTo(thisCheckIn)>=0 && checkin.compareTo(thisCheckOut)<=0) || (checkout.compareTo(thisCheckIn) >= 0 && checkout.compareTo(thisCheckOut) <= 0)) {
                    canReserve = false;
                    break;
                }
                //check if the target dates include this reservation
                else if ((checkin.compareTo(thisCheckIn)<=0 && checkin.compareTo(thisCheckOut)<=0) && (checkout.compareTo(thisCheckIn) >= 0 && checkout.compareTo(thisCheckOut) >= 0)) {
                    canReserve = false;
                    break;
                }
            }

            //Add to possible rooms if we can reserve
            if (canReserve) {
                possibleRooms.add(pair.getKey());
            }


        }

        return possibleRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();

        for (Reservation reservation : this.reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static synchronized ReservationService getInstance() {
        return singleInstance;
    }

}

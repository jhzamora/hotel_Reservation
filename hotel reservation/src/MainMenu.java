import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.RoomType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    static AdminResource adminResourceApi = AdminResource.getInstance();
    ;
    static HotelResource hotelResourceApi = HotelResource.getInstance();

    public static void findAndReserve(Scanner scanner) throws Exception {
        IRoom selectedRoom;
        Date checkin;
        Date checkout;
        System.out.println("Please input dates in dd/MM/yy format");
        System.out.println("What is your check in date?");
        String checkinstring = scanner.nextLine();
        try {
            checkin = new SimpleDateFormat("dd/MM/yy").parse(checkinstring);
        } catch (ParseException e) {
            throw new ParseException("Incorrect date format in position: ", e.getErrorOffset());
        }
        System.out.println("What is your check out date?");
        String checkoutstring = scanner.nextLine();
        try {
            checkout = new SimpleDateFormat("dd/MM/yy").parse(checkoutstring);
        } catch (ParseException e) {
            throw new ParseException("Incorrect date format", e.getErrorOffset());
        }

        Collection<IRoom> roomOptions = hotelResourceApi.findARoom(checkin, checkout);
        //If we have options
        if (!roomOptions.isEmpty()) {
            int i = 1;
            System.out.println("Available rooms, press its corresponding number to reserve");
            for (IRoom room : roomOptions) {
                System.out.println(i + ": " + room.toString());
                i++;
            }
        } else {
            //If there are no options, do new search with new dates
            //Add 7 days to check in
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkin);
            calendar.add(Calendar.DAY_OF_MONTH,7);
            checkin = calendar.getTime();
            //Add 7 days to check out
            calendar.setTime(checkout);
            calendar.add(Calendar.DAY_OF_MONTH,7);
            checkout = calendar.getTime();

            System.out.println("No rooms available\nDoing a new search with dates:\n check in: " + checkin + "\n check out: " + checkout);

            roomOptions = hotelResourceApi.findARoom(checkin, checkout);

            if (!roomOptions.isEmpty()){
                int i = 1;
                System.out.println("Available rooms, press its corresponding number to reserve");
                for (IRoom room : roomOptions) {
                    System.out.println(i + ": " + room.toString());
                    i++;
                }
            }
            else {
                throw new RuntimeException("No available rooms for your search");

            }

        }

        int selected = Integer.parseInt(scanner.nextLine());

        try {
            selectedRoom = (IRoom) roomOptions.toArray()[selected - 1];
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid option selected");
        }

        //Ask for user email
        System.out.println("Enter your user email");
        String userEmail = scanner.nextLine();

        //Get the customer
        Customer customer;
        try {
            customer = hotelResourceApi.getCustomer(userEmail);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No user found");
        }

        //Finally reserve
        try {
            hotelResourceApi.bookARoom(userEmail, selectedRoom, checkin, checkout);
            System.out.println("Your reservation is complete\nRoom:" + selectedRoom.toString() +
                    "Check in: " + checkin +
                    "\nCheck out: " + checkout +
                    "For Customer: " + adminResourceApi.getCustomer(userEmail).toString()
            );

        } catch (Exception e) {
            throw new Exception("Unexpected error, please try again" + e.getCause());
        }


    }

    public static void seeCustomerReservations(Scanner scanner) throws Exception {
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        Collection<Reservation> reservationList = hotelResourceApi.getCustomerReservations(email);

        if (!reservationList.isEmpty()) {
            System.out.println("Your upcoming reservations:\n");
            for (Reservation reservation : reservationList) {
                System.out.println(reservation.toString());
            }
        } else {
            System.out.println("No reservations found for this customer");
        }

    }

    public static void createAccount(Scanner scanner) throws Exception {
        System.out.println("Please enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Please enter your email");
        String email = scanner.nextLine();

        try {
            hotelResourceApi.createCustomer(email, firstName, lastName);
            System.out.println("Account successfully created");
        } catch (Exception e) {
            throw new Exception("Error ocurred: " + e.getLocalizedMessage());
        }

    }


}

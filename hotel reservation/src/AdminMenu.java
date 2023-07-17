import api.AdminResource;
import api.HotelResource;
import model.*;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminMenu {
    static AdminResource adminResourceApi = AdminResource.getInstance();
    public static void seeAllCustomers(){
        Collection<Customer> customers = adminResourceApi.getAllcustomers();
        for (Customer customer:customers){
            System.out.println(customer.toString());
        }
    }

    public static void seeAllRooms(){
        Collection<IRoom> rooms = adminResourceApi.getAllRooms();
        for (IRoom room:rooms){
            System.out.println(room.toString());
        }
    }

    public static void seeAllReservations(){
        adminResourceApi.displayAllReservations();
    }

    public static void addRoom(Scanner scanner) throws Exception {
        IRoom room;
        System.out.println("Press 1 for a single room or 2 for a double room");
        String option = scanner.nextLine();
        RoomType roomType;
        if (option.equals("1")){
            roomType=RoomType.SINGLE;
        }
        else if (option.equals("2")){
            roomType=RoomType.DOUBLE;
        }
        else {
            throw new Exception("Invalid option selected");
        }
        System.out.println("What's the room price?");
        String priceString = scanner.nextLine();
        //Check if priceString is a valid double
        String doubleRegex = "[0-9]+[.[0-9]+]?";
        Pattern doublePattern = Pattern.compile(doubleRegex);
        Matcher matcher = doublePattern.matcher(priceString);
        if (!matcher.find()){
            throw new Exception("Invalid price argument");
        }

        Double price = Double.parseDouble(priceString);

        //Get the room number
        System.out.println("What is the room number?");
        String roomNumber = scanner.nextLine();
        //Validate is an int
        String intRegex = "^[0-9]+$";
        Pattern intPattern = Pattern.compile(intRegex);
        Matcher matcher2 = intPattern.matcher(roomNumber);
        if (!matcher2.find()){
            throw new Exception("Invalid roon number selected");
        }

        adminResourceApi.createRoom(roomNumber,price,roomType);





    }
}

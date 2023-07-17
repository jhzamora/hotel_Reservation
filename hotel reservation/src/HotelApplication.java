import java.util.Scanner;

public class HotelApplication {

    public static String adminMenuController(Scanner scanner) throws Exception {
        String option = "1";
        while (!option.equals("main")) {
            //Display option menu
            System.out.println("Select: \n");
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            //Get user option
            option = scanner.nextLine();
            //Validate user option
            //Validate user option
            if (!option.matches("\\d+")){
                System.out.println("Invalid option selected");
                continue;
            }
            //Switch to user option
            switch (option){
                case "1":
                    try {
                        AdminMenu.seeAllCustomers();
                    }
                    catch (Exception e){
                        System.out.println("Error: " + e.getLocalizedMessage());
                    }
                    break;
                case "2":
                    try {
                        AdminMenu.seeAllRooms();
                    }
                    catch (Exception e){
                        System.out.println("Error: " + e.getLocalizedMessage());
                    }
                    break;
                case "3":
                    try {
                        AdminMenu.seeAllReservations();
                    }
                    catch (Exception e){
                        System.out.println("Error: " + e.getLocalizedMessage());
                    }
                    break;
                case "4":
                    try {
                        AdminMenu.addRoom(scanner);
                    }
                    catch (Exception e){
                        System.out.println("Error: " + e.getLocalizedMessage());

                    }
                    break;
                case "5":
                    option = "main";
                    break;

            }

        }
        return option;
    }

    public static String mainMenuController(Scanner scanner) throws Exception {
        String option = "1";
        while (!option.equals("exit") && !option.equals("admin")){
            //Display option menu
            System.out.println("Select: \n");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin menu");
            System.out.println("5. Exit");

            //Get user option
             option = scanner.nextLine();

             //Validate user option
            if (!option.matches("\\d+")){
                System.out.println("Invalid option selected");
                continue;
            }

            //Switch to handle user input
            switch (option){
                case "1":
                    try{
                        MainMenu.findAndReserve(scanner);
                    }
                    catch (Exception e){
                        System.out.println("Error: " + e.getLocalizedMessage());
                    }
                    break;

                case "2":
                    try {
                        MainMenu.seeCustomerReservations(scanner);
                    }
                    catch (Exception e){
                        System.out.println("Error: " + e.getLocalizedMessage());
                    }
                    break;
                case "3":
                    try {
                        MainMenu.createAccount(scanner);
                    }
                    catch (Exception e){
                        System.out.println("Error: " + e.getLocalizedMessage());
                    }
                    break;
                case "4":
                    option = "admin";
                    break;
                case "5":
                    option = "exit";
                    break;


            }

        }
        return option;
    }

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        //Start the program and keep it going until user selects exit
        String option = "main";
        while (!option.equals("exit")){
            if (option.equals("main") ){
                option = mainMenuController(scanner);
            } else if (option.equals("admin")) {
                option = adminMenuController(scanner);
            }
        }


    }
    }


package carShop;

import carShop.entity.User;
import carShop.entity.UserType;
import carShop.controller.Shop;

import java.util.Scanner;

public class Menu {
    Shop shop = new Shop();
    Scanner scanner = new Scanner(System.in);

    public void start(){
        System.out.println("Welcome to Car Shop, enter your user type:"
                + " \n 1. for Buyer" + "\n 2. for Seller" + "\n 3. Exit");
        String userChoice = scanner.nextLine();

        User activeUser = createUser(userChoice);

        if (activeUser == null){
            handleExit();
        }
        shop.setActiveUser(activeUser);

        showUserMenu(activeUser.getUserType()); //helper method, one way to show Menu, more flexibility
    }

    private void handleExit() {
        System.out.println("Enter 1 to exit or 2 to show main menu");
        if(scanner.nextLine().equals("1")){
            System.exit(0);
        }
        start();
    }

    private User createUser(String userChoice) {
        switch (Integer.parseInt(userChoice)) {
            case 1:
                System.out.println(" Enter your budget: ");
                double budget = Double.parseDouble(scanner.nextLine());
                return new User(UserType.BUYER, budget);
            case 2:
                return new User(UserType.OWNER);
            default:
                break;
        }
        return null;
    }

    private void handleMenuChoice(UserType userType, String userChoice) {
        switch (userType) {
            case OWNER:
                handleOwnerChoice(userChoice);
                break;
            case BUYER:
                handleBuyerChoice(userChoice);
                break;
            default:
                start();
        }
    }
    private void handleBuyerChoice(String userChoice) {
        switch (userChoice){
            case "1":
                shop.sellCar(); //from shop perspective
                break;
            case "2":
                shop.viewCars();
                break;
            case "3":
                handleExit();
                break;
            default:
                break;

        }
        showUserMenu(shop.getActiveUser().getUserType());
    }

    private void handleOwnerChoice(String userChoice) {
        switch (userChoice){
            case "1":
                shop.addCAr();
                break;
            case "2":
                shop.removeCar();
                break;
            case "3":
                shop.viewCars();
                break;
            case "4":
                shop.viewSalesHistory();
                break;
            case "5":
                handleExit();
                break;
            default:
                break;

        }
        showUserMenu(shop.getActiveUser().getUserType());
    }

    private void showUserMenu(UserType userType) {
        switch (userType){
            case OWNER:
                System.out.println(getOwnerMenu());
                break;
            case BUYER:
                System.out.println(getBuyerMenu());
                break;
            default:
                start(); //if nothing works, send to start
        }
        String userChoice = scanner.nextLine();
        handleMenuChoice(userType, userChoice);
    }

    private String getBuyerMenu() { //make print inside
        return "\n Choose one option below:"
                + "\n 1.Buy car"
                + "\n 2. View all cars"
                + "\n 3. Exit";
    }

    private String getOwnerMenu() {
        return "\n Choose one option below:"
                + "\n 1. Add car"
                + "\n 2. Remove car"
                + "\n 3. View cars"
                + "\n 4. Sales history"
                + "\n 5. Exit";
    }
}



//            if (userChoice.equalsIgnoreCase("1")) {
//                System.out.println(" Enter your budget: ");
//                int budget = Integer.parseInt(scanner.nextLine());
//                activeUser = new User(UserType.BUYER, budget);
//                //      showBuyerMenu() - user will try to do something, but show will not be ready as setActiveUser is later
//            }else{
//                activeUser = new User(UserType.OWNER);
//                //     showOwnerMenu()

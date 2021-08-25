package carShop.controller;

import carShop.entity.Car;
import carShop.entity.CarType;
import carShop.entity.User;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Shop {
    private User user;
    private ArrayList<Car> cars = new ArrayList<Car>();
    private ArrayList<Sale> salesHistory = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void setActiveUser(User activeUser) {
        this.user = activeUser;
    }

    public void addCAr() {
        Car car = collectCarInfo();
        cars.add(car);
        showSuccessMessage("Car Added");
    }

    private void showSuccessMessage(String message) {
        System.out.println(message + " successfully!");
    }

    private void showFailedMessage(String message) {
        System.out.println(" Failed!" + message);
    }

    private Car collectCarInfo() {
        Car newCar = new Car();
        try {
            System.out.println("===ADD CAR===");
            System.out.println("Enter Car Name:");
            newCar.setName(scanner.nextLine());
            System.out.println("Enter Car Price:");
            newCar.setPrice(Double.parseDouble(scanner.nextLine()));
            System.out.println("Enter Car Quantity:");
            newCar.setQuantity(Integer.parseInt(scanner.nextLine()));
            System.out.println("Enter Car Type (SEDAN, TRUCK, COUPE)");
            newCar.setCarType(CarType.valueOf(scanner.nextLine().toUpperCase()));
            newCar.setId(generateCarId());
        } catch (Exception exception) {
            exception.printStackTrace();
            //System.out.println(exception.getMessage());//in real live better throw message with better explanation for customer
            collectCarInfo();
        }
        return newCar;
    }

    private int generateCarId() {
        /* First Way
        if(cars.size() >= 1 ){
            int lastCarPosition = cars.size() - 1;
            Car lastCar = cars.get(lastCarPosition);
            return lastCar.getId() + 1;
        }
        return 0;*/
        /* Second Way
        int nextId = 0;
        try {
            Car lastCar = cars.get(cars.size() - 1);
            return lastCar.getId() + 1;
        } catch (Exception ex) {

        }
        return nextId;
    }*/
        //Third Way
        return (cars.size() < 1) ? 0 : cars.get(cars.size() - 1).getId() + 1;
    }

    public void removeCar() {

        System.out.println("Enter car id to remove");
        int carId = Integer.parseInt(scanner.nextLine());
        try {
            this.cars.remove(carId);
            showSuccessMessage("Car Removed");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Sorry, car not found");
        }
    }

    public void viewCars() {
//       for(Car car: this.cars){
//            System.out.println(car.getName());
//        }
        System.out.println("Name | \tPrice | \tQuantity | \tType");
        cars.forEach(System.out::println);
    }

    public void viewSalesHistory() {
        System.out.println("Name | \tQuantity | \tAmount");
        for (Sale sale: salesHistory){
            Car car = cars.get(sale.getCarId());
            System.out.println(car.getName() + " \t| " + sale.getQuantity()
            + "\t| " + sale.getTotal());
        }
        //System.out.println(sale.); MAKE TOTAL SALE GITHUB no 1:27
    }

    public User getActiveUser() {
        return this.user;
    }

    public void sellCar() {
        System.out.println("Enter Car ID You want to buy");
        int carId = Integer.parseInt(scanner.nextLine());

        Car car = findCarById(carId);
        boolean userCanBuyCar = false;

        try {
            userCanBuyCar = userCanBuyCar(car, user.getBudget());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (userCanBuyCar) {
            car.setQuantity(car.getQuantity() - 1);
            addSalesHistory(car.getId(), 1, car.getPrice());
            updateUserBalance(user.getBudget() - car.getPrice());
            updateCar(car);
            showSuccessMessage("Car has been bought ");
            return;
        }

    showFailedMessage(" Unable to complete car purchase!");

}

    private void updateUserBalance(double newBalance) {
        this.user.setBudget(newBalance);
    }

    private boolean userCanBuyCar(Car car, double budget) throws Exception{
        if(car == null) throw new Exception("Invalid car selection");
        if (budget < car.getPrice()) throw new Exception("You dot have enough money - " + budget);
        if(car.getQuantity() < 1) throw new Exception("Not enough cars available");
        return true;
    }

    private void updateCar(Car car) {
        cars.set(car.getId(), car);
        /* Another way to update array object if for example id is not known
        for(Car car1: this.cars){
            if(car1.getId() == car.getId()){
                car1.setQuantity(car.getQuantity());
                car1.setCarType(car.getCarType());
                car1.setPrice(car.getPrice());
            }
        }*/

    }

    private void addSalesHistory(int carId, int quantity, double total) {
        this.salesHistory.add(new Sale(carId, total, quantity));
    }

    private Car findCarById(int carId) {
        for(Car car: this.cars){
           if (car.getId() == carId) {
               return car;
           }
        }
        return null;//looping through, must be implemented
    }
}

import java.util.ArrayList;
import java.util.List;

// Class representing a Car
class Car {
    private String licensePlate;
    private String model;
    private String brand;
    private boolean isAvailable;

    public Car(String licensePlate, String model, String brand) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.isAvailable = true;  // Car is available initially
    }

    // Getters and setters
    public String getLicensePlate() { return licensePlate; }
    public String getModel() { return model; }
    public String getBrand() { return brand; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return brand + " " + model + " (" + licensePlate + ") - " + (isAvailable ? "Available" : "Rented");
    }
}

// Class representing a Customer
class Customer {
    private String name;
    private String driverLicenseNumber;

    public Customer(String name, String driverLicenseNumber) {
        this.name = name;
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getName() { return name; }
    public String getDriverLicenseNumber() { return driverLicenseNumber; }

    @Override
    public String toString() {
        return name + " (License: " + driverLicenseNumber + ")";
    }
}

// Class representing a Rental Transaction
class RentalTransaction {
    private Car car;
    private Customer customer;
    private String rentalDate;
    private String returnDate;

    public RentalTransaction(Car car, Customer customer, String rentalDate) {
        this.car = car;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.returnDate = null;  // Not returned yet
    }

    public Car getCar() { return car; }
    public Customer getCustomer() { return customer; }
    public String getRentalDate() { return rentalDate; }
    public String getReturnDate() { return returnDate; }

    public void returnCar(String returnDate) {
        this.returnDate = returnDate;
        car.setAvailable(true);
    }

    @Override
    public String toString() {
        return "Rental: " + car + " rented by " + customer + " on " + rentalDate + 
               (returnDate == null ? " (Not returned yet)" : ", returned on " + returnDate);
    }
}

// Rental Agency class managing cars, customers, and transactions
class RentalAgency {
    private List<Car> cars;
    private List<Customer> customers;
    private List<RentalTransaction> transactions;

    public RentalAgency() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    // Add car to agency
    public void addCar(Car car) {
        cars.add(car);
    }

    // Add customer to agency
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Rent a car to a customer
    public boolean rentCar(String licensePlate, String driverLicenseNumber, String rentalDate) {
        Car carToRent = findCarByLicense(licensePlate);
        Customer customer = findCustomerByLicense(driverLicenseNumber);

        if (carToRent != null && carToRent.isAvailable() && customer != null) {
            carToRent.setAvailable(false);
            RentalTransaction transaction = new RentalTransaction(carToRent, customer, rentalDate);
            transactions.add(transaction);
            System.out.println("Car rented successfully.");
            return true;
        } else {
            System.out.println("Car not available or customer not found.");
            return false;
        }
    }

    //  To Return a car
    public boolean returnCar(String licensePlate, String returnDate) {
        for (RentalTransaction transaction : transactions) {
            if (transaction.getCar().getLicensePlate().equals(licensePlate) && transaction.getReturnDate() == null) {
                transaction.returnCar(returnDate);
                System.out.println("Car returned successfully.");
                return true;
            }
        }
        System.out.println("Rental transaction not found for this car.");
        return false;
    }

    // Find car by  the license plate
    private Car findCarByLicense(String licensePlate) {
        for (Car car : cars) {
            if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return car;
            }
        }
        return null;
    }

    // Find the  customer by driver license number
    private Customer findCustomerByLicense(String driverLicenseNumber) {
        for (Customer customer : customers) {
            if (customer.getDriverLicenseNumber().equalsIgnoreCase(driverLicenseNumber)) {
                return customer;
            }
        }
        return null;
    }

    // Display all cars
    public void displayCars() {
        System.out.println("Cars in fleet:");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    // Display all customers
    public void displayCustomers() {
        System.out.println("Customers:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    // Display all transactions
    public void displayTransactions() {
        System.out.println("Rental Transactions:");
        for (RentalTransaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

// Main class to demonstrate functionality
public class CarRentalSystem {

    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        // Add some cars
        agency.addCar(new Car("KBV018X", "DEMIO", "Mazda"));
        agency.addCar(new Car("KBK016X", "BENZ", "Mercedes"));

        // Add some customers
        agency.addCustomer(new Customer("Lauryn munene", "D1234567"));
        agency.addCustomer(new Customer("Mercy Njeri", "D7654321"));

        // Display all cars and customers
        agency.displayCars();
        agency.displayCustomers();

        // Rent a car
        agency.rentCar("KBV018X", "D1234567", "2024-06-01");

        // Display transactions
        agency.displayTransactions();

        // Return a car
        agency.returnCar("KBV018X", "2024-06-05");

        // Display transactions after return
        agency.displayTransactions();

        // Display cars to see availability updated
        agency.displayCars();
    }
}
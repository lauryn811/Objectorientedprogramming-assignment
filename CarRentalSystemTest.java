public class CarRentalSystemTest {

    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        Car car1 = new Car("KAZ111K", "LAND CRUISER", "Toyota");
        Car car2 = new Car("KBF012C", "RAV4", "Toyota");

        Customer cust1 = new Customer("Mark james", "DL123");
        Customer cust2 = new Customer("Mercy Njeri", "DL456");

        agency.addCar(car1);
        agency.addCar(car2);

        agency.addCustomer(cust1);
        agency.addCustomer(cust2);

        // Test 1: Rent available car should succeed
        assert agency.rentCar("KAZ111K", "DL123", "2024-06-10") == true : "Test 1 Failed";

        // Test 2: Rent same car again should fail (not available)
        assert agency.rentCar("KAZ111K", "DL456", "2024-06-11") == false : "Test 2 Failed";

        // Test 3: Return rented car should succeed
        assert agency.returnCar("KAZ111K", "2024-06-15") == true : "Test 3 Failed";

        // Test 4: Return same car again should fail (already returned)
        assert agency.returnCar("KAZ111K", "2024-06-16") == false : "Test 4 Failed";

        // Test 5: Rent car that doesn’t exist should fail
        assert agency.rentCar("KBK999K", "DL123", "2024-06-10") == false : "Test 5 Failed";

        // Test 6: Rent car with invalid customer should fail
        assert agency.rentCar("KBF012C", "DL999", "2024-06-10") == false : "Test 6 Failed";

        System.out.println("All tests passed!");
    }
}
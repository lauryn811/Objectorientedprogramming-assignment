import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {
        // Predefined username and password for login
        String correctUsername = "Lauryn123";
        String correctPassword = "get777";

        Scanner scanner = new Scanner(System.in);

        // Allow user 3 attempts to enter correct username and password
        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = readPassword(scanner);  // Mask password input

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                System.out.println("Login successful!");
                scanner.close();
                return;  // Exit if login successful
            } else {
                System.out.println("Invalid username or password. Attempt " + attempt + " of 3.");
            }
        }

        System.out.println("Maximum login attempts exceeded. Access denied.");
        scanner.close();
    }

    // Method to read password and mask entered characters as '*'
    private static String readPassword(Scanner scanner) {
        StringBuilder password = new StringBuilder();
        // Read input line from user
        // Since masking input with '*' in console is complicated, we'll simulate it here
        // by reading the password normally and printing '*' characters as a visual substitute
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            System.out.print("*");  // Print '*' for each character typed
            password.append(input.charAt(i));
        }
        System.out.println();  // Move to next line after password input
        return password.toString();
    }
}
    


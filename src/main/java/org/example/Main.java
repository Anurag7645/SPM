package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseHelper.initializeDatabase();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSecure Password Manager");
            System.out.println("1. Save a new password");
            System.out.println("2. Retrieve a password");
            System.out.println("3. Generate a random password");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter site name: ");
                    String site = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();

                    System.out.println("Do you want to generate a random password? (yes/no)");
                    String useGenerated = scanner.nextLine().toLowerCase();

                    String password;
                    if (useGenerated.equals("yes")) {
                        password = PasswordGenerator.generatePassword();
                        System.out.println("Generated password: " + password);
                    } else {
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();
                    }

                    PasswordManager.savePassword(site, username, password);
                    break;

                case 2:
                    System.out.print("Enter site name: ");
                    String siteToRetrieve = scanner.nextLine();
                    PasswordManager.getPassword(siteToRetrieve);
                    break;

                case 3:
                    System.out.print("Enter desired password length (default 12): ");
                    int length = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Generated password: " + PasswordGenerator.generatePassword(length));
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

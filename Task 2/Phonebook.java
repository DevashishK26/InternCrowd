import java.io.*;
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "phonebook.txt";
        File file = new File(fileName);

        try {
            // Check if the file already exists, otherwise create a new file
            if (file.createNewFile()) {
                System.out.println("Phone book file created successfully.");
            } else {
                System.out.println("Phone book file already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the phone book file.");
            e.printStackTrace();
        }

        System.out.println("Welcome to the phone book!");
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add a new contact");
            System.out.println("2. Search for a contact");
            System.out.println("3. Quit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\nEnter contact name:");
                    String name = sc.next();
                    System.out.println("Enter contact phone number:");
                    String number = sc.next();
                    try {
                        // Open the phone book file in append mode
                        FileWriter fw = new FileWriter(file, true);
                        fw.write(name + ":" + number + "\n");
                        fw.close();
                        System.out.println("Contact added successfully.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while adding the contact.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("\nEnter the name to search:");
                    String searchName = sc.next();
                    try {
                        // Open the phone book file in read mode
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        boolean found = false;
                        while ((line = br.readLine()) != null) {
                            String[] parts = line.split(":");
                            if (parts[0].equals(searchName)) {
                                System.out.println("Phone number for " + parts[0] + " is " + parts[1]);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("No contact found with the name " + searchName);
                        }
                        br.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred while searching for the contact.");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("\nThank you for using the phone book!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

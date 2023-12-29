package com.example;

import java.util.Scanner;

public class AddressBookSystem {
    AddressBookMethods addressBookMethods = new AddressBookMethods();

    public void addAddressBook() {

        while (true) {
            System.out.println("Enter [1] to add details");
            System.out.println("Enter [2] to edit details");
            System.out.println("Enter [3] to delete details");
            System.out.println("Enter [4] to print details");
            System.out.println("Enter [5] to print details sorted by name");
            System.out.println("Enter [6] to print details sorted by city");
            System.out.println("Enter [7] to print details sorted by state");
            System.out.println("Enter [8] to print details sorted by zip code");
            System.out.println("Enter [9] to add details to a file");
            System.out.println("Enter [10] read details from the file");
            System.out.println("Enter [0] to exit the system");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    return;

                case 1:
                    addressBookMethods.addDetails();
                    break;

                case 2:
                    addressBookMethods.editDetails();
                    break;

                case 3:
                    addressBookMethods.deleteContact();
                    break;

                case 4:
                    addressBookMethods.printAddressBook();
                    break;

                case 5:
                    addressBookMethods.printAddressBookSortedByName();
                    break;

                case 6:
                    addressBookMethods.printAddressBookSortedByCity();
                    break;

                case 7:
                    addressBookMethods.printAddressBookSortedByState();
                    break;

                case 8:
                    addressBookMethods.printAddressBookSortedByZipCode();
                    break;

                case 9:
                    addressBookMethods.writeToFile("AddressBookFile.txt");
                    break;

                case 10:
                    addressBookMethods.readFromFile("AddressBookFile.txt");
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public void printAddressBooks() {
        addressBookMethods.printAddressBook();
    }
}

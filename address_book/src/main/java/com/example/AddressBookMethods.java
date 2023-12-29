package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class AddressBookMethods {
    Map<String, AddressBook> dataMap = new HashMap<String, AddressBook>();

    public void addDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the first name: ");
        String firstName = scanner.hasNextLine() ? scanner.nextLine() : "";

        if (dataMap.containsKey(firstName)) {
            System.out.println("User already exists with this name");
            return;
        }

        System.out.println("Please enter the last name: ");
        String lastName = scanner.hasNextLine() ? scanner.nextLine() : "";

        System.out.println("Please enter the address: ");
        String address = scanner.hasNextLine() ? scanner.nextLine() : "";

        System.out.println("Please enter the city: ");
        String city = scanner.hasNextLine() ? scanner.nextLine() : "";

        System.out.println("Please enter the state: ");
        String state = scanner.hasNextLine() ? scanner.nextLine() : "";

        System.out.println("Please enter the zip code: ");
        String zipCode = scanner.hasNextLine() ? scanner.nextLine() : "";

        System.out.println("Please enter the phone number: ");
        String phoneNumber = scanner.hasNextLine() ? scanner.nextLine() : "";

        System.out.println("Please enter the email: ");
        String email = scanner.hasNextLine() ? scanner.nextLine() : "";

        AddressBook addressBook = new AddressBook(firstName, lastName, address, city, state, zipCode, phoneNumber,
                email);
        dataMap.put(firstName, addressBook);

        if (!(Main.cityMap.containsKey(city))) {
            Main.cityMap.put(city, new ArrayList<AddressBook>());
        }
        Main.cityMap.get(city).add(addressBook);

        if (!Main.stateMap.containsKey(state)) {
            Main.stateMap.put(state, new ArrayList<AddressBook>());
        }
        Main.stateMap.get(state).add(addressBook);
    }

    void editDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the first name");
        String firstName = scanner.hasNextLine() ? scanner.nextLine() : "";
        if (dataMap.containsKey(firstName)) {
            System.out.println("--------Editing details-------");
            dataMap.remove(firstName);
            addDetails();
        } else
            System.out.println("No user exists with this name");
    }

    void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the contact's first name: ");
        String firstName = scanner.hasNextLine() ? scanner.nextLine() : "";

        if (dataMap.containsKey(firstName)) {
            dataMap.remove(firstName);
        } else
            System.out.println("No user exists with this name");
    }

    void printAddressBook() {
        for (Map.Entry<String, AddressBook> entry : dataMap.entrySet()) {
            AddressBook contact = entry.getValue();
            System.out.println("First name : " + entry.getKey());
            System.out.println("Last Name  : " + contact.getLastName());
            System.out.println("Address    : " + contact.getAddress());
            System.out.println("City       : " + contact.getCity());
            System.out.println("State      : " + contact.getState());
            System.out.println("Zip Code   : " + contact.getZip());
            System.out.println("Phone Num  : " + contact.getPhoneNumber());
            System.out.println("Email      : " + contact.getEmail());
            System.out.println("");
        }
    }

    void printAddressBookSortedByName() {
        List<Map.Entry<String, AddressBook>> entryList = new ArrayList<>(dataMap.entrySet());

        Collections.sort(entryList, Map.Entry.comparingByKey());

        for (Map.Entry<String, AddressBook> entry : entryList) {
            AddressBook contact = entry.getValue();
            System.out.println("First name : " + entry.getKey());
            System.out.println("Last Name  : " + contact.getLastName());
            System.out.println("Address    : " + contact.getAddress());
            System.out.println("City       : " + contact.getCity());
            System.out.println("State      : " + contact.getState());
            System.out.println("Zip Code   : " + contact.getZip());
            System.out.println("Phone Num  : " + contact.getPhoneNumber());
            System.out.println("Email      : " + contact.getEmail());
            System.out.println("");
        }
    }

    void printAddressBookSortedByCity() {
        List<Map.Entry<String, AddressBook>> entryList = new ArrayList<>(dataMap.entrySet());

        Collections.sort(entryList, Comparator.comparing(entry -> entry.getValue().city));

        for (Map.Entry<String, AddressBook> entry : entryList) {
            AddressBook contact = entry.getValue();
            System.out.println("First name : " + entry.getKey());
            System.out.println("Last Name  : " + contact.getLastName());
            System.out.println("Address    : " + contact.getAddress());
            System.out.println("City       : " + contact.getCity());
            System.out.println("State      : " + contact.getState());
            System.out.println("Zip Code   : " + contact.getZip());
            System.out.println("Phone Num  : " + contact.getPhoneNumber());
            System.out.println("Email      : " + contact.getEmail());
            System.out.println("");
        }

    }

    void printAddressBookSortedByState() {
        List<Map.Entry<String, AddressBook>> entryList = new ArrayList<>(dataMap.entrySet());

        Collections.sort(entryList, Comparator.comparing(entry -> entry.getValue().state));

        for (Map.Entry<String, AddressBook> entry : entryList) {
            AddressBook contact = entry.getValue();
            System.out.println("First name : " + entry.getKey());
            System.out.println("Last Name  : " + contact.getLastName());
            System.out.println("Address    : " + contact.getAddress());
            System.out.println("City       : " + contact.getCity());
            System.out.println("State      : " + contact.getState());
            System.out.println("Zip Code   : " + contact.getZip());
            System.out.println("Phone Num  : " + contact.getPhoneNumber());
            System.out.println("Email      : " + contact.getEmail());
            System.out.println("");
        }

    }

    void printAddressBookSortedByZipCode() {
        List<Map.Entry<String, AddressBook>> entryList = new ArrayList<>(dataMap.entrySet());

        Collections.sort(entryList, Comparator.comparing(entry -> entry.getValue().zip));

        for (Map.Entry<String, AddressBook> entry : entryList) {
            AddressBook contact = entry.getValue();
            System.out.println("First name : " + entry.getKey());
            System.out.println("Last Name  : " + contact.getLastName());
            System.out.println("Address    : " + contact.getAddress());
            System.out.println("City       : " + contact.getCity());
            System.out.println("State      : " + contact.getState());
            System.out.println("Zip Code   : " + contact.getZip());
            System.out.println("Phone Num  : " + contact.getPhoneNumber());
            System.out.println("Email      : " + contact.getEmail());
            System.out.println("");
        }

    }

    public void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, AddressBook> entry : dataMap.entrySet()) {
                AddressBook addressBook = entry.getValue();
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%n",
                        entry.getKey(),
                        addressBook.getFirstName(),
                        addressBook.getLastName(),
                        addressBook.getAddress(),
                        addressBook.getCity(),
                        addressBook.getState(),
                        addressBook.getZip(),
                        addressBook.getPhoneNumber(),
                        addressBook.getEmail());
                writer.write(line);
            }
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) { // Assuming 8 fields in each line
                    String key = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String address = parts[3];
                    String city = parts[4];
                    String state = parts[5];
                    String zip = parts[6];
                    String phoneNumber = parts[7];
                    String email = parts[8];

                    AddressBook addressBook = new AddressBook(firstName, lastName, address, city, state, zip,
                            phoneNumber, email);
                    dataMap.put(key, addressBook);
                }
            }

            for (AddressBook addressBook : dataMap.values()) {
                addressBook.printDetails();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

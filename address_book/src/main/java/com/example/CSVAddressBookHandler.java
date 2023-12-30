package com.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVAddressBookHandler {
    private static final String CSV_FILE_PATH = "address_book.csv";

    public static void writeContactsTooCsv(List<AddressBook> contacts) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_PATH))) {
            for (AddressBook contact : contacts) {
                String[] record = {
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getAddress(),
                        contact.getCity(),
                        contact.getState(),
                        contact.getZip(),
                        contact.getPhoneNumber(),
                        contact.getEmail(),
                };
                writer.writeNext(record);
            }
            System.out.println("Contacts written to csv file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<AddressBook> readContactsFromCsv() {
        List<AddressBook> contacts = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                AddressBook addressbook = new AddressBook();
                addressbook.setFirstName(record[0]);
                addressbook.setLastName(record[1]);
                addressbook.setAddress(record[2]);
                addressbook.setCity(record[3]);
                addressbook.setState(record[4]);
                addressbook.setZip(record[5]);
                addressbook.setPhoneNumber(record[6]);
                addressbook.setEmail(record[7]);
                contacts.add(addressbook);
            }
            System.out.println("Contacts read from CSV file successfully.");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private static List<AddressBook> createBooksList() {
        List<AddressBook> contactsList = new ArrayList<>();
        contactsList.add(new AddressBook("John", "Adam", "123 Main St", "City1", "State1",
                "12345", "5553451234", "john@gmail.com"));
        contactsList.add(new AddressBook("Jane", "Smith", "456 Oak St", "City2", "State2",
                "54321", "55523455678", "jane@gmail.com"));

        return contactsList;
    }

    public static void main(String[] args) {
        List<AddressBook> addressBooks = createBooksList();
        // CSVAddressBookHandler.writeContactsTooCsv(addressBooks);
        List<AddressBook> loadedAddressBooks = CSVAddressBookHandler.readContactsFromCsv();

        for (AddressBook book : loadedAddressBooks) {
            System.out.println(book.toString());
        }
    }

}

package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class JsonAddressBookHandler {

    private static final String JSON_FILE_PATH = "address_book.json";

    public static void writeContactsToJsonFile(List<AddressBook> contacts) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(JSON_FILE_PATH), contacts);
            System.out.println("Contacts written to JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<AddressBook> readContactsFromJsonFile() {
        List<AddressBook> contacts = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            contacts = objectMapper.readValue(new File(JSON_FILE_PATH), new TypeReference<List<AddressBook>>() {
            });
            System.out.println("Contacts read from JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private static List<AddressBook> createBooksList() {
        List<AddressBook> contactsList = new ArrayList<>();
        contactsList.add(new AddressBook("Anshika", "Semwal", "Subhash Nagar", "Dehradun", "Uttarakhand",
                "12345", "5553451234", "anshi@gmail.com"));
        contactsList.add(new AddressBook("Khushi", "Singh", "Metropolis", "Rudrapur", "UP",
                "54321", "55523455678", "khushi@gmail.com"));

        return contactsList;
    }

    public static void main(String[] args) {
        List<AddressBook> contactsList = createBooksList();

        JsonAddressBookHandler.writeContactsToJsonFile(contactsList);

        List<AddressBook> contactsFromJson = JsonAddressBookHandler.readContactsFromJsonFile();

        for (AddressBook contact : contactsFromJson) {
            System.out.println(contact);
        }
    }

}

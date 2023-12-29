package com.example;

import java.io.*;

public class AddressBook implements Serializable {
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String zip;
    String phoneNumber;
    String email;

    AddressBook(String firstName, String lastName, String address, String city, String state, String zip,
            String phoneNumber,
            String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void printDetails() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name:  " + lastName);
        System.out.println("Address:    " + address);
        System.out.println("City:       " + city);
        System.out.println("State:      " + state);
        System.out.println("Zip Code:   " + zip);
        System.out.println("Phone Num:  " + phoneNumber);
        System.out.println("E-mail:     " + email);
        System.out.println("");

    }
}

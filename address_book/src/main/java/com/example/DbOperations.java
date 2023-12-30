package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class DbOperations {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/addressbookservice";
        String user = "root";
        String password = "Ganesh1*%9";
        return DriverManager.getConnection(url, user, password);
    }

    public void addDateAddedColumn() {
        try {
            String query = "ALTER TABLE addressbooktable ADD COLUMN date_added DATE";
            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.execute();
                System.out.println("Added 'date_added' column to the addressbooktable.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void retrieveAllEntries(Connection connection) {
        try {
            String query = "SELECT * FROM addressbooktable";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int contactId = resultSet.getInt("contact_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String address = resultSet.getString("address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String zipcode = resultSet.getString("zip_code");
                    String phoneNumber = resultSet.getString("phone_number");
                    String email = resultSet.getString("email");
                    String addressBookName = resultSet.getString("address_book_name");
                    String addressBookType = resultSet.getString("address_book_type");

                    // Print or process the retrieved data as needed
                    System.out.println("Contact ID: " + contactId);
                    System.out.println("First Name: " + firstName);
                    System.out.println("Last Name: " + lastName);
                    System.out.println("Address: " + address);
                    System.out.println("City: " + city);
                    System.out.println("State: " + state);
                    System.out.println("Zip Code: " + zipcode);
                    System.out.println("Phone Number: " + phoneNumber);
                    System.out.format("Email: %s\n", email);
                    System.out.printf("Address Book Name: %s\t Address Book Type: %s\n\n", addressBookName,
                            addressBookType);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(int contact_id, String newPhoneNum, String newEmail) {
        try {
            String query = "UPDATE addressbooktable SET phone_number=?, email=? WHERE contact_id=?";
            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newPhoneNum);
                preparedStatement.setString(2, newEmail);
                preparedStatement.setInt(3, contact_id);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The phone number and/or email for this contact has been updated.");
                } else {
                    System.err.println("No records were found with the provided contact id.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveContactsByDateRange(Date startDate, Date endDate) {
        try {
            String query = "SELECT * FROM addressbooktable WHERE date_added BETWEEN ? AND ?";
            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setDate(1, startDate);
                preparedStatement.setDate(2, endDate);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int contactId = resultSet.getInt("contact_id");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        String address = resultSet.getString("address");
                        String city = resultSet.getString("city");
                        String state = resultSet.getString("state");
                        String zipCode = resultSet.getString("zip_code");
                        String phoneNumber = resultSet.getString("phone_number");
                        String email = resultSet.getString("email");
                        String addressBookName = resultSet.getString("address_book_name");
                        String addressBookType = resultSet.getString("address_book_type");
                        Date dateAdded = resultSet.getDate("date_added");

                        System.out.println("Contact ID: " + contactId);
                        System.out.println("First Name: " + firstName);
                        System.out.println("Last Name: " + lastName);
                        System.out.println("Address: " + address);
                        System.out.println("City: " + city);
                        System.out.println("State: " + state);
                        System.out.println("Zip Code: " + zipCode);
                        System.out.println("Phone Number: " + phoneNumber);
                        System.out.println("Email: " + email);
                        System.out.println("Address Book Name: " + addressBookName);
                        System.out.println("Address Book Type: " + addressBookType);
                        System.out.println("Date Added: " + dateAdded);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addContact(String firstName, String lastName, String address, String city, String state,
            String zipCode, String phoneNumber, String email, String addressBookName,
            String addressBookType, Date dateAdded) {
        try {
            String query = "INSERT INTO addressbooktable " +
                    "(first_name, last_name, address, city, state, zip_code, phone_number, email, " +
                    "address_book_name, address_book_type, date_added) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, city);
                preparedStatement.setString(5, state);
                preparedStatement.setString(6, zipCode);
                preparedStatement.setString(7, phoneNumber);
                preparedStatement.setString(8, email);
                preparedStatement.setString(9, addressBookName);
                preparedStatement.setString(10, addressBookType);
                preparedStatement.setDate(11, dateAdded);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Contact added successfully.");
                } else {
                    System.out.println("Failed to add contact.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getContactCountByCity(String city) {
        return getContactCount("city", city);
    }

    public int getContactCountByState(String state) {
        return getContactCount("state", state);
    }

    private int getContactCount(String place, String value) {
        try {
            String query = "SELECT COUNT(*) FROM addressbooktable WHERE " + place + "=?";
            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                preparedStatement.setString(1, value);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DbOperations dbOperations = new DbOperations();
        try {
            Connection connection = dbOperations.getConnection();
            // retrieveAllEntries(connection);
            // dbOperations.updateContact(4, "8449798400", "new_email@gmail.com");
            // dbOperations.addDateAddedColumn();
            // Date currentDate = new Date(System.currentTimeMillis());
            // dbOperations.addContact("Suhana", "Verma", "Vasant Vihar", "Delhi", "Delhi",
            // "33455621", "6798453487",
            // "suhana@gmail.com", "book1", "students", currentDate);
            // dbOperations.addContact("Rohan", "Sharma", "Omaxe", "Mumbai", "Maharashtra",
            // "66455621", "6798235487",
            // "rohan@gmail.com", "book1", "students", currentDate);

            // Date startDate = Date.valueOf("2023-01-01");
            // Date endDate = Date.valueOf("2023-12-31");
            // dbOperations.retrieveContactsByDateRange(startDate, endDate);

            System.out.println(dbOperations.getContactCount("city", "delhi"));
            retrieveAllEntries(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

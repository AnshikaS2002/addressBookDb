package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbOperations {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/addressbookservice";
        String user = "root";
        String password = "Ganesh1*%9";
        return DriverManager.getConnection(url, user, password);
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

    public static void main(String[] args) {
        DbOperations dbOperations = new DbOperations();
        try {
            Connection connection = dbOperations.getConnection();
            // retrieveAllEntries(connection);
            dbOperations.updateContact(4, "8449798400", "new_email@gmail.com");
            retrieveAllEntries(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

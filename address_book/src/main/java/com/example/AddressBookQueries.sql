-- UC 1 : Create a Database 
CREATE DATABASE IF NOT EXISTS AddressBookService;

USE AddressBookService;

-- UC 2 : Create a table
CREATE TABLE IF NOT EXISTS AddressBookTable (
    contact_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) ,
    last_name VARCHAR(30),
    address VARCHAR(255),
    city VARCHAR(50),
    state VARCHAR(50),
    zip_code VARCHAR(10),
    phone_number VARCHAR(20),
    email VARCHAR(50)
);

-- UC 3: Insert new contacts
INSERT INTO AddressBookTable (first_name, last_name, address, city, state, zip_code, phone_number, email)
VALUES
    ('Rohan', 'Sharma', 'Vasant Vihar', 'Dehradun', 'Uttarakhand', '234523', '9897984567', 'rohan@gmail.com'),
    ('Ritika', 'Tomar', 'Mohit Vihar', 'Rudrapur', 'Uttarakhand', '234523', '92349846875', 'ritika@gmail.com'),
	('Riya', 'Sharma', 'Mohit Vihar', 'Delhi', 'Uttarakhand', '289523', '9892344567', 'riya@gmail.com'),
    ('Anshika', 'Tomar', 'Metropolis', 'Allahbad', 'UP', '324523', '98977646875', 'anshika@gmail.com'),
	('Sonal', 'Sharma', 'Omaxe', 'Hyderabad', 'Telangana', '564523', '8957984567', 'sonal@gmail.com'),
    ('Akash', 'Rana', 'Vasant Vihar', 'Bangalore', 'Karnataka', '874523', '23479846875', 'akash@gmail.com');
    
-- UC 4: Edit existing contact by name
SET SQL_SAFE_UPDATES = 0;

UPDATE AddressBookTable
SET phone_number = '7898347832', email = 'rohan.updated@gmail.com'
WHERE first_name = 'Rohan' AND last_name = 'Sharma';

-- UC 5: Delete a person by name
DELETE FROM AddressBookTable
WHERE first_name = 'Rohan';

-- UC 6: Retrieve persons by city or state
SELECT * FROM AddressBookTable WHERE city = 'Dehradun' OR state = 'UP';

-- UC 7: Retrieve size of address book by city and state
SELECT state, COUNT(*) AS size FROM AddressBookTable GROUP BY state;
SELECT city, COUNT(*) AS size FROM AddressBookTable GROUP BY city;

-- UC 8: Retrieve entries sorted alphabetically by name for a given city
SELECT first_name, last_name FROM AddressBookTable 
WHERE city = 'Dehradun'
ORDER BY first_name ASC;

-- UC 9: Add name and type to Address Book
ALTER TABLE AddressBookTable
ADD COLUMN address_book_name VARCHAR(50),
ADD COLUMN address_book_type VARCHAR(40);

-- UC 10: Get the number of contact persons by type
SELECT address_book_type, COUNT(address_book_type)
FROM AddressBookTable
GROUP BY address_book_type;

-- UC 11: Add person to both Friend and Family
INSERT INTO AddressBookTable (first_name, last_name, address, city, state, zip_code, phone_number, email, address_book_type)
VALUES
    ('Anu', 'Srivastatv', '789 St. Jane ', 'Meerut', 'UP', '67890', '111-222-3333', 'anu@example.com', 'Friends'),
    ('Anu', 'Srivastatv', '789 St. Jane ', 'Meerut', 'UP', '67890', '111-222-3333', 'anu@example.com', 'Family');

								

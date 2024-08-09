![image](https://github.com/user-attachments/assets/6a4efaa9-ed74-4902-b58f-a963e9ad419d)
Here's a simple README file for your Java CRUD application:

---

# Java CRUD Application

This is a simple Java Swing-based application that performs CRUD (Create, Read, Update, Delete) operations on a MySQL database.

## Features

- **Add a new book**: Register a new book by entering the book name, edition, and price.
- **Edit existing records**: Update the details of an existing book using its ID.
- **Delete records**: Remove a book from the database using its ID.
- **Search records**: Search for a book by its ID and display its details.
- **View all records**: Display all books stored in the database.

## Prerequisites

- **Java Development Kit (JDK)**
  - Make sure JDK is installed on your system.
- **MySQL Database**
  - Install MySQL on your system.
  - Create a database named `cruddb`.
  - Create a table named `book` with the following schema:

    ```sql
    CREATE DATABASE cruddb;

    USE cruddb;

    CREATE TABLE book (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(255),
        edition VARCHAR(255),
        price DECIMAL(10, 2)
    );
    ```

- **MySQL Connector for Java**
  - Add the MySQL JDBC Driver (`mysql-connector-java`) to your project classpath.

## How to Run

1. **Clone the Repository**  
   Clone this repository to your local machine.

2. **Configure the Database Connection**  
   Open the `javacrud` class and ensure the `Connect()` method contains the correct credentials for your MySQL database:
   
   ```java
   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cruddb", "root", "yourpassword");
   ```

   Replace `"yourpassword"` with your MySQL root password.

3. **Compile and Run the Application**
   - Use your favorite IDE or compile and run the application using the command line:
   
   ```bash
   javac javacrud.java
   java javacrud
   ```

4. **Use the Application**
   - The application will open a GUI where you can perform CRUD operations on the `book` table.




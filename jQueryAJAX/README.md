# Employee Management System

This project is a simple Employee Management System that allows users to:
1. Add employee data using a web form.
2. Search employee details by their ID.
3. View a success message upon successfully adding employee data.

The system uses a Java servlet (`JsDataServletJQuery`) for handling backend operations and a database for storing employee details. The frontend is built with HTML, Bootstrap, and jQuery for smooth interactions.

---

## Features

### 1. Add Employee
- A form to collect employee details such as name, ID, email, phone, department, date of birth, emergency contact, date of joining, and bank.
- Submits data to the backend servlet via an AJAX `POST` request.

### 2. Search Employee
- A search functionality that fetches employee details by their ID.
- Displays results in a table format using AJAX `GET` request.

### 3. Success Page
- Displays a success message after successfully adding an employee.

---

## Prerequisites
1. **Java Development Kit (JDK) 8+**
2. **Apache Tomcat Server** (or any other servlet container)
3. **MySQL Database** (or compatible DB)
4. **Database Connection Setup**
   - A `DatabaseConnection` utility class is used for initializing database connections. Configure it as per your database setup.

---

## Setup Instructions

### Backend Setup
1. **Database Setup**
   - Create a database and table:
     ```sql
     CREATE DATABASE EmployeeDB;
     USE EmployeeDB;

     CREATE TABLE EmpTable (
         employeeName VARCHAR(100),
         employeeId VARCHAR(50) PRIMARY KEY,
         email VARCHAR(100),
         phone VARCHAR(15),
         department VARCHAR(50),
         dob DATE,
         emergencyContact VARCHAR(15),
         doj DATE,
         bank VARCHAR(50)
     );
     ```

2. **DatabaseConnection Class**
   - Configure your database connection in the `DatabaseConnection` utility class. For example:
     ```java
     public class DatabaseConnection {
         public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
             String dbURL = "jdbc:mysql://localhost:3306/EmployeeDB";
             String dbUser = "root";
             String dbPassword = "yourpassword";
             
             Class.forName("com.mysql.cj.jdbc.Driver");
             return DriverManager.getConnection(dbURL, dbUser, dbPassword);
         }
     }
     ```

3. **Servlet Deployment**
   - Place the `JsDataServletJQuery` servlet in your Java project. Update the package name if needed.

4. **Deploy to Server**
   - Compile the project and deploy the `.war` file to your Apache Tomcat server.

---

### Frontend Setup
1. **File Structure**
   - Place the following HTML files in your server's web root folder:
     - `EmployeeForm.html` - For adding employees.
     - `SearchAJAXJquery.html` - For searching employees.
     - `success.html` - For the success message.

2. **Frontend Libraries**
   - jQuery: Included via CDN.
   - Bootstrap: Included via CDN for responsive styling.

---

## Usage
1. **Start the Server**
   - Start your Apache Tomcat server.

2. **Add Employee**
   - Open `EmployeeForm.html` in a browser.
   - Fill out the form and click "Submit."
   - A success message will display upon successful data insertion.

3. **Search Employee**
   - Open `SearchAJAXJquery.html` in a browser.
   - Enter the employee ID to fetch details.
   - Results will display in a table if the employee exists.

4. **View Success Page**
   - After adding an employee, the app will redirect to `success.html`.

---

## Code Highlights

### 1. Servlet (`JsDataServletJQuery`)
- **POST Method**: Handles inserting employee data into the database.
- **GET Method**: Fetches employee data by ID from the database.

### 2. Frontend AJAX
- **Add Employee**:
  ```javascript
  $.ajax({
      url: "http://localhost:8081/jQueryAJAX/JsDataServletJQuery",
      type: "POST",
      data: formData,
      success: function (response) {
          if (response.status === "success") {
              window.location.href = `success.html?message=${encodeURIComponent(response.message)}`;
          }
      }
  });


### Dependencies
## Backend
- Java Servlet API
- MySQL Connector/J
## Frontend
- jQuery 3.6.4
- Bootstrap 5.3.0


### Notes
- Ensure your database credentials are securely stored.
- Update the servlet URLs in the frontend as per your server's configuration.
- Validate all user inputs to prevent SQL injection and XSS attacks.

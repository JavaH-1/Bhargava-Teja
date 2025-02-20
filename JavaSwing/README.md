# Employee Management System

This is a simple Java Swing-based application that allows users to input employee details, validate the inputs in real-time, and save or read data from a file. It serves as an example of using `JFrame`, `JPanel`, and `DocumentListener` for dynamic UI and validation.

## Features
- **Employee Input**: Enter employee details such as Name, Department, and ID.
- **Real-Time Validation**:
  - Name field: Only accepts alphabetic characters.
  - ID field: Only accepts numeric values.
- **File Operations**:
  - Save employee details to a text file.
  - Read employee details from a text file.
- **Dynamic Feedback**: Real-time validation messages are displayed below the input fields.
  
## Technologies Used
- **Java Swing**: For creating the user interface.
- **File I/O**: For reading and writing employee data to a file.
- **HashMap**: For storing employee data in memory.
- **Event Listeners**: For handling input validation and button actions.

## Prerequisites
- Java Development Kit (JDK) 8 or higher installed on your machine.

## Setup and Usage
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/EmployeeManagementSystem.git
## User Instructions
1. **Launch the application.**
2. **Fill in the Name, Department, and ID fields.**
- Name: Must contain only letters.
- ID: Must contain only numbers.
3. **Click the Submit button to save the employee details to a file.**
- If successful, a message will confirm the file creation.
4. **Click the Read File button to read the data stored in the file.**
- Output is currently printed to the console.

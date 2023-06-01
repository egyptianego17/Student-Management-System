# Student Management System

This project is a Java application that provides functionality for managing assignments, submissions, meetings, and feedback in an educational setting. It utilizes the JavaFX library for the user interface and incorporates Spring Security for authentication and authorization.

## How to Use

To use this project, you need to have Java and JavaFX installed on your system. Follow these steps to run the application:

1. Clone the project repository from GitHub.
2. Open the project in your preferred Java development environment.
3. Ensure that the required dependencies, such as JavaFX, Spring Security Core, and Spring Security Crypto, are available in your project.
4. Build and run the application.

## Functionality

### Controllers

The `controllers` module contains the JavaFX controllers for managing the user interface. It requires the `javafx.controls` and `javafx.fxml` modules, as well as the `spring.security.core` and `spring.security.crypto` modules for security-related functionality. The module exports the `frontend.controllers` package to make it accessible to other modules.

### Configuration

The `Config` class in the `backend` package provides configuration settings for the application. It includes file paths for storing user data, assignments, submissions, feedback, meetings, and tests. It also defines a date format for parsing and formatting dates.

### Utility Functions

The `Utils` class in the `backend` package provides various utility functions used throughout the project. These functions include printing objects, hashing and checking passwords, converting dates to strings and vice versa, and more.

### Validators

The `Validators` class in the `backend` package contains validation functions for ensuring the integrity of user input. These functions include length validation, email validation, password validation, attachment validation, deadline validation, and position validation.

### Constants

The `MeetingType` and `Positions` classes in the `backend.constants` package define enums for meeting types and user positions, respectively. These enums provide predefined values and helper methods for working with these types.

### Database Operations

The `CRUD` class in the `backend.database` package provides functions for reading from and writing to CSV files that act as a simple database for storing user data, assignments, submissions, feedback, and meetings. It includes methods for adding records, reading records from CSV files, and clearing the contents of a CSV file.

### Assignment and Feedback Classes

The `Assignment` and `Feedback` classes in the `backend.functionality` package represent assignment and feedback entities, respectively. They include fields and methods for managing assignment and feedback data, such as title, body, attachment, deadline, score, and notes. These classes also provide methods for appending records to the respective CSV files and fetching records by ID.

Please note that the provided code is not complete and may require additional implementation to function properly. Make sure to review and test the code thoroughly before using it in a production environment.

Authored by:
- Abdulrahman Omar
- Omar Shehata
- Mohamed Ali
- Mohamed Hany

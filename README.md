Bonus Program - JDBC with Oracle Database
Description

The Bonus program is a Java application that interacts with an Oracle database using JDBC (Java Database Connectivity). The program allows users to input either a Social Security Number (SSN) or a department name. Based on the input, the program retrieves and displays relevant information from the COMPANY database, specifically the PROJECT, Works_on, employee, and department tables.
Features

    Retrieve projects worked on by an employee based on SSN.
    Retrieve employees who have worked on projects controlled by a specified department based on the department name.
    Display employee details including first name, last name, and salary.
    Display project details including project number, project name, and hours worked by employees.

Setup

    Database Connection:
        Update the jdbcUrl, username, and password variables in the Bonus.java file with your Oracle database connection details.

    Oracle JDBC Driver:
        Download the Oracle JDBC driver (ojdbc.jar) and place it in the same directory as your Bonus.java file.

    User Info File:
        Create a file named "userinfo.txt" containing your Oracle database username and password, each on a separate line.

Usage

    Compile the program:

    bash

javac -cp ".:ojdbc.jar" Bonus.java

Run the program:

bash

    java -cp ".:ojdbc.jar" Bonus

    Follow the on-screen instructions to input an SSN, department name, or 'quit' to exit.

Sample Execution

less

Welcome to Bonus Program!
Enter a social security number or a department name (or 'quit' to exit): 333445555

Results:
Pnumber    Pname             Hours
1          ProductX              32.5
2          ProductY              10.0
3          ProductZ              10.0
10         Computerization       10.0

Enter a social security number or a department name (or 'quit' to exit): Research

Column Names:
fname lname salary
Results:
John  Doe   50000.0
Alice Smith 55000.0

...

Goodbye!

Troubleshooting

    If you encounter the "No suitable driver found" error, ensure that the Oracle JDBC driver is in the classpath.
    Double-check database connection details and user permissions.

License

This program is licensed under the MIT License.

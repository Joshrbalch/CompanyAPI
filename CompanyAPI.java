import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Joshua Balch
 */

public class Bonus {

    public static void main(String[] args) throws FileNotFoundException {
        // Define Oracle DB properties
        Scanner passwdScanner = new Scanner(new File("userinfo.txt"));
        String username = passwdScanner.nextLine();
        String password = passwdScanner.nextLine();

        String jdbcUrl = "jdbc:oracle:thin:@oracle.cs.ua.edu:1521:xe";

        // Initialize the JDBC connection and statement
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Welcome to Bonus Program!");

            String userInput;

            do {
                System.out.print("Enter a social security number or a department name (or 'quit' to exit): ");
                userInput = scanner.nextLine();

                if ("quit".equalsIgnoreCase(userInput)) {
                    break;
                }

                String query;
                if (userInput.matches("\\d{9}")) {
                    query = "SELECT Pnumber, Pname, Hours FROM PROJECT, Works_on WHERE Pnumber = Pno AND Essn = ?";
                } else {
                    query = "SELECT fname, lname, salary FROM employee WHERE Dno IN (SELECT Dnumber FROM department WHERE Dname = ?)";
                }
            
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, userInput);
            
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            System.out.println("Results:");
                            if (userInput.matches("\\d{9}")) {
                                System.out.printf("%-10s %-15s %10s\n", "PNUMBER", "PNAME", "HOURS");
                            }

                            else {
                                System.out.printf("%-10s %-15s %10s\n", "FNAME", "LNAME", "SALARY");
                            }
            
                            do {
                                if (userInput.matches("\\d{9}")) {
                                    System.out.printf("%-10s %-15s %10s\n", resultSet.getString("Pnumber"), resultSet.getString("Pname"), resultSet.getString("Hours"));

                                } else {
                                    System.out.printf("%-10s %-15s %10s\n", resultSet.getString("fname"), resultSet.getString("lname"), resultSet.getString("salary"));
                                }
                            } while (resultSet.next());
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                }
            
            } while (true);

            System.out.println("Goodbye!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

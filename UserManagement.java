import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserManagement {
    private Connection connection;
    

    public UserManagement(Connection connection) {
        this.connection = connection;
    }

    public void registerUser(Scanner scanner) throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.next();

        System.out.println("Enter password: ");
        String password = scanner.next();

        String insertQuery = "INSERT INTO userAuth (username, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            System.out.println("User registered successfully");
        }
    }

    public void loginUser(Scanner scanner, int maxAttempts) throws SQLException {
        while (UserAuthenticationApp.loginAttempts < maxAttempts) {
            System.out.println("Enter username: ");
            String username = scanner.next();

            System.out.println("Enter password: ");
            String password = scanner.next();

            boolean isAuthenticated = authenticateUser(username, password);
            if (isAuthenticated) {
                System.out.println("Login successful!");
                UserAuthenticationApp.loginAttempts = 0;
                return;
            } else {
                UserAuthenticationApp.loginAttempts++;
                System.out.println("Incorrect password. Attempts left: " + (maxAttempts - UserAuthenticationApp.loginAttempts));
            }
        }
        System.out.println("Too many failed login attempts. Account locked.");
    }

    public void deleteUser(Scanner scanner) throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.next();

        System.out.println("Enter password: ");
        String password = scanner.next();

        String deleteQuery = "DELETE FROM userAuth WHERE username=? AND password=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully");
            } else {
                System.out.println("No user found with the provided credentials");
            }
        }
    }

    private boolean authenticateUser(String username, String password) throws SQLException {
        String selectQuery = "SELECT * FROM userAuth WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}

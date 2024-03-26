import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class UserAuthenticationApp {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javaAuth";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Payal@2002#1998";
    public static final int MAX_LOGIN_ATTEMPTS = 5;
    public static int loginAttempts = 0;


   
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Scanner scanner = new Scanner(System.in);
            UserManagement userManagement = new UserManagement(connection);

            System.out.println("Menu");
            System.out.println("1->Register user");
            System.out.println("2->Login user");
            System.out.println("3->Delete user");
            System.out.println("4->Show user's table");
            System.out.println("press any key to exit");
            int ch;

            System.out.println("Enter your choice");
            ch = scanner.nextInt();

            switch (ch) {
                case 1:
                    userManagement.registerUser(scanner);
                    break;
                case 2:
                    userManagement.loginUser(scanner, MAX_LOGIN_ATTEMPTS);
                    break;
                case 3:
                    userManagement.deleteUser(scanner);
                    break;
                default:
                    break;
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

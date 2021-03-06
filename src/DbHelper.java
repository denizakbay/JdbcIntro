import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private String URL_DB = "jdbc:mysql://localhost:3306/world";
    private String Name = "root";
    private String Password = "984843";

    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL_DB, Name, Password);
    }

    public void showErrorMessage(SQLException exception) {
        System.out.println("Error:" + exception.getMessage());
        System.out.println("Error Code:" + exception.getErrorCode());
    }
}

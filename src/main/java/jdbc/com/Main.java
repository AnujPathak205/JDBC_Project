package jdbc.com;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("name")
                );
            }
        }
    }
}
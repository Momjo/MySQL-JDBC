import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            FileReader databaseProperties = new FileReader("db.properties");
            Properties properties = new Properties();
            properties.load(databaseProperties);
            
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);

            Album album = new Album(con);
            album.getData();
            album.searchQuery();
        } catch (Exception e) {
            System.out.println("Got an Exeption!");
            System.out.println(e);
        }
    }
}

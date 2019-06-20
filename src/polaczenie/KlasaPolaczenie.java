package polaczenie;

import java.sql.*;
import java.util.Properties;

public class KlasaPolaczenie {
    private Connection conn=null;
    public Connection dajPolaczenie(){
        String url = "jdbc:mysql://localhost:3306/biblioteka";
        String user = "root";
        String passwd = "";
        Properties prop = new Properties();
        prop.setProperty("user",user);
        prop.setProperty("password",passwd);

        try {
            conn = DriverManager.getConnection(url,prop);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nie udalo sie polaczenie do bazy!");
            return null;
        }

        System.out.println("Polaczono z baza danych przez JDBC!");
        return conn;
    }
}

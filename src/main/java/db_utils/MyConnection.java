package db_utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    // identifications de la BdD
    private final static String URL = "jdbc:mysql://localhost:3307/cinema";
    private final static String USER = "root";
    private final static String PW = "root";
    private final static String DRIVER= "com.mysql.jdbc.Driver";

    // Singleton : declaration
    private static Connection INSTANCE = null;

    // Singleton : initialisation
    static {
        try {
            Class.forName(DRIVER);
            // ouvrir la connection a la BD
            INSTANCE = DriverManager.getConnection(URL,USER, PW);
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Singleton : accesseur
    public static Connection getINSTANCE() {
        return INSTANCE;
    }
}

package swiftway.DB_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
      private static final String url="jdbc:mysql://db4free.net:3306/swiftway";
      private static final String user="admin123";
      private static final String pass="admin123";
      private static Connection connection;
      public static Connection getConnection() {
        try {

            connection=DriverManager.getConnection(url, user, pass);
            System.out.println("Connection Etablie au  base de donnees avec succes !!");

        }catch (SQLException e) {

            System.out.println("Pas de Connection avec la Base de Donnees !!");
            e.printStackTrace();

        }  
        return connection;
    }
    }


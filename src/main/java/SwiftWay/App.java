package SwiftWay;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement =cnx.createStatement();
       String query="insert into Admin values ('admin66','admin66');";
       sqlStatement.executeUpdate(query);
       System.out.println("Execution du query avec succes !!");
       sqlStatement.close();
        
   } catch (SQLException e) {
       System.out.println("Erreur D'execution du query !!");
       e.printStackTrace();
   } 
    }
}

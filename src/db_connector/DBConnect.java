package db_connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static DBConnect dbInstance;
    private String URL = "jdbc:mysql://localhost:3306/bankingapp";
    private String username = "Your-username";
    private String password = "Your-password";
    private Connection conn;

    private DBConnect(){
        try {
            conn = DriverManager.getConnection(URL, username, password);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnectionInstance(){

        dbInstance = new DBConnect();
        Connection con = dbInstance.conn;
        return con;

    }
    
    public void DBclose(){
        try{
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
    }

}

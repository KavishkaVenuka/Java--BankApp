package authenticate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db_connector.DBConnect;


public class Authenticate {

    public static Boolean authenticateUSer(String username, String password){

        String query = "SELECT * FROM authenticate WHERE username = ? AND password = ?";

        try(Connection conn = DBConnect.getConnectionInstance();
            PreparedStatement stmnt = conn.prepareStatement(query)){
                stmnt.setString(1, username);
                stmnt.setString(2, password);
                ResultSet rs = stmnt.executeQuery();
                return rs.next();

            }
            catch (SQLException e){
                e.printStackTrace();
                return false;
    
            }
        
    }
}

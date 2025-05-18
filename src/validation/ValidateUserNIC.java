package validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_connector.DBConnect;

public class ValidateUserNIC {
    
    public static Boolean checkNICAvailability(long userNIC){
        String query = "SELECT user_id FROM user WHERE user_id = ?";

        try(Connection conn = DBConnect.getConnectionInstance();
            PreparedStatement stmnt = conn.prepareStatement(query)){
                stmnt.setLong(1, userNIC);
                ResultSet set = stmnt.executeQuery();
                return !set.next();
            }
        catch (SQLException e){
            e.printStackTrace();
            return false;

        }

    }


    
}

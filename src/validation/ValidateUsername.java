package validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import db_connector.DBConnect;

public class ValidateUsername {
    public static Boolean validatUsername(String username){
        String query = "SELECT username FROM authenticate WHERE username = ?";
        try (
            Connection conn =  DBConnect.getConnectionInstance();
            PreparedStatement statement = conn.prepareStatement(query);
        ) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        }
        catch( SQLException e){
            e.printStackTrace();
            return true;

        }

    }
    
}

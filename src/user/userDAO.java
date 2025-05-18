package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import db_connector.DBConnect;

public class userDAO{

    public static Boolean saveUsrToDB(long userNIC, String userName, String userAddress){
        String query = "INSERT INTO user VALUES(?, ?, ?)";

        try(Connection conn = DBConnect.getConnectionInstance();
            PreparedStatement stmnt = conn.prepareStatement(query)){
                stmnt.setLong(1, userNIC);
                stmnt.setString(2, userName);
                stmnt.setString(3, userAddress);

                int rowInserted = stmnt.executeUpdate();
                return rowInserted > 0;

            }

            catch (SQLException e){

                e.printStackTrace();
                return false;
            }
    }



    public static String getnameWithInitials(long userNIC){
        String query = "SELECT name_with_initials FROM user WHERE user_id = ?";

        try (Connection conn = DBConnect.getConnectionInstance();
        PreparedStatement stmnt = conn.prepareStatement(query)){
            stmnt.setLong(1, userNIC);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()){
                String nameWithInitials = rs.getString("name_with_initials");
                return nameWithInitials;
            }
            else {
                String nameWithInitials = " ";
                return nameWithInitials;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return getnameWithInitials(userNIC);

        }
    }


    

}
